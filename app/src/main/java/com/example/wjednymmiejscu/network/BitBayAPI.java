package com.example.wjednymmiejscu.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BitBayAPI {
    @GET("/{curr1}{curr2}/{type}.json")
    Call<String> getData(
            @Path("curr1") String curr1,
            @Path("curr2") String curr2,
            @Path("type") String type
    );
}
