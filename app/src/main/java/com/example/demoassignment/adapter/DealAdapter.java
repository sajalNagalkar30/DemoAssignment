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

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.MyViewHolder> {

    private List<Deal> dataList;
    private Context context;

    // Constructor to initialize the data list and context
    public DealAdapter(Context context) {
        this.context = context;
        dataList = new ArrayList<>();
    }

    // Inner ViewHolder class to hold the views
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title;
        TextView txt_store;
        ImageView image_icon;
        TextView txt_like_count;
        TextView comment_count;
        TextView created_time;

        public MyViewHolder(View view) {
            super(view);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_store = itemView.findViewById(R.id.txt_store);
            comment_count = itemView.findViewById(R.id.comment_count);
            image_icon = itemView.findViewById(R.id.image_icon);
            txt_like_count = itemView.findViewById(R.id.txt_like_count);
            created_time = itemView.findViewById(R.id.created_time);
        }
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item_1, parent, false);
        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Get the data for this position
        Deal deal = dataList.get(position);
        // Set the data to the view
        Picasso.get().load(deal.getImageMedium()).into(holder.image_icon);
        holder.comment_count.setText(deal.getCommentsCount().toString());
        if(deal.getStore()!=null){
            String s=String.valueOf(deal.getStore());
            String[] parts = s.substring(1, s.length() - 1).split("=");
            System.out.println("----------------------"+parts[1]);
            holder.txt_store.setText(parts[1]);}
        holder.txt_title.setText("This website edited from website");
        holder.created_time.setText(DateUtil.convertTimestamp(deal.getCreatedAt().toString()));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public void addAll(List<Deal> deals) {
        for (Deal data : deals) {
            add(data);
        }
    }

    public void add(Deal r) {
        dataList.add(r);
        notifyItemInserted(dataList.size() - 1);
    }


    public void clear() {
        dataList.clear();
        notifyDataSetChanged();
    }

}
