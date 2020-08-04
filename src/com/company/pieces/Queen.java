package com.company.pieces;

import com.company.Board;

public class Queen extends Piece {

    @Override
    public boolean isMovePossible(Piece[][] pieces, int x, int y, int i, int j) {
        return (i - x == 0 || j - y == 0) || (Math.abs(i - x) == Math.abs(j - y));
    }

    public Queen(Board.Color color) {
        super(color);
    }

    @Override
    public String toString() {
        if (getColor() == Board.Color.WHITE) {
            return "Q";
        } else {
            return "q";
        }
    }
}
