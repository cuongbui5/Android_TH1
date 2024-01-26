package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(Context context, List<User> users) {
        super(context, 0, users);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        // Lookup view for data population
        ImageView imageView = convertView.findViewById(R.id.ivImage);
        TextView textView = convertView.findViewById(R.id.tvData);

        // Populate the data into the template view using the data object
        if (user != null) {
            // Load image into ImageView using your preferred method (e.g., Glide, Picasso, or setImageURI)
            imageView.setImageURI(user.getImg());

            // Set text to TextView
            textView.setText(user.toString());
        }

        // Return the completed view to render on screen
        return convertView;
    }


}
