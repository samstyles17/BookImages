package com.example.bookimages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity
{
    private RecyclerView recyclerCart;
    private ArrayList<CartClass> cartDataList;
    private CartAdapter cartAdapter;
    private DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    private TextView textTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("cart");
        recyclerCart = findViewById(R.id.recyclerCart);
        recyclerCart.setHasFixedSize(true);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));

        textTotalPrice = findViewById(R.id.textTotalPrice);

        cartDataList = new ArrayList<CartClass>();
        cartAdapter = new CartAdapter(cartDataList,CartActivity.this);

        recyclerCart.setAdapter(cartAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cartDataList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CartClass cartItem = snapshot.getValue(CartClass.class);
                    cartDataList.add(cartItem);


                }
                // Calculate and display the total price
                int totalPrice = calculateTotalPrice();
                //textTotalPrice.setText("Total Cart Price: " + String.valueOf(totalPrice));
                // Show the AlertDialog with the total price
                showTotalPriceAlertDialog(totalPrice);

// Notify the adapter of data changes
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private int calculateTotalPrice() {
        int totalPrice = 0;
        for (CartClass cartItem : cartDataList) {
            totalPrice += cartItem.getPrice() * cartItem.getQuantity();
        }
        Log.d("CartActivity", "Total Price: " + totalPrice);
        return totalPrice;
    }

    private void showTotalPriceAlertDialog(int totalPrice) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Total Cart Price");
        builder.setMessage("Total Price: Rs. " + totalPrice);
        builder.setPositiveButton("OK", null); // You can add an OK button
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}