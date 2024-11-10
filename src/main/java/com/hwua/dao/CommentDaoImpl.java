package com.hwua.dao;


import com.hwua.dao.I.CommentDao;
import com.hwua.entity.Comment;

import com.hwua.utils.DruidPool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private  QueryRunner queryRunner = new QueryRunner(DruidPool.getDruidDS());
    @Override
    public List<Comment> QueryAllComment() throws SQLException {
         String sql= "select id, reply, content, create_time, reply_time, nick_name, state from  amz_comment order by  create_time desc  ";
         return  queryRunner.query(sql,new BeanListHandler<>(Comment.class));
    }

    /**
     * 添加留言
     * @param comment
     * @return
     */
    @Override
    public Integer addComment(Comment comment) throws SQLException {
        String sql = "insert into amz_comment ( reply, content, create_time, reply_time, nick_name) VALUES (?,?,?,?,?)";
        return queryRunner.update(sql,comment.getReply(),comment.getContent(),comment.getCreate_Time(),comment.getReply_Time(),comment.getNick_Name() );
    }

    @Override
    public List<Comment> querypageComment(Integer start, Integer number) throws SQLException {
        String sql = "select id, reply, content, create_time, reply_time, nick_name, state from  amz_comment order by  create_time desc limit ?,?";
        return queryRunner.query(sql,new BeanListHandler<>(Comment.class),start,number);
    }

    /**
     * 查询留言个数
     * @return
     */
    @Override
    public Long queryCount() throws SQLException {
        String sql = "select count(id) from amz_comment";
        return queryRunner.query(sql,new ScalarHandler<>());
    }
}
