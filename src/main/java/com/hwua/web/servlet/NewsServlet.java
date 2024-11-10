package com.hwua.web.servlet;

import com.alibaba.fastjson.JSON;
import com.hwua.entity.News;
import com.hwua.service.I.NewService;
import com.hwua.service.NewServiceImpl;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {
    private static NewService newSvc = new NewServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");
        PrintWriter pw = response.getWriter();
        if (param.equals("queryNews")){
            //通过查询获得新闻结果集
            List<News> list = null;
            try {
                list = newSvc.queryNews();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //把结果集变成JSON传到页面
            String newList = JSON.toJSONString(list);
            pw.write(newList);
        }else if (param.equals("queryNewById")){//通过id查询新闻
            //获取id
            String id = request.getParameter("id");

            News news = new News();
            try {
                news = newSvc.queryNewById(Integer.parseInt(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (news!=null) {

                //把结果变成JSON传到页面
                String newsJson = JSON.toJSONString(news);
                pw.write(newsJson);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
