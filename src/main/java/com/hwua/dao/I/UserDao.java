package com.hwua.dao.I;

import com.hwua.entity.User;

import java.sql.SQLException;

public interface UserDao {
    public abstract User queryUser(String name, String pwd) throws SQLException;//通过姓名，密码查找
    public abstract Integer insertUser(User user) throws SQLException;//添加用户信息
    public abstract User queryName(String name) throws SQLException;//通过姓名查询用户
    public abstract User queryUserById(Long id) throws SQLException;//通过id查询用户
    public abstract User queryUserByEmail(String uname,String email) throws SQLException;//通过姓名和邮箱查询
}
