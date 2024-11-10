package com.hwua.service.I;

import com.hwua.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
    /**
     * 查询所有留言
     * @return
     * @throws SQLException
     */
    public List<Comment> QueryAllComment() throws SQLException;

    /**
     * 添加留言
     * @param comment
     * @return
     * @throws SQLException
     */
    public Integer addComment(Comment comment) throws SQLException;

    /**
     * 分页查询
     * @param start
     * @param number
     * @return
     * @throws SQLException
     */
    public List<Comment> querypageComment(Integer start, Integer number) throws SQLException;

    /**
     * 查询留言个数
     * @return
     * @throws SQLException
     */
    public Long queryCount() throws SQLException;
}
