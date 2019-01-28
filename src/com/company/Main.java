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
                System.out.println("--------" + board.getTurnToMove() + " turn to move.--------");
                String s = sc.nextLine();
                String[] move = s.toUpperCase().trim().split(" ");
                if (move.length > 3) throw new Exception();
                board.makeMove(move[1], move[2]);
                board.printBoard(board);
            }
            catch (Exception e) {
                System.out.println("----------Wrong input---------");
                board.printBoard(board);
            }
        }


    }
}
