package com.mycompany.chess;

public class ChessTestingClass {

    public static void main(String[] args) {
        Chess game = new Chess();
        game.addStandardPieces();
//        game.getPieces().stream()
//            .forEach(p -> System.out.printf("Type: "+p.getColor()+" "+p.getClass().getSimpleName() + " | Position: "+p.getPos()+"\n"));
        game.printBoard();
        game.findPiece(Position.of("D8")).setPos(Position.of("H4"));
        game.findPiece(Position.of("F2")).setPos(Position.of("F3"));
        game.findPiece(Position.of("G2")).setPos(Position.of("G4"));
        game.findPiece(Position.of("E7")).setPos(Position.of("E5"));
        game.printBoard();
        System.out.println(game.findKing(Color.WHITE).checkCheck());
        System.out.println(game.checkMate(Color.WHITE, false));
        System.out.println(game.checkMate(Color.WHITE));
        System.out.println(game.findPiece(Position.of("H4")).checkLegalMovement(Position.of("E1")));
    }
    
}
