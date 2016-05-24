package adalwin.com.flickster.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import adalwin.com.flickster.R;
import adalwin.com.flickster.models.Movie;

/**
 * Created by aramar1 on 5/20/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie>{


    private static class MovievViewHolder {
        ImageView ivPoster;
        TextView txTitle;
        TextView txOverview;
    }



    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie,movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Movie movie = (Movie)getItem(position);
        /** --Without view holder pattern--**/
       /* // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
        }
        // Lookup view for data population
        TextView  tvTitle= (TextView) convertView.findViewById(R.id.tvTitle);
        ImageView ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
        ivPoster.setImageResource(0);
        TextView tvOverview =(TextView)convertView.findViewById(R.id.tvOverview);
        // Populate the data into the template view using the data object
        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());
       String imageUri = movie.getPosterPath();
        ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
        Picasso.with(getContext()).load(imageUri).into(ivPoster);
        //Picasso.with(getContext()).load(imageUri).resize(100, 100).
                //centerCrop().into(ivPoster);
        /*Picasso.with(getContext()).load(imageUri).fit().centerCrop()
                .placeholder(R.drawable.user_placeholder)
                .error(R.drawable.user_placeholder_error)
                .into(imageView);
        */



        MovievViewHolder  viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new MovievViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

            viewHolder.txTitle = (TextView)convertView.findViewById(R.id.tvTitle);
            viewHolder.txOverview = (TextView)convertView.findViewById(R.id.tvOverview);

            viewHolder.ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
            viewHolder.ivPoster.setImageResource(0);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MovievViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.txTitle.setText(movie.getOriginalTitle());
        viewHolder.txOverview.setText(movie.getOverview());

        int orientation = getContext().getResources().getConfiguration().orientation;
        if(orientation== Configuration.ORIENTATION_LANDSCAPE){
            Picasso.with(getContext()).load(movie.getBackdropPath()).into(viewHolder.ivPoster);
            Log.d("DEBUG", "Configuration.ORIENTATION_LANDSCAPE"+ Configuration.ORIENTATION_LANDSCAPE);
        }else{
            Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.ivPoster);
            Log.d("DEBUG", "Configuration.ORIENTATION_PORTRAIT"+ Configuration.ORIENTATION_PORTRAIT);
        }
        return convertView;
    }
}
