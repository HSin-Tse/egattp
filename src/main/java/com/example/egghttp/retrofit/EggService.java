package com.example.egghttp.retrofit;

import com.example.egghttp.retrofit.beans.BasicOb;
import com.example.egghttp.retrofit.beans.account.Merchant;
import com.example.egghttp.retrofit.beans.cart.Cart;
import com.example.egghttp.retrofit.beans.goods.Good;
import com.example.egghttp.retrofit.beans.member.Coupon;
import com.example.egghttp.retrofit.beans.member.Member;
import com.example.egghttp.retrofit.beans.order.LineOrder;
import com.example.egghttp.retrofit.beans.order.Order;
import com.example.egghttp.retrofit.beans.order.Pay;
import com.example.egghttp.retrofit.params.CodeRequest;
import com.example.egghttp.retrofit.params.FooRequest;
import com.example.egghttp.retrofit.params.GetCartRequest;
import com.example.egghttp.retrofit.params.MerchantId;
import com.example.egghttp.retrofit.params.ModifyCartRequest;
import com.example.egghttp.retrofit.params.OrderRequest;
import com.example.egghttp.retrofit.params.PayRequest;
import com.example.egghttp.retrofit.params.SearchGoodsRequest;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tse on 2017/10/9.
 */


public interface EggService {

    @POST("pos/login")
    Observable<BasicOb<Merchant>> LoginR(@Body FooRequest body);


    @POST("pos/delCartByCode")
    Observable<BasicOb<Cart>> delCartByCode(@Body ModifyCartRequest body);


    @POST("pos/modifyCartByCode")
    Observable<BasicOb<Cart>> modifyCartByCode(@Body ModifyCartRequest body);

    @POST("pos/getCart")
    Observable<BasicOb<Cart>> getCart(@Body GetCartRequest body);


    @POST("pos/listOnlineOrder")
    Observable<BasicOb<List<LineOrder>>> listOnlineOrder(@Body MerchantId body);


    @POST("pos/clearCart")
    Observable<BasicOb<Cart>> clearCart();

    @POST("pos/createOrder")
    Observable<BasicOb<Order>> createOrder(@Body OrderRequest body);


    @GET("pos/syncGoods")
    Observable<BasicOb<List<Good>>> syncGoodsR(@Query("zee") String te, @Query("newsId") String newsId);


    @POST("pos/getMemberByCode")
    Observable<BasicOb<Member>> getMemberByCode(@Body CodeRequest body);


    @POST("pos/checkCouponByCode")
    Observable<BasicOb<Coupon>> checkCouponByCode(@Body CodeRequest body);


    @POST("pos/searchGoods")
    Observable<BasicOb<List<Good>>> searchGoods(@Body SearchGoodsRequest body);

    @POST("pay/payOrder")
    Observable<BasicOb<Pay>> payOrder(@Body PayRequest body);


}
