package com.company.pieces;

import com.company.Board;

public class Piece {
    public Board.Color color;
    boolean hasObstacle = false;

    private static boolean isWhiteShortCastleAvailable = true;
    private static boolean isBlackShortCastleAvailable = true;
    private static boolean isWhiteLongCastleAvailable = true;
    private static boolean isBlackLongCastleAvailable = true;

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

    public boolean isDestinationAlly(Piece piece1, Piece piece2) {
        if (piece2 != null && piece1.color == piece2.color) {
            System.out.println("Invalid move. Ally on destination point");
            return true;
        }
        else return false;
    }
    public boolean isMoveLegal(Piece[][] pieces, int x, int y, int i, int j) {
        return true;
    }

    public void makeMove(Piece[][] pieces, int x, int y, int i, int j) {
        pieces[i][j] = pieces[x][y];
        pieces[x][y] = null;
    }

    public boolean isCheckToEnemy(Piece[][] pieces, int i, int j, int xKingPosition, int yKingPosition) {
        return pieces[i][j].isMoveLegal(pieces, i, j, xKingPosition, yKingPosition) && pieces[i][j].color != pieces[xKingPosition][yKingPosition].color;
    }

    public boolean isKingUnderAttack(Piece[][] pieces, int x, int y, int i, int j, int xKingPosition, int yKingPosition) {
//        Piece[][] piecesAfterMove = pieces.clone();
//        piecesAfterMove[i][j] = piecesAfterMove[x][y];
//        piecesAfterMove[x][y] = null;
//        piecesAfterMove[x][y].makeMove(piecesAfterMove, x, y, i, j);
        pieces[i][j] = pieces[x][y];
        pieces[x][y] = null;

        boolean kingUnderAttack = false;
        out:
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {
//                System.out.println(pieces[a][b] +" " + a + " " + b);
                if (pieces[a][b] != null && pieces[a][b].color != pieces[i][j].color) {
                    if (pieces[a][b].isMoveLegal(pieces, a, b, xKingPosition, yKingPosition)) {
                        System.out.println("-----Impossible move!-----");
                        kingUnderAttack = true;
                        break out;
                    }
                }
            }
        }
        pieces[x][y] = pieces[i][j];
        pieces[i][j] = null;
        return kingUnderAttack;
    }
}
