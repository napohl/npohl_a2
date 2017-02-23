package com.csci448.npohl.npohl_a2;

import android.content.Context;

/**
 * Created by Nate on 2/15/2017.
 */

public class Options {

    private static Options sOptions;

    private boolean mTwoPlayers;
    private boolean mEmpireGoesFirst;

    public static Options get(Context context) {
        if (sOptions == null) {
            sOptions = new Options(context);
        }
        return sOptions;
    }

    private Options(Context context) {
        mTwoPlayers = false;
        mEmpireGoesFirst = false;
    }

    public boolean isTwoPlayers() {
        return mTwoPlayers;
    }

    public void setTwoPlayers(boolean twoPlayers) {
        mTwoPlayers = twoPlayers;
    }

    public boolean isEmpireGoesFirst() {
        return mEmpireGoesFirst;
    }

    public void setEmpireoesFirst(boolean EmpireGoesFirst) {
        mEmpireGoesFirst = EmpireGoesFirst;
    }
}
