package com.hwua.service.I;

import com.hwua.entity.ShopCart;

import java.sql.SQLException;
import java.util.List;

public interface ShopCartService {
    public abstract boolean addProduct(ShopCart shopCart) throws SQLException;//添加商品
    public abstract boolean delProduct(Long id) throws SQLException;//删除商品
    public abstract boolean updateNum(int pnum,Long id) throws SQLException;//修改商品数量
    public abstract List<ShopCart> showCart(Long uid) throws SQLException;//显示购物车明细
    public abstract boolean clearShopCart(Long uid) throws SQLException;//请空购物车
    public abstract ShopCart queryShopCart(Long id)throws SQLException;//根据指定购物项id查询
    public abstract Integer updateUid(Long loginID) throws SQLException;//更改用户id
}
