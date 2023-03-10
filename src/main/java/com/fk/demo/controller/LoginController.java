package com.fk.demo.controller;

import com.fk.demo.entity.Shop;
import com.fk.demo.entity.ShopWithIdcard;
import com.fk.demo.entity.User;
import com.fk.demo.result.Result;
import com.fk.demo.service.ShopService;
import com.fk.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;

import java.util.Map;
import java.util.Objects;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    ShopService shopService;

    @CrossOrigin
    @PostMapping(value = "api/login")

    public Result login(@RequestBody User requestUser) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.getUsernameAndPassword(username, requestUser.getPassword());
        if (null == user) {
            return new Result(400);
        } else {
            return new Result(200);
        }
    }


    @CrossOrigin
    @PostMapping(value = "api/register")

    public Result register(@RequestBody User requestUser) {
//        对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        //其他是否需要转换

        //这里写条件控制

        //四个分开是为了实时显示每一模块有无被使用
        //待重构
        if (userService.isEmailExist(requestUser.getEmail())) {
            return new Result(400);
        }
        if (userService.isPhoneExist(requestUser.getPhone())) {
            return new Result(400);
        }
        if (userService.isIdcardExist(requestUser.getIdcard())) {
            return new Result(400);
        }
        if (userService.isUsernameExist(username)) {
            return new Result(400);
        }

        userService.addOrUpdate(requestUser);
        return new Result(200);
    }


    @CrossOrigin
    @PostMapping(value = "api/apply")

    public Result applyForShop(@RequestBody ShopWithIdcard requestShop) {
        String shopname = requestShop.getShopName();
        shopname = HtmlUtils.htmlEscape(shopname);
        //其他是否需要转换

        //这里写条件控制

        String idcard = requestShop.getIdcard();
        User user = userService.getByIdcard(idcard);
        if (null == user || !user.getIsSeller()) {
            return new Result(400);
        }
        if (shopService.isIdExist(user.getId())) {
            return new Result(400);
        }

        Shop shop = new Shop(requestShop);
        shop.setUserId(user.getId());
        shop.setRegisterDate();
        shopService.addOrUpdate(shop);

        return new Result(200);
    }


    @CrossOrigin
    @PostMapping(value = "api/approve")

    public Shop[] displayNotApproved(@RequestBody int displayNumber) {
        //每页展示数量

        Shop[] shops = new Shop[displayNumber];
        for (int nextShopId = 1, thisShopId = 0; thisShopId < displayNumber; nextShopId++) {
            Shop shop = shopService.getByShopIdAndIsapprovedIsFalse(nextShopId);
            if (shop != null) {
                shops[thisShopId++] = shop;
            }
            //还要找一个id上界，避免溢出
        }

        return shops;
    }


    @CrossOrigin
    @PostMapping(value = "api/approve/display")

    public Result approveForShop(@RequestBody Map<Integer, Integer> map) {
        boolean isapproved = false;
        if (map.get(0) == 1) {
            isapproved = true;
        }
        int shopId = map.get(1);
        Shop shop = shopService.getByShopId(shopId);
        //保险起见
        if (shop != null) {
            //同意修改 不同意删除
            if (isapproved) {
                shop.setApproved();
                shopService.addOrUpdate(shop);
            } else {
                shopService.deleteByShopId(shopId);
            }
        }
        return new Result(200);
    }

    @CrossOrigin
    @PostMapping(value = "api/display")

    public Shop[] displayShop(@RequestBody int displayNumber) {
        //每页展示数量

        Shop[] shops = new Shop[displayNumber];
        for (int nextShopId = 1, thisShopId = 0; thisShopId < displayNumber; nextShopId++) {
            Shop shop = shopService.getByShopIdAndIsapprovedIsTrue(nextShopId);
            if (shop != null) {
                shops[thisShopId++] = shop;
            }
        }

        return shops;
    }
}
//    public Result login(@RequestBody User requestUser) {
//        // 对 html 标签进行转义，防止 XSS 攻击
//        String username = requestUser.getUsername();
//        username = HtmlUtils.htmlEscape(username);
//
//        if (!Objects.equals("admin", username) || !Objects.equals("123456", requestUser.getPassword())) {
//            String message = "账号密码错误";
//            System.out.println("test");
//            return new Result(400);
//        } else {
//            return new Result(200);
//        }
//    }
