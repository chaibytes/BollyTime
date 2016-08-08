package com.chaibytes.bollytime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pdebadarshini on 7/10/16.
 */
public class MoviesDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Movies.db";
    private static final String TABLE_NAME = "MoviesByYear";

    private static final String MOVIE_NAME = "name";
    private static final String MOVIE_DESCRIPTION = "description";

    public MoviesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + MOVIE_NAME +
                " STRING PRIMARY KEY," + MOVIE_DESCRIPTION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addMovie() { // Change to recieve movie
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MOVIE_NAME, "ABC");
        contentValues.put(MOVIE_DESCRIPTION, "Hello World!");

        int row = db.update(TABLE_NAME, contentValues, MOVIE_NAME + "= ?", new String[] {"ABC"});
        if (row == 1 ) {
            // Values
        } else {
            db.insertOrThrow(TABLE_NAME, null, contentValues);
            db.setTransactionSuccessful();
        }
        db.endTransaction();
    }

    public List<String> getMovie() {
        String query = "SELECT * FROM " + TABLE_NAME;
        // Call the Database to store the movies for that year
        List<String> movie = new ArrayList<String>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String movieName = cursor.getString(cursor.getColumnIndex(MOVIE_NAME));
            movie.add(movieName);
            String desc = cursor.getString(cursor.getColumnIndex(MOVIE_DESCRIPTION));
            movie.add(desc);
        }
        return movie;
    }
}
