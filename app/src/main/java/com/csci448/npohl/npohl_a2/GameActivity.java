package com.csci448.npohl.npohl_a2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.UUID;

/**
 * Created by Nate on 2/14/2017.
 */

public class GameActivity extends FragmentActivity {

    public static final String OPTION_OBJECT = "com.csci448.npohl_a2.options";

    public static Intent newIntent(Context packageContext, UUID optionId) {
        Intent intent = new Intent(packageContext, GameActivity.class);
        intent.putExtra(OPTION_OBJECT, optionId);
        return intent;
    }

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
