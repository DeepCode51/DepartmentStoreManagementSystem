package com.hwua.service;

import com.hwua.dao.I.ShopCartDao;
import com.hwua.dao.ShopCartDaoImpl;
import com.hwua.entity.ShopCart;
import com.hwua.service.I.ShopCartService;

import java.sql.SQLException;
import java.util.List;

public class ShopCartServiceImpl implements ShopCartService {
    private static ShopCartDao cartDao = new ShopCartDaoImpl();

    /**
     * 添加商品
     * @param shopCart
     * @return
     * @throws SQLException
     */
    @Override
    public boolean addProduct(ShopCart shopCart) throws SQLException {
        //通过uid和pid查询到他的购物项
        ShopCart userCar = cartDao.queryShopCart(shopCart.getPid(), shopCart.getUid());
        int sum = 0;
        int num;
        //先判断这个商品已存在，如果存在就增加数量
        if (userCar!=null){
            sum = shopCart.getPnum()+userCar.getPnum();
            num = cartDao.updateNum(sum,userCar.getId());
        }else {
            num  = cartDao.addProduct(shopCart);
        }
        if (num >0 ){
            return true;
        }

        return false;
    }

    /**
     * 删除购物项
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public boolean delProduct(Long id) throws SQLException {
        Integer num = cartDao.delProduct(id);
        if (num >0 ){
            return true;
        }
        return false;
    }

    /**
     * 修改购物项的数量
     * @param pnum
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public boolean updateNum(int pnum, Long id) throws SQLException {
        Integer num = cartDao.updateNum(pnum, id);
        if (num >0 ){
            return true;
        }
        return false;
    }

    /**
     * 显示用户购物车
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public List<ShopCart> showCart(Long uid) throws SQLException {
        return cartDao.showCart(uid);
    }

/*    public static void main(String[] args) throws SQLException {
        ShopCartServiceImpl s = new ShopCartServiceImpl();
        List<ShopCart> shopCarts = s.showCart(1L);
        System.out.println(shopCarts);
    }*/
    /**
     * 清空购物车
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public boolean clearShopCart(Long uid) throws SQLException {
        Integer num = cartDao.clearShopCart(uid);
        if (num >0 ){
            return true;
        }
        return false;
    }

    /**
     * 查询指定购物项
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public ShopCart queryShopCart(Long id) throws SQLException {
        return cartDao.queryShopCart(id);
    }

    /**
     * 更改用户id
     * @param loginID
     * @return
     * @throws SQLException
     */
    @Override
    public Integer updateUid(Long loginID) throws SQLException {
        return cartDao.updateUid(loginID);
    }
}
