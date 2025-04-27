package chess_main;

import chess_controller.ChessController;
import chess_model.Chess;
import chess_view.ChessGUI;
import javax.swing.SwingUtilities;

/**
 * Main class to play Chess via a GUI.
 * @author Alfonso Gallego Fernández
 */
public class ChessMainGUI {

    public static ChessController initializeStandardGame() {
        return new ChessController(Chess.standardGame(), new ChessGUI());
    }
    
    public static ChessController initializeAlmostChessGame() {
        return new ChessController(Chess.almostChessGame(), new ChessGUI());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessMainGUI::initializeAlmostChessGame);
    }
    
}
