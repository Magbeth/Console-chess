package com.company.pieces;

import com.company.Board;

public class Pawn extends Pieces {
    public boolean firstMove;

    public Pawn() {
    }

    //TODO: TO FIX: Here's a bug: pawn can move back.
    @Override
    public boolean isMoveLegal(Pieces[][] pieces, int x, int y, int i, int j) {
        if(pieces[i][j] != null && j - y == 0) return false;
        if (x == 1 || x == 6) firstMove = true;
        if (i - x == 2 && firstMove && pieces[i-1][j] == null) return true;
        if (i - x == -2 && firstMove && pieces[i+1][j] == null) return true;
        if (Math.abs(i-x) == 1 && j - y == 0) return true;
        else return false;
    }

    public Pawn(Board.Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "P";
    }
}
