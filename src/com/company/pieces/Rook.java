package com.company.pieces;

import com.company.Board;

public class Rook extends Piece {


    //check for move is possible
    @Override
    public boolean isMoveLegal(Piece[][] pieces, int x, int y, int i, int j) {
        //check for ally piece on destination square
        if(isDestinationAlly(pieces[x][y], pieces[i][j])) return false;
        //check for obstacle on path
        hasObstacle = false;
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
        return (i - x == 0 || j - y == 0) && !hasObstacle;
    }

    @Override
    public void makeMove(Piece[][] pieces, int x, int y, int i, int j) {
        if (y == 0) setShortCastleAvailable(false, pieces[x][y].color);
        else if (y == 7) setLongCastleAvailable(false, pieces[x][y].color);
        pieces[i][j] = pieces[x][y];
        pieces[x][y] = null;
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
