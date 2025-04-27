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
        return new ChessController(Chess.standardGame(), new ChessGUI(8, 8));
    }
    
    public static ChessController initializeAlmostChessGame() {
        return new ChessController(Chess.almostChessGame(), new ChessGUI(8, 8));
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChessController(Chess.standardGame(), new ChessGUI(8, 10)));
    }
    
}
