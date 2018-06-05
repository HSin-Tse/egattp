package com.example.egghttp.retrofit.params;

public class CartRequest {
    final String merchantId;
    final String code;

    public CartRequest(String merchantId, String code) {
        this.merchantId = merchantId;
        this.code = code;
    }
}
