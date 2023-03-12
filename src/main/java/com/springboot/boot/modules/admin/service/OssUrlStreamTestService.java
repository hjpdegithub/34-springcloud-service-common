package com.springboot.boot.modules.admin.service;



import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface OssUrlStreamTestService {


    void StreamOut(HttpServletResponse response,String FileFullPath,String fileName) throws IOException;

    String ossToLocalToShow(HttpServletResponse response,String FileFullPath,String fileName,String documentid) throws IOException;


}
