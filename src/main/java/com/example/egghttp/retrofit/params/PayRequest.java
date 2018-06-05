package com.example.egghttp.retrofit.params;

public class PayRequest {
    public PayRequest(String merchantId, String orderName, String authCode, String payType, String userId) {
        this.merchantId = merchantId;
        this.orderName = orderName;
        this.authCode = authCode;
        this.payType = payType;
        this.userId = userId;
    }

    final String merchantId;
    final String orderName;
    final String authCode;
    final String payType;
    final String userId;

}
