package com.hwua.dao;

import com.hwua.dao.I.OrderDao;
import com.hwua.entity.Order;
import com.hwua.utils.DruidPool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static QueryRunner qr = new QueryRunner(DruidPool.getDruidDS());
    @Override
    public List<Order> queryOrderByUid(Long uid) throws SQLException {
        String sql = "select * from amz_order where uid = ? order by create_time desc ";
        return qr.query(sql,new BeanListHandler<>(Order.class),uid);
    }

    @Override
    public Integer insertOrder(Order order) throws SQLException {
        String sql = "insert amz_order values(null,?,?,?,?,?,1,1)";
        return qr.update(sql,order.getUid(),order.getUname(),order.getUaddress(),order.getCreate_time(),order.getMoney());
    }

}
