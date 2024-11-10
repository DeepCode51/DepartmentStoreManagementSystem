package com.hwua.service;

import com.hwua.dao.I.ProductCategoryDao;
import com.hwua.dao.ProductCategoryDaoImpl;
import com.hwua.entity.ProductCategory;
import com.hwua.service.I.ProductCategoryService;

import java.sql.SQLException;
import java.util.List;

public class ProductCategoryServiceImpl implements ProductCategoryService {
    private static ProductCategoryDao productDao = new ProductCategoryDaoImpl();

    /**
     * 查询一级商品分类
     * @return 结果集
     * @throws SQLException
     */
    @Override
    public List<ProductCategory> queryParent() throws SQLException {
        return productDao.queryParent();
    }

    /**
     * 查询一级分类的id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Long> queryParentById() throws SQLException {
        return productDao.queryParentById();
    }

    /**
     * 查询二级商品分类
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public List<ProductCategory> querySon(Long id) throws SQLException {
        List<ProductCategory> list = productDao.querySon(id);
        //把集合中的一级分类删除
        for (int i = 0; i < list.size(); i++) {
            //如果id是一级分类的id就删除这个元素
            if (list.get(i).getId()==id){
                list.remove(i);
            }
        }
        return list;
    }

    /**
     * 通过id查询父类id
     * @param sonId
     * @return
     * @throws SQLException
     */
    @Override
    public Long queryParentId(Long sonId) throws SQLException {
        return productDao.queryParentId(sonId);
    }

    /**
     * 通过id查询到分类名
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public String queryProName(Long id) throws SQLException {
        return productDao.queryProName(id);
    }
}
