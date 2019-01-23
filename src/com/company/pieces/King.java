package com.company.pieces;

import com.company.Board;

public class King extends Pieces {


    //check for move is possible
    @Override
    public boolean isMoveLegal(Pieces[][] pieces, int x, int y, int i, int j) {
        hasObstacle = false;
        //check for ally on destination point
        if(isDestinationAlly(pieces[x][y], pieces[i][j])) return false;
        //castling
        if (isShortCastleAvailable(pieces[x][y].color) && !hasObstacle && i - x == 0 && j == 1) {
            return true;
        }
        if (isLongCastleAvailable(pieces[x][y].color) && !hasObstacle && i - x == 0 && j == 5) {
            return true;
        }
        //possible moves
        return Math.abs(i - x) <= 1 && Math.abs(j - y) <= 1;
    }

    public void makeShortCastle(Pieces[][] pieces, int x, int y, int i, int j) {
        pieces[i][j] = pieces[x][y];
        pieces[x][y] = null;
        pieces[i][j+1] = pieces[i][j-1];
        pieces[i][j-1] = null;
    }

    public void makeLongCastle(Pieces[][] pieces, int x, int y, int i, int j) {
        pieces[i][j] = pieces[x][y];
        pieces[x][y] = null;
        pieces[i][j - 1] = pieces[i][j + 2];
        pieces[i][j + 2] = null;
    }

    @Override
    public void makeMove(Pieces[][] pieces, int x, int y, int i, int j) {
        if (Math.abs(i - x) <= 1 && Math.abs(j-y) <= 1 ) {
            pieces[i][j] = pieces[x][y];
            pieces[x][y] = null;
        }
        else if (i - x == 0 && j == 1) makeShortCastle(pieces, x, y, i, j);
        else if (i - x == 0 && j == 5) makeLongCastle(pieces, x, y, i, j);
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
