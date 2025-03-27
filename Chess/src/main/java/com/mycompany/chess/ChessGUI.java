package com.mycompany.chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChessGUI {
    private JFrame frame;
    private JPanel boardPanel;
    private JButton[][] boardButtons;
    private Chess chess;
    private Position selectedPos = null;

    public ChessGUI() {
        chess = new Chess(); // Assuming you have a Chess class managing the game state
        chess.addStandardPieces();
        frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        boardPanel = new JPanel(new GridLayout(8, 8));
        boardButtons = new JButton[8][8];

        initializeBoard();
        updateBoard();

        frame.add(boardPanel);
        frame.setVisible(true);
    }

    private void initializeBoard() {
        for (int y = 1; y <= 8; y++) {
            for (int x = 1; x <= 8; x++) {
                JButton button = new JButton();
                button.setOpaque(true);
                button.setBorderPainted(false);

                // Set alternating colors for the chessboard
                if ((x + y) % 2 == 0) {
                    button.setBackground(Color.WHITE);
                } else {
                    button.setBackground(Color.GRAY);
                }

                final int fx = x, fy = y;
                button.addActionListener(e -> handleClick(fx, fy));

                boardButtons[y - 1][x - 1] = button;
                boardPanel.add(button);
            }
        }
    }

    private void handleClick(int x, int y) {
        Position clickedPos = Position.of(x, y);

        if (selectedPos == null) {
            // First click: Select a piece (only if there's a piece on this position)
            if (chess.checkPiece(clickedPos)) {
                selectedPos = clickedPos;
                highlightValidMoves(selectedPos);
            }
        } else {
            // Second click: Move the piece only if it's a valid move
            if (chess.checkPiece(selectedPos)) {
                Piece piece = chess.findPiece(selectedPos);
                if (piece.checkLegalMovement(clickedPos)) {
                    piece.move(clickedPos);
                }
            }
            clearHighlights();
            selectedPos = null; // Reset selection
            updateBoard();
        }
    }

    private void highlightValidMoves(Position pos) {
        if (chess.checkPiece(pos)) {
            Piece piece = chess.findPiece(pos);
            for (int y = 1; y <= 8; y++) {
                for (int x = 1; x <= 8; x++) {
                    Position potentialMove = Position.of(x, y);
                    if (piece.checkLegalMovement(potentialMove)) {
                        boardButtons[y - 1][x - 1].setBackground(Color.YELLOW);
                    }
                }
            }
        }
    }

    private void clearHighlights() {
        for (int y = 1; y <= 8; y++) {
            for (int x = 1; x <= 8; x++) {
                if ((x + y) % 2 == 0) {
                    boardButtons[y - 1][x - 1].setBackground(Color.WHITE);
                } else {
                    boardButtons[y - 1][x - 1].setBackground(Color.GRAY);
                }
            }
        }
    }

    private void updateBoard() {
        for (int y = 1; y <= 8; y++) {
            for (int x = 1; x <= 8; x++) {
                JButton button = boardButtons[y - 1][x - 1];

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
