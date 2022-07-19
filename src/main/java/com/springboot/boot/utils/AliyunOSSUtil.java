package com.springboot.boot.utils;

import cn.hutool.core.io.FileTypeUtil;
import com.aliyun.oss.*;
import com.aliyun.oss.model.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName AliyunOSSUtil
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/3/22 0022 9:34
 * @Version 1.0
 **/
@Service
@Slf4j
public class AliyunOSSUtil {
    @Autowired
    private ConstantProperties constantProperties;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static String validfileType[] = {"jpg", "JPG", "png", "PNG",
            "mp4", "MP4", "avi", "AVI", "doc", "DOC", "docx", "DOCX", "pdf", "PDF",
            "xls", "XLS", "xlsx", "XLSX","wmv","WMV"
    };
    private static String docfileType[] = {"jpg", "JPG", "png", "PNG",
             "doc", "DOC", "docx", "DOCX", "pdf", "PDF",
            "xls", "XLS", "xlsx", "XLSX"
    };
    private static String streamfileType[] = {
            "mp4", "MP4", "avi", "AVI","wmv","WMV"
    };

    private static List<String> list = Arrays.asList(validfileType);
    private static List<String> streamlist = Arrays.asList(streamfileType);
    private static List<String> doclist = Arrays.asList(streamfileType);

    /**
     * 文件格式校验
     *
     * @param uploadFile
     * @return
     */
    public Boolean fileCheck(File uploadFile) {
        log.info("=========>fileCheck开始：" + uploadFile.getName());
        String fileType = FileTypeUtil.getType(uploadFile);
        if (list.contains(fileType)) {
            return true;
        }
        return false;
    }

    /**
     * 文本格式文件校验
     *
     * @param uploadFile
     * @return
     */
    public Boolean docFileCheck(File uploadFile) {
        log.info("=========>fileCheck开始：" + uploadFile.getName());
        String fileType = FileTypeUtil.getType(uploadFile);
        if (doclist.contains(fileType)) {
            return true;
        }
        return false;
    }


    /**
     * 流文件格式校验
     *
     * @param uploadFile
     * @return
     */
    public Boolean StreamFileCheck(File uploadFile) {
        log.info("=========>fileCheck开始：" + uploadFile.getName());
        String fileType = FileTypeUtil.getType(uploadFile);
        if (streamlist.contains(fileType)) {
            return true;
        }
        return false;
    }






    /**
     * 文件格式校验
     *
     * @param
     * @return
     */

    public String uploadByStream(HttpServletResponse response, String FileFullPath, String fileName) {
        response.setContentType("application/pdf");
        String endpoint = constantProperties.getEndpoint();
        String accessKeyId = constantProperties.getKeyid();
        String accessKeySecret = constantProperties.getKeysecret();
        String bucketName = constantProperties.getBucketname();
        ClientBuilderConfiguration config = new ClientBuilderConfiguration();
        config.setSupportCname(false);
        try {
            // OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret,config);
            OSS SSossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, config);

            OSSObject ossObject = SSossClient.getObject(bucketName, FileFullPath);
            BufferedInputStream in = new BufferedInputStream(ossObject.getObjectContent());
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            byte[] car = new byte[1024];
            int L = 0;
            while ((L = in.read(car)) != -1) {
                out.write(car, 0, L);
            }
            if (out != null) {
                out.flush();
                out.close();
            }
            if (in != null) {
                in.close();
            }
            SSossClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }


    /**
     * 文件格式校验
     *
     * @param
     * @return
     */

    public String ossToLocalToShow(HttpServletResponse response, String FileFullPath, String fileName) {

        String endpoint = constantProperties.getEndpoint();
        String accessKeyId = constantProperties.getKeyid();
        String accessKeySecret = constantProperties.getKeysecret();
        String bucketName = constantProperties.getBucketname();
        ClientBuilderConfiguration config = new  ClientBuilderConfiguration();
        config.setSupportCname(false);

        String name = System.currentTimeMillis()+fileName;
        try {




            String pathName = constantProperties.getOosFilePath()+name;
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret,config);
            // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
            ossClient.getObject(new GetObjectRequest(bucketName, FileFullPath), new File(pathName));
            ossClient.shutdown();

        }catch (Exception e){
            e.printStackTrace();
        }
        return constantProperties.getOosFileShowPathPrex()+name ;

    }

    /**
     * 文件格式校验
     *
     * @param uploadFile
     * @return
     */

    public Map<String, String> picOSS(File uploadFile) {

        log.info("=========>OSS文件上传开始：" + uploadFile.getName());

        Map<String, String> rtMap = new HashMap();
        try {
            String endpoint = constantProperties.getEndpoint();
            String accessKeyId = constantProperties.getKeyid();
            String accessKeySecret = constantProperties.getKeysecret();
            String bucketName = constantProperties.getBucketname();
            String fileHost = constantProperties.getFilehost();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = format.format(new Date());
            ClientConfiguration config = new ClientConfiguration();
            config.setSupportCname(false);

            // 创建OSSClient实例
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, config);
            //容器不存在，就创建
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            //创建文件路径
            String fileKey = fileHost + "/" + (dateStr + "/" + "-" + uploadFile.getName());
            // 上传
            long time = new Date().getTime();
            ossClient.putObject(bucketName, fileKey, uploadFile);
            // 关闭client
            ossClient.shutdown();
            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
            String url = ossClient.generatePresignedUrl(bucketName, fileKey, expiration).toString();
            rtMap.put("url", url);
            rtMap.put("fileKey", fileKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtMap;

    }

    /**
     * 上传
     *
     * @param file
     * @return
     */
    public String upload(File file) {
        log.info("=========>OSS文件上传开始：" + file.getName());
        String endpoint = constantProperties.getEndpoint();
        String accessKeyId = constantProperties.getKeyid();
        String accessKeySecret = constantProperties.getKeysecret();
        String bucketName = constantProperties.getBucketname();
        String fileHost = constantProperties.getFilehost();
        System.out.println(endpoint + "endpoint");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        if (null == file) {
            return null;
        }

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            //容器不存在，就创建
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            //创建文件路径
            String fileUrl = fileHost + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            //设置权限 这里是公开读
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (null != result) {
                log.info("==========>OSS文件上传成功,OSS地址：" + fileUrl);
                return fileUrl;
            }
        } catch (OSSException oe) {
            log.error(oe.getMessage());
        } catch (ClientException ce) {
            log.error(ce.getMessage());
        } finally {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }


    /**
     * 删除
     *
     * @param fileKey
     * @return
     */
    public String deleteBlog(String fileKey) {
        log.info("=========>OSS文件删除开始");
        String endpoint = constantProperties.getEndpoint();
        String accessKeyId = constantProperties.getKeyid();
        String accessKeySecret = constantProperties.getKeysecret();
        String bucketName = constantProperties.getBucketname();
        String fileHost = constantProperties.getFilehost();
        try {
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            if (!ossClient.doesBucketExist(bucketName)) {
                log.info("==============>您的Bucket不存在");
                return "您的Bucket不存在";
            } else {
                log.info("==============>开始删除Object");
                ossClient.deleteObject(bucketName, fileKey);
                log.info("==============>Object删除成功：" + fileKey);
                return "==============>Object删除成功：" + fileKey;
            }
        } catch (Exception ex) {
            log.info("删除Object失败", ex);
            return "删除Object失败";
        }
    }

    /**
     * 查询文件名列表
     *
     * @param bucketName
     * @return
     */
    public List<String> getObjectList(String bucketName) {
        List<String> listRe = new ArrayList<>();
        String endpoint = constantProperties.getEndpoint();
        String accessKeyId = constantProperties.getKeyid();
        String accessKeySecret = constantProperties.getKeysecret();
        try {
            log.info("===========>查询文件名列表");
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
            //列出11111目录下今天所有文件
            listObjectsRequest.setPrefix("11111/" + format.format(new Date()) + "/");
            ObjectListing list = ossClient.listObjects(listObjectsRequest);
            for (OSSObjectSummary objectSummary : list.getObjectSummaries()) {
                System.out.println(objectSummary.getKey());
                listRe.add(objectSummary.getKey());
            }
            return listRe;
        } catch (Exception ex) {
            log.info("==========>查询列表失败", ex);
            return new ArrayList<>();
        }
    }

}