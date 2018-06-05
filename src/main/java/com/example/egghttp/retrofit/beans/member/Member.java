package com.example.egghttp.retrofit.beans.member;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class Member extends BaseObservable {


    @Override
    public String toString() {
        return "Pay{" +
                "userId='" + userId + '\'' +
                ", vipText='" + vipText + '\'' +
                ", money='" + money + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;

    public String getVipText() {
        return vipText;
    }

    public void setVipText(String vipText) {
        this.vipText = vipText;
    }

    private String vipText;


    @Bindable
    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }


    @Bindable
    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
        if (money.length() > 1) {

            setShow(show);
        }

    }

    private String money;
    private Boolean show;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;


}