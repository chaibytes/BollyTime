package com.chaibytes.bollytime;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MyListFragment extends Fragment {

    private OnFragmentInteractionListener mListener; // Activity Instance
    private String year;
    List<Movie> movies;
    ListView myList;
    View v;
    MoviesAdapter arrayAdapter;
    public MyListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_my_list, container, false);
        myList = (ListView) v.findViewById(R.id.listViewFragment);
        Bundle args = getArguments();
        year = args.getString("year");
        displayMoviesByYear();
        return v;
    }

    // Send data back to the fragment

    public void onButtonPressed(String movieName) {
        if (mListener != null) {
            mListener.onFragmentInteraction(movieName);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void displayMoviesByYear() {
        //Using Async Task to do the background connection to Webserver
        //new DownloadFromUrlAsync().execute(year);

        // Using ExecutorService to do the background connection
        MoviesExecutor mv = MoviesExecutor.getInstance();
        Future<List<Movie>> future = mv.getMoviesByYear(year);
        List<Movie> list = null;
        try {
            // Do something else first
            Thread.sleep(2000);
            list = future.get();
        } catch (ExecutionException e) {

        } catch (InterruptedException e) {

        }
        if (list != null) {
            movies = list;
            arrayAdapter = new MoviesAdapter(getActivity(), R.layout.text_view, movies);
            myList.setAdapter(arrayAdapter);
        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String str);
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
            if (list != null) {
                movies = list;
                arrayAdapter = new MoviesAdapter(getActivity(), R.layout.text_view, movies);
                myList.setAdapter(arrayAdapter);
            }
        }

    }
}
