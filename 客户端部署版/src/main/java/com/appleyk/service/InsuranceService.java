package com.appleyk.service;

import com.appleyk.node.Insurance;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.Map;

public interface InsuranceService {
    List<Integer> getAllTypes() throws Exception;
    List<Integer> getAllComs()throws Exception;
    Integer ComIdToName(int Com_nam)throws Exception;
    String typeIdToName(int type_id)throws Exception;
    Integer getAllComInNum(int Type_id)throws Exception;
    Map<String,Integer> getInNumByTypes()throws Exception;
    Map<String,Integer> getComInsurNumByTypes(int Com_id)throws Exception;
    Map<String,Integer> getInNumByAge()throws Exception;
    Map<String,Integer> getComInNumByAge(int Com_id)throws Exception;
    Map<Integer,String> getInsByCOm(int Com_id);
    Map<String,String> getInUrl(int In_id);
    String choose() throws ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            UnsupportedLookAndFeelException;
    File DownByUrl(String urlStr, String fileName);
    Insurance getInByName(String name);
}
