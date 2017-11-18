package com.example.android.projectriver3;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johny on 10/7/2017.
 */

public class WomenActivity extends AppCompatActivity
        implements LoaderCallbacks<List<Product>> {

    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private static final String RI_REQUEST_URL =
            "https://ri.nn4m.net/RI/sv5/api/public/index.php/category/2508/products.json";

    /**
     * Constant value for the product loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int PRODUCT_LOADER_ID = 1;

    /**
     * Adapter for the list of products
     */
    private ProductAdapter mAdapter;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_menu_white_18dp);
        setSupportActionBar(toolbar);

        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Get access to the custom title view
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        /**                    ArrayAdapter
         * Create a new adapter that takes an empty list of products as input
         *
         * Then search through the view hierarchy for the ListView in our XML layout
         *
         * Then we're associating the array adapter with a list view
         */
        mAdapter = new ProductAdapter(this, new ArrayList<Product>());

        // Find a reference to the {@link ListView} in the layout
        ListView productListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        productListView.setEmptyView(mEmptyStateTextView);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        productListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to another activity
        // to show a larger image of the selected Product.
        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Find the current Product that was clicked on
                Product currentProduct = mAdapter.getItem(position);

                // Create a new intent to view the larger image
                Intent largerImage = new Intent(WomenActivity.this, LargerImage.class);

                // Send the imageUrl as a string extra to the LargerImage activity in its extras bundle
                largerImage.putExtra("imageUrl", currentProduct.getLargerImageUrl());

                //Send the name as a string extra to the LargerImage activity in its extras bundle
                largerImage.putExtra("name", currentProduct.getName());

                //Send the price as a string extra to the LargerImage activity in its extras bundle
                largerImage.putExtra("price", currentProduct.getPrice());

                // Send the intent to launch a new activity
                startActivity(largerImage);
            }
        });


        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            android.app.LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(PRODUCT_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<Product>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new ProductLoader(this, RI_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Product>> loader, List<Product> products) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No products found."
        mEmptyStateTextView.setText(R.string.no_products);

        // Clear the adapter of previous product data
        mAdapter.clear();

        // If there is a valid list of {@link Product}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (products != null && !products.isEmpty()) {
            mAdapter.addAll(products);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Product>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }
}

