package com.hwua.dao;

import com.hwua.dao.I.NewDao;
import com.hwua.entity.News;
import com.hwua.utils.DruidPool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class NewDaoImpl implements NewDao {
    private static QueryRunner qr = new QueryRunner(DruidPool.getDruidDS());
    @Override
    public List<News> queryNews() throws SQLException {
        String sql = "select id,title,content,create_time from amz_news";
        return qr.query(sql,new BeanListHandler<>(News.class));
    }

    @Override
    public News queryNewById(Integer id) throws SQLException {
        String sql = "select id,title,content,create_time from amz_news where id = ?";
        return qr.query(sql,new BeanHandler<>(News.class),id);
    }
}
