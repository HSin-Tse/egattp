package com.example.egghttp.retrofit.params;

public class OrderRequest {
    public OrderRequest(String merchantId, String scoreCode, String cardCode, String userId, String isUseAccountMoney) {
        this.merchantId = merchantId;
        this.scoreCode = scoreCode;
        this.cardCode = cardCode;
        this.userId = userId;
        this.isUseAccountMoney = isUseAccountMoney;
    }

    final String merchantId;
    final String scoreCode;
    final String cardCode;
    final String userId;
    final String isUseAccountMoney;

}
