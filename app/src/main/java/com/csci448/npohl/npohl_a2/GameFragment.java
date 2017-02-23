package com.csci448.npohl.npohl_a2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Nate on 2/14/2017.
 */

public class GameFragment extends Fragment {

    private static final String SAVED_GAME = "game";

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

        mTopLeft = (ImageButton) v.findViewById(R.id.game_button_1_1);
        mTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(0, 0);
               }
        });

        mTopMiddle = (ImageButton) v.findViewById(R.id.game_button_1_2);
        mTopMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(0, 1);
            }
        });

        mTopRight= (ImageButton) v.findViewById(R.id.game_button_1_3);
        mTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(0, 2);
            }
        });

        mCenterLeft = (ImageButton) v.findViewById(R.id.game_button_2_1);
        mCenterLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(1, 0);
            }
        });

        mCenterMiddle = (ImageButton) v.findViewById(R.id.game_button_2_2);
        mCenterMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(1, 1);
            }
        });

        mCenterRight = (ImageButton) v.findViewById(R.id.game_button_2_3);
        mCenterRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(1, 2);
            }
        });

        mBottomLeft = (ImageButton) v.findViewById(R.id.game_button_3_1);
        mBottomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(2, 0);
            }
        });

        mBottomMiddle = (ImageButton) v.findViewById(R.id.game_button_3_2);
        mBottomMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(2, 1);
            }
        });

        mBottomRight = (ImageButton) v.findViewById(R.id.game_button_3_3);
        mBottomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(2, 2);
            }
        });

        mNewGame = (Button) v.findViewById(R.id.button_new_game);
        mNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearButtons();
                //Game.resetGame();
                setupGame();
            }
        });

        mGoBack = (Button) v.findViewById(R.id.button_go_back);
        mGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        //start a new game whenever we come back to this activity
        setupGame();

        return v;
    }
/*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_GAME, mGame);
   }
   */


    private void updateScores() {
        String empire = getString(R.string.imperial_win_count, mScore.getEmpireWins());
        String stormcloak = getString(R.string.stormcloak_win_count, mScore.getStormcloakWins());
        String ties = getString(R.string.tie_count, mScore.getTies());
        mEmpireText.setText(empire);
        mStormcloakText.setText(stormcloak);
        mDrawText.setText(ties);
    }

    /**
     * Called to setup a new game. This creates a new game, and sets up all variables needed to
     * ensure that the game can be played without errors.
     */
    private void setupGame() {
        mGame = new Game();
        if (mOptions.isImperialsGoFirst()) {
            mGame.setImperialTurn(true);
        }
        mTurns = 0;
        mActiveGame = true;
        mWinText.setText(R.string.active_game_dialog);
        //if the computer is on, and is set to go first, make first move here
        if (!mOptions.isImperialsGoFirst() && !mOptions.isTwoPlayers()) {
            computerMove();
        }
    }

    /**
     * Removes all the pictures from the buttons, for when the game is reset.
     */
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

    /**
     * updates the games arrays to keep track of where an 'x' or 'o' has been placed
     *
     * @param row the row to be updated
     * @param col the column to be updated
     */
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

        if (mGame.isImperialTurn()) {
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

    /**
     * Called whenever a game button is clicked. Handles updating the game, updating the view, and checking for a winner.
     *
     * @param row row of the button that was clicked
     * @param col column of the button that was clicked
     */
    private void handleClick(int row, int col) {
        boolean empireTurn = mGame.isImperialTurn();
        char[] array = new char[3];
        switch (row) {
            case 0:
                array = mGame.getTopRow();
                break;
            case 1:
                array = mGame.getMidRow();
                break;
            case 2:
                array = mGame.getLowRow();
                break;
            default:
                array[0] = 'x';
                array[1] = 'x';
                array[2] = 'x';
                break;
        }
        char test = array[col];
        if (test != 'x' && test != 'o' && mActiveGame) {
            updateGame(row, col);
            updateButton(row, col, mGame.isImperialTurn());
            char win = mGame.checkWin();
            if (win == 'x') {
                mWinText.setText(R.string.imperial_win_dialog);
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
            mGame.setImperialTurn(!empireTurn);
        }
        if (!mOptions.isTwoPlayers() && !mGame.isImperialTurn() && mActiveGame) {
            computerMove();
        }
    }

    /**
     * updates the proper button with the proper image
     *
     * @param row row of button to be updated
     * @param col column of button to be updated
     * @param imperialTurn is it the imperial's or the stormcloak's turn?
     */
    private void updateButton(int row, int col, boolean imperialTurn) {
        int dialogId;
        if (imperialTurn) {
            dialogId = R.drawable.imperial;
        }
        else {
            dialogId = R.drawable.stormcloak;
        }
        if (row == 0) {
            if (col == 0) {
                mTopLeft.setImageResource(dialogId);
            }
            else if (col == 1) {
                mTopMiddle.setImageResource(dialogId);
            }
            else if (col ==2) {
                mTopRight.setImageResource(dialogId);
            }
        }
        else if (row == 1) {
            if (col == 0) {
                mCenterLeft.setImageResource(dialogId);
            }
            else if (col == 1) {
                mCenterMiddle.setImageResource(dialogId);
            }
            else if (col ==2) {
                mCenterRight.setImageResource(dialogId);
            }
        }
        else if (row == 2) {
            if (col == 0) {
                mBottomLeft.setImageResource(dialogId);
            }
            else if (col == 1) {
                mBottomMiddle.setImageResource(dialogId);
            }
            else if (col ==2) {
                mBottomRight.setImageResource(dialogId);
            }
        }
    }

    /**
     * if there is a computer player, this will be called to select a random tile for the computer's turn
     */
    private void computerMove() {
        Random rand = new Random();
        int row;
        int col;
        char[] topRow = mGame.getTopRow();
        char[] midRow = mGame.getMidRow();
        char[] lowRow = mGame.getLowRow();
        char test;
        while (true) {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
            switch (row) {
                case 0:
                    test = topRow[col];
                    break;
                case 1:
                    test = midRow[col];
                    break;
                default:
                    test = lowRow[col];
                    break;
            }
            //if the selected tile is empty, fill it with the 'o', and break
            if (test != 'x' && test != 'o') {
                handleClick(row, col);
                break;
            }
        }
    }
    /*
    private void updateScreen() {
        char[] topRow = mGame.getTopRow();
        char[] midRow = mGame.getMidRow();
        char[] lowRow = mGame.getLowRow();
    }
    */
}
