package com.csci448.npohl.npohl_a2;

import android.content.Context;

import java.util.UUID;

/**
 * Created by Nate on 2/15/2017.
 */

public class Options {

    private static Options sOptions;

    private boolean mTwoPlayers;
    private boolean mEmpireGoesFirst;
    private UUID mId;

    public static Options get(Context context) {
        if (sOptions == null) {
            sOptions = new Options(context);
        }
        return sOptions;
    }

    private Options(Context context) {
        mId = UUID.randomUUID();
        mTwoPlayers = false;
        mEmpireGoesFirst = false;
    }

    public UUID getId() {
        return mId;
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
