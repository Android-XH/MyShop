package shop.util.dbUtil;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import shop.mode.ShopUser;
import shop.util.TimeUtil;
import shop.util.dbUtil.CreatConnection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class TestAddBatch {
    public static void main(String[] args) {
// TODO Auto-generated method stub
        int[] count = null;
        Long startTime = System.currentTimeMillis();
        System.out.println();
        System.out.println(new SimpleDateFormat("yyyy-M-dd HH:mm:ss").format(startTime) + "\r\n start");
        try {
            String sql ="";
            Connection conn = CreatConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pre = null;
//            sql = "INSERT INTO shop_user(name,content) values(?,?)";
            sql="INSERT into shop_user (sid,provcity,shop_dsr,shop_title,user_type,nick ) values (?,?,?,?,?,?) ON DUPLICATE KEY UPDATE provcity = VALUES(provcity),shop_title = VALUES(shop_title),user_type = VALUES(user_type),nick = VALUES(nick);";
            pre = (PreparedStatement) conn.prepareStatement(sql);
            for(int i=0;i<1000;i++){
                pre.setLong(1,i);
                pre.setString(2,"provcity");
                pre.setLong(3,i);
                pre.setString(4,"title");
                pre.setLong(5,i);
                pre.setString(6,"nick");
                pre.addBatch();
            }
            if (pre != null) {
                count = pre.executeBatch();
                conn.commit();
                pre.close();
            }
            Long endTime = System.currentTimeMillis();
            System.out.println(new SimpleDateFormat("yyyy-M-dd HH:mm:ss").format(endTime) + "\r\n end");
            System.out.println("all data count:"+count.length+"  Cost Time:"+ TimeUtil.getTime(endTime-startTime));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
