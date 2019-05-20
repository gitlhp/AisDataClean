package com.ruoyi.project.system.aistarget.dbconn;

import com.ruoyi.project.system.aistarget.domain.DBConnection;
import com.ruoyi.project.system.aistarget.domain.TargetDbconn;
import com.ruoyi.project.system.aistarget.service.AisTargetService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;

/**
 * @ClassName dbconnection
 * @Description 从前台获取参数进行数据库连接
 * @Author 李怀鹏
 * @Date 2019/4/26 10:32
 * @Version 1.0
 **/
public class Dbconntool {
    @Autowired
    private AisTargetService aisTargetService;
    //目前只支持mysql数据库
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    //private static final String url = "jdbc:mysql://localhost:3306/ruoyi?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
    //private static final String username = "root";
    //private static final String password = "hhh";
    private Dbconntool() {
    }

    static {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
   // 此方法将在前台获取参数，并返回一个数据库连接对象
    public static Connection getConnection(DBConnection dbConnection) throws SQLException {
        String ip = dbConnection.getIp();
        String port = dbConnection.getPort();
        String dbname = dbConnection.getDbname();
        String username = dbConnection.getUsername();
        String password = dbConnection.getPassword();
        //拼接url
        String url = "jdbc:mysql://"+ip+":"+port+"/"+dbname+"?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true";
        return DriverManager.getConnection(url, username, password);
    }
    //目标数据库连接
    public static Connection getConnection(TargetDbconn dbConnection) throws SQLException {
        String ip = dbConnection.getIp();
        String port = dbConnection.getPort();
        String dbname = dbConnection.getDbname();
        String username = dbConnection.getUsername();
        String password = dbConnection.getPassword();
        //拼接url
        String url = "jdbc:mysql://"+ip+":"+port+"/"+dbname+"?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true";
        return DriverManager.getConnection(url, username, password);
    }
    //按顺序释放资源
    public static void close(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    //public static void main(String[] args){
    //    //Connection connection = null;
    //    //try {
    //    //    connection = Dbconntool.getConnection();
    //    //    if (connection!=null){
    //    //        System.out.println("连接成功");
    //    //    }
    //    //} catch (SQLException e) {
    //    //    System.out.println("jjjjjjjjjjjjj");
    //    //}
    //    //if (connection!=null){
    //    //    System.out.println("连接成功");
    //    //}else {
    //    //    System.out.println("连接失败");
    //    //}
    //
    //    DbPool d
    //}
}
