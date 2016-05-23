package adalwin.com.flickster.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aramar1 on 5/20/16.
 */
public class MovieExtras {

    String title;
    String rated;
    String year;
    String runtime;
    String imdbRating;
    String imdbVotes;
    String director;
    String actors;

    String posterImage;

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }


    public MovieExtras(JSONObject jsonObject)throws JSONException {
        this.title = jsonObject.getString("Title");
        this.rated = jsonObject.getString("Rated");
        this.year = jsonObject.getString("Year");
        this.runtime = jsonObject.getString("Runtime");
        this.imdbRating =jsonObject.getString("imdbRating");
        this.imdbVotes =jsonObject.getString("imdbVotes");
        this.director = jsonObject.getString("Director");
        this.actors = jsonObject.getString("Actors");
        this.posterImage=jsonObject.getString("Poster");

    }

}
