package com.example.egghttp.retrofit.beans.cart;

import com.example.egghttp.retrofit.beans.goods.Good;

import java.util.List;

public class Cart {


    private double qty;
    private double totalMoney;
    private double freigh;
    private double discountMoney;
    private double receiveMoney;
    private double point;
    private double pointMoney;
    private double cardMoney;
    private double backMoney;
    private double goodsMoney;
    private List<Good> goodsList = null;
    private List<Good> loseStockGoods = null;

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getFreigh() {
        return freigh;
    }

    public void setFreigh(double freigh) {
        this.freigh = freigh;
    }

    public double getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(double discountMoney) {
        this.discountMoney = discountMoney;
    }

    public double getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(double receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public double getPointMoney() {
        return pointMoney;
    }

    public void setPointMoney(int pointMoney) {
        this.pointMoney = pointMoney;
    }

    public double getCardMoney() {
        return cardMoney;
    }

    public void setCardMoney(double cardMoney) {
        this.cardMoney = cardMoney;
    }

    public double getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(double backMoney) {
        this.backMoney = backMoney;
    }

    public List<Good> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Good> goodsList) {
        this.goodsList = goodsList;
    }

    public List<Good> getLoseStockGoods() {
        return loseStockGoods;
    }

    public void setLoseStockGoods(List<Good> loseStockGoods) {
        this.loseStockGoods = loseStockGoods;
    }

    public double getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(double goodsMoney) {
        this.goodsMoney = goodsMoney;
    }
}
