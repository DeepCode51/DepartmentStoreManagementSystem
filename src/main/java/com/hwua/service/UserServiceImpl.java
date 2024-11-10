package com.hwua.service;

import com.hwua.dao.I.UserDao;
import com.hwua.dao.UserDaoImpl;
import com.hwua.entity.User;
import com.hwua.service.I.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private static UserDao userDao = new UserDaoImpl();

    /**
     * 登陆
     * @param name,pwd
     * @return 登陆者信息
     * @throws SQLException
     */
    @Override
    public User login(String name,String pwd) throws SQLException {
        return userDao.queryUser(name,pwd);
    }

    /**
     * 注册
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public boolean register(User user) throws SQLException {
        //先判断用户名是否存在
        boolean b = queryName(user.getUname());
        if (b) {
            //不存在再进行注册
            Integer flag = userDao.insertUser(user);
            if (flag > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查询用户名
     * @param name
     * @return
     * @throws SQLException
     */
    @Override
    public boolean queryName(String name) throws SQLException {
        //查不到此用户名既可以注册，否则已存在
        User user = userDao.queryName(name);
        if (user==null){
            return true;
        }
        return false;
    }

    /**
     * 通过id查询用户
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public User queryUserById(Long id) throws SQLException {
        return userDao.queryUserById(id);
    }

    /**
     * 通过姓名和email找回密码
     * @param uname
     * @param email
     * @return
     * @throws SQLException
     */
    @Override
    public User retrievePwd(String uname, String email) throws SQLException {
        return userDao.queryUserByEmail(uname,email);
    }
}
