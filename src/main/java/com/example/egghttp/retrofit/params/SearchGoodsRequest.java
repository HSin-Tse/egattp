package com.example.egghttp.retrofit.params;

public class SearchGoodsRequest {
    final String code;
    final String name;

    public SearchGoodsRequest(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
