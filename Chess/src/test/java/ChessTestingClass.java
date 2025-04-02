

import chess.*;
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
        
        
    }
    
}
