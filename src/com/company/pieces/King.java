package com.company.pieces;

import com.company.Board;

public class King extends Pieces {


    //check for move is possible
    @Override
    public boolean isMoveLegal(Pieces[][] pieces, int x, int y, int i, int j) {
        hasObstacle = false;
        //check for ally on destination point
        if(isDestinationAlly(pieces[x][y], pieces[i][j])) return false;
        //check for obstacle on path. Possible moves are Rook-like or Bishop-like. Using its methods.
        //Bishop-like
        if (Math.abs(i - x) == Math.abs(j - y ) && Math.abs(i - x) == 1 ) {
            Pieces bishop = new Bishop();
            return bishop.isMoveLegal(pieces, x, y, i, j);
        }
        //Rook-like
        if ((i - x == 0 && Math.abs(j - y) == 1) || (j - y == 0 && Math.abs(i - x) == 1)) {
            Pieces rook = new Rook();
            return rook.isMoveLegal(pieces, x, y, i, j);
        }

        else return false;
    }

    public King(Board.Color color) {
        this.color = color;
    }

    public King() {
    }

    @Override
    public String toString() {
        return "K";
    }
}
