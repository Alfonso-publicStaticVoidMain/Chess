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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Chess chess = Chess.standardGame();
            ChessGUI view = new ChessGUI();
            ChessController controller = new ChessController(chess, view);
        });
    }
    
}
