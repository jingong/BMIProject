package cn.edu.ldu.dao;

import cn.edu.ldu.bean.BmiBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiajingong on 2017/7/4.
 */
public class DBHelper {
    public static final String url = "jdbc:mysql://127.0.0.1:3306/bmi?useUnicode=true&characterEncoding=utf-8";

    public static final String user = "root";

    public static final String password = "root";
    //包的支持
    public static final String name = "com.mysql.jdbc.Driver";

    private Connection connection;

    public PreparedStatement pst;



    public DBHelper() {
    }
    public DBHelper(String sql) {
        //
        try {
            //实用工具类加载驱动
            Class.forName(name);
            System.out.println("驱动加载成功");
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("获取链接成功");
            pst = connection.prepareStatement(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("驱动加载失败");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取链接失败");
        }

    }
    //存储数据到数据库
    public static boolean addBmi(BmiBean bean){
        String sql = "insert into bmiinfo(date,height,weight,bmi) " +
                "values(?,?,?,?)";
        DBHelper db = new DBHelper(sql);
        try {
            db.pst.setString(1,bean.getDate());
            db.pst.setString(2,bean.getHeight());
            db.pst.setString(3,bean.getWeight());
            db.pst.setString(4,bean.getBmi());
            int update = db.pst.executeUpdate();

            if (update > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭连接
            db.close();
        }
        return false;
    }
    //删除数据
    public static boolean deleteBmi(String sign){
        //强制类型转换
        int id = Integer.parseInt(sign);
        String sql = "delete from bmiinfo where id=?";
        DBHelper db = new DBHelper(sql);
        try {
            db.pst.setInt(1,id);
            int update = db.pst.executeUpdate();
            if (update > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //获取数据
    public static List<BmiBean> getBmi(){
        String sql = "select * from bmiinfo";
        DBHelper db = new DBHelper(sql);
        List<BmiBean> list = null;
        try {
            ResultSet set = db.pst.executeQuery();
            list = new ArrayList<>();
            while (set.next()){
                BmiBean bean = new BmiBean();
                bean.setBmi(set.getString("bmi"));
                bean.setDate(set.getString("date"));
                bean.setHeight(set.getString("height"));
                bean.setWeight(set.getString("weight"));
                bean.setId(set.getInt("id"));
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
        return list;
    }
    public void close(){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connection = null;
                pst = null;
            }
        }
    }
}
