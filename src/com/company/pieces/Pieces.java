package com.company.pieces;

import com.company.Board;

public class Pieces {
    Board.Color color;
    boolean hasObstacle = false;

    public boolean isCastleAvailable() {
        return isCastleAvailable;
    }

    public void setCastleAvailable(boolean castleAvailable) {
        isCastleAvailable = castleAvailable;
    }

    private boolean isCastleAvailable = true;

    public boolean isDestinationAlly(Pieces piece1, Pieces piece2) {
        if (piece2 != null && piece1.color == piece2.color) return true;
        else return false;
    }
    public boolean isMoveLegal(Pieces[][] pieces, int x, int y, int i, int j) {
        return true;
    }

}
