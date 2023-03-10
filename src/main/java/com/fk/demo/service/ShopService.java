package com.fk.demo.service;

import com.fk.demo.dao.ShopDao;
import com.fk.demo.entity.Shop;
import com.fk.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    ShopDao shopDao;

    public boolean isIdExist(int id) {
        Shop shop = shopDao.findByUserId(id);
        return null != shop;
    }

    public void addOrUpdate(Shop shop) {
        shopDao.save(shop);
    }

    public Shop getByShopIdAndIsapprovedIsTrue(int shopId) {
        return shopDao.findByShopIdAndIsapprovedIsTrue(shopId);
    }

    public Shop getByShopId(int shopId) {
        return shopDao.findByShopId(shopId);
    }

    public Shop getByShopIdAndIsapprovedIsFalse(int shopId) {
        return shopDao.findByShopIdAndIsapprovedIsFalse(shopId);
    }

    public void deleteByShopId(int shopId) {
        shopDao.deleteByShopId(shopId);
    }
}
