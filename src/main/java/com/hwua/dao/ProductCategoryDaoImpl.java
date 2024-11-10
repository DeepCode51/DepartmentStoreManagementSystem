package com.hwua.dao;

import com.hwua.dao.I.ProductCategoryDao;
import com.hwua.entity.ProductCategory;
import com.hwua.utils.DruidPool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductCategoryDaoImpl implements ProductCategoryDao {
    private static QueryRunner qr = new QueryRunner(DruidPool.getDruidDS());
    @Override
    public List<ProductCategory> queryParent() throws SQLException {
        String sql = "select DISTINCT a1.id,a1.`name`,a1.parent_id FROM amz_product_category a1,amz_product_category a2 WHERE a1.id = a2.parent_id";
        return qr.query(sql,new BeanListHandler<>(ProductCategory.class));
    }

    @Override
    public List<Long> queryParentById() throws SQLException {
        String sql = "select distinct parent_id from amz_product_category";
        return qr.query(sql,new ColumnListHandler<>());
    }

    @Override
    public List<ProductCategory> querySon(Long id) throws SQLException {
        String sql = "select id,name,parent_id from amz_product_category where parent_id = ?";
        return qr.query(sql,new BeanListHandler<>(ProductCategory.class),id);
    }

    @Override
    public Long queryParentId(Long sonId) throws SQLException {
        String sql = "select parent_id from amz_product_category where id = ?";
        return qr.query(sql,new ScalarHandler<>(),sonId);
    }

    @Override
    public String queryProName(Long id) throws SQLException {
        String sql = "select name from amz_product_category where id = ?";
        return qr.query(sql,new ScalarHandler<>(),id);
    }
}
