package com.example.ebookbackend.controller;

import com.example.ebookbackend.domain.Cart;
import com.example.ebookbackend.domain.Result;
import com.example.ebookbackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, methods = {
        RequestMethod.GET, RequestMethod.POST,
        RequestMethod.PUT, RequestMethod.DELETE
}, allowCredentials = "true")
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping("/user/cart/{uid}")
    public Result getAllCartsByUid(@PathVariable(name = "uid") int uid) {
        return Result.success(cartService.getAllCartsByUid(uid));
    }

    @RequestMapping("/cart/remove/{cid}")
    public Result removeCartByCid(@PathVariable(name = "cid") int cid) {
        cartService.removeCart(cid);
        return Result.success(1);
    }

    @RequestMapping("/cart/remove/all/{uid}")
    public Result removeAllCart(@PathVariable(name = "uid") int uid) {
        cartService.removeAllCart(uid);
        return Result.success("清空成功");
    }

    @RequestMapping("/cart/update/single/number")
    public Result updateSingleCartNumber(@RequestBody Cart cart) {
        cartService.updateSingleCartNumber(cart);
        return Result.success("修改成功");
    }

    @RequestMapping("/cart/update/all/number")
    public Result updateAllCartNumber(@RequestBody List<Cart> carts) {
        cartService.updateAllCartNumber(carts);
        return Result.success("修改成功");
    }
}
