package com.snapin.callbacks;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Prasanna on 2/23/17.
 */

public interface RetrofitEndUserInterFace {

    @GET("/json")
    public void getLatAndLong(@Field("address") String address,
                              @Field("key") String key, Callback<Object> loginCallback);
}
