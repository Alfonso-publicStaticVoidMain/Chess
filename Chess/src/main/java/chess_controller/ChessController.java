package chess_controller;

import chess_model.*;
import chess_view.ChessGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;

/**
 *
 * @author Alfonso Gallego Fernández
 */
public class ChessController implements ActionListener {
    
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
    }

    /**
     * Getter for the game attribute of the controller.
     * @return The {@link Chess} game the controller is controlling.
     */
    public Chess getGame() {return game;}
    
    /**
     * Method intended to be used as the action listener for the view's buttons
     * on the chess board, where x and y are the coordinates of that button
     * @param x X coordinate of the button clicked.
     * @param y Y coordinate of the button clicked.
     */
    public void handleClick(int x, int y) {
        if (x == 0 || y == 0) return; // Ignore label clicks
        if (game.isGameFinished()) return; // Don't do anything if the game has ended.
        Position clickedPos = Position.of(x, y);
        boolean redHighlights = false;
        if (selectedPos == null) { // First click stores the selected position.
            if (game.checkPiece(clickedPos) && game.findPiece(clickedPos).getColor() == game.getActivePlayer()) {
                selectedPos = clickedPos;
                view.highlightValidMoves(selectedPos);
            }
        } else { // Second click attempts to do the movement.
            if (game.checkPiece(selectedPos)) {
                Piece piece = game.findPiece(selectedPos);
                boolean playDone = false;
                
                if (piece instanceof King) {
                    if (clickedPos.equals(Position.of(3, piece.getColor().initRow())) && game.checkLeftCastling(game.getActivePlayer())) {
                        game.doLeftCastling(game.getActivePlayer());
                        playDone = true;
                        // A play of left castling was done.
                    }
                    if (clickedPos.equals(Position.of(7, piece.getColor().initRow())) && game.checkRightCastling(game.getActivePlayer())) {
                        game.doRightCastling(game.getActivePlayer());
                        playDone = true;
                        // A play of right castling was done.
                    }
                    // If none of the ifs were entered, playDone stays as false and castlingInfo as 0.
                }
                
                if (!playDone && piece != null && piece.checkLegalMovement(clickedPos)) {
                    piece.move(clickedPos);
                    
                    if (piece instanceof Pawn && piece.getPos().y() == game.getActivePlayer().crowningRow()) { // Pawn crowning
                        game.crownPawn(piece, view.pawnCrowningMenu(piece));
                    }
                    playDone = true;
                }
                
                if (piece instanceof King && !piece.checkLegalMovement(clickedPos)) {
                    view.highlightPiecesThatCanCapture(clickedPos);
                    redHighlights = true;
                }
                
                if (playDone) { // Record the play (special case for castling) and update the active player
                    view.updatePlayHistory(game.getLastPlay());
                    game.changeActivePlayer();
                    view.updateActivePlayer();
                }
            }
            if (!redHighlights) view.clearHighlights();
            selectedPos = null;
            view.updateBoard();
            
            if (game.checkMate(game.getActivePlayer())) {
                view.checkMessage(game.getActivePlayer());
                game.finishGame();
            } else if (this.game.checkMate(game.getActivePlayer(), false)) {
                view.drawMessage(game.getActivePlayer());
                game.finishGame();
            }
        }
    }
    
    public void resetClick() {
        boolean userVerification = view.areYouSureYouWantToDoThis("Do you want to reset the game?");
        if (!userVerification) return;
        game = Chess.standardGame();
        view.updateBoard();
        view.updateActivePlayer();
        view.resetPlayHistory();
    }
    
    public void saveClick() {
        boolean userVerification = view.areYouSureYouWantToDoThis("Do you want to save the state of the game?");
        if (!userVerification) return;
        String filePath = view.userTextInputMessage("Enter the name of your game");
        try (
            FileOutputStream fos = new FileOutputStream("savedgames"+File.separator+filePath+".dat", false);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos))
        {
            oos.writeObject(game);
        } catch (IOException ex) {
            System.err.println("Error:" + ex.getMessage());
        }
    }
    
    public void loadClick() {
        boolean userVerification = view.areYouSureYouWantToDoThis("Do you want to load a saved game?");
        if (!userVerification) return;
        try (
            FileInputStream fis = new FileInputStream(view.fileChooser("."+File.separator+"savedgames"));
            BufferedInputStream bufis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bufis))
        {
            while (bufis.available()>0) {
                game = (Chess) ois.readObject();
            }
            view.updateBoard();
            view.updateActivePlayer();
            view.reloadPlayHistory();
        }
        catch (IOException ex) {System.err.println("Error:" + ex.getMessage());}
        catch (ClassNotFoundException ex) {System.err.println("Err:" + ex.getMessage());}
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        JButton clickedButton = (JButton) e.getSource();
        if (command.equals("boardButton")) {
            int x = (int) clickedButton.getClientProperty("x");
            int y = (int) clickedButton.getClientProperty("y");
            this.handleClick(x, y);
        }
        if (command.equals("reset")) {
            this.resetClick();
        }
        if (command.equals("save")) {
            this.saveClick();
        }
        if (command.equals("load")) {
            this.loadClick();
        }
    }
    
}
