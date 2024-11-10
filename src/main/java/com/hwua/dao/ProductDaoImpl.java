package com.hwua.dao;

import com.hwua.dao.I.ProductDao;
import com.hwua.entity.Product;
import com.hwua.utils.DruidPool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    QueryRunner qr = new QueryRunner(DruidPool.getDruidDS());

    @Override
    public List<Product> queryAllProduct() throws SQLException {
        String sql = "select id,name,description,price,stock,major_id,minor_id,img_source from amz_product";
        return qr.query(sql,new BeanListHandler<>(Product.class));
    }

    @Override
    public List<Product> queryMaxProduct() throws SQLException {
        String sql = "select id,name,description,price,stock,major_id,minor_id,img_source from amz_product order by stock desc limit 0,6";
        return qr.query(sql,new BeanListHandler<>(Product.class));
    }



    @Override
    public List<Product> queryProductLimit(Integer start,Integer pageSize) throws SQLException {
        String sql = "select id,name,description,price,stock,major_id,minor_id,img_source from amz_product limit ?,?";
        return qr.query(sql,new BeanListHandler<>(Product.class),start,pageSize);
    }

    @Override
    public Product queryProductById(Integer id) throws SQLException {
        String sql = "select id,name,description,price,stock,major_id,minor_id,img_source from amz_product where id = ?";
        return qr.query(sql,new BeanHandler<>(Product.class),id);
    }

    @Override
    public Long queryProductCount() throws SQLException {
        String sql = "select count(*) from amz_product";
        return qr.query(sql,new ScalarHandler<>());
    }

    @Override
    public List<Product> queryProByParentId(Long parentId) throws SQLException {
        String sql = "select id,name,description,price,stock,major_id,minor_id,img_source from amz_product where major_id = ?";
        return qr.query(sql,new BeanListHandler<>(Product.class),parentId);
    }

    @Override
    public List<Product> queryProBySonId(Long sonId) throws SQLException {
        String sql = "select id,name,description,price,stock,major_id,minor_id,img_source from amz_product where minor_id = ?";
        return qr.query(sql,new BeanListHandler<>(Product.class),sonId);
    }

    @Override
    public List<Product> queryLikeByName(String proName) throws SQLException {
        String sql = "select id,name,description,price,stock,major_id,minor_id,img_source from amz_product where 1=1 ";
        StringBuilder sb = new StringBuilder();
        if (proName!=""){
            for (int i = 0; i < proName.length(); i++) {
                if (i==0) {
                    sb.append("'%");
                }
                sb.append(proName.charAt(i));
                sb.append("%");
                if (i==(proName.length()-1)){
                    sb.append("'");
                }
            }
            sql = sql+"and name like "+sb;
        }
        return qr.query(sql,new BeanListHandler<>(Product.class));
    }

    @Override
    public Integer delProStock(Long id, Long pnum) throws SQLException {
        String sql = "UPDATE amz_product SET stock = ? WHERE id = ? ";
        return qr.update(sql,pnum,id);
    }


}
