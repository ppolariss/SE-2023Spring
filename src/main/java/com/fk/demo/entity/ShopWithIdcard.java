package com.fk.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "shop")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ShopWithIdcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private int shopId;
//    @Column(name = "user_id")
//    private int userId;
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
    @Column(name = "registerDate")
    private Date registerDate;
    //注册时间的理解
    private String idcard;


    public ShopWithIdcard() {
        isapproved = false;
        //初始化为false
    }

    public String getIdcard() {
        return idcard;
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
