package com.hwua.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwua.entity.*;
import com.hwua.service.*;
import com.hwua.service.I.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@WebServlet("/doBuy")
public class BuyServlet extends HttpServlet {
    private static ProductCategoryService pcs;
    private static ObjectMapper mapper ;
    private static ProductService ps;
    private static ShopCartService shopCarSvs;
    private static OrderService orderSvs;
    private static OrderDetailService odSvs;
    private static UserService userSvc;


    @Override
    public void init() throws ServletException {
        orderSvs = new OrderServiceImpl();
        odSvs = new OrderDetailServiceImpl();
        shopCarSvs = new ShopCartServiceImpl();
        pcs = new ProductCategoryServiceImpl();
        mapper = new ObjectMapper();
        ps = new ProductServiceImpl();
        userSvc = new UserServiceImpl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");
        Order order = new Order();
        OrderDetail orderDetail = new OrderDetail();
        //获取当前用户信息
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        //订单总价
        BigDecimal totalPrice = new BigDecimal(0);
        //获取当前时间字符串
        Timestamp times = new Timestamp(new Date().getTime());
        if (param.equals("buy")){//购物车结算

            //如果没有登陆，跳转到登陆页面
            if (user==null){
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }else {
                //获取用户id
                long uid = user.getId();
                try {

                    //获取该用户的购物车
                    List<ShopCart> shopCarts = shopCarSvs.showCart(uid);
                    for (ShopCart shopCart : shopCarts) {
                        //累加购物项获得总价
                        totalPrice = totalPrice.add(shopCart.getSubtotal());
                    }

                    order.setUid(uid);//用户id
                    order.setUname(user.getUname());//用户姓名
                    order.setUaddress(user.getAddress());//用户地址
                    order.setCreate_time(times);//时间
                    order.setMoney(totalPrice);//金额
                    //添加订单
                    orderSvs.insertOrder(order);

                    //获取本次订单的id
                    List<Order> orders = orderSvs.queryOrderByUid(uid);
                    long orderId = orders.get(0).getId();
                    orderDetail.setOid(orderId);//设置订单id
                    //每个购物项对应一个订单明细
                    for (ShopCart shopCart : shopCarts) {
                        orderDetail.setPid(shopCart.getPid());//设置商品id
                        orderDetail.setQuantity(shopCart.getPnum());//设置商品数量
                        orderDetail.setMoney(shopCart.getSubtotal());//设置小计金额
                        //添加订单明细
                        odSvs.insertOrderDetail(orderDetail);
                        //删除对应的库存 = 原库存 - 购买的数量
                        Long pnum = shopCart.getPro().getStock() - shopCart.getPnum();
                        //调用方法更改库存
                        ps.delProStock(shopCart.getPro().getId(),pnum);
                    }

                    //清空购物车
                    shopCarSvs.clearShopCart(uid);

                    //把订单明细和订单放入到作用域,并跳转到订单页面
                    session.setAttribute("orderList",orders);
                    response.sendRedirect(request.getContextPath()+"/shopping-result.jsp");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else if (param.equals("buyOne")){//立即购买操作
             String pid = request.getParameter("pid");//商品id
            Long pnum = Long.parseLong(request.getParameter("pnum"));//商品数量

            //如果没有登陆，跳转到登陆页面
            if (user==null){
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }else {
                //获取用户id
                long uid = user.getId();
                try {
                    //查询商品单价
                    Product product = ps.queryProductById(Integer.parseInt(pid));
                    totalPrice = product.getPrice().multiply(new BigDecimal(pnum));//获取小计
                    //添加订单
                    order.setUid(uid);//用户id
                    order.setUname(user.getUname());//用户姓名
                    order.setUaddress(user.getAddress());//用户地址
                    order.setCreate_time(times);//时间
                    order.setMoney(totalPrice);//金额
                    //添加订单
                    orderSvs.insertOrder(order);
                    //获取本次订单的id
                    List<Order> orders = orderSvs.queryOrderByUid(uid);
                    long orderId = orders.get(0).getId();
                    orderDetail.setOid(orderId);//设置订单id
                    orderDetail.setPid(Long.parseLong(pid));//设置商品id
                    orderDetail.setQuantity(pnum);//设置商品数量
                    orderDetail.setMoney(totalPrice);//设置小计金额
                    //添加订单明细
                    odSvs.insertOrderDetail(orderDetail);
                    //删除对应库存
                    Long num =product.getStock() - pnum;
                    ps.delProStock(Long.parseLong(pid),num);
                    //把订单明细和订单放入到作用域,并跳转到订单页面
                    session.setAttribute("orderList",orders);
                    response.sendRedirect(request.getContextPath()+"/shopping-result.jsp");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else if (param.equals("show")){
            //如果没有登陆，跳转到登陆页面
            if (user==null){
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }else {
                //获取用户id
                long uid = user.getId();
                try {
                    List<Order> orders = orderSvs.queryOrderByUid(uid);
                    //把订单明细和订单放入到作用域,并跳转到订单页面
                    session.setAttribute("orderList",orders);
                    response.sendRedirect(request.getContextPath()+"/orders_view.jsp");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
