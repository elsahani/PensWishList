package sg.edu.rp.c346.id20023841.penswishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Pens> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<Pens> objects) {
        super(context,resource,objects);

        parent_context = context;
        layout_id = resource;
        versionList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvPenName = rowView.findViewById(R.id.textViewShowName);
        TextView tvPenPrice = rowView.findViewById(R.id.textViewShowPrice);
        TextView tvPenColour = rowView.findViewById(R.id.textViewShowColour);
        TextView tvPenNibSize = rowView.findViewById(R.id.textViewShowNibSize);
        RatingBar rbs = rowView.findViewById(R.id.ratingBarShowStar);
        ImageView image = rowView.findViewById(R.id.imageViewNew);

        // Obtain the Android Version information based on the position
        Pens currentVersion = versionList.get(position);

        // Set values to the TextView to display the corresponding information
        tvPenName.setText(currentVersion.getName());
        tvPenPrice.setText(Double.toString(currentVersion.getPrice()));
        tvPenColour.setText(currentVersion.getColour());
        tvPenNibSize.setText(Double.toString(currentVersion.getNibsize()));
        rbs.setRating(currentVersion.getStars());

        return rowView;
    }

}

