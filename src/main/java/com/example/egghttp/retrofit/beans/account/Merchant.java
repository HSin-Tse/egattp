package com.example.egghttp.retrofit.beans.account;

public class Merchant
{
    private String token;

    private String merchantId;

    private User user;

    public String getToken ()
    {
        return token;
    }

    public void setToken (String token)
    {
        this.token = token;
    }

    public String getMerchantId ()
    {
        return merchantId;
    }

    public void setMerchantId (String merchantId)
    {
        this.merchantId = merchantId;
    }

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [token = "+token+", merchantId = "+merchantId+", user = "+user+"]";
    }
}