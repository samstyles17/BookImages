package com.example.bookimages;

public class DataClass
{
    public DataClass()
    {

    }
    private String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public DataClass(String imageURL) {
        this.imageURL = imageURL;
    }
}
