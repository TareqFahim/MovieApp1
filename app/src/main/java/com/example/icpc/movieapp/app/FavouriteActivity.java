package com.example.icpc.movieapp.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class FavouriteActivity extends AppCompatActivity implements  MovieList {
    boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (!isTablet(this)) {
            mTwoPane = false;

        } else {
            mTwoPane = true;
        }
        if (savedInstanceState == null) {
            FavouriteActivityFragment movFavFrag = new FavouriteActivityFragment();
            movFavFrag.setSelectFavMovie(this);
            getSupportFragmentManager().beginTransaction().add(R.id.fav_first_container, movFavFrag).commit();
        }

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
            getSupportFragmentManager().beginTransaction().replace(R.id.fav_second_container, movDetails).commit();
        }else{
            Intent intent = new Intent(this, MovieDetail.class).putExtras(extra);
            startActivity(intent);
        }
    }
}
