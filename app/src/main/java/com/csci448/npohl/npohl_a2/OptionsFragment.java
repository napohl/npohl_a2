package com.csci448.npohl.npohl_a2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.UUID;

import static android.view.ViewConfiguration.get;

/**
 * Created by Nate on 2/14/2017.
 */

public class OptionsFragment extends Fragment {

    private CheckBox mTwoPlayerBox;
    private CheckBox mEmpireBox;
    private Button mClearScore;

    private Options mOptions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID optionId = (UUID) getActivity().getIntent().getSerializableExtra(OptionsActivity.OPTION_OBJECT);
        mOptions = Options.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_options, container, false);
        mTwoPlayerBox = (CheckBox) v.findViewById(R.id.two_player_checkbox);
        mTwoPlayerBox.setChecked(mOptions.isTwoPlayers() == true);
        mTwoPlayerBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mOptions.setTwoPlayers(isChecked);
            }
        });

        mEmpireBox = (CheckBox) v.findViewById(R.id.empire_first_checkbox);
        mEmpireBox.setChecked(mOptions.isEmpireGoesFirst() == true);
        mEmpireBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mOptions.setEmpireoesFirst(isChecked);
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
