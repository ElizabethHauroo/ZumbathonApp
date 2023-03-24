package com.example.zumbathonapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zumbathonapp.Model.Model;
import com.example.zumbathonapp.R;

import java.util.ArrayList;

public class AllUserAdapter extends RecyclerView.Adapter<AllUserAdapter.AllUserViewHolder> {

    ArrayList<Model> mList;
    Context context;

    public AllUserAdapter(ArrayList<Model> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public AllUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new AllUserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllUserViewHolder holder, int position) {
        Model model = mList.get(position);
        holder.FullName.setText(model.getFullName());
        holder.Account.setText(model.getAccount());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class AllUserViewHolder extends RecyclerView.ViewHolder{
        TextView FullName, Account;
        public AllUserViewHolder(@NonNull View itemView) {
            super(itemView);
            FullName = itemView.findViewById(R.id.recycle_item_name);
            Account = itemView.findViewById(R.id.recycle_item_account);
        }
    }
}
