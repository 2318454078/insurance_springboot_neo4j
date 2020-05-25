import com.appleyk.database.JDBCKit;
import com.appleyk.node.Insurance;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String name = null;
        String sn = "百年如意宝意外伤害保险";
        name = sn;
        Map<String,String> insurance = new HashMap<String, String>();
        insurance =  getInByNameTwo(name);
//
//        System.out.println(insurance.get("投保年龄"));
//        System.out.println(insurance.get("宽限期"));
//        System.out.println(insurance.get("犹豫期"));
//        System.out.println(insurance.get("责任条款"));
//        System.out.println(insurance.get("免责条款"));
//        System.out.println(insurance.get("缴费方式"));
//        System.out.println(insurance.get("保险期间"));
//        System.out.println(insurance.get("受益人"));
//
//        System.out.println("结束了吗");
        //System.out.println(insurance);
        Insurance in = new Insurance();
        in = getInByName(name);
        System.out.println(in.getIn_zerentiaokuan());

    }

    //根据保险的名字获取保险信息
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
                //System.out.println("????");
                //System.out.println(resultSet.getString("insurance_toubaonianling"));
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

    public Map<String,String> getInByNameTwo(String name){
        Connection connection= null;
        ResultSet resultSet=null;
        PreparedStatement statement=null;
        Map<String,String> insurance = new HashMap<String, String>();

        try{
            connection = JDBCKit.getConnection();
            String sql="select * from insurance where insurance_name = \""+name+"\";";
            statement=connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                insurance.put("投保年龄",resultSet.getString("insurance_toubaonianling"));

                insurance.put("宽限期",resultSet.getString("insurance_kuanxianqi"));
                insurance.put("犹豫期",resultSet.getString("insurance_youyuqi"));
                insurance.put("责任条款",resultSet.getString("insurance_zerentiaokuan"));
                insurance.put("免责条款",resultSet.getString("insurance_mianzetiaokuan"));
                insurance.put("缴费方式",resultSet.getString("insurance_jiaofeifangshi"));
                insurance.put("保险期间",resultSet.getString("insurance_baoxianqijian"));
                insurance.put("受益人",resultSet.getString("insurance_shouyiren"));
            }
            return insurance;
        }catch (Exception ex){
            ex.printStackTrace();
            JDBCKit.release(resultSet, null, connection);
            return null;
        }
    }
}
