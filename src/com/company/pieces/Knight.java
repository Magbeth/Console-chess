package com.company.pieces;

import com.company.Board;

public class Knight extends Pieces {
    public Knight() {
    }

    public boolean isMoveLegal() {
        return true;
    }

    public Knight(Board.Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "N";
    }
}
