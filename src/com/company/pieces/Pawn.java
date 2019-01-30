package com.company.pieces;

import com.company.Board;


public class Pawn extends Piece {
    public boolean firstMove;

    public Pawn() {
    }

    //check for move is possible
    @Override
    public boolean isMoveLegal(Piece[][] pieces, int x, int y, int i, int j) {
        if(pieces[i][j] != null && j - y == 0) return false;
        //check if it fist move for pawn
        if (x == 1 || x == 6) firstMove = true;
        //possible first move for WHITE
        if (i - x == 2 && j - y == 0 && firstMove && pieces[i-1][j] == null) return true;
        //possible first move for BLACK
        if (i - x == -2 && j - y == 0 && firstMove && pieces[i+1][j] == null) return true;
        //other possible moves excluding destroying enemy pieces
        if (((i - x == 1 && pieces[x][y].color == Board.Color.WHITE) || (i - x == -1 && pieces[x][y].color == Board.Color.BLACK)) && j - y == 0) return true;
        //destroying enemy pieces
        return pieces[i][j] != null && !isDestinationAlly(pieces[x][y], pieces[i][j]) && Math.abs(i - x) == 1 && Math.abs(j - y) == 1;
    }

    @Override
    public void makeMove(Piece[][] pieces, int x, int y, int i, int j) {
        pieces[i][j] = pieces[x][y];
        pieces[x][y] = null;
        firstMove = false;
    }

    public Pawn(Board.Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        if (this.color == Board.Color.WHITE) return "P";
        else return "p";
    }
}
