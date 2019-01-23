package com.company;

import com.company.pieces.*;

public class Board {

    public enum Color {BLACK, WHITE}

    public final int SIZE = 8;
    private Pieces[][] pieces;
    private Color turnToMove = Color.WHITE;

    public Color getTurnToMove() {
        return turnToMove;
    }

    public void setTurnToMove(Color turnToMove) {
        this.turnToMove = turnToMove;
    }

    public Pieces[][] getPieces() {
        return pieces;
    }

    void swapTurnToMove(Color color) {
        if (color == Color.WHITE) setTurnToMove(Color.BLACK);
        else setTurnToMove(Color.WHITE);
    }

    int literalToNumber (String s) {
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

    //starting desc representation
    private final Pieces[][] STARTING_BOARD = {
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

    //Initializing desk with pieces
    public Board() {
        this.pieces = STARTING_BOARD;
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
    public void makeMove(String piece, String from, String to) {
        int y = literalToNumber(from);
        int x = Integer.parseInt(from.substring(1)) - 1;
        int j = literalToNumber(to);
        int i = Integer.parseInt(to.substring(1)) - 1;
        Pieces pie = null;
        switch (piece) {
            case "P":
                pie = new Pawn();
                break;
            case "Q":
                pie = new Queen();
                break;
            case "K":
                pie = new King();
                break;
            case "N":
                pie = new Knight();
                break;
            case "R":
                pie = new Rook();
                break;
            case "B":
                pie = new Bishop();
                break;
        }
        if (pieces[x][y].color != turnToMove) System.out.println("-----It is " + turnToMove + " turn to move!-----");
        //check for move possibility
        if(pie.isMoveLegal(pieces, x, y, i, j) && pieces[x][y].color == turnToMove) {

            //Castle become not available if King or Rook moved
            if (pie instanceof King) {
                pie.setShortCastleAvailable(false, pieces[x][y].color);
                pie.setLongCastleAvailable(false, pieces[x][y].color);
            }
            else if (pie instanceof Rook) {
                if (y == 0) pie.setShortCastleAvailable(false, pieces[x][y].color);
                else if (y == 7) pie.setLongCastleAvailable(false, pieces[x][y].color);
            }
            //make move
            pie.makeMove(pieces, x, y, i, j);
            swapTurnToMove(pieces[i][j].color);
        }
    }
}
