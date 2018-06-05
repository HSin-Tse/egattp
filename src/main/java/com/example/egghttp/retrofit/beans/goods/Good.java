package com.example.egghttp.retrofit.beans.goods;

public class Good
{

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                ", sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", spuId=" + spuId +
                ", marketPrice=" + marketPrice +
                ", price=" + price +
                ", memberPrice=" + memberPrice +
                ", specificate='" + specificate + '\'' +
                ", specPath='" + specPath + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", picture='" + picture + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }

    private float id;
    private String barcode;
    private String sn;
    private String name;

    private float totalMoney;

    private float spuId;
    private float marketPrice;
    private float price;
    private float memberPrice;
    private String specificate;
    private String specPath;
    private String createdAt;
    private String updatedAt;
    private int qty;
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    private String picture;




    // Getter Methods

    public float getId() {
        return id;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getSn() {
        return sn;
    }

    public String getName() {
        return name;
    }

    public float getSpuId() {
        return spuId;
    }

    public float getMarketPrice() {
        return marketPrice;
    }

    public float getPrice() {
        return price;
    }

    public float getMemberPrice() {
        return memberPrice;
    }

    public String getSpecificate() {
        return specificate;
    }

    public String getSpecPath() {
        return specPath;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpuId(float spuId) {
        this.spuId = spuId;
    }

    public void setMarketPrice(float marketPrice) {
        this.marketPrice = marketPrice;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setMemberPrice(float memberPrice) {
        this.memberPrice = memberPrice;
    }

    public void setSpecificate(String specificate) {
        this.specificate = specificate;
    }

    public void setSpecPath(String specPath) {
        this.specPath = specPath;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}