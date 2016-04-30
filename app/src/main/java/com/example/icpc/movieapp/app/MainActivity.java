package com.example.icpc.movieapp.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MovieList{
    boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //FrameLayout rightFragment = (FrameLayout) findViewById(R.id.second_container);
        if (!isTablet(this)) {
            mTwoPane = false;

        } else {
            mTwoPane = true;
        }
        if (savedInstanceState == null) {
            MovieFragment movFrag = new MovieFragment();
            movFrag.setMovieListListner(this);
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, movFrag).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }else if(id == R.id.action_favourite){
            startActivity(new Intent(this, FavouriteActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    @Override
    public void openSelectedmovie(String[] MovieInformation) {
        Bundle extra = new Bundle();       // Use it to pass Array with the intent
        String key = "xx";
        extra.putStringArray(key, MovieInformation);
        if(mTwoPane){
            MovieDetailFragment movDetails = new MovieDetailFragment();
            movDetails.setArguments(extra);
            getSupportFragmentManager().beginTransaction().replace(R.id.second_container, movDetails).commit();
//            MovieDetailFragment movDetails = (MovieDetailFragment)getSupportFragmentManager().findFragmentById(R.id.second_container);
//            movDetails.setArguments(extra);
        }else{
            //extra.putStringArray(key, MovieInformation);
            Intent intent = new Intent(this, MovieDetail.class).putExtras(extra);
            startActivity(intent);
        }
    }
}
