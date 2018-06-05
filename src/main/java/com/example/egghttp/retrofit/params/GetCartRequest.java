package com.example.egghttp.retrofit.params;

public class GetCartRequest {
    public GetCartRequest(String merchantId, String userId, String cardCode) {
        this.merchantId = merchantId;
        this.userId = userId;
        this.cardCode = cardCode;
    }

    final String merchantId;
    final String userId;
    final String cardCode;


}
