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
//        int m = 1;
//        for (int k = Math.min(x, i)+1; k < Math.max(x, i); k++) {
//            int l = Math.max(y, j); // l = 5
//            if (pieces[k][j-m] != null) { //02 35 -- x = {1,2} 14 23  | 13 24
//                hasObstacle = true;
//                break;
//            }
//            m++;
//        }
        if ( (x > i && y > j) || (x < i && j > y)) {
            for (int k = 1; k < Math.abs(i - x); k++) {
                int a = Math.max(x, i);
                int b = Math.max(y, j);
                if (pieces[a - k][b - k] != null) {
                    hasObstacle = true;
                    break;
                }
            }
        }
        else if (x > i && j > y) {
            for (int k = 1; k < Math.abs(i - x); k++) {
                if (pieces[i + k][j - k] != null) {
                    hasObstacle = true;
                    break;
                }
            }
        }
        else if (x < i && j < y) {
            for (int k = 1; k < Math.abs(i - x); k++) {
                if (pieces[i - k][j + k] != null) {
                    hasObstacle = true;
                    break;
                }
            }
        }
        //TODO Check moves
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
