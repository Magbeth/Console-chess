package com.company;

import com.company.pieces.Bishop;
import com.company.pieces.King;
import com.company.pieces.Knight;
import com.company.pieces.Pawn;
import com.company.pieces.Piece;
import com.company.pieces.Queen;
import com.company.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public enum Color {BLACK, WHITE}

    public final int SIZE = 8;
    private final Piece[][] pieces;
    private Color turnToMove = Color.WHITE;

    //King coordinates for checking of move possibility. King can't move on attacked squares and can't remain under check after move
    private int xWhiteKingPosition = 0;
    private int yWhiteKingPosition = 3;
    private int xBlackKingPosition = 7;
    private int yBlackKingPosition = 3;

    private void setKingPosition(int x, int y, Color color) {
        if (color == Color.WHITE) {
            xWhiteKingPosition = x;
            yWhiteKingPosition = y;
        } else {
            xBlackKingPosition = x;
            yBlackKingPosition = y;
        }
    }

    public Color getTurnToMove() {
        return turnToMove;
    }

    private void swapTurnToMove(Color color) {
        if (color == Color.WHITE) {
            this.turnToMove = Color.BLACK;
        } else {
            this.turnToMove = Color.WHITE;
        }
    }

    //Transform user input for columns into array indexes
    private int literalToNumber(String s) {
        int x = 8;
        switch (s.substring(0, 1)) {
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

    private String numberToLiteral(int n) {
        String s = "";
        switch (n) {
        case 0:
            s = "H";
            break;
        case 1:
            s = "G";
            break;
        case 2:
            s = "F";
            break;
        case 3:
            s = "E";
            break;
        case 4:
            s = "D";
            break;
        case 5:
            s = "C";
            break;
        case 6:
            s = "B";
            break;
        case 7:
            s = "A";
            break;
        }
        return s;
    }

    //Initializing desk with pieces
    public Board() {
        //starting desc representation
        this.pieces = new Piece[][] {
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
            System.out.print((i + 1) + "/|");
            for (int j = 0; j < board.SIZE; j++) {
                if (board.pieces[i][j] != null) {
                    System.out.print("_" + board.pieces[i][j] + "_|");
                } else {
                    System.out.print("___|");
                }
            }
            System.out.println();
        }
    }

    public List<String> findPossibleMoves(Piece[][] pieces, Color color) {
        List<String> possibleMoves = new ArrayList<>();

        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {
                if (pieces[a][b] != null && pieces[a][b].getColor() == color) {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            int kingPositionX;
                            int kingPositionY;
                            if (pieces[a][b].getColor() == Color.WHITE) {
                                kingPositionX = xWhiteKingPosition;
                                kingPositionY = yWhiteKingPosition;
                            } else {
                                kingPositionX = xBlackKingPosition;
                                kingPositionY = yBlackKingPosition;
                            }
                            if (pieces[a][b].isMoveLegal(pieces, a, b, i, j) &&
                                    !pieces[a][b].isKingUnderAttack(pieces, a, b, i, j, kingPositionX, kingPositionY)) {
                                String move = pieces[a][b].toString() + " " + numberToLiteral(b) + "" + (a + 1) + " " + numberToLiteral(j) + "" + (i + 1);
                                possibleMoves.add(move);
                            }
                        }
                    }
                }
            }
        }
        return possibleMoves;
    }

    public void printPossibleMoves() {
        List<String> possibleMoves = findPossibleMoves(pieces, turnToMove);
        for (String move : possibleMoves) {
            System.out.print(move + " ");
        }
        System.out.println();
    }

    public int getPossibleMovesNumber() {
        return findPossibleMoves(pieces, turnToMove).size();
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
        if (pie.getColor() != turnToMove) {
            System.out.println("-----It is " + turnToMove + " turn to move!-----");
        }
        //check for move possibility
        if (pie.isMoveLegal(pieces, x, y, i, j) && pie.getColor() == turnToMove) {

            //make move
            int kingPositionX;
            int kingPositionY;
            if (pie.getColor() == Color.WHITE) {
                kingPositionX = xWhiteKingPosition;
                kingPositionY = yWhiteKingPosition;
            } else {
                kingPositionX = xBlackKingPosition;
                kingPositionY = yBlackKingPosition;
            }

            if (!pie.isKingUnderAttack(pieces, x, y, i, j, kingPositionX, kingPositionY)) {

                if (pie instanceof King) {
                    setKingPosition(i, j, pie.getColor());
                }

                pie.makeMove(pieces, x, y, i, j);

                if (pie.getColor() == Color.WHITE) {
                    if (pie.isCheckToEnemy(pieces, i, j, xBlackKingPosition, yBlackKingPosition)) {
                        System.out.println("-------CHECK to BLACK-------");
                    }
                } else {
                    if (pie.isCheckToEnemy(pieces, i, j, xWhiteKingPosition, yWhiteKingPosition)) {
                        System.out.println("-------CHECK to WHITE-------");
                    }
                }
                swapTurnToMove(pie.getColor());
            }
        } else {
            System.out.println("-----------Invalid move-----------");
        }
    }
}
