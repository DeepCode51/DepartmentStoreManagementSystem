package com.hwua.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwua.entity.Product;
import com.hwua.service.I.ProductCategoryService;
import com.hwua.service.I.ProductService;
import com.hwua.service.ProductCategoryServiceImpl;
import com.hwua.service.ProductServiceImpl;

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

@WebServlet("/proCategory")
public class ProCategoryServlet extends HttpServlet {
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
        //查询二级类目的商品
        if (param.equals("querySonId")){
            //父类名
            String parName = "";
            //子类名
            String sonName = "";
            //父子类id
            Long parId = 0l;
            Long sonId  = 0l;
            //获取二级类目ID
            String str = request.getParameter("id");
            long id = Long.parseLong(str);
            try {
                //获取父类id
                List<Long> parentId = pcs.queryParentById();
                List<Product> list = null;
                //如果传过来的是一级分类ID就打印一级分类集合
                if (parentId.contains(id)){
                    list = ps.queryProByParentId(id);
                    //查询分类名
                    parName = pcs.queryProName(id);
                    //设置父类id
                    parId = id;
                }else {//否则打印二级分类
                    //得到对应ID的结果集
                    list = ps.queryProBySonId(id);
                    //通过子类id查询父类的id
                    sonId = id;
                    parId = pcs.queryParentId(id);
                    //查询分类名
                    parName = pcs.queryProName(parId);
                    sonName = pcs.queryProName(id);
                }
                    map.put("sonList",list);
                    //把父类名和子类名传入集合
                    map.put("parentName",parName);
                    map.put("sonName",sonName);
                    map.put("parId",parId);
                    map.put("sonId",sonId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }else if (param.equals("queryProList")){//进行模糊查询
            String qname = request.getParameter("qname");
            System.out.println(qname);
            //父类名
            String parName = "";
            //子类名
            String sonName = "";
            //父子类id
            Long parId = 0L;
            Long sonId  = 0L;
            try {
                //得到模糊查询结果集
                List<Product> list = ps.queryLikeByName(qname);
                if (list!=null) {
                    //得到集合中第一个父子类id
                    Product product = list.get(0);
                    parId = product.getMajor_id();
                    sonId = product.getMinor_id();
                    //查询分类名
                    parName = pcs.queryProName(parId);
                    sonName = pcs.queryProName(sonId);
                }
                map.put("sonList",list);
                //把父类名和子类名传入集合
                map.put("parentName",parName);
                map.put("sonName",sonName);
                map.put("parId",parId);
                map.put("sonId",sonId);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
