package com.hwua.dao;

import com.hwua.dao.I.UserDao;
import com.hwua.entity.User;
import com.hwua.utils.DruidPool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    //创建JDBC的对象
    private static QueryRunner qr = new QueryRunner(DruidPool.getDruidDS());

    @Override
    public User queryUser(String name, String pwd) throws SQLException {
        String sql = "select id,uname,pwd,sex,birthday,idcard,email,mobile,address,utype from amz_user where uname = ? and pwd = ?";
        return qr.query(sql, new BeanHandler<>(User.class),name,pwd);
    }

    @Override
    public Integer insertUser(User user) throws SQLException{
        String sql = "insert amz_user values(null,?,?,?,?,?,?,?,?,0)";
        return qr.update(sql,user.getUname(),user.getPwd(),user.getSex(),user.getBirthday(),user.getIdcard(),user.getEmail(),user.getMobile(),user.getAddress());
    }

    @Override
    public User queryName(String name) throws SQLException {
        String sql = "select id,uname,pwd,sex,birthday,idcard,email,mobile,address,utype from amz_user where uname = ?";
        return qr.query(sql,new BeanHandler<>(User.class),name);
    }

    @Override
    public User queryUserById(Long id) throws SQLException {
        String sql = "select id,uname,pwd,sex,birthday,idcard,email,mobile,address,utype from amz_user where id = ?";
        return qr.query(sql,new BeanHandler<>(User.class),id);
    }

    @Override
    public User queryUserByEmail(String uname, String email) throws SQLException {
        String sql = "select id,uname,pwd,sex,birthday,idcard,email,mobile,address,utype from amz_user where uname = ? and email = ?";
        return qr.query(sql,new BeanHandler<>(User.class),uname,email);
    }
}
