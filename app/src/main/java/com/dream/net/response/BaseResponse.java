package com.dream.net.response;

import org.json.JSONObject;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

public abstract class BaseResponse implements Listener<JSONObject>, ErrorListener {
    /***
     * 访问监听
     */
    public ResponseListener mlistener;
    private int resultCode = -1;
    public String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 设置监听
     *
     * @param listener
     */
    public void setListener(ResponseListener listener) {

        mlistener = listener;
    }

    public void onRequestSuccess(BaseResponse rep) {
        if (mlistener != null) {
            mlistener.onResponse(rep);
        }

    }

    public void mFail(VolleyError error) {
        if (mlistener != null) {
            mlistener.fail(error);
        }

    }

    public interface ResponseListener {
        /**
         * 访问成功
         *
         * @param respone
         */
        public void onResponse(BaseResponse respone);

        /**
         * 访问失败
         *
         * @param error
         */
        public void fail(VolleyError error);
    }
}
