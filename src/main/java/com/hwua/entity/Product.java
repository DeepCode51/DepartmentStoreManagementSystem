package com.hwua.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/*商品类*/
@Table(name = "amz_product", schema = "d_shop", catalog = "")
public class Product {
    private long id;//商品id
    private String name;//商品名
    private String description;//商品描述
    private BigDecimal price;//商品价格
    private long stock;//商品库存
    private long major_id;//一级标题
    private long minor_id;//二级标题
    private String img_source;//商品图片
    private BigDecimal maxPrice;//商品最大金额

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", major_id=" + major_id +
                ", minor_id=" + minor_id +
                ", img_source='" + img_source + '\'' +
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "stock")
    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "major_id")
    public long getMajor_id() {
        return major_id;
    }

    public void setMajor_id(long major_id) {
        this.major_id = major_id;
    }

    @Basic
    @Column(name = "minor_id")
    public long getMinor_id() {
        return minor_id;
    }

    public void setMinor_id(long minor_id) {
        this.minor_id = minor_id;
    }

    @Basic
    @Column(name = "img_source")
    public String getImg_source() {
        return img_source;
    }

    public void setImg_source(String img_source) {
        this.img_source = img_source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product that = (Product) o;

        if (id != that.id) return false;
        if (stock != that.stock) return false;
        if (major_id != that.major_id) return false;
        if (minor_id != that.minor_id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (img_source != null ? !img_source.equals(that.img_source) : that.img_source != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (int) (stock ^ (stock >>> 32));
        result = 31 * result + (int) (major_id ^ (major_id >>> 32));
        result = 31 * result + (int) (minor_id ^ (minor_id >>> 32));
        result = 31 * result + (img_source != null ? img_source.hashCode() : 0);
        return result;
    }

    /**
     * 商品最大金额 = 库存*单价
     * @return
     */
    public BigDecimal getMaxPrice() {
        return maxPrice = price.multiply(new BigDecimal(stock));

    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }
}
