package com.example.bookimages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>
{

    private ArrayList<CartClass> cartDataList;
    private Context context;

    public CartAdapter(ArrayList<CartClass> cartDataList, Context context) {
        this.cartDataList = cartDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {

        CartClass cartItem = cartDataList.get(position);
        // Bind data to views
        holder.cartTitle.setText(cartItem.getTitle());
        holder.cartPrice.setText("Price: Rs." + cartItem.getPrice());
        holder.cartQuantity.setText("Quantity: " + cartItem.getQuantity());

        // Load image using Glide
        Glide.with(context).load(cartItem.getImageUrl()).into(holder.cartImage);

    }

    @Override
    public int getItemCount() {
        return cartDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView cartImage;
        TextView cartTitle,cartPrice,cartQuantity;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cartImage = itemView.findViewById(R.id.cartImage);
            cartTitle = itemView.findViewById(R.id.cartbookTitle);
            cartPrice = itemView.findViewById(R.id.cartbookPrice);
            cartQuantity = itemView.findViewById(R.id.cartbookQuantity);
        }
    }
}
