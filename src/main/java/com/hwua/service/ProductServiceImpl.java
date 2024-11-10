package com.hwua.service;

import com.hwua.dao.I.ProductDao;
import com.hwua.dao.ProductDaoImpl;
import com.hwua.entity.Product;
import com.hwua.service.I.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static ProductDao ps = new ProductDaoImpl();

    /**
     * 查询商品结果集
     * @return
     * @throws SQLException
     */
    @Override
    public List<Product> queryAllProduct() throws SQLException {
        return ps.queryAllProduct();
    }

    /**
     * 查询库存最多的前6个商品
     * @return
     * @throws SQLException
     */
    @Override
    public List<Product> queryMaxProduct() throws SQLException {
        return ps.queryMaxProduct();
    }

    /**
     * 分页查询
     * @param start
     * @param pageSize
     * @return
     * @throws SQLException
     */
    @Override
    public List<Product> queryProductLimit(Integer start, Integer pageSize) throws SQLException {
        return ps.queryProductLimit(start,pageSize);
    }

    /**
     * 通过id查询商品
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Product queryProductById(Integer id) throws SQLException {
        return ps.queryProductById(id);
    }

    /**
     * 查询商品数量
     * @return
     * @throws SQLException
     */
    @Override
    public Long queryProductCount() throws SQLException {
        return ps.queryProductCount();
    }

    @Override
    public List<Product> queryProByParentId(Long parentId) throws SQLException {
        return ps.queryProByParentId(parentId);
    }

    @Override
    public List<Product> queryProBySonId(Long sonId) throws SQLException {
        return ps.queryProBySonId(sonId);
    }

    @Override
    public List<Product> queryLikeByName(String proName) throws SQLException {
        return ps.queryLikeByName(proName);
    }

    @Override
    public Integer delProStock(Long id, Long pnum) throws SQLException {
        return ps.delProStock(id,pnum);
    }

}
