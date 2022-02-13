package com.example.retrofit2_example.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilits {
    public static final String BASE_URL = "https://api.unsplash.com/";
    public static final String API_KEY = "9FCRMrBSngRC8fwB51xzXPCuljEd--mKyM4rWwu8IF0";
    public static final String PER_PAGE = "9FCRMrBSngRC8fwB51xzXPCuljEd--mKyM4rWwu8IF0";

    public static Retrofit retrofit = null;
    public static  ApiInterface getApiInterface(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
