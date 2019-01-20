package com.company.pieces;

import com.company.Board;

public class Rook extends Pieces {

    //obstacle on piece path. Rook can't jump through over pieces.
    boolean hasObstacle = false;

    //check for move is possible
    @Override
    public boolean isMoveLegal(Pieces[][] pieces, int x, int y, int i, int j) {
        //check for ally piece on destination square
        if (pieces[i][j] != null && pieces[i][j].color == pieces[x][y].color) return false;
        //check for obstacle on path
        if (i - x == 0) {
            for (int k = Math.min(y, j) + 1; k < Math.max(y, j); k++) {
                if (pieces[x][k] != null) {
                    hasObstacle = true;
                    break;
                }
            }
        }
        if (j - y == 0) {
            for (int k = Math.min(x, i) + 1; k < Math.max(x, i); k++) {
                if (pieces[k][j] != null) {
                    hasObstacle = true;
                    break;
                }
            }
        }
        //possible moves
        if ((i - x == 0 || j - y == 0) && !hasObstacle) return true;

        else return false;
    }

    public Rook(Board.Color color) {
        this.color = color;
    }

    public Rook() {
    }

    @Override
    public String toString() {
        return "R";
    }
}
