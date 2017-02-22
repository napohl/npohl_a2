package com.csci448.npohl.npohl_a2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Nate on 2/13/2017.
 */

public class WelcomeFragment extends Fragment {

    //private static final String SAVED_SETTINGS = "options";

    private Button mPlayButton;
    private Button mOptionsButton;
    private Button mQuitButton;

    private Options mOption;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOption = Options.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);

        mPlayButton = (Button) v.findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GameActivity.newIntent(getActivity(), mOption.getId());
                startActivity(intent);
            }
        });

        mOptionsButton = (Button) v.findViewById(R.id.options_button);
        mOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = OptionsActivity.newIntent(getActivity(), mOption.getId());
                startActivity(intent);
            }
        });

        mQuitButton = (Button) v.findViewById(R.id.quit_button);
        mQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        return v;
    }

    /*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_SETTINGS, mOption);
    }
    */

}
