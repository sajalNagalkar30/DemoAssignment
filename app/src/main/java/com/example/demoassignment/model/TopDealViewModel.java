package com.example.demoassignment.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class TopDealViewModel extends ViewModel {
    private TopDealRepository topdealRepository;
    private LiveData<List<Deal>> deals;
    private LiveData<Boolean> isLoading;
    private LiveData<Boolean> isLoadingmore;

    public TopDealViewModel() {
        topdealRepository = new TopDealRepository();
        deals = topdealRepository.getDeal();
        isLoading = topdealRepository.isLoading();
        isLoadingmore = topdealRepository.isLoadingmore();
    }

    public LiveData<List<Deal>> getDeals() {
        return deals;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public LiveData<Boolean> isLoadingmore() {
        return isLoadingmore;
    }

    public void loadMoreUsers() {
        topdealRepository.loadMoreUsers();
    }

    public void refreshUsers() {
        topdealRepository.refreshUsers();
    }

    public boolean isLastPage() {
        return topdealRepository.isLastPage();
    }

}
