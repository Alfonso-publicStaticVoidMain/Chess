package com.mycompany.chess;

import java.util.Scanner;

public class ChessMainClass {

    static Scanner keyboard = new Scanner(System.in, "ISO-8859-1");
    
    public static Position[] printMoveMenu(Color activePlayer, Chess game) {
        boolean fin = false;
        Position initPos = Position.of(1, 1), finPos = Position.of(1, 1);
        do {
            System.out.println("Introduce your play. First the initial position, then the final position.");
            String initPosString = keyboard.nextLine();
            String finPosString = keyboard.nextLine();
            try {
                initPos = Position.of(initPosString);
                finPos = Position.of(finPosString);
                fin = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Invalid positions. Introduce them again.");
            }
            if (!game.checkPiece(initPos)
                || game.findPiece(initPos).getColor() != activePlayer
                || !game.findPiece(initPos).checkLegalMovement(finPos)) fin = false;
        } while (!fin);
        return new Position[] {initPos, finPos};
    }
    
    public static void main(String[] args) {
        Chess game = new Chess();
        game.addStandardPieces();
        boolean fin = false;
        Color activePlayer;
        
        int turnCounter = 0;
        int maxTurns = 120;
        
        game.printBoard();
        System.out.printf("----------------------------------%n");
        
        while (!fin) {
            boolean playDone = false;
            activePlayer = turnCounter%2==0 ? Color.WHITE : Color.BLACK;
            System.out.println("It's the turn of the " + activePlayer + " player");
            
            if (game.checkMate(activePlayer)) {
                System.out.println(activePlayer + " is in checkmate.");
                fin = true;
            } else if (game.findKing(activePlayer).checkCheck()) System.out.println(activePlayer + " is in check.");
            else if (game.checkMate(activePlayer, false)) {
                System.out.println(activePlayer + " isn't in check, but all possible plays would cause a check.");
                fin = true;
            }
            
            if (!fin) {
                // TO DO: Castling menu
                
                Position[] positions = printMoveMenu(activePlayer, game);
                Position initPos = positions[0];
                Position finPos = positions[1];
                Piece pieceToMove = game.findPiece(initPos);
                pieceToMove.move(finPos);
                if (pieceToMove instanceof Pawn && finPos.y() == activePlayer.crowningRow()) {
                    game.crownPawn(pieceToMove, "queen");
                }
            }
            game.printBoard();
            System.out.printf("----------------------------------%n");
            turnCounter++;
            if (turnCounter > maxTurns) {
                System.out.println("Turn limit of " + maxTurns + " reached.");
                fin = true;
            }
        }
    }
    
}
