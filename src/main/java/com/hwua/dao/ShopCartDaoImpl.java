package com.hwua.dao;

import com.hwua.dao.I.ShopCartDao;
import com.hwua.entity.ShopCart;
import com.hwua.utils.DruidPool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ShopCartDaoImpl implements ShopCartDao {
    private static QueryRunner qr = new QueryRunner(DruidPool.getDruidDS());
    @Override
    public Integer addProduct(ShopCart shopCart) throws SQLException {
        String sql = "insert amz_shop_cart values(null,?,?,?)";
        return qr.update(sql,shopCart.getPid(),shopCart.getPnum(),shopCart.getUid());
    }

    @Override
    public Integer delProduct(Long id) throws SQLException {
        String sql = "delete from amz_shop_cart where id = ?";
        return qr.update(sql,id);
    }

    @Override
    public Integer updateNum(int pnum,Long id) throws SQLException {
        String sql = "update amz_shop_cart set pnum = ? where id = ?";
        return qr.update(sql,pnum,id);
    }

    @Override
    public List<ShopCart> showCart(Long uid) throws SQLException {
        String sql = "select id,pid,pnum,uid from amz_shop_cart where uid = ?";
        return qr.query(sql, new BeanListHandler<>(ShopCart.class),uid);
    }

    @Override
    public Integer clearShopCart(Long uid) throws SQLException {
        String sql = "delete from amz_shop_cart where uid = ?";
        return qr.update(sql,uid);
    }

    @Override
    public ShopCart queryShopCart(Integer pid, Long uid) throws SQLException {
        String sql = "select id,pid,pnum,uid from amz_shop_cart where pid = ? and uid = ?";
        return qr.query(sql, new BeanHandler<>(ShopCart.class),pid,uid);
    }

    @Override
    public ShopCart queryShopCart(Long id) throws SQLException {
        String sql = "select id,pid,pnum,uid from amz_shop_cart where id = ?";
        return qr.query(sql, new BeanHandler<>(ShopCart.class),id);
    }

    @Override
    public Integer updateUid(Long loginID) throws SQLException {
        String sql = "update amz_shop_cart set uid = ? where uid = 0";
        return qr.update(sql,loginID);
    }

}
