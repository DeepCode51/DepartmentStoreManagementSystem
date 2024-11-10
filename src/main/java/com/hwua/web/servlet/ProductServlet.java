package com.hwua.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwua.entity.Paging;
import com.hwua.entity.Product;
import com.hwua.entity.ProductCategory;
import com.hwua.service.I.ProductCategoryService;
import com.hwua.service.I.ProductService;
import com.hwua.service.ProductCategoryServiceImpl;
import com.hwua.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/Product")
public class ProductServlet extends HttpServlet {
    private static ProductCategoryService pcs;
    private static ObjectMapper mapper ;
    private static ProductService ps;


    @Override
    public void init() throws ServletException {
        pcs = new ProductCategoryServiceImpl();
        mapper = new ObjectMapper();
        ps = new ProductServiceImpl();
        super.init();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,Object> map = new HashMap<>();
        String param = request.getParameter("param");
        //查询商品分类
        if (param.equals("queryProductCategory")){
            try {
                //查询一级分类
                List<ProductCategory> parentList = pcs.queryParent();
                //把一级分类放入到map中
                map.put("parent",parentList);

                //查询一级分类的id
                List<Long> listById = pcs.queryParentById();
                //通过一级分类id查询出二级分类
                for (Long id : listById){
                    List<ProductCategory> sonList = pcs.querySon(id);
                    //把二级分类都放入到map中
                    map.put(id.toString(),sonList);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else if (param.equals("queryMaxProduct")){//查询热卖商品
            try {
                //得到热卖商品结果集
                List<Product> list = ps.queryMaxProduct();
                //放入map集合中
                map.put("list",list);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (param.equals("paging")){//分页查询
            Integer currentPage = Integer.parseInt(request.getParameter("currentPage"));//当前页的页数
            Integer pageSize = Integer.parseInt(request.getParameter("PageSize"));//每一页的记录
            Paging page = new Paging();
            List<Product> pageList = null;
            Long totalNumber = 0L;
            try {
                //进行分页查询得到结果集
                pageList = ps.queryProductLimit((currentPage - 1) * pageSize, pageSize);
                //查询出总记录
                totalNumber = ps.queryProductCount();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //把数据封装成实体类
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);
            page.setList(pageList);
            page.setTotalNumber(totalNumber);
            map.put("paging",page);
        }else if (param.equals("productById")){//通过id查询商品详情
            String id = request.getParameter("id");
            //父类名
            String parName = "";
            //子类名
            String sonName = "";
            //父子类id
            Long parId = 0l;
            Long sonId  = 0l;
            try {
                //进行id查询商品
                Product product = ps.queryProductById(Integer.parseInt(id));
                //设置父子类id
                parId = product.getMajor_id();
                sonId = product.getMinor_id();
                //通过父子类id查询父子类名
                parName = pcs.queryProName(parId);
                sonName = pcs.queryProName(sonId);
                //把数据放入map
                map.put("productById",product);
                //把父类名和子类名传入集合
                map.put("parentName",parName);
                map.put("sonName",sonName);
                map.put("parId",parId);
                map.put("sonId",sonId);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //获取最近浏览记录
            //进行字符串拼接
            String browseId = getBrowseId(id, request);
            //把拼接的字符串传到Cookie
            Cookie cookie = new Cookie("browseId", browseId);
            cookie.setMaxAge(60*60*24);//让cookie保存一天
            response.addCookie(cookie);//把cookie放到响应头中

        }else if (param.equals("prcLook")){//打印最近浏览记录
            Cookie[] cookies = request.getCookies();
            String browseId = null;
            boolean flag = false;//默认第一次访问
            if (cookies!=null){

                for (Cookie cookie : cookies) {
                    //如果找到浏览记录
                    if (cookie.getName().equals("browseId")){
                        flag = true;//改为非第一次
                        //通过K找到V
                        browseId = cookie.getValue();
                    }
                }
            }

            //如果是第一次访问，没有记录自己返回
            if (cookies==null || flag==false){
                return;
            }

            //进行字符串切割,获取商品id数组
            String[] split = browseId.split("-");
            //通过id获取商品并加到list中
            List<Product> list = new ArrayList<>();
            for (String id : split) {
                try {
                    Product product = ps.queryProductById(Integer.parseInt(id));
                    list.add(product);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            map.put("lookList",list);
        }
        //把map转换成json写入到页面
        mapper.writeValue(response.getWriter(),map);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //字符串拼接
    public static String getBrowseId(String id,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String browseId = null;
        boolean flag = false;//默认第一次访问

        if (cookies!=null){
            for (Cookie cookie : cookies) {
                //如果在cookies中找到k就说明不是第一次访问
                if (cookie.getName().equals("browseId")){
                    flag = true;//改为非第一次
                    //通过K获得V
                    browseId = cookie.getValue();
                    break;
                }
            }
        }

        //如果第一次访问直接返回id
        if (cookies==null || flag==false){
            return id;
        }

        //进行字符串切割
        String[] split = browseId.split("-");
        //因为要进行增删所有变成集合进行操作
        LinkedList<String> list = new LinkedList<>(Arrays.asList(split));
        //如果浏览记录小于3
        if (list.size()<3){
            //如果出现重复的就删除之前的
            if (list.contains(id)){
               list.remove(id);
           }
        }

        //如果浏览记录等于3
        if (list.size()==3){
            //如果有重复的就删除重复的，否则删除最后一个
            if (list.contains(id)){
                list.remove(id);
            }else{
                list.removeLast();
            }
        }
        //最后一定会添加当前的id
        list.addFirst(id);

        //进行字符串拼接
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            //第一个下标永远不加"-"
            if (i>0){
                sb.append("-");
            }
            sb.append(list.get(i));
        }

        return sb.toString();
    }
}
