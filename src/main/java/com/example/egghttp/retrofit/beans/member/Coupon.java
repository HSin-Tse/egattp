package com.example.egghttp.retrofit.beans.member;

import android.databinding.BaseObservable;

public class Coupon extends BaseObservable {


    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCanUse() {
        return canUse;
    }

    public void setCanUse(String canUse) {
        this.canUse = canUse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "cardCode='" + cardCode + '\'' +
                ", canUse='" + canUse + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    private String cardCode ="-1";
    private String canUse;
    private String message;



}