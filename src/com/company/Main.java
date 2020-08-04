package com.company;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Board board = new Board();

        System.out.println("Write move in format 'Piece' 'from' 'to'. Example: P 01 03");
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                board.printBoard(board);
                System.out.println("--------" + board.getTurnToMove() + " turn to move.--------");
                board.printPossibleMoves();
                if (board.getPossibleMovesNumber() == 0) {
                    System.out.println("------CHECKMATE to " + board.getTurnToMove() + "------");
                    break;
                }
                String s = sc.nextLine();
                String[] move = s.toUpperCase().trim().split(" ");
                if (move.length > 3) throw new Exception();
                board.makeMove(move[1], move[2]);
            }
            catch (Exception e) {
                System.out.println("----------Wrong input---------");
            }
        }


    }
}
