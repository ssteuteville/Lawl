package com.lawl.ui;

/**
 * Created by Shane Steuteville on 4/13/2014.
 */
import android.util.Log;

import com.loopj.android.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;

/**
 * Class implemented to be used as a client for Riot's RESTFUL API.
 * This class only returns JSONObjects. It is intended to be generic and the parsing of the JSON
 * is left to the user.
 */
public class RiotApiClient {
    private static final String BASE_URL = "https://prod.api.pvp.net";
    private String API_KEY;
    private static AsyncHttpClient client = new AsyncHttpClient();
    public RiotApiClient(String API_KEY)
    {
            this.API_KEY = "api_key=" + API_KEY;
    }

    public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(getPath(url), params, responseHandler);
    }

    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getPath(url), params, responseHandler);
    }

    private String getPath(String url)
    {
        Log.e("YYYYYYYYYYYYY", BASE_URL + url + this.API_KEY);
        return BASE_URL + url + this.API_KEY;
    }
}
