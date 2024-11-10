package com.hwua.service.I;

import com.hwua.entity.Order;
import com.hwua.entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;


public interface OrderService  {
    public abstract List<Order> queryOrderByUid(Long uid) throws SQLException;//通过用户id查询订单信息
    public abstract Boolean insertOrder(Order order)throws SQLException;//插入订单和订单明细
}
