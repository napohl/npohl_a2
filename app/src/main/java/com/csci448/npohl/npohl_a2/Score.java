package com.csci448.npohl.npohl_a2;

import android.content.Context;

/**
 * Created by Nate on 2/22/2017.
 */

/**
 * Score will maintain the score over the lifetime of the app. This is done using the static
 * variable sScore, which creates a static Score object that all fragments can access and use.
 */
public class Score {

    /**
     * sScore is a static object so that it can be saved across activities
     */
    private static Score sScore;

    private int mEmpireWins;
    private int mStormcloakWins;
    private int mTies;

    /**
     * get is used by the fragments to retrieve and use the static score object.
     *
     * @param context which activity is calling this function?
     * @return Returns the static object
     */
    public static Score get(Context context) {
        if (sScore == null) {
            sScore = new Score(context);
        }
        return sScore;
    }

    private Score(Context context) {
        mEmpireWins = 0;
        mStormcloakWins = 0;
        mTies = 0;
    }

    public int getEmpireWins() {
        return mEmpireWins;
    }

    public void resetEmpireWins() {
        mEmpireWins = 0;
    }

    public void incrementEmpireWins() {
        mEmpireWins++;
    }

    public int getStormcloakWins() {
        return mStormcloakWins;
    }

    public void resetStormcloakWins() {
        mStormcloakWins = 0;
    }

    public void incrementStormcloakWins() {
        mStormcloakWins++;
    }

    public int getTies() {
        return mTies;
    }

    public void resetTies() {
        mTies = 0;
    }

    public void incrementTies() {
        mTies++;
    }
}
