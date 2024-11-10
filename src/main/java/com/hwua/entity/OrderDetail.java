package com.hwua.entity;

import com.hwua.service.I.ProductService;
import com.hwua.service.ProductServiceImpl;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.SQLException;

/*OrderDetail订单明细*/
@Table(name = "amz_order_detail", schema = "d_shop", catalog = "")
public class OrderDetail {
    private long id;//订单明细id
    private long oid;//订单编号
    private long pid;//商品id
    private long quantity;//订单总数量
    private BigDecimal money;//订单总金额
    private Product product;//商品信息

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", oid=" + oid +
                ", pid=" + pid +
                ", quantity=" + quantity +
                ", money=" + money +
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
    @Column(name = "oid")
    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    @Basic
    @Column(name = "pid")
    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "quantity")
    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "money")
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetail that = (OrderDetail) o;

        if (id != that.id) return false;
        if (oid != that.oid) return false;
        if (pid != that.pid) return false;
        if (quantity != that.quantity) return false;
        if (money != null ? !money.equals(that.money) : that.money != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (oid ^ (oid >>> 32));
        result = 31 * result + (int) (pid ^ (pid >>> 32));
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        result = 31 * result + (money != null ? money.hashCode() : 0);
        return result;
    }

    /**
     * 通过id获取商品信息
     * @return
     */
    public Product getProduct() {
        product = null;
        //通过id获取商品信息
        ProductService proSvs = new ProductServiceImpl();
        try {
            product =  proSvs.queryProductById((int)getPid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
