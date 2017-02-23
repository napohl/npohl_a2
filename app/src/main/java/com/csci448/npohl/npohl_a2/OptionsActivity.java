package com.csci448.npohl.npohl_a2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Nate on 2/14/2017.
 */

public class OptionsActivity extends FragmentActivity {

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, OptionsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.activity_fragment);

        if (fragment == null) {
            fragment = new OptionsFragment();
            fm.beginTransaction().add(R.id.activity_fragment, fragment).commit();
        }
    }
}
