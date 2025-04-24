package chess_view;

import chess_controller.ChessController;
import chess_model.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;

/**
 * Class to implement a GUI for chess.
 * @author Alfonso Gallego
 * @version 1.0
 */
public class ChessGUI extends JFrame {
    private final JPanel boardPanel;
    private final JPanel topPanel;
    private final JPanel rightPanel;
    private final JLabel activePlayerLabel;
    private final JTable playHistoryArea;
    private final JPanel tablePanel;
    private final JScrollPane scrollPane;
    private final DefaultTableModel tableModel;
    private final JButton[][] boardButtons;
    private final JButton resetButton;
    private final JButton saveButton;
    private final JButton loadButton;
    
    private ChessController controller;

    public ChessGUI() {
        this.setTitle("Chess Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);

        // Top panel - Active player + Save & Reset buttons
        topPanel = new JPanel();
        
        activePlayerLabel = new JLabel("Active Player: WHITE", SwingConstants.CENTER);
        activePlayerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        activePlayerLabel.setPreferredSize(new Dimension(250, 30));
        
        resetButton = ChessGUI.standardButton("Reset");       
        saveButton = ChessGUI.standardButton("Save");      
        loadButton = ChessGUI.standardButton("Load");
        
        topPanel.add(activePlayerLabel, BorderLayout.WEST);
        topPanel.add(Box.createRigidArea(new Dimension(150, 0)), BorderLayout.EAST);
        topPanel.add(resetButton, BorderLayout.EAST);
        topPanel.add(saveButton, BorderLayout.EAST);
        topPanel.add(loadButton, BorderLayout.EAST);
        
        this.add(topPanel, BorderLayout.NORTH);
        
        // Right panel - Play History
        rightPanel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new String[] {"Piece", "Initial Pos", "Final Pos", "Piece Captured"}, 0);
        playHistoryArea = new JTable(tableModel);
        scrollPane = new JScrollPane(playHistoryArea);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        rightPanel.setPreferredSize(new Dimension(400, 0));
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Play History", 
            TitledBorder.CENTER, TitledBorder.TOP, 
            new Font("Arial", Font.BOLD, 16), Color.BLACK));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        rightPanel.add(tablePanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
        
        // Central (board) panel - Chess board
        boardPanel = new JPanel(new GridLayout(9, 9));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        boardButtons = new JButton[9][9];
        this.initializeBoard();
        this.add(boardPanel);
        this.setVisible(true);
    }
    
    public static JButton standardButton(String label) {
        JButton result = new JButton();
        result.setOpaque(true);
        result.setBorderPainted(false);
        result.setFont(new Font("Arial", Font.BOLD, 16));
        result.setText(label);
        result.setActionCommand(label);
        return result;
    }
    
    public void setController(ChessController controller) {
        this.controller = controller;
        this.updateBoard();
        for (JButton[] buttonArray : boardButtons) {
            for (JButton button : buttonArray) {
                button.addActionListener(this.controller);
            }
        }
        resetButton.addActionListener(this.controller);
        saveButton.addActionListener(this.controller);
        loadButton.addActionListener(this.controller);
    }
    
    public void initializeBoard() {
        for (int y = 8; y >= 0; y--) {
            for (int x = 0; x <= 8; x++) {
                JButton button = new JButton();
                button.setOpaque(true);
                button.setBorderPainted(false);
                boardButtons[x][y] = button;

                if (x == 0 && y == 0) {
                    // Bottom-left empty corner (label intersection)
                    button.setText("");
                } else if (y == 0) {
                    // Column labels (A to H) at the bottom
                    button.setText(""+ ChessController.convertNumberToLetter(x));
                    button.setFont(new Font("Arial", Font.BOLD, 16));
                    button.setEnabled(false);
                } else if (x == 0) {
                    // Row labels (1 to 8) on the left
                    button.setText(String.valueOf(y));
                    button.setFont(new Font("Arial", Font.BOLD, 16));
                    button.setEnabled(false);
                } else {
                    // Regular chessboard squares
                    if ((x + y) % 2 == 0) {
                        button.setBackground(Color.WHITE);
                    } else {
                        button.setBackground(Color.GRAY);
                    }
                    button.setFont(new Font("Dialog", Font.PLAIN, 24));
                    button.setActionCommand("boardButton");
                    button.putClientProperty("x", x);
                    button.putClientProperty("y", y);
                }
                boardPanel.add(button);
            }
        }
    }

    public void highlightValidMoves(Position pos) {
        Chess chess = controller.getGame();
        if (chess.checkPiece(pos)) {
            Piece piece = chess.findPiece(pos);
            for (int x = 1; x <= 8; x++) {
                for (int y = 1; y <= 8; y++) {
                    Position potentialMove = Position.of(x, y);
                    if (piece.checkLegalMovement(potentialMove)) {
                        boardButtons[x][y].setBackground(Color.GREEN);
                    }
                    if (piece.checkLegalMovement(potentialMove, false) && !piece.checkLegalMovement(potentialMove, true)) {
                        boardButtons[x][y].setBackground(Color.ORANGE);
                    }
                    if (piece instanceof King && (
                        ((potentialMove.equals(Position.of(3, piece.getColor().initRow())) && chess.checkLeftCastling(piece.getColor()))
                        || (potentialMove.equals(Position.of(7, piece.getColor().initRow())) && chess.checkRightCastling(piece.getColor())))
                    )) {
                        boardButtons[x][y].setBackground(Color.GREEN);
                    }
                }
            }
        }
    }
    
    public void highlightPiecesThatCanCaptureKing(Position initPos, Position finPos) {
        Chess auxGame = controller.getGame().copyGame();
        ChessColor activePlayer = controller.getGame().getActivePlayer();
        Piece pieceToMove = auxGame.findPiece(initPos);
        pieceToMove.move(finPos, false);
        auxGame.getPieces().stream()
            .filter(piece -> // Filter for the pieces of a different color than active player that can move to capture active player's King.
                piece.getColor() != activePlayer &&
                piece.checkLegalMovement(auxGame.findKing(activePlayer).getPos())
            )
            .map(piece -> boardButtons[piece.getPos().x()][piece.getPos().y()]) // Map each piece to its button on the board
            .forEach(button -> { // Set up a timer on each of those buttons to light it red during 1 second
                Color originalColor = button.getBackground();
                button.setBackground(Color.RED);
                button.repaint();

                Timer timer = new Timer(1000, e -> button.setBackground(originalColor));
                timer.setRepeats(false);
                timer.start();
            });
    }
    
    public void highlightPiecesThatCanCapture(Position initPos, Position finPos) {
        Chess chess = controller.getGame();
        if (chess.checkPiece(initPos) && chess.findPiece(initPos) instanceof King) {
            Piece foundPiece = chess.findPiece(initPos);
            chess.getPieces().stream()
                .filter(piece -> piece.getColor() != foundPiece.getColor())
                .filter(piece -> // Filter to all pieces that could move and capture to the given position, accounting for Pawn's special movement when capturing
                (piece instanceof Pawn) ?
                    Position.yDist(piece.getPos(), finPos) == piece.getColor().yDirection()
                    && Math.abs(Position.xDist(piece.getPos(), finPos)) == 1
                : piece.checkLegalMovement(finPos, false))
                .map(piece -> boardButtons[piece.getPos().x()][piece.getPos().y()]) // Map each piece to the button representing its position
                .forEach(button -> { // Set up a timer on each of those buttons to light it red during 1 second
                    Color originalColor = button.getBackground();
                    button.setBackground(Color.RED);
                    button.repaint();

                    Timer timer = new Timer(1000, e -> button.setBackground(originalColor));
                    timer.setRepeats(false);
                    timer.start();
                });
        }
    }

    public void clearHighlights() {
        for (int x = 1; x <= 8; x++) {
            for (int y = 1; y <= 8; y++) {
                if ((x + y) % 2 == 0) {
                    boardButtons[x][y].setBackground(Color.WHITE);
                } else {
                    boardButtons[x][y].setBackground(Color.GRAY);
                }
            }
        }
    }

    public void updateBoard() {
        Chess chess = controller.getGame();
        for (int x = 1; x <= 8; x++) {
            for (int y = 1; y <= 8; y++) {
                JButton button = boardButtons[x][y];
                if (chess.checkPiece(Position.of(x, y))) {
                    Piece piece = chess.findPiece(Position.of(x, y));
                    button.setText(piece.toString());
                } else {
                    button.setText("");
                }
            }
        }
    }
    
    public String pawnCrowningMenu(Piece piece) {
        String[] options = {"Queen", "Knight", "Rook", "Bishop"};
        int n = JOptionPane.showOptionDialog(
            this,
            "You can crown a pawn. What piece do you want to crown your pawn into?\nNot selecting any option will automatically select Queen.",
            "Crowning Menu",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]); //default button title
        return switch (n) {
            case 1 -> "Knight";
            case 2 -> "Rook";
            case 3 -> "Bishop";
            default -> "Queen";
        };
    }
    
    public void updateActivePlayer() {
        activePlayerLabel.setText("Active Player: "+controller.getGame().getActivePlayer());
    }
    
    public void updatePlayHistory(Play lastPlay) {
        if (lastPlay.castlingInfo() != 0) {
            tableModel.addRow(new Object[] {
                lastPlay.piece().getSimpleName(),
                lastPlay.initPos(),
                lastPlay.finPos(),
                lastPlay.castlingInfo() == -1 ? "L.Castling" : "R.Castling"
            });
        } else {
            tableModel.addRow(new Object[] {
                lastPlay.piece().getSimpleName(),
                lastPlay.initPos(),
                lastPlay.finPos(),
                lastPlay.pieceCaptured() != null ? lastPlay.pieceCaptured().getSimpleName() : ""
            });
        }
    }
    
    public void checkMessage(ChessColor activePlayer) {
        JOptionPane.showConfirmDialog(
            this,
            activePlayer+" is in checkmate.\n"+activePlayer.opposite()+" wins.",
            "End of the game",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null
        );
        tableModel.addRow(new Object[] {activePlayer.opposite()+" wins.", "---", "---", "---"});
    }
    
    public void drawMessage(ChessColor activePlayer) {
        JOptionPane.showConfirmDialog(
            this,
            activePlayer+" isn't in check but every move would cause a check.\nThe game is a draw.",
            "End of the game",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null
        );
        tableModel.addRow(new Object[] {"The game is a draw.", "---", "---", "---"});
    }
    
    public boolean areYouSureYouWantToDoThis(String message) {
        return JOptionPane.showConfirmDialog(
            this,
            message,
            "Are you sure you want to do this?",
            JOptionPane.OK_CANCEL_OPTION
        ) == JOptionPane.OK_OPTION;
    }
    
    public String userTextInputMessage(String title) {
        JTextField textField = new JTextField(20);
        int n = JOptionPane.showConfirmDialog(
            this,
            textField,
            title,
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );
        if (n == JOptionPane.OK_OPTION && !textField.getText().isEmpty() && !textField.getText().isBlank()) {
            return textField.getText();
        } else {
            return ""+controller.getGame().hashCode();
        }
    }
    
    public File fileChooser(String startingPath) throws IOException {
        JFileChooser fileChooser = new JFileChooser(startingPath);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("DAT files", "dat"));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        throw new IOException("No file selected.");
    }
    
    public void resetPlayHistory() {
        tableModel.setRowCount(0);
    }
    
    public void reloadPlayHistory() {
        resetPlayHistory();
        for (Play play : controller.getGame().getPlayHistory()) {
            updatePlayHistory(play);
        }
    }
    
}