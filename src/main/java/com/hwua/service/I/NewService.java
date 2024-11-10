package com.hwua.service.I;

import com.hwua.entity.News;

import java.sql.SQLException;
import java.util.List;

public interface NewService {
    public abstract List<News> queryNews() throws SQLException;//查询所有的新闻
    public abstract News queryNewById(Integer id) throws SQLException;//通过新闻编号查询新闻
}
