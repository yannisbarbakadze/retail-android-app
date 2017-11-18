package com.example.android.projectriver3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Johny on 10/12/2017.
 */

public class LargerImage extends AppCompatActivity {

    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.larger_image);

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

        // Load the name that was sent from the intent to the bundle
        String name = getIntent().getStringExtra("name");

        // Find the TextView with view ID nameView
        TextView nameView = (TextView) findViewById(R.id.name_view);
        nameView.setText(name);

        // Load the price that was sent from the intent to the bundle
        String price = getIntent().getStringExtra("price");

        // Find the TextView with view ID priceView
        TextView priceView = (TextView) findViewById(R.id.price_view);
        priceView.setText(price);

        // Load the umageUrl that was sent from the intent to the bundle
        String imageUrl = getIntent().getStringExtra("imageUrl");

        // Find the ImageView with view ID image
        ImageView imageView = (ImageView) findViewById(R.id.largerImage);

        try {
            Picasso.with(getBaseContext()).load(imageUrl).into(imageView);
        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, "Blank url", e);
        }

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}