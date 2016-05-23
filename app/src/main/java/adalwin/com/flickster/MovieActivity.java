package adalwin.com.flickster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import adalwin.com.flickster.adapter.MoviesAdapter;
import adalwin.com.flickster.models.Movie;
import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {
    ArrayList<Movie> movies;
    MoviesAdapter moviesAdapter;
    ListView lvMovies;
    private int ACTIVITY_ID=10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        lvMovies=(ListView)findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(this,movies);
        lvMovies.setAdapter(moviesAdapter);



        //GEt the movies we want to Display
        String url="https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieResults = null;
                try {
                    movieResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJsonArray(movieResults));
                    moviesAdapter.notifyDataSetChanged();
                    Log.d("DEBUG",movies.toString());
                }catch(JSONException je){
                    je.printStackTrace();

                }

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                launchEditActivityView((Movie)lvMovies.getItemAtPosition(position));

            }
        });



    }

    public void launchEditActivityView(Movie movie) {
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(this, XtraMovieActivity.class);
        i.putExtra("searchMovie",(Serializable)movie.getOriginalTitle());
        startActivity(i);
        //startActivityForResult(i, ACTIVITY_ID);

    }




}
