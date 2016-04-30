package com.example.icpc.movieapp.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ICPC on 4/23/2016.
 */
public class MovieDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 7;
    static final String DATABASE_NAME = "favourite.db";

    public static final String TABLE_NAME = "favourite";
    public static final String COLUMN_MOVIE_POSTER = "poster";
    public static final String COLUMN_MOVIE_TITLE = "title";
    public static final String COLUMN_MOVIE_RATE = "rate";
    public static final String COLUMN_MOVIE_OVERVIEW = "overview";
    public static final String COLUMN_MOVIE_DATE = "date";
    public static final String COLUMN_MOVIE_ID = "movie_id";

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_FAVOURITE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_MOVIE_POSTER + " TEXT, " +
                COLUMN_MOVIE_DATE + "  TEXT, " +
                COLUMN_MOVIE_TITLE + " TEXT, " +
                COLUMN_MOVIE_RATE + " TEXT, " +
                COLUMN_MOVIE_OVERVIEW + " TEXT, " +
                COLUMN_MOVIE_ID + " TEXT PRIMARY KEY" +
                ");";
        db.execSQL(SQL_CREATE_FAVOURITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void insertDateToTable(String poster, String date, String title, String rate, String overview, String movieID){
        SQLiteDatabase ddb = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(COLUMN_MOVIE_POSTER, poster);
        val.put(COLUMN_MOVIE_DATE, date);
        val.put(COLUMN_MOVIE_TITLE, title);
        val.put(COLUMN_MOVIE_RATE, rate);
        val.put(COLUMN_MOVIE_OVERVIEW, overview);
        val.put(COLUMN_MOVIE_ID, movieID);

        ddb.insert(TABLE_NAME, null, val);
        ddb.close();
    }
    public MovieDataDB getData(){
        SQLiteDatabase datab = this.getReadableDatabase();

        Cursor movieData = datab.rawQuery("select * from " + TABLE_NAME + ";", null);

        if(movieData.getCount() <= 0)
            return null;
        movieData.moveToFirst();
        MovieDataDB favMovies = new MovieDataDB();
        do{
            favMovies.favPosters.add(movieData.getString(movieData.getColumnIndex(COLUMN_MOVIE_POSTER)));
            favMovies.favTitles.add(movieData.getString(movieData.getColumnIndex(COLUMN_MOVIE_TITLE)));
            favMovies.favDates.add(movieData.getString(movieData.getColumnIndex(COLUMN_MOVIE_DATE)));
            favMovies.favRates.add(movieData.getString(movieData.getColumnIndex(COLUMN_MOVIE_RATE)));
            favMovies.favOverviews.add(movieData.getString(movieData.getColumnIndex(COLUMN_MOVIE_OVERVIEW)));
            favMovies.favMovieIDs.add(movieData.getString(movieData.getColumnIndex(COLUMN_MOVIE_ID))); // contain ID of the movie
        }while (movieData.moveToNext());
        datab.close();
        return favMovies;
    }
}