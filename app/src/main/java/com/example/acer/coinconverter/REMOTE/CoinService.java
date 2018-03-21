package com.example.acer.coinconverter.REMOTE;

import com.example.acer.coinconverter.MODEL.Coin;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by acer on 17-03-2018.
 */

public interface CoinService {
    @GET("data/price")
    retrofit2.Call<Coin> calculateValue(@Query("fsym") String from, @Query("tsyms") String to);


}
