package com.example.bookimages;

public class BookClass
{
   private String title;
   private String imageURL;
   private int price;

   private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BookClass()
   {

   }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public BookClass(String title, String imageURL, int price, int quantity) {
        this.title = title;
        this.imageURL = imageURL;
        this.price = price;
        this.quantity = quantity;
    }
}
