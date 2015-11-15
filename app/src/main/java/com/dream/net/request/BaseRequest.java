package com.dream.net.request;

import org.json.JSONObject;

public abstract class BaseRequest {


    public abstract String getTag();

    public abstract String getUrl();

    public abstract JSONObject getJsonParam();
}
