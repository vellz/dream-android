package com.dream.net.core;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mengruicheng on 2015/11/14.
 */
public class JsonRequestCookies extends JsonObjectRequest {
    private String cookie;

    public JsonRequestCookies(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener ,String cookies) {
        super(method, url, jsonRequest, listener, errorListener);
        this.cookie=cookies;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> head = new HashMap<>();
        head.put("cookie", cookie);
        return head;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            JSONObject jsonObject = new JSONObject(jsonString);
            if (response != null && response.statusCode == HttpURLConnection.HTTP_OK) {
                try {
                    String cookies = response.headers.get("cookie");
                    jsonObject.put("cookie", cookies);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
            return Response.success(jsonObject,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
}
