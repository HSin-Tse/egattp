package com.example.egghttp.retrofit.beans.account;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

public class User extends BaseObservable
{
    private String id;

    private String userName;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Bindable
    public String getUserName ()
    {
        return userName;
    }

    public void setUserName (String userName)
    {

        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", userName = "+userName+"]";
    }
}