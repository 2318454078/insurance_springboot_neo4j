package com.appleyk.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    //实现pdf解析成txt
    String result(String pdfpath) throws Exception;
}
