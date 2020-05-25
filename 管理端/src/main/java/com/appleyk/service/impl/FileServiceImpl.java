package com.appleyk.service.impl;

import com.appleyk.service.FileService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FileServiceImpl implements FileService {
//pdf转txt具体实现
    @Override
    public  String result (String pdfpath) throws Exception {
        File file = new File(pdfpath);
        String strresult = null;
        try {
            PDDocument document = PDDocument.load(file);
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper textStripper = new PDFTextStripper();
                String exposeContent = textStripper.getText(document);
                String[] content = exposeContent.split("\\n");
                StringBuffer stringBuffer = new StringBuffer();
                for (String line : content) {
                    stringBuffer.append(line);
                }
                strresult = stringBuffer.toString();
            }

        } catch (Exception e) {
            return ("读取pdf文件异常...");
        }
        return strresult;
    }
}
