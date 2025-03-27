package com.mycompany.chess;

import javax.swing.*;
import java.awt.*;

public class ChessGUI {
    private JFrame frame;
    private JPanel boardPanel;
    private JButton[][] boardButtons;
    private Chess chess;
    private Position selectedPos = null;

    public ChessGUI() {
        chess = new Chess();
        chess.addStandardPieces();

        frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        boardPanel = new JPanel(new GridLayout(9, 9)); // Extra row & column
        boardButtons = new JButton[9][9];

        initializeBoard();
        updateBoard();

        frame.add(boardPanel);
        frame.setVisible(true);
    }

    private void initializeBoard() {
        for (int y = 8; y >= 0; y--) { // Flip rows so 1 is at bottom
            for (int x = 0; x <= 8; x++) { // Keep columns left-to-right
                JButton button = new JButton();
                button.setOpaque(true);
                button.setBorderPainted(false);
                boardButtons[x][y] = button;

                if (x == 0 && y == 0) {
                    // Bottom-left empty corner (label intersection)
                    button.setText("");
                } else if (y == 0) {
                    // Column labels (A to H) at the bottom
                    button.setText(String.valueOf((char) ('A' + x - 1)));
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

                    final int fx = x, fy = y;
                    button.addActionListener(e -> handleClick(fx, fy));
                }

                boardPanel.add(button);
            }
        }
    }

    private void handleClick(int x, int y) {
        if (x == 0 || y == 0) return; // Ignore label clicks
        Position clickedPos = Position.of(x, y);

        if (selectedPos == null) {
            if (chess.checkPiece(clickedPos)) {
                selectedPos = clickedPos;
                highlightValidMoves(selectedPos);
            }
        } else {
            if (chess.checkPiece(selectedPos)) {
                Piece piece = chess.findPiece(selectedPos);
                if (piece.checkLegalMovement(clickedPos)) {
                    piece.move(clickedPos);
                }
            }
            clearHighlights();
            selectedPos = null;
            updateBoard();
        }
    }

    private void highlightValidMoves(Position pos) {
        if (chess.checkPiece(pos)) {
            Piece piece = chess.findPiece(pos);
            for (int x = 1; x <= 8; x++) {
                for (int y = 1; y <= 8; y++) {
                    Position potentialMove = Position.of(x, y);
                    if (piece.checkLegalMovement(potentialMove)) {
                        boardButtons[x][y].setBackground(Color.YELLOW);
                    }
                }
            }
        }
    }

    private void clearHighlights() {
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

    private void updateBoard() {
        for (int x = 1; x <= 8; x++) {
            for (int y = 1; y <= 8; y++) {
                JButton button = boardButtons[x][y];

                if (chess.checkPiece(Position.of(x, y))) {
                    Piece piece = chess.findPiece(Position.of(x, y));
                    button.setText(getPieceSymbol(piece));
                } else {
                    button.setText("");
                }
            }
        }
    }

    private String getPieceSymbol(Piece piece) {
        if (piece instanceof Pawn) return piece.getColor() == ChessColor.WHITE ? "♙" : "♟";
        if (piece instanceof Knight) return piece.getColor() == ChessColor.WHITE ? "♘" : "♞";
        if (piece instanceof Bishop) return piece.getColor() == ChessColor.WHITE ? "♗" : "♝";
        if (piece instanceof Rook) return piece.getColor() == ChessColor.WHITE ? "♖" : "♜";
        if (piece instanceof Queen) return piece.getColor() == ChessColor.WHITE ? "♕" : "♛";
        if (piece instanceof King) return piece.getColor() == ChessColor.WHITE ? "♔" : "♚";
        return "?";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessGUI::new);
    }
}