package com.google.player;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {


    @FormUrlEncoded
    @POST("verify/oxootv")
    Call<Data> run(@Field("purchase_code") String a,
                               @Field("package_name") String packageName);
}
