package com.mycompany.chess;

import java.util.stream.IntStream;

public class ChessTestingClass {

    public static void main(String[] args) {
        Chess game = new Chess();
        game.addStandardPieces();
//        game.getPieces().stream()
//            .forEach(p -> System.out.printf("Type: "+p.getColor()+" "+p.getClass().getSimpleName() + " | Position: "+p.getPos()+"\n"));
//        game.printBoard();
//        System.out.printf("----------------------------------%n");
        Piece whitePawn = game.findPiece(Position.of("D2"));
        King blackKing = game.findKing(Color.BLACK);
        game.getPieces().remove(game.findPiece(Position.of("D7")));
        game.getPieces().remove(game.findPiece(Position.of("E7")));
        game.getPieces().remove(game.findPiece(Position.of("C7")));
        whitePawn.setPos(Position.of("D7"));
        game.printBoard();
        System.out.println(blackKing.checkLegalMovement(Position.of("E7"), true));
//        System.out.println("The black King can move to E7: "+blackKing.checkLegalMovement(Position.of("E7")));
//        System.out.println("If black King would move to E7, would it be in check? "+blackKing.checkIfMovementCausesCheck(Position.of("E7")));
//        System.out.println("The black King is in check: "+blackKing.checkCheck());
//        System.out.println("BLACK is in checkmate: "+game.checkMate(Color.BLACK));
//        System.out.println("Pieces that can move to E7:");
//        game.getPieces().stream()
//            .filter(piece -> piece.checkLegalMovement(Position.of("E7")))
//            .forEach(piece -> System.out.println(piece));
//        System.out.printf("----------------------------------%n");
//        blackKing.setPos(Position.of("E7"));
//        game.printBoard();
//        System.out.println("The black King is in check: "+blackKing.checkCheck());
//        System.out.println("BLACK is in checkmate: "+game.checkMate(Color.BLACK));
//        System.out.println("Pieces that can move to E7:");
//        game.getPieces().stream()
//            .filter(piece -> piece.checkLegalMovement(Position.of("E7")))
//            .forEach(piece -> System.out.println(piece));
        
//        Piece blackPawn = game.findPiece(Position.of("E7"));
//        whitePawn.setPos(Position.of("D5"));
//        game.printBoard();
//        System.out.printf("----------------------------------%n");
//        blackPawn.move(Position.of("E5"));
//        game.printBoard();
//        System.out.printf("----------------------------------%n");
//        whitePawn.move(Position.of("E6"));
//        game.printBoard();
//        System.out.printf("----------------------------------%n");
        
        
        
//        King whiteKing = game.findKing(Color.WHITE);
//        King blackKing = game.findKing(Color.BLACK);
//        Piece blackQueen = game.findPiece(Position.of("D8"));
//        System.out.println("The white King is in check: "+whiteKing.checkCheck());
//        System.out.println("WHITE is in checkmate: "+game.checkMate(Color.WHITE));
//        System.out.println("Pieces that can move to white King's position:");
//        game.getPieces().stream()
//            .filter(piece -> piece.checkLegalMovement(whiteKing.getPos()))
//            .forEach(piece -> System.out.println(piece));
//        
//        System.out.println("The black King is in check: "+blackKing.checkCheck());
//        System.out.println("BLACK is in checkmate: "+game.checkMate(Color.BLACK));
//        System.out.println("Pieces that can move to black King's position:");
//        game.getPieces().stream()
//            .filter(piece -> piece.checkLegalMovement(blackKing.getPos()))
//            .forEach(piece -> System.out.println(piece));
        
        
//        game.findPiece(Position.of("D8")).setPos(Position.of("H4"));
//        game.findPiece(Position.of("F2")).setPos(Position.of("F3"));
//        game.findPiece(Position.of("G2")).setPos(Position.of("G4"));
//        game.findPiece(Position.of("E7")).setPos(Position.of("E5"));
//        game.printBoard();
//        
//        
//        System.out.println("The King is in check: "+whiteKing.checkCheck());
//        System.out.println("WHITE is in soft checkmate: "+game.checkMate(Color.WHITE, false));
//        System.out.println("WHITE is in checkmate: "+game.checkMate(Color.WHITE));
//        System.out.println("Queen can move to E1: "+blackQueen.checkLegalMovement(Position.of("E1")));
//        System.out.println("King can move to F2: "+whiteKing.checkLegalMovement(Position.of("F2")));

        
        
//        System.out.printf("----------------------------------%n");
//        whiteKing.setPos(Position.of("F2"));
//        game.printBoard();
//        System.out.println("The King is in check: "+whiteKing.checkCheck());
//        System.out.println("WHITE is in soft checkmate: "+game.checkMate(Color.WHITE, false));
//        System.out.println("WHITE is in checkmate: "+game.checkMate(Color.WHITE));
//        System.out.println("Queen can move to F2: "+game.findPiece(Position.of("H4")).checkLegalMovement(Position.of("F2")));
//        System.out.println("King can move to E3: "+whiteKing.checkLegalMovement(Position.of("E3")));
//        System.out.printf("----------------------------------%n");
//        game.findPiece(Position.of("H1")).checkLegalMovement(Position.of("H4"));
//        game.printBoard();
//        
//        System.out.println(game.isPathClear(Position.of("H1"), Position.of("H4")));
//        System.out.println(game.isPathClear(Position.of("F1"), Position.of("H3")));
        
    }
    
}
