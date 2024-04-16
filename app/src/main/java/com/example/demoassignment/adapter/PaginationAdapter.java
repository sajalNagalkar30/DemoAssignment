package com.example.demoassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.demoassignment.R;
import com.example.demoassignment.model.Deal;
import com.example.demoassignment.util.DateUtil;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<Deal> Results;
    private Context context;

    private boolean isLoadingAdded = false;

    public PaginationAdapter(Context context) {
        this.context = context;
        Results = new ArrayList<>();
    }
    public void clearData() {
        Results.clear();
        notifyDataSetChanged();  // Notify the adapter to refresh the RecyclerView
    }
    public List<Deal> getMovies() {
        return Results;
    }

    public void setDeals(List<Deal> movieResults) {
        this.Results = movieResults;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.simple_list_item_1, parent, false);
        viewHolder = new DealVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Deal result = Results.get(position); // Movie

        switch (getItemViewType(position)) {
            case ITEM:
                final DealVH movieVH = (DealVH) holder;


               /* Deal deal = result.get(position);*/
                Picasso.get().load(result.getImageMedium()).into(movieVH.image_icon);
                movieVH.comment_count.setText(result.getCommentsCount().toString());
                if(result.getStore()!=null){
                String s=String.valueOf(result.getStore());
                String[] parts = s.substring(1, s.length() - 1).split("=");
                System.out.println("----------------------"+parts[1]);
                    movieVH.txt_store.setText(parts[1]);}
                movieVH.txt_title.setText("This website edited from website");
                movieVH.created_time.setText(DateUtil.convertTimestamp(result.getCreatedAt().toString()));

                /**
                 * Using Glide to handle image loading.
                 */

                break;

            case LOADING:
//                Do nothing
                break;


        }

    }

    @Override
    public int getItemCount() {
        return Results == null ? 0 : Results.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == Results.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(Deal r) {
        Results.add(r);
        notifyItemInserted(Results.size() - 1);
    }

    public void addAll(List<Deal> moveResults) {
        for (Deal result : moveResults) {
            add(result);
        }
    }

    public void remove(Deal r) {
        int position = Results.indexOf(r);
        if (position > -1) {
            Results.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;

    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Deal());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = Results.size() - 1;
        Deal result = getItem(position);

        if (result != null) {
            Results.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Deal getItem(int position) {
        return Results.get(position);
    }


   /*
   View Holders
   _________________________________________________________________________________________________
    */

    protected class DealVH extends RecyclerView.ViewHolder {
        TextView txt_title;
        TextView txt_store;
        ImageView image_icon;
        TextView txt_like_count;
        TextView comment_count;
        TextView created_time;

        public DealVH(View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.txt_title);
            txt_store = itemView.findViewById(R.id.txt_store);
            comment_count = itemView.findViewById(R.id.comment_count);
            image_icon = itemView.findViewById(R.id.image_icon);
            txt_like_count = itemView.findViewById(R.id.txt_like_count);
            created_time = itemView.findViewById(R.id.created_time);


        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


}
