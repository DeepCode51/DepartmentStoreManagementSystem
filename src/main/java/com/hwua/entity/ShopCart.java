package com.hwua.entity;

import com.hwua.service.I.ProductService;
import com.hwua.service.ProductServiceImpl;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

//购物项
@Table(name = "amz_shop_cart", schema = "d_shop", catalog = "")
public class ShopCart {
    private long id;//购物车id
    private Integer pid;//商品id
    private int pnum;//商品数量
    private long uid;//用户id
    private Product pro;//商品信息
    private BigDecimal subtotal;//小计

    @Override
    public String toString() {
        return "ShopCart{" +
                "id=" + id +
                ", pid=" + pid +
                ", pnum=" + pnum +
                ", uid=" + uid +
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
    @Column(name = "pid")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "pnum")
    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    @Basic
    @Column(name = "uid")
    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopCart that = (ShopCart) o;

        if (id != that.id) return false;
        if (pid != that.pid) return false;
        if (pnum != that.pnum) return false;
        if (uid != that.uid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (pid ^ (pid >>> 32));
        result = 31 * result + pnum;
        result = 31 * result + (int) (uid ^ (uid >>> 32));
        return result;
    }

    /**
     * 获取对应id的商品
     * @return
     */
    public Product getPro() {
        pro = null;
        //通过id获取商品信息
        ProductService proSvs = new ProductServiceImpl();
        try {
            pro =  proSvs.queryProductById(getPid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pro;
    }

    public void setPro(Product pro) {
        this.pro = pro;
    }

    /**
     * 商品小计 = 商品数量*单价
     * @return
     */
    public BigDecimal getSubtotal() {
        //获得商品单价
        BigDecimal price = getPro().getPrice();
        //商品数量*单价
        subtotal = price.multiply(new BigDecimal(pnum));
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
