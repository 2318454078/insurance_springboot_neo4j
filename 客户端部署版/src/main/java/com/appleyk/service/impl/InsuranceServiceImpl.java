package com.appleyk.service.impl;

import com.appleyk.database.JDBCKit;
import com.appleyk.node.Insurance;
import com.appleyk.service.InsuranceService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Primary
public class InsuranceServiceImpl implements InsuranceService {

    //获取所有的保险类型的id
    @Override
    public List<Integer> getAllTypes(){
        Connection connection= null;
        ResultSet resultSet=null;
        PreparedStatement statement=null;
        List<Integer> listNum = new ArrayList<Integer>();

        try{
            connection = JDBCKit.getConnection();
            String sql="SELECT type_id FROM type;";
            statement=connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int Num = resultSet.getInt("type_id");
                listNum.add(Num);
            }
            return listNum;
        } catch (Exception ex){
            ex.printStackTrace();
            JDBCKit.release(resultSet, null, connection);
            return null;
        }
    }

    //获取所有的保险公司id
    @Override
    public List<Integer> getAllComs(){
        Connection connection= null;
        ResultSet resultSet=null;
        PreparedStatement statement=null;
        List<Integer> listNum = new ArrayList<Integer>();

        try{
            connection = JDBCKit.getConnection();
            String sql="SELECT company_id FROM company;";
            statement=connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int Num = resultSet.getInt("company_id");
                listNum.add(Num);
            }
            return listNum;
        } catch (Exception ex){
            ex.printStackTrace();
            JDBCKit.release(resultSet, null, connection);
            return null;
        }
    }

    //根据公司name获取公司id
    @Override
    public Integer ComIdToName(int Com_nam){
        Connection connection= null;
        ResultSet resultSet=null;
        PreparedStatement statement=null;

        try{
            connection = JDBCKit.getConnection();
            String sql="SELECT company_id FROM company where company_name = " + Com_nam + ";";
            statement=connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            int id = resultSet.getInt("company_id");
            return id;
        } catch (Exception ex){
            ex.printStackTrace();
            JDBCKit.release(resultSet, null, connection);
            return null;
        }
    }

    //根据保险类型id获得name
    @Override
    public String typeIdToName(int type_id){
        Connection connection= null;
        ResultSet resultSet=null;
        PreparedStatement statement=null;

        try{
            connection = JDBCKit.getConnection();
            String sql="SELECT type_name FROM type where type_id = " + type_id + ";";
            statement=connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            String Name = null;
            while(resultSet.next())
            {
                Name = resultSet.getString("type_name");
            }

            return Name;
        } catch (Exception ex){
            ex.printStackTrace();
            JDBCKit.release(resultSet, null, connection);
            return null;
        }
    }

    //根据指定保险类型返回保险的数量
    @Override
    public Integer getAllComInNum(int Type_id){
        Connection connection= null;
        ResultSet resultSet=null;
        PreparedStatement statement=null;
        int Num = 0;

        try{
            connection = JDBCKit.getConnection();
            String sql="select count(insurance_id) as Num from insurance where insurance_typeid = " + Type_id + ";";
            statement=connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Num = resultSet.getInt("Num");
            }
            return Num;
        }catch (Exception ex){
            ex.printStackTrace();
            JDBCKit.release(resultSet, null, connection);
            return null;
        }
    }

    //根据保险的名字获取保险信息
    @Override
    public Insurance getInByName(String name){
        Connection connection= null;
        ResultSet resultSet=null;
        PreparedStatement statement=null;
        Insurance insurance = new Insurance();

        try{
            connection = JDBCKit.getConnection();
            String sql="select * from insurance where insurance_name = \""+name+"\";";
            statement=connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString("insurance_toubaonianling"));
                insurance.setIn_toubaonianling(resultSet.getString("insurance_toubaonianling"));
                insurance.setIn_kuanxianqi(resultSet.getString("insurance_kuanxianqi"));
                insurance.setIn_youyuqi(resultSet.getString("insurance_youyuqi"));
                insurance.setIn_zerentiaokuan(resultSet.getString("insurance_zerentiaokuan"));
                insurance.setIn_mianzetiaokuan(resultSet.getString("insurance_mianzetiaokuan"));
                insurance.setIn_jiaofeifangshi(resultSet.getString("insurance_jiaofeifangshi"));
                insurance.setIn_baoxianqijian(resultSet.getString("insurance_baoxianqijian"));
                insurance.setIn_shouyiren(resultSet.getString("insurance_shouyiren"));
            }
            return insurance;
        }catch (Exception ex){
            ex.printStackTrace();
            JDBCKit.release(resultSet, null, connection);
            return null;
        }

    }


    //返还所有公司每一类保险的数量(返回值map：type_name,InsuranceNum)
    @Override
    public Map<String,Integer> getInNumByTypes(){
        Connection connection= null;
        ResultSet resultSet=null;
        PreparedStatement statement=null;
        //每个类型保险的数量
        Map<String ,Integer> NumByTypes = new HashMap<String, Integer>();
        //获取保险的所有类型id
        List<Integer> getAllTypes = getAllTypes();

        try{
            connection = JDBCKit.getConnection();
            //遍历所有类型
            for(int i = 0;i < getAllTypes.size();i++){

                int type_id = getAllTypes.get(i);
                String sql="SELECT COUNT(insurance_id) as NUM FROM insurance where insurance_typeid = " + type_id + ";";
                statement=connection.prepareStatement(sql);
                resultSet = statement.executeQuery();

                while(resultSet.next()){
                    String type_name = typeIdToName(type_id);
                    System.out.println(type_name);
                    int Num = resultSet.getInt("NUM");
                    NumByTypes.put(type_name,Num);

                }
            }
            return NumByTypes;
        } catch (Exception ex){
            ex.printStackTrace();
            JDBCKit.release(resultSet, null, connection);
            return null;
        }
    }

    //返回指定id公司旗下每一类保险的数量(返回值map：type_name,InsuranceNum)
    @Override
    public Map<String,Integer> getComInsurNumByTypes(int Com_id){
        Connection connection= null;
        ResultSet resultSet=null;
        PreparedStatement statement=null;
        Map<String ,Integer> getNumByCom = new HashMap<String ,Integer>();

        List<Integer> typeids = getAllTypes();//获得所有type
        int type_id = 0;

        try{
            connection = JDBCKit.getConnection();
            for (int i = 0;i < typeids.size();i++){
                type_id = typeids.get(i);
                System.out.println(type_id);
                String sql="SELECT COUNT(insurance_id) as Num FROM insurance where insurance_companyid = " + Com_id + " and insurance_typeid = " + type_id + ";";
                statement=connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                while (resultSet.next()){
                    int Num = resultSet.getInt("Num");
                    String TypeNam = typeIdToName(type_id);
                    getNumByCom.put(TypeNam,Num);
                }
            }
            return getNumByCom;
        } catch (Exception ex){
            ex.printStackTrace();
            JDBCKit.release(resultSet, null, connection);
            return null;
        }

    }

    //返还所有保险公司不同投保年龄范围的保险数量(返回值map：age(0-55，55-60,60--)，num)
    @Override
    public Map<String,Integer> getInNumByAge(){
        Connection connection= null;
        ResultSet resultSet=null;
        PreparedStatement statement=null;
        Map<String,Integer> listNum = new HashMap<String, Integer>();

        try{
            connection = JDBCKit.getConnection();
            String sql1="SELECT COUNT(insurance_id) AS Num FROM insurance WHERE insurance_toubaonianling <= 55;";
            String sql2="SELECT COUNT(insurance_id) AS Num FROM insurance WHERE insurance_toubaonianling > 55 and insurance_toubaonianling <=60;";
            String sql3="SELECT COUNT(insurance_id) AS Num FROM insurance WHERE insurance_toubaonianling > 60;";
            statement=connection.prepareStatement(sql1);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int Num = resultSet.getInt("Num");
                listNum.put("0~55",Num);
            }
            statement=connection.prepareStatement(sql2);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int Num = resultSet.getInt("Num");
                listNum.put("55~60",Num);
            }
            statement=connection.prepareStatement(sql3);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int Num = resultSet.getInt("Num");
                listNum.put("60~120",Num);
            }
            return listNum;
        } catch (Exception ex){
            ex.printStackTrace();
            JDBCKit.release(resultSet, null, connection);
            return null;
        }
    }

    //提供公司id，返还不同投保年龄范围的保险数量(返回值map：age(0-55，55-60,60--)，num)
    @Override
    public Map<String,Integer> getComInNumByAge(int Com_id){
        //public String getComInNumByAge(int Com_id){
        Connection connection= null;
        ResultSet resultSet=null;
        PreparedStatement statement=null;
        Map<String,Integer> listNum = new HashMap<String, Integer>();

        try{
            connection = JDBCKit.getConnection();
            String sql1="SELECT COUNT(insurance_id) AS Num FROM insurance WHERE insurance_toubaonianling <= 55 AND insurance_companyid = " + Com_id +";";
            String sql2="SELECT COUNT(insurance_id) AS Num FROM insurance WHERE insurance_toubaonianling > 55 and insurance_toubaonianling <=60 AND insurance_companyid = " + Com_id +";";
            String sql3="SELECT COUNT(insurance_id) AS Num FROM insurance WHERE insurance_toubaonianling > 60 AND insurance_companyid = " + Com_id + ";";
            statement=connection.prepareStatement(sql1);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int Num = resultSet.getInt("Num");
                listNum.put("0~55",Num);
            }
            statement=connection.prepareStatement(sql2);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int Num = resultSet.getInt("Num");
                listNum.put("55~60",Num);
            }
            statement=connection.prepareStatement(sql3);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int Num = resultSet.getInt("Num");
                listNum.put("60~120",Num);
            }

            return listNum;
            //   return "zhengchang";
        } catch (Exception ex){
            ex.printStackTrace();
            JDBCKit.release(resultSet, null, connection);
            return null;

        }
    }

    //根据保险公司id获取旗下所有保险的id和name
    @Override
    public Map<Integer,String> getInsByCOm(int Com_id){
        Connection connection= null;
        ResultSet resultSet=null;
        PreparedStatement statement=null;
        Map<Integer,String> Ins = new HashMap<Integer, String>();

        try{
            connection = JDBCKit.getConnection();
            String sql="SELECT insurance_id,insurance_name FROM insurance WHERE insurance_companyid =" +  Com_id + ";";
            statement=connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int key = resultSet.getInt("insurance_id");
                String value = resultSet.getString("insurance_name");
                Ins.put(key,value);
            }
            return Ins;
        }catch (Exception ex){
            ex.printStackTrace();
            JDBCKit.release(resultSet, null, connection);
            return null;
        }
    }

    //根据保险的id获取服务器的url(返回值：map<name,url>)
    @Override
    public Map<String,String> getInUrl(int In_id){
        Connection connection= null;
        ResultSet resultSet=null;
        PreparedStatement statement=null;
        Map<String,String> url = new HashMap<String, String>();

        try{
            connection = JDBCKit.getConnection();
            String sql="SELECT url,insurance_name FROM insurance WHERE insurance_id = " + In_id + ";";
            statement=connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String value = resultSet.getString("url");
                String key = resultSet.getString("insurance_name");
                url.put(key,value);
            }
            return url;
        }catch (Exception ex){
            ex.printStackTrace();
            JDBCKit.release(resultSet, null, connection);
            return null;
        }
    }

    //选择文件保存位置
    @Override
    public String choose() throws ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            UnsupportedLookAndFeelException {
        String path = null;
        //设置界面风格
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFileChooser jdir = new JFileChooser();
        //设置选择路径模式
        jdir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //设置对话框标题
        jdir.setDialogTitle("请选择路径");
        if (JFileChooser.APPROVE_OPTION == jdir.showOpenDialog(null)) {//用户点击了确定
            path = jdir.getSelectedFile().getAbsolutePath();//取得路径选择
        }
        System.out.println(path);
        return path;
    }

    //根据获取的url下载（实现可自主选择下载路径）
    //urlStr：pdf在服务器的url；fileName：要下载的保险合同的名字
    @SuppressWarnings("finally")
    @Override
    public File DownByUrl(String urlStr, String fileName) {
        File file = null;
        try {
            // 统一资源
            URL url = new URL(urlStr);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            //设置超时
            httpURLConnection.setConnectTimeout(1000*5);
            //设置请求方式，默认是GET
            httpURLConnection.setRequestMethod("GET");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            // 打开到此 URL引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();
            // 文件大小
            int fileLength = httpURLConnection.getContentLength();

            // 控制台打印文件大小
            System.out.println("您要下载的文件大小为:" + fileLength / (1024 * 1024) + "MB");

            // 建立链接从请求中获取数据
            URLConnection con = url.openConnection();
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            // 指定文件名称(有需求可以自定义)
            String fileFullName = fileName + ".pdf";

            // 指定存放位置
            String downloadDir = choose();
            String path = downloadDir + File.separatorChar + fileFullName;
            file = new File(path);
            // 校验文件夹目录是否存在，不存在就创建一个目录
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[2048];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
                // 控制台打印文件下载的百分比情况
                System.out.println("下载了-------> " + len * 100 / fileLength + "%\n");
            }
            // 关闭资源
            bin.close();
            out.close();
            System.out.println("文件下载成功！");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("文件下载失败！");
        } finally {
            return file;
        }
    }
}
