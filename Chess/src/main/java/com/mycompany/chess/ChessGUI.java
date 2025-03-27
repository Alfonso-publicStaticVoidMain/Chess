package com.mycompany.chess;

import javax.swing.*;
import java.awt.*;

public class ChessGUI {
    private JFrame frame;
    private JPanel boardPanel;
    private JButton[][] boardButtons;
    private Chess chess;
    private Position selectedPos = null;
    private ChessColor activePlayer = ChessColor.WHITE;

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

        if (selectedPos == null) { // First click stores the selected position.
            if (chess.checkPiece(clickedPos) && chess.findPiece(clickedPos).getColor() == activePlayer) {
                selectedPos = clickedPos;
                highlightValidMoves(selectedPos);
            }
        } else { // Second click attempts to do the movement.
            if (chess.checkPiece(selectedPos)) {
                Piece piece = chess.findPiece(selectedPos);
                boolean playDone = false;
                
                if (piece instanceof King) {
                    if (clickedPos.equals(Position.of(3, piece.getColor().initRow())) && chess.checkLeftCastling(piece.getColor())) {
                        chess.doLeftCastling(activePlayer);
                        playDone = true;
                    }
                    if (clickedPos.equals(Position.of(7, piece.getColor().initRow())) && chess.checkRightCastling(piece.getColor())) {
                        chess.doRightCastling(activePlayer);
                        playDone = true;
                    }
                }
                
                if (!playDone && piece.checkLegalMovement(clickedPos)) {
                    piece.move(clickedPos);
                    
                    if (piece instanceof Pawn && piece.getPos().y() == piece.getColor().crowningRow()) {
                        // Menu to crown a Pawn
                        Object[] options = {"Queen", "Knight", "Rook", "Bishop"};
                        int n = JOptionPane.showOptionDialog(frame,
                            "You can crown a pawn. What piece do you want to crown your pawn into?\nNot selecting any option will automatically select Queen",
                            "Crowning Menu",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,     //do not use a custom Icon
                            options,  //the titles of buttons
                            options[0]); //default button title
                        String newPieceType = "Queen";
                        switch (n) {
                            //case 0 -> newPieceType = "Queen";
                            case 1 -> newPieceType = "Knight";
                            case 2 -> newPieceType = "Rook";
                            case 3 -> newPieceType = "Bishop";
                            //case -1 -> newPieceType = "Queen";
                        }
                        chess.crownPawn(piece, newPieceType);
                    }
                    playDone = true;
                }
                if (playDone) activePlayer = activePlayer == ChessColor.WHITE ? ChessColor.BLACK : ChessColor.WHITE;
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
                        boardButtons[x][y].setBackground(Color.GREEN);
                    }
                    if (piece instanceof King &&
                        (((potentialMove.equals(Position.of(3, piece.getColor().initRow())) && chess.checkLeftCastling(piece.getColor()))
                        || (potentialMove.equals(Position.of(7, piece.getColor().initRow())) && chess.checkRightCastling(piece.getColor()))))
                    ) boardButtons[x][y].setBackground(Color.GREEN);
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