package com.example.demoassignment.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.demoassignment.R;
import com.example.demoassignment.adapter.DealAdapter;
import com.example.demoassignment.model.Deal;
import com.example.demoassignment.model.TopDealViewModel;

import java.util.List;


public class SecondFragment extends Fragment {

    private TopDealViewModel topdealViewModel;
    private DealAdapter adapter;
    private RecyclerView recyclerViewDeal;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private ProgressBar progress_bottom;
    private RecyclerView deals_list;
    LinearLayoutManager linearLayoutManager;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_second, container, false);

        topdealViewModel = new ViewModelProvider(this).get(TopDealViewModel.class);
        progressBar = v.findViewById(R.id.progress_circular);
        progress_bottom = v.findViewById(R.id.progress_bottom);
        swipeRefreshLayout = v.findViewById(R.id.refreshLayout);
        recyclerViewDeal =v. findViewById(R.id.deals_list);

        adapter = new DealAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewDeal.setLayoutManager(linearLayoutManager);
        recyclerViewDeal.setItemAnimator(new DefaultItemAnimator());
        recyclerViewDeal.setAdapter(adapter);

        topdealViewModel.getDeals().observe(this, new Observer<List<Deal>>() {
            @Override
            public void onChanged(List<Deal> users) {
                adapter.addAll(users);
            }
        });

        topdealViewModel.isLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (!isLoading && progressBar.getVisibility() == View.VISIBLE) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        topdealViewModel.isLoadingmore().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean && progress_bottom.getVisibility() == View.VISIBLE) {
                    progress_bottom.setVisibility(View.GONE);
                }
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                topdealViewModel.refreshUsers();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // Pagination: Listen for scroll events
        recyclerViewDeal.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!topdealViewModel.isLoading().getValue() && !topdealViewModel.isLastPage()) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        progress_bottom.setVisibility(View.VISIBLE);
                        topdealViewModel.loadMoreUsers();
                    }
                }
            }
        });





        return v;
    }
}