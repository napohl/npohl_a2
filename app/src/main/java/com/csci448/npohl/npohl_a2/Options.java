package com.csci448.npohl.npohl_a2;

/**
 * Created by Nate on 2/15/2017.
 */

public class Options {

    private boolean mTwoPlayers;
    private boolean mEmpireGoesFirst;
    //that is all the options for now

    public Options() {
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
