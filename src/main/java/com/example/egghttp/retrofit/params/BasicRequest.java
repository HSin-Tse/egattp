package com.example.egghttp.retrofit.params;

public class BasicRequest<T>
{
    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    private T body;

    private String code;

    private String msg;


    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getMsg ()
    {
        return msg;
    }

    public void setMsg (String msg)
    {
        this.msg = msg;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [body = "+body+", code = "+code+", msg = "+msg+"]";
    }
}