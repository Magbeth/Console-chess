package com.company.pieces;

import com.company.Board;


public class Pawn extends Pieces {
    public boolean firstMove;

    public Pawn() {
    }

    //TODO: TO FIX: Here's a bug: pawn can move back.
    //check for move is possible
    @Override
    public boolean isMoveLegal(Pieces[][] pieces, int x, int y, int i, int j) {
        if(pieces[i][j] != null && j - y == 0) return false;
        //check if it fist move for pawn
        if (x == 1 || x == 6) firstMove = true;
        //possible first move for WHITE
        if (i - x == 2 && firstMove && pieces[i-1][j] == null) return true;
        //possible first move for BLACK
        if (i - x == -2 && firstMove && pieces[i+1][j] == null) return true;
        //other possible moves excluding destroying enemy pieces
        if (Math.abs(i-x) == 1 && j - y == 0) return true;
        //destroying enemy pieces
        if (pieces[i][j] != null && pieces[i][j].color != pieces[x][y].color && Math.abs(i-x) == 1 && Math.abs(j - y) == 1) return true;
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
