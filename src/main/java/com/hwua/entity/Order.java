package com.hwua.entity;

import com.hwua.service.I.OrderDetailService;
import com.hwua.service.I.OrderService;
import com.hwua.service.OrderDetailServiceImpl;
import com.hwua.service.OrderServiceImpl;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/*订单类*/
@Table(name = "amz_order", schema = "d_shop", catalog = "")
public class Order {
    private long id;//订单id
    private long uid;//用户id
    private String uname;//用户姓名
    private String uaddress;//用户地址
    private Timestamp create_time;//订单创建时间
    private BigDecimal money;//订单金额
    private int status;//订单状态
    private int type;//订单类型
    private List<OrderDetail> odList;//订单明细
    private long order_number;//订单编号

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                ", uaddress='" + uaddress + '\'' +
                ", create_time=" + create_time +
                ", money=" + money +
                ", status=" + status +
                ", type=" + type +
                '}';
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uid")
    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "uname")
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Basic
    @Column(name = "uaddress")
    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    @Basic
    @Column(name = "money")
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order amzOrder = (Order) o;

        if (id != amzOrder.id) return false;
        if (uid != amzOrder.uid) return false;
        if (status != amzOrder.status) return false;
        if (type != amzOrder.type) return false;
        if (uname != null ? !uname.equals(amzOrder.uname) : amzOrder.uname != null) return false;
        if (uaddress != null ? !uaddress.equals(amzOrder.uaddress) : amzOrder.uaddress != null) return false;
        if (create_time != null ? !create_time.equals(amzOrder.create_time) : amzOrder.create_time != null) return false;
        if (money != null ? !money.equals(amzOrder.money) : amzOrder.money != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (uid ^ (uid >>> 32));
        result = 31 * result + (uname != null ? uname.hashCode() : 0);
        result = 31 * result + (uaddress != null ? uaddress.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + type;
        return result;
    }

    /**
     * 通过订单id获取订单明细
     * @return
     */
    public List<OrderDetail> getOdList() {
        odList = null;
        OrderDetailService orderSvs = new OrderDetailServiceImpl();
        try {
            odList = orderSvs.queryOrderDetailByOid(getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return odList;
    }

    public void setOdList(List<OrderDetail> odList) {
        this.odList = odList;
    }

    public long getOrder_number() {
        return order_number;
    }

    public void setOrder_number(long order_number) {
        this.order_number = order_number;
    }
}
