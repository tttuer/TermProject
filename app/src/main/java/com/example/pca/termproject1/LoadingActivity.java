package com.example.pca.termproject1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.State;
import com.github.jorgecastillo.clippingtransforms.WavesClippingTransform;
import com.github.jorgecastillo.listener.OnStateChangeListener;

/**
 * Created by pca on 2016-12-01.
 */

public class LoadingActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        FillableLoader fl = (FillableLoader) findViewById(R.id.fillableLoader);
        fl.setSvgPath(Paths.GITHUB);
        fl.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(int i) {
                if (i == State.FINISHED) {
                    startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                    LoadingActivity.this.finish();
                }
            }
        });
        fl.reset();
        fl.start();
    }

    private class splashhandler implements Runnable{
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class)); // 로딩이 끝난후 이동할 Activity
            LoadingActivity.this.finish(); // 로딩페이지 Activity Stack에서 제거
        }
    }
}
