package com.example.android.projectriver3;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Johny on 10/7/2017.
 */

/**
 * Loads a list of products by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class ProductLoader extends AsyncTaskLoader<List<Product>> {

    /** Tag for log messages */
    private static final String LOG_TAG = ProductLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link ProductLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public ProductLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "TEST: onStartLoading() called...");
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Product> loadInBackground() {
        Log.i(LOG_TAG, "TEST: loadInBackground() called...");
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of products.
        List<Product> products = QueryUtils.fetchProductData(mUrl);
        return products;
    }
}
