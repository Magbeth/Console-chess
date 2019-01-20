package com.company;

import com.company.pieces.*;

public class Board {

    public enum Color {BLACK, WHITE}

    public final int SIZE = 8;
    private Pieces[][] pieces;

    public Pieces[][] getPieces() {
        return pieces;
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
        System.out.println(" |_0_|_1_|_2_|_3_|_4_|_5_|_6_|_7_|");
        for (int i = 0; i < board.SIZE; i++) {
            System.out.print(i+"|");
            for (int j = 0; j < board.SIZE; j++) {
                if (board.pieces[i][j] != null) System.out.print("_" + board.pieces[i][j] + "_|");
                else System.out.print("___|");
            }
            System.out.println();
        }
    }

    //checking if user move is possible and changing game desc state if true
    public void makeMove(String piece, String from, String to) {
        int x = Integer.parseInt(from.substring(0,1));
        int y = Integer.parseInt(from.substring(1));
        int i = Integer.parseInt(to.substring(0,1));
        int j = Integer.parseInt(to.substring(1));
        Pieces pie = null;
        switch (piece.charAt(0)) {
            case 'P':
                pie = new Pawn();
                break;
            case 'Q':
                pie = new Queen();
                break;
            case 'K':
                pie = new King();
                break;
            case 'N':
                pie = new Knight();
                break;
            case 'R':
                pie = new Rook();
                break;
        }
        if(pie.isMoveLegal(pieces, x, y, i, j)) {
            pieces[i][j] = pieces[x][y];
            pieces[x][y] = null;
        }
    }
}
