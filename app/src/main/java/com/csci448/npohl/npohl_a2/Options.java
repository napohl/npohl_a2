package com.csci448.npohl.npohl_a2;

import android.content.Context;

/**
 * Created by Nate on 2/15/2017.
 */

public class Options {

    /**
     * options is a static variable so that it can be used between activities
     */
    private static Options sOptions;

    private boolean mTwoPlayers;
    private boolean mImperialsGoFirst;

    public static Options get(Context context) {
        if (sOptions == null) {
            sOptions = new Options(context);
        }
        return sOptions;
    }

    private Options(Context context) {
        mTwoPlayers = false;
        mImperialsGoFirst = false;
    }

    public boolean isTwoPlayers() {
        return mTwoPlayers;
    }

    public void setTwoPlayers(boolean twoPlayers) {
        mTwoPlayers = twoPlayers;
    }

    public boolean isImperialsGoFirst() {
        return mImperialsGoFirst;
    }

    public void setImperialsGoFirst(boolean imperialsGoFirst) {
        mImperialsGoFirst = imperialsGoFirst;
    }
}
