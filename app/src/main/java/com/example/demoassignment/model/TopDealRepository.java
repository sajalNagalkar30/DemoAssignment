package com.example.demoassignment.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.demoassignment.apis.ApiService;
import com.example.demoassignment.apis.DealApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopDealRepository {
    private ApiService apiService;
    private MutableLiveData<List<Deal>> topdealLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoadingmore = new MutableLiveData<>();
    private int currentPage = 1;
    private int TOTAL_PAGES = 10;
    private boolean isLastPage = false;

    public TopDealRepository() {
        apiService = DealApi.getClient().create(ApiService.class);
        topdealLiveData.setValue(new ArrayList<>());
        loadDeals();
    }

    public LiveData<List<Deal>> getDeal() {
        return topdealLiveData;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }
    public LiveData<Boolean> isLoadingmore() {
        return isLoadingmore;
    }

    public boolean isLastPage() {
        return isLastPage;
    }
    public void loadDeals() {
        isLoading.setValue(true);
        apiService.getTopDeals( TOTAL_PAGES, currentPage, "id,created_at,created_at_in_millis,image_medium,comments_count,store{name}").enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null) {
                        List<Deal> deals = apiResponse.getDeals();
                        if (deals != null && deals.size() > 0) {
                            currentPage++;
                            List<Deal> currentData = topdealLiveData.getValue();
                            currentData.addAll(deals);
                            topdealLiveData.setValue(currentData);
                        } else {
                            isLastPage = true;
                        }

                    }
                }
                isLoading.setValue(false);
                isLoadingmore.setValue(false);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                isLoading.setValue(false);
                isLoadingmore.setValue(false);
                // Handle failure
            }
        });
    }

    public void loadMoreUsers() {
        if (!isLoading.getValue() && !isLastPage) {
            loadDeals();
        }
    }

    public void refreshUsers() {
        currentPage = 1;
        isLastPage = false;
        topdealLiveData.setValue(new ArrayList<>());
        loadDeals();
    }

}
