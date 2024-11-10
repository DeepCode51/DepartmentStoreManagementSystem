package com.hwua.dao.I;

import com.hwua.entity.ProductCategory;

import java.sql.SQLException;
import java.util.List;

public interface ProductCategoryDao {
    //查询一级产品分类
    public abstract List<ProductCategory> queryParent() throws SQLException;
    //查询一级产品分类的id
    public abstract List<Long> queryParentById() throws SQLException;
    //查询二级产品分类
    public abstract List<ProductCategory> querySon(Long id) throws SQLException;
    public abstract Long queryParentId(Long sonId) throws SQLException;//通过二级ID查询一级ID
    public abstract String queryProName(Long id) throws SQLException;//通过id查询分类名

}
