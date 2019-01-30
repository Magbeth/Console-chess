package com.company.pieces;

import com.company.Board;

public class Queen extends Piece {

    //check for move is possible
//    @Override
//    public boolean isMoveLegal(Piece[][] pieces, int x, int y, int i, int j) {
//        hasObstacle = false;
//        //check for ally on destination point
//        if(isDestinationAlly(pieces[x][y], pieces[i][j])) {
//            return false;
//        }
//
//        //check for obstacle on path. Possible moves are Rook-like or Bishop-like. Using its methods.
//        //Bishop-like
//        if (Math.abs(i - x) == Math.abs(j - y) ) {
//            Piece bishop = new Bishop();
//            return bishop.isMoveLegal(pieces, x, y, i, j);
//        }
//        //Rook-like
//        if (i - x == 0 || j - y == 0) {
//            Piece rook = new Rook();
//            return rook.isMoveLegal(pieces, x, y, i, j);
//        }
//
//        else return false;
//    }

    @Override
    public boolean isMovePossible(Piece[][] pieces, int x, int y, int i, int j){
        return (i - x == 0 || j - y == 0) || (Math.abs(i - x) == Math.abs(j - y));
    }

    public Queen(Board.Color color) {
        this.color = color;
    }

    public Queen() {
    }

    @Override
    public String toString() {
        if (this.color == Board.Color.WHITE) return "Q";
        else return "q";
    }
}
