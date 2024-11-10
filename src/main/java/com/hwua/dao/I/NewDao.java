package com.hwua.dao.I;

import com.hwua.entity.News;

import java.sql.SQLException;
import java.util.List;

public interface NewDao {
    public abstract List<News> queryNews() throws SQLException;//查询所有的新闻
    public abstract News queryNewById(Integer id) throws SQLException;//通过新闻编号查询新闻
}
