package chess_controller;

import chess_model.*;
import chess_view.ChessGUI;
import javax.swing.JOptionPane;

/**
 *
 * @author Alfonso Gallego Fernández
 */
public class ChessController {
    
    /**
     * {@link Chess} game the controller is controlling.
     */
    private Chess game;
    /**
     * {@link ChessGUI} view the controller is controlling.
     */
    private ChessGUI view;
    /**
     * {@link Position} currently stored as the potential initial position of
     * a movement.
     */
    private Position selectedPos;

    /**
     * Standard constructor for the {@code ChessController} class, setting the
     * {@link Chess} game, its {@link ChessGUI} view and setting itself as the
     * controller attribute of that view, then initializing its board by 
     * giving its buttons the appropiate actionLister and finally updating the
     * board.
     * @param game
     * @param view 
     */
    public ChessController(Chess game, ChessGUI view) {
        this.game = game;
        this.view = view;
        this.view.setController(this);
        this.view.initializeBoard();
        this.view.updateBoard();
    }

    /**
     * Getter for the game attribute of the controller.
     * @return The {@link Chess} game the controller is controlling.
     */
    public Chess getGame() {
        return game;
    }
    
    /**
     * "Getter" for the active player of the controller.
     * @return The {@link ChessColor} of the active player of the {@link Chess}
     * game the controller is controlling.
     */
    public ChessColor activePlayer() {
        return this.game.activePlayer();
    }
    
    /**
     * Method intended to be used as the action listener for the view's buttons
     * on the chess board, where x and y are the coordinates of that button
     * @param x
     * @param y 
     */
    public void handleClick(int x, int y) {
        if (x == 0 || y == 0) return; // Ignore label clicks
        if (this.game.isEndOfGame()) return; // Don't do anything if the game has ended.
        Position clickedPos = Position.of(x, y);
        
        if (selectedPos == null) { // First click stores the selected position.
            if (this.game.checkPiece(clickedPos) && this.game.findPiece(clickedPos).getColor() == this.activePlayer()) {
                selectedPos = clickedPos;
                this.view.highlightValidMoves(selectedPos);
            }
        } else { // Second click attempts to do the movement.
            if (this.game.checkPiece(selectedPos)) {
                Piece piece = this.game.findPiece(selectedPos);
                boolean playDone = false;
                int castlingInfo = 0; // -1 = left castling was done; 1 = right castling was done; 0 = no castling was done
                
                if (piece instanceof King) {
                    if (clickedPos.equals(Position.of(3, piece.getColor().initRow())) && this.game.checkLeftCastling(this.activePlayer())) {
                        this.game.doLeftCastling(this.activePlayer());
                        playDone = true;
                        castlingInfo = -1;
                        // A play of left castling was done.
                    }
                    if (clickedPos.equals(Position.of(7, piece.getColor().initRow())) && this.game.checkRightCastling(this.activePlayer())) {
                        this.game.doRightCastling(this.activePlayer());
                        playDone = true;
                        castlingInfo = 1;
                        // A play of right castling was done.
                    }
                    // If none of the ifs were entered, playDone stats as false and castlingInfo as 0.
                }
                
                if (!playDone && piece != null && piece.checkLegalMovement(clickedPos)) {
                    piece.move(clickedPos);
                    
                    if (piece instanceof Pawn && piece.getPos().y() == this.activePlayer().crowningRow()) { // Pawn crowning
                        this.game.crownPawn(piece, this.view.pawnCrowningMenu(piece));
                    }
                    playDone = true;
                }
                if (playDone) { // Record the play (special case for castling) and update the active player
                    this.view.updatePlayHistory(castlingInfo, this.game.getLastPlay());
                    this.game.changeActivePlayer();
                    this.view.updateActivePlayer();
                }
            }
            this.view.clearHighlights();
            selectedPos = null;
            this.view.updateBoard();
            
            if (this.game.checkMate(this.game.activePlayer())) {
                this.view.checkMessage(this.game.activePlayer().opposite());
                this.game.endGame();
            } else if (this.game.checkMate(this.game.activePlayer(), false)) {
                this.view.drawMessage(this.game.activePlayer());
                this.game.endGame();
            }
        }
    }
    
    /**
     * Static method to convert a letter to a number.
     * @param letter Letter to convert.
     * @return The integer number representning its position in the english
     * alphabet.
     * @throws IllegalArgumentException If the letter is anything other than
     * [a-h].
     * @hidden 
     */
    public static int convertLetterToNumber(char letter) throws IllegalArgumentException {
        return switch (Character.toLowerCase(letter)) {
            case 'a' -> 1;
            case 'b' -> 2;
            case 'c' -> 3;
            case 'd' -> 4;
            case 'e' -> 5;
            case 'f' -> 6;
            case 'g' -> 7;
            case 'h' -> 8;
            default -> throw new IllegalArgumentException("Invalid letter to convert to number: "+letter);
        };
    }
    
    /**
     * Static method to convert a number to a letter.
     * @param num Number to convert to a letter.
     * @return The letter in the number's position in the english alphabet.
     * @throws IllegalArgumentException If the number isn't within 1 and 8 (both
     * inclusive).
     * @hidden 
     */
    public static char convertNumberToLetter(int num) throws IllegalArgumentException {
        return switch (num) {
            case 1 -> 'A';
            case 2 -> 'B';
            case 3 -> 'C';
            case 4 -> 'D';
            case 5 -> 'E';
            case 6 -> 'F';
            case 7 -> 'G';
            case 8 -> 'H';
            default -> throw new IllegalArgumentException("Invalid number to convert to letter: "+num);
        };
    }
    
}
