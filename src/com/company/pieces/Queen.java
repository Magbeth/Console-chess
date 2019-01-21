package com.company.pieces;

import com.company.Board;

public class Queen extends Pieces {

    //check for move is possible
    @Override
    public boolean isMoveLegal(Pieces[][] pieces, int x, int y, int i, int j) {
        hasObstacle = false;
        //check for ally on destination point
        if(isDestinationAlly(pieces[x][y], pieces[i][j])) {
            return false;
        }

        //check for obstacle on path. Possible moves are Rook-like or Bishop-like. Using its methods.
        //Bishop-like
        if (Math.abs(i - x) == Math.abs(j - y) ) {
            Pieces bishop = new Bishop();
            return bishop.isMoveLegal(pieces, x, y, i, j);
        }
        //Rook-like
        if (i - x == 0 || j - y == 0) {
            Pieces rook = new Rook();
            return rook.isMoveLegal(pieces, x, y, i, j);
        }

        else return false;
    }
    public Queen(Board.Color color) {
        this.color = color;
    }

    public Queen() {
    }

    @Override
    public String toString() {
        return "Q";
    }
}
