package com.example.icpc.movieapp.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class FavouriteActivityFragment extends Fragment {

    View rootView;
    MovieDbHelper data ;
    GridView favMoviesGrid;
    ImageView favImageView;
    public ImageCustomAdapter favPostersAdapter;
    String [] intentExtra = new String[6];
    MovieList selectFavMovie;
    boolean onStartTablet = true;
    public FavouriteActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_favourite, container, false);
        favMoviesGrid = (GridView) rootView.findViewById(R.id.favourite_gridview_movies);
        favImageView = (ImageView) rootView.findViewById(R.id.list_item_favourite_imageview);
        data= new MovieDbHelper(getActivity());
        final MovieDataDB movData = data.getData();
        if(movData != null) {
            favPostersAdapter = new ImageCustomAdapter(getActivity(), (ArrayList) movData.favPosters);
            favMoviesGrid.setAdapter(favPostersAdapter);

            favMoviesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    intentExtra[0] = (String) movData.favPosters.get(position);
                    intentExtra[1] = (String) movData.favTitles.get(position);
                    intentExtra[2] = (String) movData.favDates.get(position);
                    intentExtra[3] = (String) movData.favOverviews.get(position);
                    intentExtra[4] = (String) movData.favRates.get(position);
                    intentExtra[5] = (String) movData.favMovieIDs.get(position);
//
                    selectFavMovie.openSelectedmovie(intentExtra);
                }
            });
        }
        return rootView;
    }
    public void setSelectFavMovie(MovieList favmoviee){
        selectFavMovie = favmoviee;
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        selectFavMovie = (MovieList)getActivity();
    }
}
