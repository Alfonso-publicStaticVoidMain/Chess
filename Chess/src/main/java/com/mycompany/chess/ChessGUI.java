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
        chess = new Chess();

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
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();
                button.setOpaque(true);
                button.setBorderPainted(false);

                // Set alternating colors for the chessboard
                if ((row + col) % 2 == 0) {
                    button.setBackground(Color.WHITE);
                } else {
                    button.setBackground(Color.GRAY);
                }

                final int r = row, c = col;
                button.addActionListener(e -> handleClick(r, c));

                boardButtons[row][col] = button;
                boardPanel.add(button);
            }
        }
    }

    private void handleClick(int row, int col) {
        Position clickedPos = Position.of(row, col);

        if (selectedPos == null) {
            // First click: Select a piece
            if (chess.checkPiece(clickedPos)) {
                selectedPos = clickedPos;
                highlightValidMoves(selectedPos);
            }
        } else {
            // Second click: Move the piece if legal
            
            Piece piece = chess.findPiece(selectedPos);
            if (piece != null && piece.checkLegalMovement(clickedPos)) {
                piece.move(clickedPos);
            }
            clearHighlights();
            selectedPos = null; // Reset selection
            updateBoard();
        }
    }

    private void highlightValidMoves(Position pos) {
        Piece piece = chess.findPiece(pos);
        if (piece != null) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    Position potentialMove = Position.of(row, col);
                    if (piece.checkLegalMovement(potentialMove)) {
                        boardButtons[row][col].setBackground(Color.YELLOW);
                    }
                }
            }
        }
    }

    private void clearHighlights() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    boardButtons[row][col].setBackground(Color.WHITE);
                } else {
                    boardButtons[row][col].setBackground(Color.GRAY);
                }
            }
        }
    }

    private void updateBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = boardButtons[row][col];
                Piece piece = chess.findPiece(Position.of(row, col));

                if (piece != null) {
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
