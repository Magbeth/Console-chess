package com.company.pieces;

import com.company.Board;

public class Bishop extends Piece {

    @Override
    public boolean isMovePossible(Piece[][] pieces, int x, int y, int i, int j) {
        return Math.abs(i - x) == Math.abs(j - y);
    }

    public Bishop(Board.Color color) {
        super(color);
    }

    @Override
    public String toString() {
        if (getColor() == Board.Color.WHITE) {
            return "B";
        } else {
            return "b";
        }
    }
}
