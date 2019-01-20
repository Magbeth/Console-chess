package com.company.pieces;

import com.company.Board;

public class Queen extends Pieces {
    public Queen() {
    }

    public boolean isMoveLegal(Pieces[][] pieces, int x, int y, int i, int j) {
        return true;
    }
    public Queen(Board.Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Q";
    }
}
