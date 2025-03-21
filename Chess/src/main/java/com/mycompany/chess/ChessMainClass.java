package com.mycompany.chess;

public class ChessMainClass {

    public static void main(String[] args) {
        Chess game = new Chess();
        game.addStandardPieces();
        boolean fin = false;
        Color activePlayer;
        
        int turnCounter = 0;
        int maxTurns = 120;
        
        while (!fin) {
            boolean playDone = false;
            activePlayer = turnCounter%2==0 ? Color.WHITE : Color.BLACK;
            
            if (game.findKing(activePlayer).checkCheck()) System.out.println(activePlayer + " is in check.");
            else if (game.checkMate(activePlayer, false)) {
                System.out.println(activePlayer + " isn't in check, but all possible plays would cause a check.");
                fin = true;
            }
            
            if (game.checkMate(activePlayer)) {
                System.out.println(activePlayer + " is in checkmate.");
                fin = true;
            }
            
            if (!fin) {
                
                
                
                
                
                
            }
        }
    }
    
}
