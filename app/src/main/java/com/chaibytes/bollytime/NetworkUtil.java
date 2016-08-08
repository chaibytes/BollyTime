package com.chaibytes.bollytime;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by pdebadarshini on 7/8/16.
 */
public class NetworkUtil {
    private static final Gson gson = new Gson();
    private static String downloadUrl(String str) throws IOException {
        String result;
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(str);
            // Download url
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            return readInputStream(inputStream);

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
    }

    private static String readInputStream(InputStream in) throws IOException {
        Reader input = new InputStreamReader(in, "UTF-8");

        char[] bufChar = new char[8 * 1024];
        StringBuilder sb = new StringBuilder();
        int numOfCharsRead;
        while ((numOfCharsRead = (input.read(bufChar, 0, bufChar.length))) != -1) {
            sb.append(bufChar, 0, numOfCharsRead);
        }
        input.close();
        return (new String(sb));
    }

    public static List<Movie> getMoviesByYear(String year) throws IOException{
        String url = "https://api.cinemalytics.com/v1/movie/year/" + year +
                "/?auth_token=C921EC5E129E43820A1E4B3F277CAE84";
        String response = downloadUrl(url);
        //Log.d("NetworkUtil", response);
        Type type = new TypeToken<List<Movie>>(){}.getType();
        return gson.fromJson(response, type);
    }
}
