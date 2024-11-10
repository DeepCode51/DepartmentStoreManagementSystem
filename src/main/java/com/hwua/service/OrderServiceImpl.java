package com.hwua.service;

import com.hwua.dao.I.OrderDao;
import com.hwua.dao.I.OrderDetailDao;
import com.hwua.dao.OrderDaoImpl;
import com.hwua.dao.OrderDetailDaoImpl;
import com.hwua.entity.Order;
import com.hwua.entity.OrderDetail;
import com.hwua.service.I.OrderService;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static OrderDao orderDao = new OrderDaoImpl();

    /**
     * 查询订单
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public List<Order> queryOrderByUid(Long uid) throws SQLException {
        return orderDao.queryOrderByUid(uid);
    }


    @Override
    public Boolean insertOrder(Order order) throws SQLException{
        //插入订单。
        Integer integer = orderDao.insertOrder(order);
        if (integer>0){
            return true;
        }
        return false;
    }
}
