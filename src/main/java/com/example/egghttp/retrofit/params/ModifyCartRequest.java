package com.example.egghttp.retrofit.params;

public class ModifyCartRequest {
    final String merchantId;
    final String code;
    final String qty;

    public ModifyCartRequest(String merchantId, String code,String qty) {
        this.merchantId = merchantId;
        this.code = code;
        this.qty = qty;
    }
}
