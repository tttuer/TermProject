package com.example.pca.termproject1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

/**
 * Created by pca on 2016-11-25.
 */

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    MapsActivity mapsActivity;
    TaskActivity taskActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        mapsActivity = new MapsActivity();
        taskActivity = new TaskActivity();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, taskActivity).commit();

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("일정"));
        tabs.addTab(tabs.newTab().setText("지도"));
        tabs.addTab(tabs.newTab().setText("메모"));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity","선택된 탭 : " + position);

                Fragment selected = null;
                if(position == 0){
                    selected = taskActivity;
                } else if(position == 1){
                    selected = mapsActivity;
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
