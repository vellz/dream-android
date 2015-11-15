package com.dream.net.core;

import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dream.net.request.BaseRequest;
import com.dream.net.response.BaseResponse;
import com.dream.utils.Constant;
import com.dream.utils.PreferUtil;

/***
 * 网络访问类，适用于小数据据访问
 *
 * @author meng_yuan
 */
public class CustomNetworkCore {
    private Context mContext;
    static RequestQueue mRequestQueue;


    public CustomNetworkCore(Context context) {
        this.mContext = context;
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(mContext);
    }

    public void requestByJson(final BaseRequest request, final BaseResponse responese) {
        JSONObject reqParam = request.getJsonParam();
        JsonRequestCookies req;
        if (reqParam == null) {

            req = new JsonRequestCookies(Method.GET, request.getUrl(), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    PreferUtil.saveString(mContext, "cookie", response.optString("cookie"));
                    responese.onResponse(response);
                }
            }, responese,PreferUtil.getString(mContext,Constant.COOKIESTR));

        }else{
            req = new JsonRequestCookies(Method.POST, request.getUrl(), reqParam, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    PreferUtil.saveString(mContext, "cookie", response.optString("cookie"));
                    responese.onResponse(response);
                }
            }, responese,PreferUtil.getString(mContext,Constant.COOKIESTR));

        }

        mRequestQueue.add(req);
    }
}
