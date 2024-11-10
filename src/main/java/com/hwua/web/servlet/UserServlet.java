package com.hwua.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwua.entity.ShopCart;
import com.hwua.entity.User;
import com.hwua.service.I.ShopCartService;
import com.hwua.service.I.UserService;
import com.hwua.service.ShopCartServiceImpl;
import com.hwua.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static UserService userSvc;
    private static ObjectMapper mapper;
    private static ShopCartService shopcarSvs;


    @Override
    public void init() throws ServletException {
        shopcarSvs = new ShopCartServiceImpl();
        userSvc = new UserServiceImpl();
        mapper = new ObjectMapper();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        Map<String,Object> map = new HashMap<>();
        String param = request.getParameter("param");
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regID = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        //邮箱正则表达式
        String regEm="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,3}$";
        //手机号正则表达式
        String regNum = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        //进行登陆判断
        if (param.equals("login")){
            String username = request.getParameter("username");
            String pwd = request.getParameter("pwd");
            String code = request.getParameter("code");
            //判断用户是否存在
            try {
                user = userSvc.login(username,pwd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //判断验证码是否正确
            HttpSession session = request.getSession(false);
            String checkCode = (String) session.getAttribute("checkCode");
            System.out.println(checkCode);

            if (user==null){ //假设用户不存在
                map.put("success",false);
                map.put("error","账号密码错误！");
//            }else if (!code.equals(checkCode)){ //如果验证码不正确
//                map.put("success",false);
//                map.put("error","验证码错误");
//            }else if (user!=null && code.equals(checkCode)){//如果账号密码验证码都正确则通过
//                map.put("success",true);
//                //把用户信息放入session
//                session.setAttribute("user",user);
//                try {
//                    //查询登陆之前是否有加入购物车
//                    List<ShopCart> shopCarts = shopcarSvs.showCart(0L);
//                    if (shopCarts!=null){
//                        //就把虚拟Uid改为当前用户id
//                        shopcarSvs.updateUid(user.getId());
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }

            }else if (user!=null){//如果账号密码验证码都正确则通过
                map.put("success",true);
                //把用户信息放入session
                session.setAttribute("user",user);
                try {
                    //查询登陆之前是否有加入购物车
                    List<ShopCart> shopCarts = shopcarSvs.showCart(0L);
                    if (shopCarts!=null){
                        //就把虚拟Uid改为当前用户id
                        shopcarSvs.updateUid(user.getId());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else if (param.equals("register")){//判断注册是否成功

            //获取验证码
            HttpSession session = request.getSession(false);
            String checkCode = (String) session.getAttribute("checkCode");
            System.out.println(checkCode);
            //获取用户填写的参数
            String username = request.getParameter("username");
            String pwd = request.getParameter("pwd");
            String confirm = request.getParameter("confirm");
            String sex = request.getParameter("sex");
            //把生日字符串转换成date
            String birthday = request.getParameter("birthday");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            try {
                date = sdf.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //转换成sql.Date
            java.sql.Date date1 = new java.sql.Date(date.getTime());
            String identity = request.getParameter("identity");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String address = request.getParameter("address");
            String code = request.getParameter("code");

            //把参数封装成类
            user.setUname(username);
            user.setPwd(pwd);
            user.setSex(Integer.parseInt(sex));
            user.setBirthday(date1);
            user.setIdcard(identity);
            user.setEmail(email);
            user.setMobile(mobile);
            user.setAddress(address);
            if (username.equals("")){
                map.put("success",false);
                map.put("error","用户名不能为空！");
            }else if (pwd.equals("")){
                map.put("success",false);
                map.put("error","密码不能为空！");
            }else if (confirm.equals("")){
                map.put("success",false);
                map.put("error","确认密码不能为空！");
            }else if (!pwd.equals(confirm)){
                map.put("success",false);
                map.put("error","两次密码不相同！");
            } else if (sex.equals("")){
                map.put("success",false);
                map.put("error","请选择性别！");
            }else if (birthday.equals("")){
                map.put("success",false);
                map.put("error","生日不能为空！");
            }else if (identity.equals("")){
                map.put("success",false);
                map.put("error","请输入身份证号");
            }else if (identity.matches(regID)==false){
                map.put("success",false);
                map.put("error","请输入正确的身份证号");
            }else if (email.equals("")){
                map.put("success",false);
                map.put("error","邮箱不能为空！");
            }else if (email.matches(regEm)==false){
                map.put("success",false);
                map.put("error","请输入正确邮箱！列如：xxx@xx.com");
            }else if (mobile.equals("")){
                map.put("success",false);
                map.put("error","手机号不能为空！");
            }else if (mobile.matches(regNum)==false){
                map.put("success",false);
                map.put("error","请输入正确的手机号");
            }else if (address.equals("")){
                map.put("success",false);
                map.put("error","地址不能为空！");
            }else if (code.equals("")){
                map.put("success",false);
                map.put("error","验证码不能为空！");
            }else if (!code.equals(checkCode)){
                map.put("success",false);
                map.put("error","验证码不正确！");
            }else {//否则进行登陆操作
                try {
                    boolean register = userSvc.register(user);
                    //判断是否登陆成功
                    if (register){
                        map.put("success",true);
                    }else {
                        map.put("success",false);
                        map.put("error","登陆失败！");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else if (param.equals("retrievePwd")){
            String reusername = request.getParameter("reusername");//用户名
            String reuname = request.getParameter("reuname");//真实姓名
            String reemail = request.getParameter("reemail");//email
            try {
                //找回密码
                User reUser = userSvc.retrievePwd(reusername, reemail);
                if (reUser!=null){
                    map.put("success",true);
                    map.put("reUser",reUser);
                }else if (reusername.equals("")){
                    map.put("success",false);
                    map.put("error","用户名不能为空！");
                }else if (reuname.equals("")){
                    map.put("success",false);
                    map.put("error","真实姓名不能为空！");
                }else if (reemail.equals("")){
                    map.put("success",false);
                    map.put("error","邮箱不能为空！");
                }else if (reemail.matches(regEm)==false) {
                    map.put("success", false);
                    map.put("error", "请输入正确邮箱！列如：xxx@xx.com");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else if (param.equals("exit")){//退出操作
            HttpSession session = request.getSession(false);
            //删除作用于中的user
            session.removeAttribute("user");
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }else if (param.equals("nameBlur")){
            String username = request.getParameter("username");
            if (username.equals("")){
                return;
            }else {
                try {
                    boolean flag = userSvc.queryName(username);//判断用户名是否使用
                    if (flag) {
                        map.put("success1", true);
                        map.put("error", "恭喜您，该用户名可以使用！");
                    } else {
                        map.put("success1", false);
                        map.put("error", "该用户名已存在！");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //把数据变成json写入到页面
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
