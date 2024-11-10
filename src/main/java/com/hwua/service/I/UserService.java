package com.hwua.service.I;

import com.hwua.entity.User;

import java.sql.SQLException;

public interface UserService {
    public abstract User login(String name,String pwd) throws SQLException;//登陆
    public abstract boolean register(User user) throws SQLException;//注册
    public abstract boolean queryName(String name) throws SQLException;//通过姓名查询用户
    public abstract User queryUserById(Long id) throws SQLException;//通过id查询用户
    public abstract User retrievePwd(String uname,String email) throws SQLException;//通过姓名和邮箱找回密码
}
