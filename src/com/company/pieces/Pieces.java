package com.company.pieces;

import com.company.Board;

public class Pieces {
    Board.Color color;

    public boolean isMoveLegal(Pieces[][] pieces, int x, int y, int i, int j) {
        return true;
    }
    public Pieces[][] destroy(Pieces[][] pieces, int i, int j) {
        pieces[i][j] = null;
        return pieces;
    }
}
