package com.chaibytes.bollytime;

import android.util.Log;

import java.io.IOException;

/**
 * Created by pdebadarshini on 7/10/16.
 */
public class MyRunnable implements Runnable {

    public MyRunnable(String year) {
    }
    @Override
    public void run() {
        try {
            Log.d("Runnable", Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

    }
}
