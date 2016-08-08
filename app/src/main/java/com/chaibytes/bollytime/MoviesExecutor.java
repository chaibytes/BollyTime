package com.chaibytes.bollytime;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by pdebadarshini on 7/10/16.
 */
public class MoviesExecutor {

    private static MoviesExecutor myExecutor;
    private static final int POOL_SIZE = 5;

    public final ExecutorService service;
    private final int poolSize;

    private MoviesExecutor(int poolSize) {
        this.poolSize = poolSize;
        service = Executors.newFixedThreadPool(this.poolSize);
    }

    // Needs to be called from Class itself
    public synchronized static MoviesExecutor getInstance() {
        if (myExecutor == null) {
            myExecutor = new MoviesExecutor(POOL_SIZE);
        }
        return myExecutor;
    }

    public Future<List<Movie>> getMoviesByYear(final String year) {
        return service.submit(new Callable<List<Movie>>() {
                                  @Override
                                  public List<Movie> call() throws Exception {
                                      return NetworkUtil.getMoviesByYear(year);
                                  }
                              });
        // return service.submit(new MyCallableMovies(year));
    }
}
