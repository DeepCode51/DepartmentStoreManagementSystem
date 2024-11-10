package com.hwua.dao.I;

import com.hwua.entity.Comment;

import java.sql.SQLException;
import java.util.List;

/*留言*/
public interface CommentDao {
    /**
     * 查询所有评论
     * @return
     */
    public List<Comment> QueryAllComment() throws SQLException;

    /**
     * 添加留言
     * @param comment
     * @return
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
     */
    public Long queryCount() throws SQLException;
}
