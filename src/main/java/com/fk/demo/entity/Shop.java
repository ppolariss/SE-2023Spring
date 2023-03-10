package com.fk.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "shop")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private int shopId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "isapproved")
    private boolean isapproved;
    @Column(name = "shop_name")
    private String shopName;
    @Column(name = "tag")
    private String tag;
    @Column(name = "introduction")
    private String introduction;
    @Column(name = "address")
    private String address;
    @Column(name = "fund")
    private double fund;
    @Column(name = "register_date")
    private Date registerDate;
    //注册时间的理解


    public Shop() {
        isapproved = false;
        //初始化为false
    }

    public Shop(ShopWithIdcard shopWithIdcard) {
        isapproved = false;
        //初始化为false
        shopName = shopWithIdcard.getShopName();
        tag = shopWithIdcard.getTag();
        introduction = shopWithIdcard.getIntroduction();
        address = shopWithIdcard.getAddress();
    }


    public int getShopId() {
        return shopId;
    }


    public boolean getIsapproved() {
        return isapproved;
    }

    public void setApproved() {
        this.isapproved = true;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setRegisterDate() {
        this.registerDate = new Date();//初始化了吗
    }


    public double getFund() {
        return this.fund;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getAddress() {
        return address;
    }

    public String getTag() {
        return tag;
    }

    public String getIntroduction() {
        return introduction;
    }


}
