package com.chaibytes.bollytime;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BollyTimeMovies extends AppCompatActivity
        implements MyListFragment.OnFragmentInteractionListener {

    private TextView text;
    private Button btn1;
    private EditText editText;
    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolly_time_movies);

        text = (TextView) findViewById(R.id.displayText);
        btn1 = (Button) findViewById(R.id.getMoviesByYearBtnbutton);
        btn1.setOnClickListener(new ButtonOnClickListener());

        editText = (EditText) findViewById(R.id.editText);
        checkNetworkConnection();
        Toast.makeText(BollyTimeMovies.this, "onCreate() method called", Toast.LENGTH_SHORT).show();

        searchBtn = (Button) findViewById(R.id.btn_search_movies);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchableActivity.class);
                intent.putExtra("year", editText.getText().toString());
                Toast.makeText(getApplicationContext(), "Calling SearchableActivity", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    private void checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //Toast.makeText(this, "Network Connected", Toast.LENGTH_SHORT).show();
        } else {
            // display an error
            text.setText("No network connection");
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(BollyTimeMovies.this, "onDestroy() method called", Toast.LENGTH_LONG).show();
    }

    private class ButtonOnClickListener implements View.OnClickListener {
         @Override
         public void onClick(View v) {
             // Calls the Fragment to display the movies list for that year
             String year = editText.getText().toString();
             Bundle args = new Bundle();
             args.putString("year", year);
             // Call a ListFragment that displays the movies
             FragmentManager fragmentManager = getFragmentManager();

             MyListFragment fragment = (MyListFragment) fragmentManager.findFragmentById(R.id.list_frag);
             if (fragment == null) {
                 MyListFragment newfragment = new MyListFragment();
                 // Passing data from Activity to fragment
                 newfragment.setArguments(args);
                 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                 // Replace will clean up the space instead of over writing
                 fragmentTransaction.replace(R.id.main_frag, newfragment);
                 fragmentTransaction.commit();
             }


             // Store and Retrieve Movies to a SQLLite databases
//             MoviesDatabaseHelper helper = new MoviesDatabaseHelper(getApplicationContext());
//             helper.addMovie();
//             List<String> strArr = helper.getMovie();
//             Toast.makeText(getApplicationContext(), strArr.get(1), Toast.LENGTH_LONG).show();
         }

    }

    // Call back method from the Fragment: Passing data from Fragment to Activity
    @Override
    public void onFragmentInteraction(String movieName) {
        // DO something with the value returned
        Toast.makeText(this, movieName, Toast.LENGTH_SHORT).show();
    }

    // Async Task class
    private class DownloadFromUrlAsync extends AsyncTask<String, Void, List<Movie>> {


        @Override
        protected List<Movie> doInBackground(String... params) {
            try {
                NetworkUtil myUtil = new NetworkUtil();
                return myUtil.getMoviesByYear(params[0]);
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Movie> list) {
            // Do something for displaying the list of Movies
            if (list == null) {
                text.setText("Unable to parse and get results");
            } else {
                // Do something else
            }
        }

    }

}
