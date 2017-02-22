package com.csci448.npohl.npohl_a2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by Nate on 2/14/2017.
 */

public class GameFragment extends Fragment {

    private TextView mWinText;
    private TextView mEmpireText;
    private TextView mStormcloakText;
    private TextView mDrawText;

    private Button mTopLeft;
    private Button mTopMiddle;
    private Button mTopRight;
    private Button mCenterLeft;
    private Button mCenterMiddle;
    private Button mCenterRight;
    private Button mBottomLeft;
    private Button mBottomMiddle;
    private Button mBottomRight;

    private Button mNewGame;
    private Button mGoBack;

    private Options mOptions;
    private Game mGame;

    private int mEmpireWins;
    private int mStormcloakWins;
    private int mDraws;
    private int mTurns;
    private boolean mActiveGame;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID optionId = (UUID) getActivity().getIntent().getSerializableExtra(OptionsActivity.OPTION_OBJECT);
        mOptions = Options.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        mWinText = (TextView) v.findViewById(R.id.end_game_dialog);

        //start a new game whenever we come back to this activity
        setupGame();

        mTopLeft = (Button) v.findViewById(R.id.game_button_1_1);
        mTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mTopLeft, 0, 0);
            }
        });

        mTopMiddle = (Button) v.findViewById(R.id.game_button_1_2);
        mTopMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mTopMiddle, 0, 1);
            }
        });

        mTopRight= (Button) v.findViewById(R.id.game_button_1_3);
        mTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mTopRight, 0, 2);
            }
        });

        mCenterLeft = (Button) v.findViewById(R.id.game_button_2_1);
        mCenterLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mCenterLeft, 1, 0);
            }
        });

        mCenterMiddle = (Button) v.findViewById(R.id.game_button_2_2);
        mCenterMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mCenterMiddle, 1, 1);
            }
        });

        mCenterRight = (Button) v.findViewById(R.id.game_button_2_3);
        mCenterRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mCenterRight, 1, 2);
            }
        });

        mBottomLeft = (Button) v.findViewById(R.id.game_button_3_1);
        mBottomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mBottomLeft, 2, 0);
            }
        });

        mBottomMiddle = (Button) v.findViewById(R.id.game_button_3_2);
        mBottomMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mBottomMiddle, 2, 1);
            }
        });

        mBottomRight = (Button) v.findViewById(R.id.game_button_3_3);
        mBottomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mBottomRight, 2, 2);
            }
        });

        mNewGame = (Button) v.findViewById(R.id.button_new_game);
        mNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupGame();
                clearButtons();
            }
        });

        mGoBack = (Button) v.findViewById(R.id.button_go_back);
        mGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return v;
    }

    private void setupGame() {
        mGame = new Game();
        if (mOptions.isEmpireGoesFirst()) {
            mGame.setEmpireTurn(true);
        }
        mTurns = 0;
        mActiveGame = true;
        mWinText.setText(R.string.active_game_dialog);
    }

    private void clearButtons() {
        mTopLeft.setText("");
        mTopMiddle.setText("");
        mTopRight.setText("");

        mCenterLeft.setText("");
        mCenterMiddle.setText("");
        mCenterRight.setText("");

        mBottomLeft.setText("");
        mBottomMiddle.setText("");
        mBottomRight.setText("");
    }

    private void updateGame(int row, int col) {

        char[] edit = new char[3];
        if (row == 0) {
            edit = mGame.getTopRow();
        }
        else if (row == 1) {
            edit = mGame.getMidRow();
        }
        else if (row == 2){
            edit = mGame.getLowRow();
        }

        if (mGame.isEmpireTurn()) {
            edit[col] = 'x';

        }
        else {
            edit[col] = 'o';
        }

        if (row == 0) {
            mGame.setTopRow(edit);
        }
        else if (row == 1) {
            mGame.setMidRow(edit);
        }
        else if (row == 2){
            mGame.setLowRow(edit);
        }
    }

    private void handleClick(Button b, int row, int col) {
        boolean empireTurn = mGame.isEmpireTurn();
        String buttonText = b.getText().toString();
        if (buttonText != "x" && buttonText != "o" && mActiveGame) {
            updateGame(row, col);
            if (empireTurn) {
                b.setText("x");
            } else {
                b.setText("o");
            }
            char win = mGame.checkWin();
            if (win == 'x') {
                mWinText.setText(R.string.empire_win_dialog);
                mActiveGame = false;
            } else if (win == 'o') {
                mWinText.setText(R.string.stormcloak_win_dialog);
                mActiveGame = false;
            } else if (++mTurns == 9) {
                mWinText.setText(R.string.tie_dialog);
                mActiveGame = false;
            }
            mGame.setEmpireTurn(!empireTurn);
        }
    }
}
