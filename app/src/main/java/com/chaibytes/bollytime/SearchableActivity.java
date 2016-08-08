package com.chaibytes.bollytime;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SearchableActivity extends AppCompatActivity {

    private ListView listView;
    private List<Movie> moviesList;
    private MoviesAdapter2 moviesAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        Toast.makeText(this, "onCreate() method of SearchableActivity", Toast.LENGTH_SHORT).show();

        listView = (ListView) findViewById(R.id.listViewForMoviesFilter);

        // Get the intent verify the action and get the query

        Intent intent = getIntent();
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            doSearchQuery(query);
//        }
        String year = intent.getStringExtra("year");
        doSearchQuery(year);

    }

    private void doSearchQuery(String query) {
        // Display the search results in the listView
        MoviesExecutor moviesExecutor = MoviesExecutor.getInstance();
        Future<List<Movie>> future = moviesExecutor.getMoviesByYear(query);
        try {
            Thread.sleep(1000);
            moviesList = future.get();
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        }
        if (moviesList != null) {
            moviesAdapter2 = new MoviesAdapter2(this, moviesList);
            listView.setAdapter(moviesAdapter2);
        }
    }

    // Menu and Search related items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                moviesAdapter2.filter(query.toString().trim());
                listView.invalidate();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                moviesAdapter2.filter(newText.toString().trim());
                listView.invalidate();
                return true;

            }
        });

//        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
//            @Override
//            public boolean onMenuItemActionCollapse(MenuItem item) {
//                return true;
//            }
//
//            @Override
//            public boolean onMenuItemActionExpand(MenuItem item) {
//                return true;
//            }
//        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
