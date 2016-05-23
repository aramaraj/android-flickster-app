package adalwin.com.flickster;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adalwin.com.flickster.models.MovieExtras;
import cz.msebera.android.httpclient.Header;
public class XtraMovieActivity extends AppCompatActivity {
    Intent v;
    public static ImageView ivPoster;
    public static TextView tvTitle;
    public static TextView tvRated;
    public static TextView tvRating;
    public static TextView tvYear;
    public static TextView tvRuntime;
    public static TextView tvDirector;
    public static TextView tvActors;
    //public Button buttonClose;
   public  ImageButton buttonClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xtra_movie);

        ivPoster = (ImageView)findViewById(R.id.ivPoster);
        tvTitle= (TextView)findViewById(R.id.tvExtraTitle);
        tvRated= (TextView)findViewById(R.id.tvExtraRated);
        tvRating= (TextView)findViewById(R.id.tvRating);
        tvYear= (TextView)findViewById(R.id.tvExtraYear);
        tvRuntime= (TextView)findViewById(R.id.tvExtraRuntime);
        tvDirector = (TextView)findViewById(R.id.tvDirector);
        tvActors=(TextView)findViewById(R.id.tvActors);
        //buttonClose=(Button)findViewById(R.id.buttonClose) ;
        buttonClose=(ImageButton)findViewById(R.id.imgclose) ;


        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivPoster.setImageResource(0);
        v = getIntent();
        String todo = (String) v.getSerializableExtra("searchMovie");//Captain+America%3A+Civil+War
        //GEt the movies we want to Display
        String url = "http://www.omdbapi.com/?t="+todo+"&y=&plot=short&r=json";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieResults = null;
                try {
                    MovieExtras movieExtras  = new MovieExtras(response);
                    fillFields(movieExtras,getBaseContext());
                    Log.d("DEBUG", response.toString());
                } catch (JSONException je) {
                    je.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

}

    public static void fillFields(MovieExtras moviesExtras, Context context){
        tvTitle.setText(moviesExtras.getTitle());
        tvRated.setText(moviesExtras.getRated());
        tvRating.setText(moviesExtras.getImdbRating());
        tvYear.setText("| "+moviesExtras.getYear());
        tvRuntime.setText("| "+moviesExtras.getRuntime());
        tvDirector.setText(moviesExtras.getDirector());
        tvActors.setText(moviesExtras.getActors());
        ivPoster.setImageResource(0);
        int orientation =context.getResources().getConfiguration().orientation;
        if(orientation== Configuration.ORIENTATION_LANDSCAPE){
            Picasso.with(context).load(moviesExtras.getPosterImage()).into(ivPoster);
            Log.d("DEBUG", "Configuration.ORIENTATION_LANDSCAPE"+ Configuration.ORIENTATION_LANDSCAPE);
        }else{
            Picasso.with(context).load(moviesExtras.getPosterImage()).into(ivPoster);
            Log.d("DEBUG", "Configuration.ORIENTATION_PORTRAIT"+ Configuration.ORIENTATION_PORTRAIT);
        }

    };




}
