package chess_controller;

import chess_model.Chess;
import chess_model.King;
import chess_model.Pawn;
import chess_model.Piece;
import chess_model.Play;
import chess_model.Position;
import chess_view.ChessGUI;
import javax.swing.JOptionPane;

/**
 *
 * @author Alfonso Gallego Fernández
 */
public class ChessController {
    private Chess game;
    private ChessGUI view;

    public ChessController(Chess game, ChessGUI view) {
        this.game = game;
        this.view = view;
        this.view.setController(this);
        this.view.initializeBoard();
        this.view.updateBoard();
    }

    public Chess getGame() {
        return game;
    }

    public ChessGUI getView() {
        return view;
    }
    
    // TO DO
    /*
    private void handleClick(int x, int y) {
        if (x == 0 || y == 0) return; // Ignore label clicks
        if (this.game.isEndOfGame()) return; // Don't do anything if the game has ended.
        Position clickedPos = Position.of(x, y);

        if (selectedPos == null) { // First click stores the selected position.
            if (chess.checkPiece(clickedPos) && chess.findPiece(clickedPos).getColor() == this.activePlayer()) {
                selectedPos = clickedPos;
                highlightValidMoves(selectedPos);
            }
        } else { // Second click attempts to do the movement.
            if (chess.checkPiece(selectedPos)) {
                Piece piece = chess.findPiece(selectedPos);
                boolean playDone = false;
                int castlingInfo = 0; // -1 = left castling was done; 1 = right castling was done; 0 = no castling was done
                
                if (piece instanceof King) {
                    if (clickedPos.equals(Position.of(3, piece.getColor().initRow())) && chess.checkLeftCastling(this.activePlayer())) {
                        chess.doLeftCastling(this.activePlayer());
                        playDone = true;
                        castlingInfo = -1;
                    }
                    if (clickedPos.equals(Position.of(7, piece.getColor().initRow())) && chess.checkRightCastling(this.activePlayer())) {
                        chess.doRightCastling(this.activePlayer());
                        playDone = true;
                        castlingInfo = 1;
                    }
                }
                
                if (!playDone && piece != null && piece.checkLegalMovement(clickedPos)) {
                    piece.move(clickedPos);
                    
                    if (piece instanceof Pawn && piece.getPos().y() == this.activePlayer().crowningRow()) { // Pawn crowning
                        // Menu to crown a Pawn
                        Object[] options = {"Queen", "Knight", "Rook", "Bishop"};
                        int n = JOptionPane.showOptionDialog(mainFrame,
                            "You can crown a pawn. What piece do you want to crown your pawn into?\nNot selecting any option will automatically select Queen.",
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
                if (playDone) { // Record the play (special case for castling) and update the active player
                    if (castlingInfo != 0) {
                        tableModel.addRow(new Object[] {this.activePlayer() + " "+ (castlingInfo == -1 ? "Castling (left)" : "Castling (right)"), "", "", ""});
                    } else {
                        Play lastPlay = chess.getLastPlay();
                        tableModel.addRow(new Object[] {
                            lastPlay.piece().getSimpleName(),
                            lastPlay.initPos(),
                            lastPlay.finPos(),
                            lastPlay.pieceCaptured() != null ? lastPlay.pieceCaptured().getSimpleName() : ""
                        });
                    }
                    this.changeActivePlayer();
                    activePlayerLabel.setText("Active Player: "+this.activePlayer());
                }
            }
            clearHighlights();
            selectedPos = null;
            updateBoard();
            if (chess.checkMate(this.activePlayer())) {
                JOptionPane.showConfirmDialog(
                    mainFrame,
                    this.activePlayer() + " is in checkmate.\n"+this.activePlayer().opposite()+" wins.",
                    "End of the game",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null
                );
                tableModel.addRow(new Object[] {this.activePlayer().opposite()+" wins.", "---", "---", "---"});
                endOfGame = true;
            } else if (chess.checkMate(this.activePlayer(), false)) {
                JOptionPane.showConfirmDialog(
                    mainFrame,
                    this.activePlayer()+" isn't in check but every move would cause a check.\nThe game is a draw.",
                    "End of the game",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null
                );
                tableModel.addRow(new Object[] {"The game is a draw.", "---", "---", "---"});
                endOfGame = true;
            }
        }
    }
    */
}
