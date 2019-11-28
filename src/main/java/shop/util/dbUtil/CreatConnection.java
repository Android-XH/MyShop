package shop.util.dbUtil;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class CreatConnection {
    // private static String url = "jdbc:mysql://192.168.0.205:3306/test1";
    private static String url = "jdbc:mysql://localhost:3306/shop?useUnicode=true&characterEncoding=utf8&useSSL=false&rewriteBatchedStatements=true";
    private static String username = "root";
    private static String password = "123456";
    private static Connection con = null;
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}