package com.aisle.techchallengeassets.service;

import com.aisle.techchallengeassets.service.model.Login;
import com.aisle.techchallengeassets.service.model.LoginRquest;
import com.aisle.techchallengeassets.service.model.OTP_Resp;
import com.aisle.techchallengeassets.service.model.OtpRequest;
import com.aisle.techchallengeassets.service.model.UserResp;
import com.aisle.techchallengeassets.util.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by india on 8/6/2018.
 */

public interface ILoginService {

    @Headers("Cookie:__cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720")
    @POST(Constants.Login)
    Call<Login> fetchLogin(@Body LoginRquest body);

    @Headers("Cookie:__cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720")
    @POST(Constants.OTP)
    Call<OTP_Resp> fetchOtp(@Body OtpRequest body);

    @Headers("Cookie:__cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720")
    @GET(Constants.USER)
    Call<UserResp> fetchUser(@Header("Authorization")String token);

}
