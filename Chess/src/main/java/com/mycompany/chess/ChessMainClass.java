package com.mycompany.chess;

import java.util.List;
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
    
    public static char printCastlingMenu(Color activePlayer, Chess game) {
        if (game.checkLeftCastling(activePlayer) && game.checkRightCastling(activePlayer)) {
            System.out.println("You can do both left and right castling.");
            System.out.println("Do you want to do either? (L for left, R for right, 0 for none.)");
            String answer = keyboard.nextLine();
            while (!answer.equals("L") && !answer.equals("R") && !answer.equals("0")) {
                System.out.println("Answer outside of expected parameters. Answer again (L for left, R for right, 0 for none.)");
                answer = keyboard.nextLine();
            }
            return answer.charAt(0);
        }
        if (game.checkLeftCastling(activePlayer)) {
            System.out.println("You can do left acastling.");
            System.out.println("Do you want to do it? (write L for yes, 0 for no.)");
            String answer = keyboard.nextLine();
            while (!answer.equals("L") && !answer.equals("0")) {
                System.out.println("Answer outside of expected parameters. Answer again (L for yes, 0 for no.)");
                answer = keyboard.nextLine();
            }
            return answer.charAt(0);
        }
        if (game.checkRightCastling(activePlayer)) {
            System.out.println("You can do right acastling.");
            System.out.println("Do you want to do it? (write R for yes, 0 for no.)");
            String answer = keyboard.nextLine();
            while (!answer.equals("R") && !answer.equals("0")) {
                System.out.println("Answer outside of expected parameters. Answer again (R for yes, 0 for no.)");
                answer = keyboard.nextLine();
            }
            return answer.charAt(0);
        }
        return '0';
    }
    
    public static String printCrowningMenu(Pawn pawn, Chess game) {
        System.out.println("You can crown a Pawn! What Piece do you want to crown it to?");
        System.out.println("Write the name of the Piece: Queen, Knight, Rook or Bishop (not case sensitive.)");
        String answer = keyboard.nextLine();
        List<String> chessPieces = List.of("queen", "rook", "bishop", "knight");
        while (!chessPieces.contains(answer.toLowerCase())) {
            System.out.println("Answer outside of expected parameters. Answer again.");
            System.out.println("Write the name of the Piece: Queen, Knight, Rook or Bishop (not case sensitive.)");
            answer = keyboard.nextLine();
        }
        return answer;
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
                char castlingAnswer = printCastlingMenu(activePlayer, game);
                switch (castlingAnswer) {
                    case 'L' -> {
                        game.doLeftCastling(activePlayer);
                        playDone = true;
                    }
                    case 'R' -> {
                        game.doRightCastling(activePlayer);
                        playDone = true;
                    }
                    case '0' -> {}
                }
                if (!playDone) {
                    Position[] positions = printMoveMenu(activePlayer, game);
                    Position initPos = positions[0];
                    Position finPos = positions[1];
                    Piece pieceToMove = game.findPiece(initPos);
                    pieceToMove.move(finPos);
                    if (pieceToMove instanceof Pawn pawn && finPos.y() == activePlayer.crowningRow()) {
                        game.printBoard();
                        System.out.printf("----------------------------------%n");
                        String crowningAnswer = printCrowningMenu(pawn, game);
                        game.crownPawn(pieceToMove, crowningAnswer);
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
    
}
