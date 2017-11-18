package com.example.android.projectriver3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        getSupportActionBar().setIcon(R.drawable.ic_menu_white_18dp);
        setSupportActionBar(toolbar);

        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Find the Button that shows the listView
        Button women = (Button) findViewById(R.id.women);
        // Set a click listener on that View and sent an intent
        women.setOnClickListener(new View.OnClickListener() {//
            //The code in this method will be executed when the women's button is clicked on.
            @Override
            public void onClick(View view) {
                Intent womenIntent = new Intent(MainActivity.this, WomenActivity.class);
                startActivity(womenIntent);
            }
        });

        //Find the Button that shows the listView
        Button womanNav = (Button) findViewById(R.id.nav_women);
        // Set a click listener on that View
        womanNav.setOnClickListener(new View.OnClickListener() {//
            //The code in this method will be executed when the women's button is clicked on.
            @Override
            public void onClick(View view) {
                Intent womenIntent = new Intent(MainActivity.this, WomenActivity.class);
                startActivity(womenIntent);
            }
        });
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
