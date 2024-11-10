package com.hwua.service;

import com.hwua.dao.I.NewDao;
import com.hwua.dao.NewDaoImpl;
import com.hwua.entity.News;
import com.hwua.service.I.NewService;

import java.sql.SQLException;
import java.util.List;

public class NewServiceImpl implements NewService {
    private static NewDao newDao = new NewDaoImpl();

    /**
     * 查询所有新闻
     * @return
     * @throws SQLException
     */
    @Override
    public List<News> queryNews() throws SQLException {
        return newDao.queryNews();
    }

    /**
     * 通过id查询新闻
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public News queryNewById(Integer id) throws SQLException {
        return newDao.queryNewById(id);
    }
}
