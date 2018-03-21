package com.example.acer.coinconverter.REMOTE;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by acer on 17-03-2018.
 */

public class RetrofitClient {
    public static Retrofit retrofit=null;

    public static Retrofit getClient (String baseURL){
        if (retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create() )
                    .build();
        }
        return  retrofit;
    }
}
