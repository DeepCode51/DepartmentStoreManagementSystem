package com.hwua.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidPool {
    private static DataSource ds;
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();//生成本地线程类

    public static DataSource getDruidDS(){
        //读取配置文件
        InputStream is = DruidPool.class.getClassLoader().getResourceAsStream("druid.properties");
        //调用输入流读取
        Properties pro = new Properties();
        try {
            pro.load(is);
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    //获取一个连接对象,第一次获取是从数据源中取一个绑定到线程上,后面直接从线程上去获取绑定的连接对象
    public static Connection getConnection() throws SQLException {
        Connection conn = tl.get();
        //如果是第一次，创建一个绑定到本地线程
        if (conn==null){
            conn = ds.getConnection();
            tl.set(conn);
        }
        return conn;

    }

    //开启事务
    public static void startTransaction() throws SQLException {
        Connection conn = getConnection();
        conn.setAutoCommit(false);//开启事务
    }

    //提交事务
    public static void commit() throws SQLException {
        Connection conn = getConnection();
        conn.commit();//提交事务
        conn.close();//关闭资源
        tl.remove();//删除本地线程
    }

    //回滚事务
    public static void rollback() throws SQLException {
        Connection conn = getConnection();
        conn.rollback();
        conn.close();//关闭资源
        tl.remove();//删除本地线程
    }
}
