package com.company.pieces;

import com.company.Board;

public class Knight extends Piece {
    public Knight() {
    }

    //check for move is possible
    @Override
    public boolean isMoveLegal(Piece[][] pieces, int x, int y, int i, int j) {
        //check for ally piece on destination square
        if(isDestinationAlly(pieces[x][y], pieces[i][j])) return false;
        //possible moves
        if (Math.abs(i - x) == 2 && Math.abs(j - y) == 1) return true;
        else if (Math.abs(i - x) == 1 && Math.abs(j - y) == 2) return true;
        else return false;
    }

    public Knight(Board.Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "N";
    }
}
