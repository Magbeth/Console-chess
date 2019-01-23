package com.company;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard(board);
        System.out.println("Write move in format 'Piece' 'from' 'to'. Example: P 01 03");
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("-------" + board.getTurnToMove() + " turn to move.-------");
                String s = sc.nextLine();
                String[] move = s.toUpperCase().trim().split(" ");
                board.makeMove(move[0], move[1], move[2]);
                board.printBoard(board);
            }
            catch (Exception e) {
                System.out.println("----------Invalid move----------");
                board.printBoard(board);
            }
        }


    }
}
