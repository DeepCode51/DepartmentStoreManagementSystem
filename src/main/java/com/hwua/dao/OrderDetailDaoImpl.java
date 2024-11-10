package com.hwua.dao;

import com.hwua.dao.I.OrderDetailDao;
import com.hwua.entity.OrderDetail;
import com.hwua.utils.DruidPool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    private static QueryRunner qr = new QueryRunner(DruidPool.getDruidDS());
    @Override
    public List<OrderDetail> queryOrderDetailByOid(Long oid) throws SQLException {
        String sql = "select id,oid,pid,quantity,money from amz_order_detail where oid = ?";
        return qr.query(sql,new BeanListHandler<>(OrderDetail.class),oid);
    }

    @Override
    public Integer insertOrderDetail(OrderDetail orderDetail) throws SQLException {
        String sql = "insert amz_order_detail values(null,?,?,?,?)";
        return qr.update(sql,orderDetail.getOid(),orderDetail.getPid(),orderDetail.getQuantity(),orderDetail.getMoney());
    }
}
