package com.lucaskoch.movieapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.lucaskoch.movieapp.listeners.OnCastDetail;
import com.lucaskoch.movieapp.listeners.OnDetailListener;
import com.lucaskoch.movieapp.listeners.OnSearchApiListener;
import com.lucaskoch.movieapp.listeners.OnSearchVideoListener;
import com.lucaskoch.movieapp.model.CastCrew;
import com.lucaskoch.movieapp.model.SearchApiResponse;
import com.lucaskoch.movieapp.model.SearchArrayObject;
import com.lucaskoch.movieapp.model.Video;
import com.lucaskoch.movieapp.model.VideosModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void searchMovies(OnSearchApiListener listener, String movie_name) {
        getMovies getMovies = retrofit.create(RequestManager.getMovies.class);
        Call<SearchApiResponse> call = getMovies.callMovies(
                "715369ad83702bbb01d37884acb031ed",
                "1",
                movie_name);

        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<SearchApiResponse> call, @NonNull Response<SearchApiResponse> response) {


                //Si el servidor esta caido, de otra manera no tira error en la respuesta
                if (response.code() == 200) {
                    listener.onResponseInfo(response.body());
                } else {
                    Toast.makeText(context, "Couldn't fetch data!!", Toast.LENGTH_SHORT).show();
                    MainActivity.dialog.dismiss();
                    return;
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchApiResponse> call, @NonNull Throwable t) {
                listener.onErrorInfo(t.getMessage());
            }
        });
    }

    public void searchDetails(OnDetailListener listener, String movie_id) {
        getDetails getDetails = retrofit.create(RequestManager.getDetails.class);
        Call<SearchArrayObject> call = getDetails.callDetails(
                movie_id,
                "715369ad83702bbb01d37884acb031ed");

        call.enqueue(new Callback<SearchArrayObject>() {
            @Override
            public void onResponse(@NonNull Call<SearchArrayObject> call, @NonNull Response<SearchArrayObject> response) {
                //Si el servidor esta caido, de otra manera no tira error en la respuesta
                if (response.code() == 200) {
                    listener.onResponseInfo(response.body());
                } else {
                    Toast.makeText(context, "Couldn't fetch data!!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchArrayObject> call, @NonNull Throwable t) {
                listener.onErrorInfo(t.getMessage());
            }
        });
    }

    public void searchCast(OnCastDetail listener, String movie_id) {
        getCast getCast = retrofit.create(RequestManager.getCast.class);
        Call<CastCrew> call = getCast.callCast(
                movie_id,
                "715369ad83702bbb01d37884acb031ed");

        call.enqueue(new Callback<CastCrew>() {
            @Override
            public void onResponse(@NonNull Call<CastCrew> call, @NonNull Response<CastCrew> response) {
                //Si el servidor esta caido, de otra manera no tira error en la respuesta
                if (response.code() == 200) {
                    listener.onResponseInfo(response.body());
                } else {
                    Toast.makeText(context, "Couldn't fetch data!!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(@NonNull Call<CastCrew> call, @NonNull Throwable t) {
                listener.onErrorInfo(t.getMessage());
            }
        });
    }

    public void searchVideo(OnSearchVideoListener listener, String movie_id) {
        getVideo getVideo = retrofit.create(RequestManager.getVideo.class);
        Call<Video> call = getVideo.callVideo(
                movie_id,
                "715369ad83702bbb01d37884acb031ed");

        call.enqueue(new Callback<Video>() {
            @Override
            public void onResponse(@NonNull Call<Video> call, @NonNull Response<Video> response) {
                //Si el servidor esta caido, de otra manera no tira error en la respuesta
                if (response.code() == 200) {
                    listener.onResponseInfo(response.body());
                } else {
                    Toast.makeText(context, "Couldn't fetch data!!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(@NonNull Call<Video> call, @NonNull Throwable t) {
                listener.onErrorInfo(t.getMessage());
            }
        });
    }

    public void searchPopular(OnSearchApiListener listener) {
        getPopular getPopular = retrofit.create(RequestManager.getPopular.class);
        Call<SearchApiResponse> call = getPopular.callPopular(
                "715369ad83702bbb01d37884acb031ed",
                "1");

        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<SearchApiResponse> call, @NonNull Response<SearchApiResponse> response) {


                //Si el servidor esta caido, de otra manera no tira error en la respuesta
                if (response.code() == 200) {
                    listener.onResponseInfo(response.body());
                } else {
                    Toast.makeText(context, "Couldn't fetch data!!", Toast.LENGTH_SHORT).show();
                    MainActivity.dialog.dismiss();
                    return;
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchApiResponse> call, @NonNull Throwable t) {
                listener.onErrorInfo(t.getMessage());
            }
        });
    }

    public interface getMovies {

        //https://api.themoviedb.org/3/search/movie?api_key=715369ad83702bbb01d37884acb031ed&language=en-US&page=1&include_adult=false&query=Jack+Reacher
        @GET("3/search/movie")
        Call<SearchApiResponse> callMovies(
                @Query("api_key") String key,
                @Query("page") String page,
                @Query("query") String movie_name
        );

    }

    public interface getDetails {
        @GET("3/movie/{movie_id}")
        Call<SearchArrayObject> callDetails(
                @Path("movie_id") String movie_id,
                @Query("api_key") String key
        );
    }

    public interface getCast {
        @GET("3/movie/{movie_id}/credits")
        Call<CastCrew> callCast(
                @Path("movie_id") String movie_id,
                @Query("api_key") String key
        );
    }

    public interface getVideo {
        @GET("3/movie/{movie_id}/videos")
        Call<Video> callVideo(
                @Path("movie_id") String movie_id,
                @Query("api_key") String key
        );
    }

    public interface getPopular {
        @GET("3/movie/popular")
        Call<SearchApiResponse> callPopular(
                @Query("api_key") String key,
                @Query("page") String page
        );
    }

}
