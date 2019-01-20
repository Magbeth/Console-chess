package com.company.pieces;

import com.company.Board;

public class King extends Pieces {


    //check for move is possible
    @Override
    public boolean isMoveLegal(Pieces[][] pieces, int x, int y, int i, int j) {
        hasObstacle = false;
        //check for ally on destination point
        if(isDestinationAlly(pieces[x][y], pieces[i][j])) return false;
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
