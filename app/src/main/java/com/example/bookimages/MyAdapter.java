package com.example.bookimages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    private ArrayList<DataClass> dataList;
    private Context context;

    public MyAdapter(ArrayList<DataClass> dataList, Context context)
    {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        Glide.with(context).load(dataList.get(position).getImageURL()).into(holder.recyclerImage);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView recyclerImage;


        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            recyclerImage = itemView.findViewById(R.id.recyclerImage);

        }
    }
}
