package com.chaibytes.bollytime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pdebadarshini on 8/6/16.
 */
public class MoviesAdapter2 extends BaseAdapter {

    private Context context;
    private List<Movie> movies;
    List<Movie> list;

    public MoviesAdapter2(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
        list = new ArrayList<Movie>();
        list.addAll(this.movies);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.textviewformovie, null);
            holder = new ViewHolder();
            holder.title = (TextView)convertView.findViewById(R.id.movietitle);
            holder.subTitle = (TextView)convertView.findViewById(R.id.moviedescription);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.title.setText(movies.get(position).getTitle());
        holder.subTitle.setText(movies.get(position).getGenre());

        String url = movies.get(position).getPosterPath();
        Picasso.with(context).load(url).into(holder.imageView);

        return convertView;

    }

    public class ViewHolder {
        TextView title, subTitle;
        ImageView imageView;
    }


    public void filter(String queryText) {
        String text = queryText.toLowerCase();
        movies.clear();

        if (text.length() == 0) {
            movies.addAll(list);
        } else {
            for (Movie m : list) {
                if (text.length() != 0 && m.getTitle().toLowerCase().contains(text)) {
                    movies.add(m);
                }
            }
        }
        notifyDataSetChanged();
    }
}
