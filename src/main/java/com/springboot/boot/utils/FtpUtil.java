package com.springboot.boot.utils;

import com.alipay.api.internal.util.file.IOUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author:zhoujh
 * @Function:TODO
 * @date 2020/5/21 15:13
 * @ClassName:
 * @version:
 * @remark:
 */
@Slf4j
@Configuration
public class FtpUtil {
    /**访问地址*/
    @Value("${att.upload.path}")
    private static String path;

    /**存放地址*/
    @Value("${att.upload.docBase}")
    private static String docBase;

    //ftp服务器地址
    @Value("${att.hostname}")
    private String hostname;
    //ftp服务器端口号默认为21
    @Value("${att.port}")
    private Integer port;
    //ftp登录账号
    @Value("${att.username}")
    private String username;
    //ftp登录密码
    @Value("${att.password}")
    private String password;

    public FTPClient ftpClient = null;

    public Map<String, Object> upload(HttpServletRequest request){
        final Map<String, Object> result = new HashMap<>();
        //初始化多部份解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查了request是否为null，请求是否为post，form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
            //转换为多部份request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest中所有文件名
            Iterator it = multiRequest.getFileNames();
            while (it.hasNext()){
                MultipartFile file = multiRequest.getFile(it.next().toString());
                if(file != null){
                    //获取文件名
                    String fileName = file.getOriginalFilename();
                    // 文件后缀
                    String suffixes = fileName.substring(fileName.lastIndexOf("."));
                    // 重命名文件
                    String nowName = getNewName(suffixes);
                    final ByteArrayOutputStream fileStream = new ByteArrayOutputStream();
                    // 存放位置
                    String savePath = getFilePath(docBase, nowName);
                    try {
                        // 复制文件内容
                        IOUtils.copy(file.getInputStream(), fileStream);
                        //向session中存入文件内容的引用，为保存文件做准备
                        request.getSession().setAttribute(nowName,fileStream);
                        // 复制文件
                        FileUtils.copyInputStreamToFile(new ByteArrayInputStream(fileStream.toByteArray()), new File(savePath));
                    } catch (Exception e) {
                        return null;
                    }
                    result.put("nowName",nowName);
                    result.put("savePath",savePath);
                    result.put("fileSize",file.getSize());
                    result.put("url",getFilePath(path,nowName));
                } else {
                    return result;
                }
            }
        }
        return result;
    }

    public static Map<String, String> upload(String imgBase64){
        log.info("==============开始上传图片=============");
        Map<String, String> result = new HashMap<>();
        if(imgBase64 != null){
            // 文件后缀
            String suffixes = ".jpg";
            // 重命名
            String nowName = getNewName(suffixes);
            // 存放位置
            String savePath = getFilePath(docBase, nowName);
            try {
//                IOUtils.copy(file.getInputStream(), fileStream);
                mkdir(savePath.substring(0,savePath.lastIndexOf("/")));
                FtpUtil.base64MultipartFile(imgBase64,savePath);
//                file.transferTo(new File(savePath));
                log.info("==============上传成功==============");
            } catch (Exception e) {
                log.error("==============出现异常，上传失败==============");
                return null;
            }
            result.put("nowName",nowName);
            result.put("path",getFilePath(nowName));
            result.put("savePath",savePath);
//            result.put("url",getFilePath(path,nowName));
        }
        return result;
    }

    /**
     * 初始化ftp服务器
     */
    public void initFtpClient() {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            log.info("...ftp服务器:connecting"+hostname+":"+port+"...."+DateUtils.format(new Date()));
            ftpClient.connect(hostname, port); //连接ftp服务器
            if(!ftpClient.isConnected()){
                ftpClient.connect(hostname, port);
            }
            boolean flag=ftpClient.login(username, password); //登录ftp服务器
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            log.info("connect ing.ftp服务器..flag:"+flag+"...replyCode:"+replyCode);
            if(!FTPReply.isPositiveCompletion(replyCode)){
                log.info("connect failed...ftp服务器:"+hostname+":"+port+"...."+DateUtils.format(new Date()));
            }
            log.info("connect successfu...ftp服务器:"+hostname+":"+port+"...."+DateUtils.format(new Date()));
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     * @param pathname ftp服务保存地址
     * @param fileName 上传到ftp的文件名
     *  @param originfilename 待上传文件的名称（绝对地址） *
     * @return
     */
    public boolean uploadFile( String pathname, String fileName,String originfilename){
        boolean flag = false;
        InputStream inputStream = null;
        try{
            log.info("开始上传文件");
            inputStream = new FileInputStream(new File(originfilename));
            initFtpClient();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            CreateDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            log.info("上传文件成功");
        }catch (Exception e) {
            log.info("上传文件失败");
            e.printStackTrace();
        }finally{
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
    /**
     * 上传文件
     * @param path ftp服务保存地址
     * @param fileName 上传到ftp的文件名重命名的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public Map<Boolean,String> uploadArrFile(String path, LinkedList<String> fileName, LinkedList<InputStream> inputStream){
        boolean flag = false;
        Map<Boolean,String> map = Maps.newHashMap();
        String realPath = "";
        try{
            log.info("======开始上传文件======");
            log.info("======上传文件地址======"+path+"====系统存储文件名=="+fileName);
//            initFtpClient();
            boolean b1=ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            realPath = path + "/" + fileName;
            boolean b2=CreateDirecroty(path);
            boolean b3=ftpClient.makeDirectory(path);
            boolean b4=ftpClient.changeWorkingDirectory(path);
            ftpClient.enterLocalPassiveMode();//被动模式解决虚拟机切换为容器ftp上传不能使用问题
            log.info("======上传b1:"+b1+"=b2:"+b2+"=b3:"+b3+"=b4:"+b4);
            for(int i=0;i<fileName.size();i++){
                boolean flagUP=ftpClient.storeFile(fileName.get(i), inputStream.get(i));
                log.info("======上传flagUP======"+flagUP);
                inputStream.get(i).close();
            }
            ftpClient.logout();
            flag = true;
            log.info("======上传文件成功======");
        }catch (Exception e) {
            log.error("======上传文件失败======");
            e.printStackTrace();
        }finally{
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            for(int i=0;i<inputStream.size();i++){
                if(null != inputStream.get(i)){
                    try {
                        inputStream.get(i).close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        map.put(flag,realPath);
        return map;
    }
    /**
     * 上传文件
     * @param path ftp服务保存地址
     * @param fileName 上传到ftp的文件名重命名的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public Map<Boolean,String> uploadFile(String path, String fileName, InputStream inputStream){
        boolean flag = false;
        Map<Boolean,String> map = Maps.newHashMap();
        String realPath = "";
        try{
            log.info("======开始上传文件======");
            log.info("======上传文件地址======"+path+"====系统存储文件名=="+fileName);
            initFtpClient();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 设置为被动模式
            ftpClient.enterLocalPassiveMode();
            realPath = path + "/" + fileName;
            CreateDirecroty(path);
            ftpClient.makeDirectory(path);
            ftpClient.changeWorkingDirectory(path);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            log.info("======上传文件成功======");
        }catch (Exception e) {
            log.error("======上传文件失败======");
            e.printStackTrace();
        }finally{
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        map.put(flag,realPath);
        return map;
    }
    //改变目录路径
    public boolean changeWorkingDirectory(String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    public boolean CreateDirecroty(String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(directory)) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), StandardCharsets.ISO_8859_1);
                path = path + "/" + subDirectory;
                if (!existFile(path)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        log.info("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(subDirectory);
                    }
                } else {
                    changeWorkingDirectory(subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    //判断ftp服务器文件是否存在
    public boolean existFile(String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }
    //创建目录
    public boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /** * 下载文件 *
     * @param pathname FTP服务器文件目录 *
     * @param filename 文件名称 *
     * @param localpath 下载后的文件路径 *
     * @return */
    public boolean downloadFile(String pathname, String filename, String localpath){
        boolean flag = false;
        OutputStream os=null;
        try {
            log.info("======开始下载文件======");
            initFtpClient();
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile file : ftpFiles){
                if(filename.equalsIgnoreCase(file.getName())){
                    File localFile = new File(localpath + "/" + file.getName());
                    os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();
            flag = true;
            log.info("======下载文件成功======");
        } catch (Exception e) {
            log.error("======下载文件失败======");
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(null != os){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /** * 删除文件 *
     * @param pathname FTP服务器保存目录 *
     * @param filename 要删除的文件名称 *
     * @return */
    public boolean deleteFile(String pathname, String filename){
        boolean flag = false;
        try {
            log.info("======开始删除文件======");
            initFtpClient();
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.dele(filename);
            ftpClient.logout();
            flag = true;
            log.info("======删除文件成功======");
        } catch (Exception e) {
            log.error("======删除文件失败======");
            e.printStackTrace();
        } finally {
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    public static void mkdir(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            if (!dir.isFile()) {
                return;
            }
            dir.delete();
        }
        dir.mkdirs();
    }

    /**
     * 获取文件绝对路径
     * @param request
     * @param host
     * @return
     */
    public static String getFileUrl(HttpServletRequest request, String host) {
        String path = request.getScheme() + "://" + host + request.getContextPath() + "/";
        int port = request.getServerPort();
        if (80 != port) {
            path = request.getScheme() + "://" + host + ":" + request.getServerPort() + request.getContextPath() + "/";
        }
        return path;
    }

    /**
     * 文件路径
     * @param pathname
     * @return
     */
    public static String getFilePath(String pathname) {
        StringBuilder sb = new StringBuilder().append(DateUtils.formatDateYMD(new Date())).append("/").append(pathname);
        return sb.toString();
    }

    /**
     * 文件路径
     * @param filepath
     * @param filename
     * @return
     */
    public static String getFilePath(String filepath, String filename) {
        StringBuilder sb = new StringBuilder(filepath).append(DateUtils.formatDateYMD(new Date())).append("/").append(filename);
        return sb.toString();
    }

    /**
     * 文件重命名
     * @param suffixes
     * @return
     */
    public static String getNewName(String suffixes){
        return UUID.randomUUID().toString().replaceAll("\\-", "") + suffixes;
    }

    /**
     * 删除文件
     * @param path
     */
    public static void delFiles(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }
    public static boolean base64MultipartFile(String imgStr, String imagePath){
        if (imgStr == null){
            return false;
        }
        try {
            String[] baseStr = imgStr.split(",");
            Base64.Decoder decoder= Base64.getMimeDecoder();
            byte[] b =  new byte[0];
            b = decoder.decode(baseStr[1]);
            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imagePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
