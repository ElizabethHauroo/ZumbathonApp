package com.example.zumbathonapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<UserAdapter> list;

    public MyAdapter(Context context, ArrayList<UserAdapter> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.registeredusers, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        UserAdapter userAdapter = list.get(position);
        holder.FullName.setText(userAdapter.getFullName());
        holder.Account.setText(userAdapter.getAccount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView FullName, Account;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            FullName = itemView.findViewById(R.id.recyclerView_name);
            Account = itemView.findViewById(R.id.recyclerView_status);
        }
    }
}
