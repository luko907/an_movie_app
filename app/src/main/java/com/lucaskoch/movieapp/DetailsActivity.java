package com.lucaskoch.movieapp;

import static com.lucaskoch.movieapp.MainActivity.dialog;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lucaskoch.movieapp.listeners.OnCastDetail;
import com.lucaskoch.movieapp.listeners.OnDetailListener;
import com.lucaskoch.movieapp.model.CastCrew;
import com.lucaskoch.movieapp.model.SearchArrayObject;
import com.squareup.picasso.Picasso;


public class DetailsActivity extends AppCompatActivity {
    ImageView backdrop_background_view, poster_view;
    TextView title_view, year_release_view, runtime_view, average_score_view, genre_view,
            btn_trailer_view, overview_view, director_view, studios_view, actors_view;
    RequestManager manager;
    RequestManager castManager;
    LinearLayout title_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        backdrop_background_view = findViewById(R.id.backdrop_background_view);
        poster_view = findViewById(R.id.poster_view);
        title_view = findViewById(R.id.title_view);
        year_release_view = findViewById(R.id.year_release_view);
        runtime_view = findViewById(R.id.runtime_view);
        average_score_view = findViewById(R.id.average_score_view);
        genre_view = findViewById(R.id.genre_view);
        btn_trailer_view = findViewById(R.id.btn_trailer_view);
        overview_view = findViewById(R.id.overview_view);
        director_view = findViewById(R.id.director_view);
        studios_view = findViewById(R.id.studios_view);
        actors_view = findViewById(R.id.actors_view);
        title_layout = findViewById(R.id.title_layout);

        String movie_id = getIntent().getStringExtra("data");
        manager = new RequestManager(this);
        castManager = new RequestManager(this);

        manager.searchDetails(listener, movie_id);
        castManager.searchCast(castListener, movie_id);

        btn_trailer_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsActivity.this, TrailerActivity.class).putExtra("data", movie_id));
            }
        });
    }

    private final OnCastDetail castListener = new OnCastDetail() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onResponseInfo(CastCrew response) {
            /*Log.v("Tag", "OnCastDetail " + response.getCrew());*/
            actors_view.setText(response.getCast());
            director_view.setText(response.getCrew());


        }

        @Override
        public void onErrorInfo(String message) {
            dialog.dismiss();
            Toast.makeText(DetailsActivity.this, "An Error ocurred!", Toast.LENGTH_SHORT).show();
        }
    };

    private final OnDetailListener listener = new OnDetailListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onResponseInfo(SearchArrayObject response) {
            if (response.getTitle().isEmpty()) {
                Toast.makeText(DetailsActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult(response);
        }

        @Override
        public void onErrorInfo(String message) {
            dialog.dismiss();
            Toast.makeText(DetailsActivity.this, "An Error ocurred!", Toast.LENGTH_SHORT).show();
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    private void showResult(SearchArrayObject response) {
        title_view.setText(response.getTitle());
        if (response.getTitle().length() > 20) {
            LinearLayout.MarginLayoutParams params = (LinearLayout.MarginLayoutParams) title_layout.getLayoutParams();
            params.topMargin = 60;
        }
        Picasso.get().load(response.getBackdrop_path()).into(backdrop_background_view);
        Picasso.get().load(response.getPoster_path()).into(poster_view);
        year_release_view.setText(response.getRelease_date());
        runtime_view.setText(response.getRuntime() + " min");
        average_score_view.setText(response.getVote_average());
        overview_view.setText(response.getOverview());
        studios_view.setText(response.getProduction_companies());
        genre_view.setText(response.getGenres());


    }

}