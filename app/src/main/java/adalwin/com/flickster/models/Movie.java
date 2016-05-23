package adalwin.com.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aramar1 on 5/20/16.
 */
public class Movie {
    String posterPath;
    String originalTitle;
    String overview;

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


    public Movie(JSONObject jsonObject)throws JSONException{
            this.posterPath =jsonObject.getString("poster_path");
            this.originalTitle = jsonObject.getString("original_title");
            this.overview = jsonObject.getString("overview");
    }

    public static ArrayList<Movie> fromJsonArray(JSONArray jsonArray){

        ArrayList<Movie> moviesArray=new ArrayList<Movie>();
        for (int i=0;i<jsonArray.length();i++){
            try {

                moviesArray.add(new Movie(jsonArray.getJSONObject(i)));
            }
            catch(JSONException jse){
                jse.printStackTrace();
                }
            }
            return moviesArray;
        }


}
