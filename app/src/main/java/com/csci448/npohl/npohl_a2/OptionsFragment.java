package com.csci448.npohl.npohl_a2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by Nate on 2/14/2017.
 */

public class OptionsFragment extends Fragment {

    private CheckBox mTwoPlayers;
    private CheckBox mXFirst;
    private Button mClearScore;

    private Options mOptions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_options, container, false);
        mTwoPlayers = (CheckBox) v.findViewById(R.id.two_player_checkbox);
        mTwoPlayers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //TODO: change value of option
            }
        });

        mXFirst = (CheckBox) v.findViewById(R.id.x_first_checkbox);
        mXFirst.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //TODO: change value of option
            }
        });

        mClearScore = (Button) v.findViewById(R.id.clear_score_button);
        mClearScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: pass in score as an extra and set to zero here
            }
        });
        return v;
    }
}
