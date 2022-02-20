package com.lucaskoch.movieapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lucaskoch.movieapp.adapters.HomeRecycleAdapter;
import com.lucaskoch.movieapp.listeners.OnMovieClickListener;
import com.lucaskoch.movieapp.listeners.OnSearchApiListener;
import com.lucaskoch.movieapp.model.SearchApiResponse;



public class MainActivity extends AppCompatActivity implements OnMovieClickListener {
    SearchView search_view;
    RecyclerView recycler_view_home;
    HomeRecycleAdapter adapter;
    RequestManager manager;
    RequestManager popularManager;
    static ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_view = (SearchView) findViewById(R.id.search_view);
        recycler_view_home = (RecyclerView) findViewById(R.id.recycler_view_home);
      /*  String text = getBaseContext().getResources().getString(R.string.movies_search_hint);*/

        dialog = new ProgressDialog(this);
        manager = new RequestManager(this);
        popularManager = new RequestManager(this);
        popularManager.searchPopular(popularListener);

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Please wait...");
                dialog.show();
                manager.searchMovies(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }
    private final OnSearchApiListener popularListener = new OnSearchApiListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onResponseInfo(SearchApiResponse response) {
            /*Log.v("Tag", "response " + response.getResults().toString());*/
            showPopular(response);
        }

        @Override
        public void onErrorInfo(String message) {

        }
    };
    private final OnSearchApiListener listener = new OnSearchApiListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onResponseInfo(SearchApiResponse response) {
            dialog.dismiss();
            if (response.getResults().isEmpty()) {
                Toast.makeText(MainActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                return;
            }
           /* List<SearchArrayObject> movies = new ArrayList<>(response.getResults());
            Log.v("Tag", "Response = "+ movies.toString());*/
            showResult(response);
        }

        @Override
        public void onErrorInfo(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, "An Error ocurred!", Toast.LENGTH_SHORT).show();
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showPopular(SearchApiResponse response){
        recycler_view_home.setHasFixedSize(true);
        recycler_view_home.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        //Primer parametro this
        adapter = new HomeRecycleAdapter(/*this,*/response.getResults(), this);
        recycler_view_home.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showResult(SearchApiResponse response) {
        recycler_view_home.setHasFixedSize(true);
        recycler_view_home.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        //Primer parametro this
        adapter = new HomeRecycleAdapter(/*this,*/response.getResults(), this);
        recycler_view_home.setAdapter(adapter);
    }

    @Override
    public void onMovieClick(int id) {
        startActivity(new Intent(MainActivity.this, DetailsActivity.class).putExtra("data", new String(String.valueOf(id))));
    }
}