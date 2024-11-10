package com.hwua.dao.I;

import com.hwua.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    public abstract List<Product> queryAllProduct() throws SQLException;//查询所有商品
    public abstract List<Product> queryMaxProduct() throws SQLException;//查询库存最多的商品
    public abstract List<Product> queryProductLimit(Integer start,Integer pageSize) throws SQLException;//分页查询
    public abstract Product queryProductById(Integer id) throws SQLException;//通过id查询
    public abstract Long queryProductCount() throws SQLException;//查询出商品总记录数
    public abstract List<Product> queryProByParentId(Long parentId) throws SQLException;//通过一级ID查询商品
    public abstract List<Product> queryProBySonId(Long sonId) throws SQLException;//通过二级ID查询商品
    public abstract List<Product> queryLikeByName(String proName) throws SQLException;//通过字段查询到宝贝
    public abstract Integer delProStock(Long id,Long pnum) throws SQLException;//删除对应的库存
}
