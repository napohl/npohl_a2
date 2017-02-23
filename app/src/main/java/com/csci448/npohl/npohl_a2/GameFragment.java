package com.csci448.npohl.npohl_a2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Nate on 2/14/2017.
 */

public class GameFragment extends Fragment {

    private TextView mWinText;
    private TextView mEmpireText;
    private TextView mStormcloakText;
    private TextView mDrawText;

    private ImageButton mTopLeft;
    private ImageButton mTopMiddle;
    private ImageButton mTopRight;
    private ImageButton mCenterLeft;
    private ImageButton mCenterMiddle;
    private ImageButton mCenterRight;
    private ImageButton mBottomLeft;
    private ImageButton mBottomMiddle;
    private ImageButton mBottomRight;

    private Button mNewGame;
    private Button mGoBack;

    private Options mOptions;
    private Score mScore;
    private Game mGame;

    private int mTurns;
    private boolean mActiveGame;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOptions = Options.get(getActivity());
        mScore = Score.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        mWinText = (TextView) v.findViewById(R.id.end_game_dialog);
        mEmpireText = (TextView) v.findViewById(R.id.empire_wins);
        mStormcloakText = (TextView) v.findViewById(R.id.stormcloak_wins);
        mDrawText = (TextView) v.findViewById(R.id.tie_text);
        updateScores();

        //start a new game whenever we come back to this activity
        setupGame();

        mTopLeft = (ImageButton) v.findViewById(R.id.game_button_1_1);
        mTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mTopLeft, 0, 0);
               }
        });

        mTopMiddle = (ImageButton) v.findViewById(R.id.game_button_1_2);
        mTopMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mTopMiddle, 0, 1);
            }
        });

        mTopRight= (ImageButton) v.findViewById(R.id.game_button_1_3);
        mTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mTopRight, 0, 2);
            }
        });

        mCenterLeft = (ImageButton) v.findViewById(R.id.game_button_2_1);
        mCenterLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mCenterLeft, 1, 0);
            }
        });

        mCenterMiddle = (ImageButton) v.findViewById(R.id.game_button_2_2);
        mCenterMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mCenterMiddle, 1, 1);
            }
        });

        mCenterRight = (ImageButton) v.findViewById(R.id.game_button_2_3);
        mCenterRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mCenterRight, 1, 2);
            }
        });

        mBottomLeft = (ImageButton) v.findViewById(R.id.game_button_3_1);
        mBottomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mBottomLeft, 2, 0);
            }
        });

        mBottomMiddle = (ImageButton) v.findViewById(R.id.game_button_3_2);
        mBottomMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(mBottomMiddle, 2, 1);
            }
        });

        mBottomRight = (ImageButton) v.findViewById(R.id.game_button_3_3);
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

    private void updateScores() {
        String empire = getString(R.string.empire_win_count, mScore.getEmpireWins());
        String stormcloak = getString(R.string.stormcloak_win_count, mScore.getStormcloakWins());
        String ties = getString(R.string.tie_count, mScore.getTies());
        mEmpireText.setText(empire);
        mStormcloakText.setText(stormcloak);
        mDrawText.setText(ties);
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
        mTopLeft.setImageResource(android.R.color.transparent);
        mTopMiddle.setImageResource(android.R.color.transparent);
        mTopRight.setImageResource(android.R.color.transparent);

        mCenterLeft.setImageResource(android.R.color.transparent);
        mCenterMiddle.setImageResource(android.R.color.transparent);
        mCenterRight.setImageResource(android.R.color.transparent);

        mBottomLeft.setImageResource(android.R.color.transparent);
        mBottomMiddle.setImageResource(android.R.color.transparent);
        mBottomRight.setImageResource(android.R.color.transparent);
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

    private void handleClick(ImageButton b, int row, int col) {
        boolean empireTurn = mGame.isEmpireTurn();
        char[] array = new char[3];
        switch (row) {
            case 0: array = mGame.getTopRow();
                break;
            case 1: array = mGame.getMidRow();
                break;
            case 2: array = mGame.getLowRow();
                break;
            default: array[0] = 'x';
                array[1] = 'x';
                array[2] = 'x';
                break;
        }
        char test = array[col];
        if (test != 'x' && test != 'o' && mActiveGame) {
            updateGame(row, col);
            if (empireTurn) {
                b.setImageResource(R.drawable.imperial);
            }
            else {
                b.setImageResource(R.drawable.stormcloak);
            }
            char win = mGame.checkWin();
            if (win == 'x') {
                mWinText.setText(R.string.empire_win_dialog);
                mScore.incrementEmpireWins();
                mActiveGame = false;
                updateScores();
            } else if (win == 'o') {
                mWinText.setText(R.string.stormcloak_win_dialog);
                mScore.incrementStormcloakWins();
                mActiveGame = false;
                updateScores();
            } else if (++mTurns == 9) {
                mWinText.setText(R.string.tie_dialog);
                mScore.incrementTies();
                mActiveGame = false;
                updateScores();
            }
            mGame.setEmpireTurn(!empireTurn);
        }
        /*
        if (mGame.isEmpireTurn()) {
                    //TODO: add empire image
                    mTopLeft.setImageResource(R.drawable.imperial);
                }
                else {
                    //TODO: add stormcloak image
                    mTopLeft.setImageResource(R.drawable.stormcloak);
                }

         */
    }
}
