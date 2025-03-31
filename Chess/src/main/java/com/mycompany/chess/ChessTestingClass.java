package com.mycompany.chess;

import java.util.List;
import java.util.stream.IntStream;

public class ChessTestingClass {

    public static void debugLegalityOfMovement(Piece piece, Position pos) {
        System.out.println(piece.getColor() + " " + piece.getClass().getSimpleName() + " can move to "+pos+": "+piece.checkLegalMovement(pos));
    }
    
    public static void debugCheck(King king) {
        System.out.println(king.getColor() + " King is in check: "+king.checkCheck());
    }
    
    public static void debugCheckMate(King king) {
        System.out.println(king.getColor()+" King is in checkmate: "+king.getGame().checkMate(king.getColor()));
    }
    
    public static void debugPositionsAPieceCanMoveTo(Piece piece) {
        System.out.println("Positions the "+piece.getColor() + " " + piece.getClass().getSimpleName()+" can move to:");
        List<Position> validPositionList = Position.validPositions().stream()
            .filter(piece::checkLegalMovement)
            .toList();
        
        for (int y = 8; y >= 0; y--) {
            System.out.print(y + " ");
            for (int x = 1; x <= 8; x++) {
                boolean printedPiece = false;
                if (y != 0 && validPositionList.contains(Position.of(x, y))) {
                    System.out.print("|**|");
                    printedPiece = true;
                }
                if (!printedPiece && y != 0) System.out.print("|__|");
                else if (y == 0) System.out.print("  " + Chess.convertNumberToLetter(x) + " ");
            }
            System.out.printf("%n");
        }
    }
    
    public static void debugPiecesThatCanMoveToPosition(Position pos, Chess game) {
        System.out.println("Pieces that can move to "+pos);
        game.getPieces().stream()
            .filter(piece -> piece.checkLegalMovement(pos))
            .forEach(System.out::println);
    }
    
    public static void main(String[] args) {
        Chess game = new Chess();
        game.addStandardPieces();
//        game.getPieces().stream()
//            .forEach(p -> System.out.printf("Type: "+p.getColor()+" "+p.getClass().getSimpleName() + " | Position: "+p.getPos()+"\n"));
//        game.printBoard();
//        System.out.printf("----------------------------------%n");
//        Piece whitePawn = game.findPiece(Position.of("D2"));
        King blackKing = game.findKing(ChessColor.BLACK);
        Piece whiteQueen = game.findPiece(Position.of("D1"));
        game.getPieces().remove(game.findPiece(Position.of("D8")));
        whiteQueen.setPos(Position.of("D8"));
        game.printBoard();
        System.out.printf("----------------------------------%n");
        debugPositionsAPieceCanMoveTo(whiteQueen);
//        game.getPieces().remove(game.findPiece(Position.of("D7")));
//        game.getPieces().remove(game.findPiece(Position.of("E7")));
//        game.getPieces().remove(game.findPiece(Position.of("C7")));
//        whitePawn.setPos(Position.of("D7"));
//        game.printBoard();
//        System.out.println("The black King can move to E7: "+blackKing.checkLegalMovement(Position.of("E7")));
//        System.out.println("If black King would move to E7, would it be in check? "+blackKing.checkIfMovementCausesCheck(Position.of("E7")));
//        System.out.println("The black King is in check: "+blackKing.checkCheck());
//        System.out.println("BLACK is in checkmate: "+game.checkMate(Color.BLACK));
//        System.out.println("Pieces that can move to E7:");
//        game.getPieces().stream()
//            .filter(piece -> piece.checkLegalMovement(Position.of("E7")))
//            .forEach(piece -> System.out.println(piece));
//        System.out.printf("----------------------------------%n");
//        blackKing.move(Position.of("E7"));
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
