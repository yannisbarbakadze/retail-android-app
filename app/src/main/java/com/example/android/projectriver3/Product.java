package com.example.android.projectriver3;

/**
 * Created by Johny on 10/7/2017.
 */

/**
 /**
 * {@link Product} object represents a single product that a user wants to see.
 * It contains an image of the product, the name of the product and the price of the product
 */
public class Product {

    /**
     * Image of the product
     */
    private String mImageUrl;

    /**
     * Larger Image of the product
     */
    private String mLargerImageUrl;

    /**
     * Name of the product
     */
    private String mName;

    /**
     * Price of the product
     */
    private String mPrice;

    /**
     * Constructs a new {@link Product} object initializing the three variables.
     *
     * @param imageUrl is the url of the image
     * @param name     is the name of the product
     * @param price    is the price of the product
     */
    public Product(String imageUrl, String largerImageUrl, String name, String price) {
        mImageUrl = imageUrl;
        mLargerImageUrl = largerImageUrl;
        mName = name;
        mPrice = price;
    }

    /**
     * Returns the url of the product.
     */
    public String getImageUrl() {
        return mImageUrl;
    }

    /**
     * Returns the url of the product.
     */
    public String getLargerImageUrl() {
        return mLargerImageUrl;
    }

    /**
     * Returns the name of the product.
     */
    public String getName() {
        return mName;
    }

    /**
     * Returns the price of the product.
     */
    public String getPrice() { return "£" + mPrice; }
}
