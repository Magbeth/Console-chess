package com.company.pieces;

import com.company.Board;

public class Rook extends Pieces {
    public Rook() {
    }

    public boolean isMoveLegal() {
        return true;
    }

    public Rook(Board.Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "R";
    }
}
