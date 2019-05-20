package br.com.douglas.speaktous.remote;

import br.com.douglas.speaktous.model.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface APICall {

    @Headers({"Content-Type: application/json",})
    @POST("token/generate-token")
    Call<JWTToken> userLogin(@Body User user);

    @Headers({"Content-Type: application/json",})
    @POST("token/newUser")
    Call<JWTToken> salvaPessoa(@Body Pessoa pessoa);

    @Headers({"Content-Type: application/json",})
    @POST("token/postagem")
    Call<JWTPostagem> salvaPostagem(@Body Postagem postagem);

    @Headers({ "Content-Type: application/json",})
    @GET("users")
    Call<JWTUsers> getUser();
}
