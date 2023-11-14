package com.example.bookimages;

public class CartClass
{
    private String title;
    private String imageUrl;
    private int price;

    private int quantity;

    public CartClass()
    {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartClass(String title, String imageUrl, int price, int quantity) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
    }
}
