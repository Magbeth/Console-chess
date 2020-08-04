package com.company.pieces;

import com.company.Board;

public class Rook extends Piece {

    @Override
    public boolean isMovePossible(Piece[][] pieces, int x, int y, int i, int j) {
        return i - x == 0 || j - y == 0;
    }

    @Override
    public void makeMove(Piece[][] pieces, int x, int y, int i, int j) {
        if (y == 0) {
            setShortCastleAvailable(false, pieces[x][y].getColor());
        } else if (y == 7) {
            setLongCastleAvailable(false, pieces[x][y].getColor());
        }
        pieces[i][j] = pieces[x][y];
        pieces[x][y] = null;
    }

    public Rook(Board.Color color) {
        super(color);
    }

    @Override
    public String toString() {
        if (getColor() == Board.Color.WHITE) {
            return "R";
        } else {
            return "r";
        }
    }
}
