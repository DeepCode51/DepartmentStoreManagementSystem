package com.hwua.web.servlet;

import com.alibaba.fastjson.JSON;

import com.hwua.dao.CommentDaoImpl;
import com.hwua.dao.I.CommentDao;

import com.hwua.entity.Comment;
import com.hwua.entity.Paging;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/com.do")
public class CommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentDao commentDao = new CommentDaoImpl();
        String param = request.getParameter("param");

        if (param.equals("queryCom")){//获取所有留言
            List<Comment> comList = null;
            Paging page = new Paging();
            /*获取每页个数*/
            Integer pagesize = Integer.parseInt(request.getParameter("pagesize"));
            /*获取当前页*/
            Integer currentPage = Integer.parseInt(request.getParameter("currentPage"));
            try {
                /*获取每页内容*/
                comList = commentDao.querypageComment((currentPage - 1) * pagesize, pagesize);
              /*  comList = commentDao*/
            } catch (SQLException e) {
                e.printStackTrace();
            }
            /*获取商品数量*/
            Long count = null;
            try {
                count = commentDao.queryCount();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            /*为page赋值*/
            page.setTotalNumber(count);
            page.setCurrentPage(currentPage);
            page.setPageSize(pagesize);
            page.setCList(comList);
            System.out.println(page.getCList());
            String str = JSON.toJSONString(page);
            response.getWriter().write(str);
        }else if (param.equals("addCom")){
            String guestName = request.getParameter("guestName");
            String guestTitle = request.getParameter("guestTitle");
            String guestContent = request.getParameter("guestContent");
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(date);/*将创建时间字符串化*/
            Comment comment = new Comment(guestTitle, guestContent, format, format, guestName, null);
            System.out.println(comment);
            try {
                Integer row = commentDao.addComment(comment);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath()+"/guestbook.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
