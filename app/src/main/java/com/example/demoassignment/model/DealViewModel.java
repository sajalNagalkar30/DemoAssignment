package com.example.demoassignment.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class DealViewModel extends ViewModel {
    private DealRepository dealRepository;
    private LiveData<List<Deal>> deals;
    private LiveData<Boolean> isLoading;
    private LiveData<Boolean> isLoadingmore;

    public DealViewModel() {
        dealRepository = new DealRepository();
        deals = dealRepository.getDeal();
        isLoading = dealRepository.isLoading();
        isLoadingmore = dealRepository.isLoadingmore();
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
        dealRepository.loadMoreUsers();
    }

    public void refreshUsers() {
        dealRepository.refreshUsers();
    }

    public boolean isLastPage() {
        return dealRepository.isLastPage();
    }

}
