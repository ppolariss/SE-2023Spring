package com.fk.demo.dao;

import com.fk.demo.entity.Shop;
import com.fk.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopDao extends JpaRepository<Shop, Integer> {
    Shop findByShopId(int shopId);

    Shop findByUserId(int userId);

    Shop findByShopIdAndIsapprovedIsTrue(int shopId);

    Shop findByShopIdAndIsapprovedIsFalse(int shopId);

    Shop deleteByShopId(int shopId);

}
