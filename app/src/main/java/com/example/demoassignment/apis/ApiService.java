package com.example.demoassignment.apis;




import com.example.demoassignment.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

  @Headers({"X-Desidime-Client:08b4260e5585f282d1bd9d085e743fd9"}) @GET("v4/home/new")
    Call<ApiResponse> getDeals(

            @Query("per_page") int per_page,
            @Query("page") int pageIndex,
            @Query("fields") String fields
    );

  @Headers({"X-Desidime-Client:08b4260e5585f282d1bd9d085e743fd9"}) @GET("v4/home/discussed")
  Call<ApiResponse> getTopDeals(

          @Query("per_page") int per_page,
          @Query("page") int pageIndex,
          @Query("fields") String fields
  );


}


