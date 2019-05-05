package br.com.douglas.speaktous.remote;

import br.com.douglas.speaktous.model.JWTToken;
import br.com.douglas.speaktous.model.JWTUsers;
import br.com.douglas.speaktous.model.User;
import retrofit2.Call;
import retrofit2.http.*;

public interface APICall {

    @Headers({"Content-Type: application/json",})
    @POST("token/generate-token")
    Call<JWTToken> userLogin(@Body User user);

    @Headers({ "Content-Type: application/json",})
    @GET("users")
    Call<JWTUsers> getUser(@Header("Authorization") String auth);
}
