package com.snapin.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.snapin.callbacks.RetrofitEndUserInterFace;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Prasanna on 2/23/17.
 */

public class RetrofitExcution {

    public static void getLongitudeAndLatitide(Context context, String address, String key) {
        final ProgressDialog loading = Alert.getInstance().callLoadingAlert(context, "Loading....");
        loading.show();

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(AppContants.GET_LONG_LATITUDE_API).build();

        RetrofitEndUserInterFace api = restAdapter.create(RetrofitEndUserInterFace.class);

        api.getLatAndLong(address,key, new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {
                Log.v("TAG", "Server response " + new Gson().toJson(0));
                loading.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("TAG", "Server response " + error.getMessage());
                loading.dismiss();
            }
        });
    }


}
