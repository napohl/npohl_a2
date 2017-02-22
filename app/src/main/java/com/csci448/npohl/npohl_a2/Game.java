package com.csci448.npohl.npohl_a2;

/**
 * Created by nate on 2/21/17.
 */

public class Game {

    /**
     * These three arrays will be used as the game board. When initialized, they will be size 3,
     * with the char being set to 'e' for empty. When a player clicks a button, it will be set to
     * an 'x' or 'o' depending on whose turn it is.
     */
    private char[] topRow;
    private char[] midRow;
    private char[] lowRow;
    private boolean empireTurn;

    public Game() {
        topRow = new char[3];
        midRow = new char[3];
        lowRow = new char[3];
        for (int i = 0; i < 3; i++) {
            topRow[i] = 'e';
            midRow[i] = 'e';
            lowRow[i] = 'e';
        }
        empireTurn = false;
    }

    /**
     * Check for wins
     * @return
     */
    private char checkRow(char[] array) {
        if (array[0] == 'x' && array [1] == 'x' && array[2] == 'x') {
            return 'x';
        }
        else if (array[0] == 'o' && array [1] == 'o' && array[2] == 'o') {
            return 'o';
        }
        else return 'e';
    }

    private char checkColumn(int i) {
        if (topRow[i] == 'x' && midRow[i] == 'x' && lowRow[i] == 'x') {
            return 'x';
        }
        else if (topRow[i] == 'o' && midRow[i] == 'o' && lowRow[i] == 'o') {
            return 'o';
        }
        else {
            return 'e';
        }
    }

    private char checkDiag() {
        if (topRow[0] == 'x' && midRow[1] == 'x' && lowRow[2] == 'x') {
            return 'x';
        }
        else if (topRow[2] == 'x' && midRow[1] == 'x' && lowRow[0] == 'x') {
            return 'x';
        }
        else if (topRow[0] == 'o' && midRow[1] == 'o' && lowRow[2] == 'o') {
            return 'o';
        }
        else if (topRow[2] == 'o' && midRow[1] == 'o' && lowRow[0] == 'o') {
            return 'o';
        }
        else {
            return 'e';
        }
    }

    public char checkWin() {
        char whoWon;
        whoWon = checkRow(topRow);
        if (whoWon == 'x' || whoWon == 'o') return whoWon;
        whoWon = checkRow(midRow);
        if (whoWon == 'x' || whoWon == 'o') return whoWon;
        whoWon = checkRow(lowRow);
        if (whoWon == 'x' || whoWon == 'o') return whoWon;

        for (int i = 0; i < 3; i++) {
            whoWon = checkColumn(i);
            if (whoWon == 'x' || whoWon == 'o') return whoWon;
        }

        whoWon = checkDiag();
        return whoWon;
    }

    public char[] getTopRow() {
        return topRow;
    }

    public void setTopRow(char[] topRow) {
        this.topRow = topRow;
    }

    public char[] getMidRow() {
        return midRow;
    }

    public void setMidRow(char[] midRow) {
        this.midRow = midRow;
    }

    public char[] getLowRow() {
        return lowRow;
    }

    public void setLowRow(char[] lowRow) {
        this.lowRow = lowRow;
    }

    public boolean isEmpireTurn() {
        return empireTurn;
    }

    public void setEmpireTurn(boolean empireTurn) {
        this.empireTurn = empireTurn;
    }
}
