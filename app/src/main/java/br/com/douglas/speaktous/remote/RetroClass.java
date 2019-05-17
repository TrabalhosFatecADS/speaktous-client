package br.com.douglas.speaktous.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {

    private static final String BASE_URL = "http://192.168.43.13:8080/"; //http://192.168.0.17:8080/users

    private static Retrofit getRetrofitInstance(){

        Gson gson =  new GsonBuilder().setLenient().create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static APICall getAPICall(){
        return  getRetrofitInstance().create(APICall.class);
    }
}
