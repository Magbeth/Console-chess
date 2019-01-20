package com.company.pieces;

import com.company.Board;
import com.company.pieces.Pieces;

public class Queen extends Pieces {
    public Queen() {
    }

    public boolean isMoveLegal() {
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
