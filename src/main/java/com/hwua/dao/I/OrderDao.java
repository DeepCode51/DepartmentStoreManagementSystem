package com.hwua.dao.I;

import com.hwua.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    public abstract List<Order> queryOrderByUid(Long uid) throws SQLException;//通过用户id查询订单信息
    public abstract Integer insertOrder(Order order) throws SQLException;//添加订单信息
}
