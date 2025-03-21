package com.mycompany.chess;

import java.util.Optional;

public abstract class Piece {
    private Position pos;
    private Color color;
    private Chess game;

    public abstract boolean checkLegalMovement(Position finPos);
    public abstract boolean checkLegalMovement(Position finPos, boolean checkCheck);
    public abstract Piece copy();
    
    public Position getPos() {return this.pos;}
    public void setPos(Position pos) {this.pos = pos;}
    public Color getColor() {return this.color;}
    public void setColor(Color color) {this.color = color;}
    public Chess getGame() {return this.game;}
    public void setGame(Chess game) {this.game = game;}

    /**
     * The initial row of {@code this} Piece on the board.
     * @return Returns an integer representing the initial row of the Piece.
     * The method will be overwritten in the {@link Pawn} class returning
     * the initRowPawn of its color.
     */
    public int initRow() {
        return this.color.initRow();
    }
    
    /**
     * <p>
     * If the movement is legal, moves {@code this} Piece to the specified
     * Position.
     * </p>
     * @param finPos Position we're trying to move the Piece to.
     * @param checkCheck State parameter to check if the movement should be
     * declared illegal if it'd cause a check.
     * @return Returns true if the movement was sucessfully done. In that case,
     * updates {@code this}'s position, and if there was a Piece previously in
     * that position, eliminates it from its game's playRecord.
     * Then the movement is recorded into {@code this}'s game's playRecord
     * attribute.
     * @see
     *      {@link Piece#checkLegalMovement(Position, boolean)}
     *      {@link Pawn#xDirEnPassant}
     *      {@link Chess#findPiece}
     */
    public boolean move(Position finPos, boolean checkCheck) {
        Chess chessGame = this.getGame();
        Position initPos = this.getPos();
        Piece eatenPiece = null;
        if (this.checkLegalMovement(finPos, checkCheck)) {
            if (chessGame.checkPiece(finPos)) {
                eatenPiece = chessGame.findPiece(finPos);
                chessGame.getPieces().remove(eatenPiece);
            }
            this.setPos(finPos);
            if (this instanceof Pawn pawn) {
                int Xmovement = Position.xDist(initPos, finPos);
                if (Xmovement == pawn.xDirEnPassant()) {
                    eatenPiece = chessGame.findPiece(Position.of(finPos.x(), finPos.y() - this.getColor().yDirection()));
                    chessGame.getPieces().remove(eatenPiece);
                }
            }
            this.getGame().getPlayRecord().add(new Play(this, initPos, finPos, Optional.of(eatenPiece)));
        }
        return false;
    }
    
    public boolean move(Position finPos) {
        return move(finPos, true);
    }
    
    /**
     * <p>
     * Method that performs basic legality checks on a piece movement.
     * It is intended to be referenced by the implementations of the
     * {@link Piece#checkLegalMovement(Position, boolean)} method
     * on each of the child classes of Piece.
     * </p>
     * @param piece The piece that we want to move.
     * @param finPos The position we want to move the piece to.
     * @param checkCheck state parameter to track if we need to declare the 
     * movement illegal if it causes a check.
     * @return Returns false if either of the following happens:
     *      There's a piece of the same color in the final position.
     *      We are checking for checks and the movement causes one.
     *      The final position is the same as the initial position.
     * @see
     *      {@link Chess#checkPieceSameColorAs(Piece, Position)}
     *      {@link Chess#checkIfMovementCausesCheck(Piece, Position)}
     */
    public static boolean basicLegalityChecks(Piece piece, Position finPos, boolean checkCheck) {
        if (piece.getGame().checkPieceSameColorAs(piece, finPos)) return false;
        if (checkCheck && piece.getGame().checkIfMovementCausesCheck(piece, finPos)) return false;
        return !piece.getPos().equals(finPos);
    }
    
    @Override
    public String toString() {
        return "" + Character.toUpperCase(this.getColor().name().charAt(0)) + Character.toUpperCase(this.getClass().getSimpleName().charAt(0));
    }
    
}