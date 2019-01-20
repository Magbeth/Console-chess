package com.company.pieces;

import com.company.Board;

public class Bishop extends Pieces {
    public Bishop() {
    }

    public boolean isMoveLegal(Pieces[][] pieces, int x, int y, int i, int j) {
        return true;
    }

    public Bishop(Board.Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "B";
    }
}
