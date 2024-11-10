package com.hwua.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwua.entity.Product;
import com.hwua.entity.ShopCart;
import com.hwua.entity.User;
import com.hwua.service.I.ProductCategoryService;
import com.hwua.service.I.ProductService;
import com.hwua.service.I.ShopCartService;
import com.hwua.service.ProductCategoryServiceImpl;
import com.hwua.service.ProductServiceImpl;
import com.hwua.service.ShopCartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/shopCar")
public class ShopCarServlet extends HttpServlet {
    private static ProductCategoryService pcs;
    private static ObjectMapper mapper ;
    private static ProductService ps;
    private static ShopCartService cartService;


    @Override
    public void init() throws ServletException {
        pcs = new ProductCategoryServiceImpl();
        mapper = new ObjectMapper();
        ps = new ProductServiceImpl();
        cartService = new ShopCartServiceImpl();
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShopCart shopCart = new ShopCart();
        Map<String,Object> map = new HashMap<>();
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");

        String param = request.getParameter("param");
        if (param.equals("showCar")){//添加购物项
            String pid = request.getParameter("id");//商品id
            Integer pnum = Integer.parseInt(request.getParameter("num"));//商品数量
            //判断用户是否登陆
            Long uid = 0L;
            //如果登陆了替换为当前用户id
            if (user!=null){
                uid = user.getId();
            }
            //封装成对象
            shopCart.setPid(Integer.parseInt(pid));
            shopCart.setPnum(pnum);
            shopCart.setUid(uid);
            try {
                //添加商品到购物车
                cartService.addProduct(shopCart);
                //获取购物车结果集
                List<ShopCart> shopCarts = cartService.showCart(uid);
                //设置到作用域并重定向到购物车页面
                session.setAttribute("shopCar",shopCarts);
                response.sendRedirect(request.getContextPath()+"/shopping.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (param.equals("update")){//执行 商品数量修改
            Long id = Long.parseLong(request.getParameter("id"));
            Integer pnum =Integer.parseInt(request.getParameter("pnum"));//获取当前商品数量
            String type = request.getParameter("type");//判断修改类型
            if (type.equals("-")) {//减操作
                pnum -= 1;
            }else if (type.equals("+")){//加操作
                pnum += 1;
            }
            try {
                //通过id获取商品信息
                ShopCart updateCart = cartService.queryShopCart(id);
                Product product = ps.queryProductById(updateCart.getPid());
                //修改的数量不能<=0
                if (pnum<=0){
                    map.put("updatesuccess",false);
                    map.put("error","操作有误！商品最低数量为1");
                    map.put("updatePro",product);
                    //把购物项数量改为1
                    cartService.updateNum(1, id);
                }else if (pnum >product.getStock()){//修改的数量不能大于库存
                    map.put("updatesuccess",false);
                    map.put("error","操作有误！商品最大库存为"+product.getStock());
                    map.put("updatePro",product);
                    //把购物项数量改为最大库存
                    cartService.updateNum((int)product.getStock(), id);
                }else {
                    //修改商品数量
                    cartService.updateNum(pnum, id);
                    //获得更改过数量的购物项
                    updateCart = cartService.queryShopCart(id);
                    map.put("updatesuccess",true);
                    map.put("updateCart", updateCart);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (param.equals("delPro")){//删除购物项
            System.out.println("111");
            String id = request.getParameter("id");
            Long uid = 0L;
            //如果登陆了替换为当前用户id
            if (user!=null){
                uid = user.getId();
            }
            try {
                //根据ID删除对应购物项
                cartService.delProduct(Long.parseLong(id));
                //获取购物车结果集
                List<ShopCart> shopCarts = cartService.showCart(uid);
                System.out.println(shopCarts);
                //设置到作用域并重定向到购物车页面
                session.setAttribute("shopCar",shopCarts);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect(request.getContextPath()+"/shopping.jsp");
        }else if (param.equals("show")){
            //判断用户是否登陆
            Long uid = 0L;
            //如果登陆了替换为当前用户id
            if (user!=null){
                uid = user.getId();
            }
            //查询购物项
            try {
                List<ShopCart> shopCarts = cartService.showCart(uid);
                //设置到作用域并重定向到购物车页面
                session.setAttribute("shopCar",shopCarts);
                response.sendRedirect(request.getContextPath()+"/shopping.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        //把结果集转换为json发给页面
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
