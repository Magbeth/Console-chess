package com.company.pieces;

import com.company.Board;

public class Bishop extends Piece {

    //check for move is possible
    @Override
    public boolean isMoveLegal(Piece[][] pieces, int x, int y, int i, int j) {
        hasObstacle = false;
        //check for ally on destination point
        if(isDestinationAlly(pieces[x][y], pieces[i][j])) return false;
        //check for obstacle on path
        if (Math.abs(i - x) == Math.abs(j - y)) {
            if ((x > i && y > j) || (x < i && j > y)) {
                for (int k = 1; k < Math.abs(i - x); k++) {
                    int a = Math.max(x, i);
                    int b = Math.max(y, j);
                    if (pieces[a - k][b - k] != null) {
                        hasObstacle = true;
                        break;
                    }
                }
            } else if (x > i && j > y) {
                for (int k = 1; k < Math.abs(i - x); k++) {
                    if (pieces[i + k][j - k] != null) {
                        hasObstacle = true;
                        break;
                    }
                }
            } else if (x < i && j < y) {
                for (int k = 1; k < Math.abs(i - x); k++) {
                    if (pieces[i - k][j + k] != null) {
                        hasObstacle = true;
                        break;
                    }
                }
            }
        }
        //possible moves
        return Math.abs(i - x) == Math.abs(j - y) && !hasObstacle;
    }

    public Bishop(Board.Color color) {
        this.color = color;
    }

    public Bishop() {
    }

    @Override
    public String toString() {
        return "B";
    }
}
