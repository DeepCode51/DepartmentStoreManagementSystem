package com.hwua.service;


import com.hwua.dao.CommentDaoImpl;
import com.hwua.dao.I.CommentDao;
import com.hwua.entity.Comment;
import com.hwua.service.I.CommentService;


import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao = null;
    public CommentServiceImpl(){
        commentDao = new CommentDaoImpl();
    }
    /**
     * 查询所有留言
     * @return
     * @throws SQLException
     */
    @Override
    public List<Comment> QueryAllComment() throws SQLException {
        return commentDao.QueryAllComment();
    }

    @Override
    public Integer addComment(Comment comment) throws SQLException {
        return commentDao.addComment(comment);
    }

    /**
     * 分页查询
     * @param start
     * @param number
     * @return
     * @throws SQLException
     */
    @Override
    public List<Comment> querypageComment(Integer start, Integer number) throws SQLException {
        return commentDao.querypageComment(start,number );
    }

    /**  @Override
     * 查询留言个数
     * @return
     * @throws SQLException
     */
    public Long queryCount() throws SQLException {
        return commentDao.queryCount();
    }

}
