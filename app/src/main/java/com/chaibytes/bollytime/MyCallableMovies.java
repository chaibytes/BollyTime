package com.chaibytes.bollytime;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by pdebadarshini on 7/10/16.
 */
public class MyCallableMovies implements Callable<List<Movie>> {
    private final String year;

    public MyCallableMovies(String year) {
        this.year = year;
    }
    @Override
    public List<Movie> call() throws Exception {
        return NetworkUtil.getMoviesByYear(year);
    }
}
