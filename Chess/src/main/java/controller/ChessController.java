package controller;

import chess_model.*;
import java.awt.Color;
import view.ChessGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

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
    private Piece selectedPiece;

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
        view.clearHighlights();
        if (x == 0 || y == 0) return; // Ignore label clicks
        if (game.isGameFinished()) return; // Don't do anything if the game has ended.
        Position clickedPos = Position.of(x, y);
        if (selectedPiece == null) { // First click stores the selected piece.
            if (game.checkPiece(clickedPos)) {
                Piece piece = game.findPiece(clickedPos);
                if (piece.getColor() == game.getActivePlayer()) {
                    selectedPiece = piece;
                    view.highlightValidMoves(piece);
                } else {
                    view.highlightMovesOfEnemyPiece(piece);
                }
            }
        } else { // Second click attempts to do the movement.
                boolean playDone = false;
                
                if (!selectedPiece.checkLegalMovement(clickedPos)) {
                    view.highlightPiecesThatCanCaptureKing(selectedPiece, clickedPos);
                }
                
                if (selectedPiece instanceof King) {
                    if (game.checkLeftCastling(game.getActivePlayer()) &&
                        clickedPos.equals(game.leftCastlingKingPosition(game.getActivePlayer()))    
                    ) {
                        game.doLeftCastling(game.getActivePlayer());
                        playDone = true;
                        // A play of left castling was done.
                    }
                    
                    if (game.checkRightCastling(game.getActivePlayer()) &&
                        clickedPos.equals(game.rightCastlingKingPosition(game.getActivePlayer()))    
                    ) {
                        game.doRightCastling(game.getActivePlayer());
                        playDone = true;
                        // A play of right castling was done.
                    }
                }
                
                if (!playDone && selectedPiece.checkLegalMovement(clickedPos)) {
                    selectedPiece.move(clickedPos);
                    if (!game.isGameStarted()) game.startGame();
                    if (selectedPiece instanceof Pawn && selectedPiece.getPos().y() == game.getActivePlayer().crowningRow(game.getConfig())) { // Pawn crowning
                        game.crownPawn(selectedPiece, view.pawnCrowningMenu(selectedPiece, game.getConfig().crownablePieces()));
                    }
                    playDone = true;
                }

                if (playDone) { // Record the play (special case for castling) and update the active player
                    view.updatePlayHistory(game.getLastPlay());
                    game.changeActivePlayer();
                    view.updateActivePlayer();
                }
            
            selectedPiece = null;
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
        game = switch (game.getConfig().typeOfGame()) {
            /*case "Standard Chess"*/ default -> Chess.standardGame();
            case "Almost Chess" -> Chess.almostChessGame();
            case "Capablanca Chess" -> Chess.capablancaGame();
            case "Janus Chess" -> Chess.janusGame();
            case "Modern Chess" -> Chess.modernGame();
            case "Tutti Frutti Chess" -> Chess.tuttiFruttiGame();
        };
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
            System.err.println("I/O error: " + ex.getMessage());
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
            Chess chessGame = (Chess) ois.readObject();
            if (chessGame.getConfig().rows() == game.getConfig().rows() && chessGame.getConfig().cols() == game.getConfig().cols()) {
                boolean playerChoice = true;
                if (!chessGame.getConfig().typeOfGame().equals(game.getConfig().typeOfGame())) {
                    playerChoice = view.areYouSureYouWantToDoThis("The game you wanted to load is of type: "+chessGame.getConfig().typeOfGame()+", while you're playing "+game.getConfig().typeOfGame()+
                    "\nBut thankfully they are compatible in size. Do you still want to load that game?");
                }
                if (playerChoice) {
                    game = chessGame;
                    view.updateBoard();
                    view.updateActivePlayer();
                    view.reloadPlayHistory();
                }
            } else {
                view.informPlayer("Incompatible dimensions", "Your selected game is of type "+chessGame.getConfig().typeOfGame()+" ("+chessGame.getConfig().rows()+"x"+chessGame.getConfig().cols()+"), while your current one is "+
                game.getConfig().typeOfGame()+" ("+game.getConfig().rows()+"x"+game.getConfig().cols()+")");
            }
            
        }
        catch (IOException ex) {System.err.println("I/O error: " + ex.getMessage());}
        catch (ClassNotFoundException ex) {System.err.println("Class not found: " + ex.getMessage());}
    }
    
    /**
     * Static method to convert a letter to a number.
     * @param letter Letter to convert.
     * @return The integer number representning its position in the english
     * alphabet.
     * @hidden 
     */
    public static int convertLetterToNumber(char letter) {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(Character.toUpperCase(letter))+1;
//        return switch (Character.toLowerCase(letter)) {
//            case 'a' -> 1;
//            case 'b' -> 2;
//            case 'c' -> 3;
//            case 'd' -> 4;
//            case 'e' -> 5;
//            case 'f' -> 6;
//            case 'g' -> 7;
//            case 'h' -> 8;
//            default -> throw new IllegalArgumentException("Invalid letter to convert to number: "+letter);
//        };
    }
    
    /**
     * Static method to convert a number to a letter.
     * @param num Number to convert to a letter.
     * @return The letter in the number's position in the english alphabet.
     * @hidden 
     */
    public static char convertNumberToLetter(int num) {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(num-1);
//        return switch (num) {
//            case 1 -> 'A';
//            case 2 -> 'B';
//            case 3 -> 'C';
//            case 4 -> 'D';
//            case 5 -> 'E';
//            case 6 -> 'F';
//            case 7 -> 'G';
//            case 8 -> 'H';
//            default -> throw new IllegalArgumentException("Invalid number to convert to letter: "+num);
//        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println("[DEBUG] ChessController action received: "+command);
        switch (command) {
            case "Board Button" -> {
                JButton clickedButton = (JButton) e.getSource();
                int x = (int) clickedButton.getClientProperty("x");
                int y = (int) clickedButton.getClientProperty("y");
                System.out.println("[DEBUG] Position: "+Position.of(x, y)+" (x="+x+", y="+y+")");
                handleClick(x, y);
            }
            case "Reset" -> this.resetClick();
            case "Save" -> this.saveClick();
            case "Load" -> this.loadClick();
            case "Back" -> SwingUtilities.invokeLater( () -> {
                view.dispose();
                new IndexController();
            });
            default -> {}
        }
    }
}
