package com.hwua.service;

import com.hwua.dao.I.OrderDetailDao;
import com.hwua.dao.OrderDetailDaoImpl;
import com.hwua.entity.OrderDetail;
import com.hwua.service.I.OrderDetailService;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {
    private static OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();

    /**
     * 通过订单id查询订单明细
     * @param oid
     * @return
     * @throws SQLException
     */
    @Override
    public List<OrderDetail> queryOrderDetailByOid(Long oid) throws SQLException {
        return orderDetailDao.queryOrderDetailByOid(oid);
    }

    /**
     * 添加订单明细
     * @param orderDetail
     * @return
     * @throws SQLException
     */
    @Override
    public Integer insertOrderDetail(OrderDetail orderDetail) throws SQLException {
        return orderDetailDao.insertOrderDetail(orderDetail);
    }
}
