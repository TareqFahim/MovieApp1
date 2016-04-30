package com.example.icpc.movieapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MovieDetail extends AppCompatActivity {
    View rootView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = this.getIntent();
        Bundle b = intent.getExtras();
        if (savedInstanceState == null) {
            MovieDetailFragment mDetailFrag = new MovieDetailFragment();
            mDetailFrag.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.second_container, mDetailFrag).commit();
        }

    }

}
