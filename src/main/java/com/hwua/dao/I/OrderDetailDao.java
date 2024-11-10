package com.hwua.dao.I;

import com.hwua.entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDao {
    public abstract List<OrderDetail> queryOrderDetailByOid(Long oid) throws SQLException;//通过订单id查询订单明细
    public abstract Integer insertOrderDetail(OrderDetail orderDetail) throws SQLException;//插入订单明细
}
