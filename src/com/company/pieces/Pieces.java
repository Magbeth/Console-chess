package com.company.pieces;

import com.company.Board;

public class Pieces {
    public Board.Color color;
    boolean hasObstacle = false;

    private boolean isWhiteShortCastleAvailable = true;
    private boolean isBlackShortCastleAvailable = true;
    private boolean isWhiteLongCastleAvailable = true;
    private boolean isBlackLongCastleAvailable = true;


    public boolean isShortCastleAvailable(Board.Color color) {
        if (color == Board.Color.BLACK)return isBlackShortCastleAvailable;
        else return isWhiteShortCastleAvailable;
    }

    public boolean isLongCastleAvailable(Board.Color color) {
        if (color == Board.Color.WHITE) return isWhiteLongCastleAvailable;
        else return isBlackLongCastleAvailable;
    }

    public void setLongCastleAvailable(boolean longCastleAvailable, Board.Color color) {
        if (color == Board.Color.WHITE) isWhiteLongCastleAvailable = longCastleAvailable;
        else if (color == Board.Color.BLACK) isBlackLongCastleAvailable = longCastleAvailable;
//        System.out.println(color + "long castle " + longCastleAvailable);
    }

    public void setShortCastleAvailable(boolean castleAvailable, Board.Color color) {
        if (color == Board.Color.WHITE) isWhiteShortCastleAvailable = castleAvailable;
        else if (color == Board.Color.BLACK) isBlackShortCastleAvailable = castleAvailable;
//        System.out.println(color + "short castle " + castleAvailable);
    }

    public boolean isDestinationAlly(Pieces piece1, Pieces piece2) {
        if (piece2 != null && piece1.color == piece2.color) {
            System.out.println("Invalid move. Ally on destination point");
            return true;
        }
        else return false;
    }
    public boolean isMoveLegal(Pieces[][] pieces, int x, int y, int i, int j) {
        return true;
    }

    public void makeMove(Pieces[][] pieces, int x, int y, int i, int j) {
        pieces[i][j] = pieces[x][y];
        pieces[x][y] = null;
    }

}
