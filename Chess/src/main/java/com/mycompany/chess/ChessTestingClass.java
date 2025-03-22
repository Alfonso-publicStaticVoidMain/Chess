package com.mycompany.chess;

public class ChessTestingClass {

    public static void main(String[] args) {
        Chess game = new Chess();
        game.addStandardPieces();
//        game.getPieces().stream()
//            .forEach(p -> System.out.printf("Type: "+p.getColor()+" "+p.getClass().getSimpleName() + " | Position: "+p.getPos()+"\n"));
        game.printBoard();
        game.findPiece(Position.of("D2")).move(Position.of("D3"));
    }
    
}
