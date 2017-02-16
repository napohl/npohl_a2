package com.csci448.npohl.npohl_a2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Nate on 2/14/2017.
 */

public class GameActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.activity_fragment);

        if (fragment == null) {
            fragment = new GameFragment();
            fm.beginTransaction().add(R.id.activity_fragment, fragment).commit();
        }
    }
}
