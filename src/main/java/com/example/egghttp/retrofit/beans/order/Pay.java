package com.example.egghttp.retrofit.beans.order;

public class Pay {


    public final static String SUCCESS = "SUCCESS";
    public final static String QUERY = "QUERY";
    public final static String UNPAY = "UNPAY";

    @Override
    public String toString() {
        return "Pay{" +
                "status='" + status + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", buyerLogonId='" + buyerLogonId + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                '}';
    }

    private String status;
    private String outTradeNo;
    private String buyerLogonId;
    private String tradeNo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

}