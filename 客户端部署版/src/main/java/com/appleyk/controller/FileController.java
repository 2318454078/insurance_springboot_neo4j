package com.appleyk.controller;

import com.appleyk.result.FileResult;
import com.appleyk.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/rest/appleyk/filehandle")
public class FileController {
    @Autowired
    FileService fileService;

    @RequestMapping(value="/upload",produces = {"application/json;charset=UTF-8"})
    public String handleFileUpload(@RequestParam("file") MultipartFile file , Model model) {
        Map map = new HashMap();
        String fileName = null;
        String strresult = null;
        if (file.isEmpty()) {
            model.addAttribute("msg","上传失败，请选择文件");
            return "policy_analysis";
        }
        if (!file.getOriginalFilename().endsWith(".pdf")&&!file.getOriginalFilename().endsWith(".PDF")) {
            model.addAttribute("msg","上传失败，请选择PDF类型文件上传");
            return "policy_analysis";
        }
//        String fileName = file.getOriginalFilename();
//        String filePath = "E:/desktop/";
//        String pdfpath = filePath+fileName;
//        File dest = new File(filePath + fileName);
        //获取上传文件名
        fileName = file.getOriginalFilename();
        //获取后缀名
        String sname = fileName.substring(fileName.lastIndexOf("."));
        //时间格式化格式
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //获取当前时间并作为时间戳
        String timeStamp=simpleDateFormat.format(new Date());
        //拼接新的文件名
        String newName ="保单"+timeStamp+sname;
        //指定上传文件的路径
        String path = FileResult.FilePath + newName;
        //上传保存

        try {

            file.transferTo(new File(path));
            strresult = fileService.result(path);

            Pattern pattern_name = Pattern.compile("^(.*?)条款");
            Pattern pattern_toubaonianling = Pattern.compile("投保年龄\\S.*?。\\s",Pattern.DOTALL);
//            Pattern pattern_kuanxianqi = Pattern.compile("宽限期\\S.*?。\\s",Pattern.DOTALL);
//            Pattern pattern_youyuqi = Pattern.compile("犹豫期\\S.*?。\\s",Pattern.DOTALL);
            Pattern pattern_zerentiaokuan = Pattern.compile("保险责任\\S.*?。\\s\\s",Pattern.DOTALL);
//            Pattern pattern_mianzetiaokuan = Pattern.compile("责任免除\\S.*。\\s",Pattern.DOTALL);
            Pattern pattern_jiaofeifangshi = Pattern.compile("保险费的交纳\\s.*?。\\s",Pattern.DOTALL);
            Pattern pattern_baoxianqijian = Pattern.compile("保险期间\\s.*?。\\s",Pattern.DOTALL);
            Pattern pattern_shouyiren = Pattern.compile("受益人\\s.*?。\\s",Pattern.DOTALL);

            Matcher m_name = pattern_name.matcher(strresult);
            Matcher m_toubaonianling = pattern_toubaonianling.matcher(strresult);
            Matcher m_zerentiaokuan = pattern_zerentiaokuan.matcher(strresult);
            Matcher m_jiaofeifangshi = pattern_jiaofeifangshi.matcher(strresult);
            Matcher m_baoxianqijian = pattern_baoxianqijian.matcher(strresult);
            Matcher m_shouyiren = pattern_shouyiren.matcher(strresult);

            if(m_name.find()){
                String result_name = m_name.group(1).trim();
                model.addAttribute("insurance_name",result_name.trim());
            }
            if(m_toubaonianling.find(1000)){
                model.addAttribute("insurance_toubaonianling",m_toubaonianling.group(0));
            }



            if(m_zerentiaokuan.find(1000)){
                model.addAttribute("insurance_zerentiaokuan",m_zerentiaokuan.group(0));
            }

            if(m_jiaofeifangshi.find(1000)){
                model.addAttribute("insurance_jiaofeifangshi",m_jiaofeifangshi.group(0));
            }
            if(m_baoxianqijian.find(1000)){
                model.addAttribute("insurance_baoxianqijian",m_baoxianqijian.group(0));
            }
            if(m_shouyiren.find(1500)){
                model.addAttribute("insurance_shouyiren",m_shouyiren.group(0));
            }
            model.addAttribute("msg1","上传成功");
            return "policy_analysis";

        }catch (FileNotFoundException e){
            e.printStackTrace();
            model.addAttribute("msg","上传失败");
            return "policy_analysis";
        }
        catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg","解析失败");
            return "policy_analysis";
        }

    }


}