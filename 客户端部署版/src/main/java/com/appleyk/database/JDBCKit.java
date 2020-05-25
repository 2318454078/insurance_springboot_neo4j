package com.appleyk.database;

import java.sql.*;

public class JDBCKit
{
    private static final String driverName="com.mysql.cj.jdbc.Driver";
    private static final String url="jdbc:mysql://47.102.200.206:3306/znwd?useSSL=false";
    private static final String user="root";
    private static final String password="root";
    /**
     * 鑾峰彇鏁版嵁搴撹繛锟�?
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 閲婃斁璧勬簮
     * @param rs
     * @param pstmt
     * @param conn
     */
    public static void release(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if(rs != null) {
                rs.close();
            }
            if(pstmt != null) {
                pstmt.close();
            }
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
