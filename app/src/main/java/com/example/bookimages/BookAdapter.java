package com.example.bookimages;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder>
{
    private ArrayList<BookClass> bookDataList;
    private Context context;

    public BookAdapter(ArrayList<BookClass> bookDataList, Context context) {
        this.bookDataList = bookDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public BookAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
       return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.MyViewHolder holder, int position) {
        BookClass book = bookDataList.get(position);
        Glide.with(context).load(bookDataList.get(position).getImageURL()).into(holder.recyclerImage);
        holder.title.setText(book.getTitle());
        holder.price.setText("Price: Rs." + book.getPrice());

        // Set OnClickListener for the FAB
        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show an AlertDialog with a number picker
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Select Quantity");

                NumberPicker numberPicker = new NumberPicker(context);
                numberPicker.setMinValue(1); // Minimum quantity
                numberPicker.setMaxValue(10); // Maximum quantity

                builder.setView(numberPicker);

                builder.setPositiveButton("Add to Cart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int selectedQuantity = numberPicker.getValue();

                        // Set the selected quantity to the book item
                        book.setQuantity(selectedQuantity);

                        // Get the reference to the "cart" collection in the database
                        DatabaseReference cartReference = FirebaseDatabase.getInstance().getReference().child("cart");
                        String cartItemId = cartReference.push().getKey();
                        // Create a new HashMap to hold the cart item data
                        HashMap<String, Object> cartItemData = new HashMap<>();
                        cartItemData.put("title", book.getTitle());
                        cartItemData.put("imageUrl", book.getImageURL());
                        cartItemData.put("price", book.getPrice());
                        cartItemData.put("quantity", book.getQuantity());

                        cartReference.child(cartItemId).setValue(cartItemData)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(context, "Added to cart: " + selectedQuantity + " x " + book.getTitle(), Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(context, "Failed to add to cart", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });



                    }
                });

                builder.setNegativeButton("Cancel", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return bookDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView recyclerImage;
        TextView title,price;

        FloatingActionButton fab;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerImage = itemView.findViewById(R.id.recyclerImage);
            title = itemView.findViewById(R.id.titleBook);
            price = itemView.findViewById(R.id.priceBook);
            fab = itemView.findViewById(R.id.fabCart);
        }
    }
}
