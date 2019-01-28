package com.company;

import com.company.pieces.*;

public class Board {

    public enum Color {BLACK, WHITE}

    public final int SIZE = 8;
    private Piece[][] pieces;
    private Color turnToMove = Color.WHITE;
    private boolean checkToWhite = false;
    private boolean checktoBlack = false;

    private int xWhiteKingPosition = 0;
    private int yWhiteKingPosition = 3;
    private int xBlackKingPosition = 7;
    private int yBlackKingPosition = 3;

    private void setKingPosition(int x, int y, Color color) {
        if (color == Color.WHITE) {
            xWhiteKingPosition = x;
            yWhiteKingPosition = y;
        }
        else {
            xBlackKingPosition = x;
            yBlackKingPosition = y;
        }
    }
    public Color getTurnToMove() {
        return turnToMove;
    }

    private void setTurnToMove(Color turnToMove) {
        this.turnToMove = turnToMove;
    }


    private void swapTurnToMove(Color color) {
        if (color == Color.WHITE) setTurnToMove(Color.BLACK);
        else setTurnToMove(Color.WHITE);
    }

    private int literalToNumber (String s) {
        int x = 8;
        switch (s.substring(0,1)) {
            case "A":
                x = 7;
                break;
            case "B":
                x = 6;
                break;
            case "C":
                x = 5;
                break;
            case "D":
                x = 4;
                break;
            case "E":
                x = 3;
                break;
            case "F":
                x = 2;
                break;
            case "G":
                x = 1;
                break;
            case "H":
                x = 0;
                break;
        }
        return x;
    }

    //Initializing desk with pieces
    public Board() {
        //starting desc representation
        this.pieces = new Piece[][]{
                {
                        new Rook(Color.WHITE), new Knight(Color.WHITE), new Bishop(Color.WHITE),
                        new King(Color.WHITE), new Queen(Color.WHITE), new Bishop(Color.WHITE),
                        new Knight(Color.WHITE), new Rook(Color.WHITE)
                },
                {
                        new Pawn(Color.WHITE), new Pawn(Color.WHITE), new Pawn(Color.WHITE),
                        new Pawn(Color.WHITE), new Pawn(Color.WHITE), new Pawn(Color.WHITE),
                        new Pawn(Color.WHITE), new Pawn(Color.WHITE)
                },
                {
                        null, null, null, null, null, null, null, null
                },
                {
                        null, null, null, null, null, null, null, null
                },
                {
                        null, null, null, null, null, null, null, null
                },
                {
                        null, null, null, null, null, null, null, null
                },
                {
                        new Pawn(Color.BLACK), new Pawn(Color.BLACK), new Pawn(Color.BLACK),
                        new Pawn(Color.BLACK), new Pawn(Color.BLACK), new Pawn(Color.BLACK),
                        new Pawn(Color.BLACK), new Pawn(Color.BLACK)
                },
                {
                        new Rook(Color.BLACK), new Knight(Color.BLACK), new Bishop(Color.BLACK),
                        new King(Color.BLACK), new Queen(Color.BLACK), new Bishop(Color.BLACK),
                        new Knight(Color.BLACK), new Rook(Color.BLACK)
                }
        };
    }

    //display current desc state to console
    public void printBoard(Board board) {
        System.out.println("  | H | G | F | E | D | C | B | A |");
        System.out.println("__|///|///|///|///|///|///|///|///|");
        for (int i = 0; i < board.SIZE; i++) {
            System.out.print((i+1)+"/|");
            for (int j = 0; j < board.SIZE; j++) {
                if (board.pieces[i][j] != null) System.out.print("_" + board.pieces[i][j] + "_|");
                else System.out.print("___|");
            }
            System.out.println();
        }
    }

    //checking if user move is possible and changing game desc state if true
    public void makeMove(String from, String to) {
        //Translating user input to coordinates
        int y = literalToNumber(from);
        int x = Integer.parseInt(from.substring(1)) - 1;
        int j = literalToNumber(to);
        int i = Integer.parseInt(to.substring(1)) - 1;
        Piece pie = pieces[x][y];

        //check turn to move
        if (pie.color != turnToMove) System.out.println("-----It is " + turnToMove + " turn to move!-----");
        //check for move possibility
        if(pie.isMoveLegal(pieces, x, y, i, j) && pie.color == turnToMove) {

            //make move
            int kingPositionX;
            int kingPositionY;
            if (pie.color == Color.WHITE) {
                kingPositionX = xWhiteKingPosition;
                kingPositionY = yWhiteKingPosition;
            }
            else {
                kingPositionX = xBlackKingPosition;
                kingPositionY = yBlackKingPosition;
            }
            if (!pie.isKingUnderAttack(pieces, x, y, i, j, kingPositionX, kingPositionY)) {
//                System.out.println("Move is possible");

                //Castle become not available if King or Rook moved
                if (pie instanceof King && (pie.isShortCastleAvailable(pie.color) || pie.isLongCastleAvailable(pie.color))) {
                    pie.setShortCastleAvailable(false, pie.color);
                    pie.setLongCastleAvailable(false, pie.color);
                    setKingPosition(i, j, pie.color);
                }
                else if (pie instanceof Rook && (pie.isShortCastleAvailable(pie.color) || pie.isLongCastleAvailable(pie.color))) {
                    if (y == 0) pie.setShortCastleAvailable(false, pie.color);
                    else if (y == 7) pie.setLongCastleAvailable(false, pie.color);
                }
                pie.makeMove(pieces, x, y, i, j);

                if (pie.color == Color.WHITE) {
                    if (pie.isCheckToEnemy(pieces, i, j, xBlackKingPosition, yBlackKingPosition)) {
                        System.out.println("-------CHECK to BLACK-------");
                        checktoBlack = true;
                    }
                } else {
                    if (pie.isCheckToEnemy(pieces, i, j, xWhiteKingPosition, yWhiteKingPosition)) {
                        System.out.println("-------CHECK to WHITE-------");
                        checkToWhite = true;
                    }
                }
                swapTurnToMove(pie.color);
            }
        }
    }
}
