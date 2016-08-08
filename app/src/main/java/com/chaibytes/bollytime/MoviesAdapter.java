package com.chaibytes.bollytime;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by pdebadarshini on 7/9/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie> {

    private Context context;
    private List<Movie> movies;
    private int resource;

    public MoviesAdapter(Context context, int resourceId, List<Movie> movies) {
        super(context, resourceId, movies);
        this.context = context;
        this.movies = movies;
        this.resource = resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.text_view, null);
            holder = new ViewHolder();
            holder.tv = (TextView)convertView.findViewById(R.id.textView1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tv.setText(movies.get(position).getTitle());
        return convertView;
    }

    private static class ViewHolder {
        public TextView tv;
    }
}
