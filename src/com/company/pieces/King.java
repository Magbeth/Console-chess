package com.company.pieces;

import com.company.Board;

public class King extends Pieces {
    public King() {
    }

    public boolean isMoveLegal(Pieces[][] pieces, int x, int y, int i, int j) {
        return true;
    }

    public King(Board.Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "K";
    }
}
