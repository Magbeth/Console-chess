package com.company.pieces;

import com.company.Board;

public class Piece {

    private static boolean whiteShortCastleAvailable = true;
    private static boolean blackShortCastleAvailable = true;
    private static boolean whiteLongCastleAvailable = true;
    private static boolean blackLongCastleAvailable = true;

    protected boolean hasObstacle;
    private Board.Color color;
    private int xCoordinate;
    private int yCoordinate;

    public Piece(Board.Color color) {
        this.color = color;
    }

    public boolean isShortCastleAvailable(Board.Color color) {
        if (color == Board.Color.BLACK) {
            return blackShortCastleAvailable;
        } else {
            return whiteShortCastleAvailable;
        }
    }

    public boolean isLongCastleAvailable(Board.Color color) {
        if (color == Board.Color.WHITE) {
            return whiteLongCastleAvailable;
        } else {
            return blackLongCastleAvailable;
        }
    }

    public void setLongCastleAvailable(boolean longCastleAvailable, Board.Color color) {
        if (color == Board.Color.WHITE) {
            whiteLongCastleAvailable = longCastleAvailable;
        } else if (color == Board.Color.BLACK) {
            blackLongCastleAvailable = longCastleAvailable;
        }
    }

    public void setShortCastleAvailable(boolean castleAvailable, Board.Color color) {
        if (color == Board.Color.WHITE) {
            whiteShortCastleAvailable = castleAvailable;
        } else if (color == Board.Color.BLACK) {
            blackShortCastleAvailable = castleAvailable;
        }
    }

    public boolean isDestinationAlly(Piece piece1, Piece piece2) {
        return piece2 != null && piece1.color == piece2.color;
    }

    public boolean isMovePossible(Piece[][] pieces, int x, int y, int i, int j) {
        return false;
    }

    public boolean isMoveLegal(Piece[][] pieces, int x, int y, int i, int j) {
        if (isDestinationAlly(pieces[x][y], pieces[i][j])) {
            return false;
        }
        if (pieces[x][y].isMovePossible(pieces, x, y, i, j)) {
            int disX = 0;
            int disY = 0;
            if (x < i) {
                disX = 1;
            } else if (x > i) {
                disX = -1;
            }
            if (y < j) {
                disY = 1;
            } else if (y > j) {
                disY = -1;
            }

            do {
                x = x + disX;
                y = y + disY;
                if (x == i && y == j) {
                    break;
                }
                if (pieces[x][y] != null) {
                    return false;
                }
            } while (true);
        } else {
            return false;
        }
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
        Piece temp = pieces[i][j];
        pieces[i][j] = pieces[x][y];
        pieces[x][y] = null;

        if (pieces[i][j] instanceof King) {
            xKingPosition = i;
            yKingPosition = j;
        }

        boolean kingUnderAttack = false;
        out:
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {
                if (pieces[a][b] != null && pieces[a][b].color != pieces[i][j].color) {
                    if (pieces[a][b].isMoveLegal(pieces, a, b, xKingPosition, yKingPosition)) {
                        kingUnderAttack = true;
                        break out;
                    }
                }
            }
        }
        pieces[x][y] = pieces[i][j];
        pieces[i][j] = temp;
        return kingUnderAttack;
    }

    public void setCoordinate(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public Board.Color getColor() {
        return this.color;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    protected void setColor(Board.Color color) {
        this.color = color;
    }
}
