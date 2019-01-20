package com.company.pieces;

import com.company.Board;

public class Bishop extends Pieces {

    //check for move is possible
    @Override
    public boolean isMoveLegal(Pieces[][] pieces, int x, int y, int i, int j) {
        hasObstacle = false;
        //check for ally on destination point
        if(isDestinationAlly(pieces[x][y], pieces[i][j])) return false;
        //check for obstacle on path
        int m = 1;
        for (int k = Math.min(x, i)+1; k < Math.max(x, i); k++) {
            int l = Math.max(y, j);
            if (pieces[k][l-m] != null) {
                hasObstacle = true;
                break;
            }
            m++;
        }
        //possible moves
        if (Math.abs(i - x) == Math.abs(j - y) && !hasObstacle) return true;
        else return false;
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
