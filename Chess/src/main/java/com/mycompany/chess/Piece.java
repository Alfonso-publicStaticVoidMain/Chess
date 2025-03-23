package com.mycompany.chess;

import java.util.Optional;

public abstract class Piece {
    private Position pos;
    private Color color;
    private Chess game;
    
    protected Piece(Position pos, Color color) {
        this.pos = pos;
        this.color = color;
    }

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
     * @return Returns an integer representing the initial row of the Piece,
     * as is stored in that Piece's Color.
     * The method will be overwritten in the {@link Pawn} class, returning
     * the attribute initRowPawn of its Color.
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
     * @param recordMovement State parameter to track if the movement should
     * be added to the playRecord attribute of the Chess game of {@code this}.
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
    public boolean move(Position finPos, boolean checkCheck, boolean recordMovement) {
        Chess chessGame = this.getGame();
        Position initPos = this.getPos();
        Piece eatenPiece;
        if (this.checkLegalMovement(finPos, checkCheck)) {
            if (chessGame.checkPiece(finPos)) {
                eatenPiece = chessGame.findPiece(finPos);
                chessGame.getPieces().remove(eatenPiece);
                if (recordMovement) chessGame.getPlayRecord().add(new Play(this, initPos, finPos, Optional.of(eatenPiece)));
            } else if (this instanceof Pawn pawn) {
                int Xmovement = Position.xDist(initPos, finPos);
                if (pawn.checkLegalEnPassant() && Xmovement == pawn.xDirEnPassant()) {
                    eatenPiece = chessGame.getLastPlay().getPiece();
                    chessGame.getPieces().remove(eatenPiece);
                    if (recordMovement) chessGame.getPlayRecord().add(new Play(this, initPos, finPos, Optional.of(eatenPiece)));
                }
            }
            this.setPos(finPos);
            if (recordMovement) chessGame.getPlayRecord().add(new Play(this, initPos, finPos));
            
            if (chessGame.getLeftCastlingAvaliability().get(this.getColor()) && (
                initPos.equals(Position.of(1, this.getColor().initRow()))
                || initPos.equals(Position.of(5, this.getColor().initRow())))
                ) chessGame.getLeftCastlingAvaliability().put(this.getColor(), false);
            
            if (chessGame.getRightCastlingAvaliability().get(this.getColor()) && (
                initPos.equals(Position.of(8, this.getColor().initRow()))
                || initPos.equals(Position.of(5, this.getColor().initRow())))
                ) chessGame.getRightCastlingAvaliability().put(this.getColor(), false);
        }
        return false;
    }

    public boolean move(Position finPos, boolean checkCheck) {
        return this.move(finPos, checkCheck, true);
    }
    
    public boolean move(Position finPos) {
        return this.move(finPos, true, true);
    }
    
    /**
     * <p>
     * Checks if the movement of a certain Piece to a certain Position causes
     * the King of that Piece's Color to be in check.
     * </p>
     * @param finPos Position we're attempting to move the Piece to.
     * @return Returns true if the movement causes the King of the same color
     * as the {@code piece} to be in check after moving it to {@code finPos}.
     * To achieve this, it copies the game into an auxiliary game, performs the
     * movement there, and then checks if the auxiliary King is in check.
     * @see
     *      {@link Chess#findPiece}
     *      {@link Chess#findKing}
     *      {@link Piece#move(Position, boolean)}
     *      {@link King#checkCheck()}
     */
    public boolean checkIfMovementCausesCheck(Position finPos) {
        Chess auxGame = this.getGame().copyGame();
        Piece copyOfPiece = auxGame.findPiece(this.getPos());
        copyOfPiece.move(finPos, false, true);
        //System.out.println("[DEBUG] Position of Piece "+copyOfPiece+" = "+copyOfPiece.getPos());
        return auxGame.findKing(copyOfPiece.getColor()).checkCheck();
    }
    
    /**
     * <p>
     * Method that performs basic legality checks on a piece movement.
     * It is intended to be referenced by the implementations of the
     * {@link Piece#checkLegalMovement(Position, boolean)} method
     * on each of the child classes of Piece.
     * </p>
     * @param finPos Position we want to move the piece to.
     * @param checkCheck State parameter to track if we need to declare the 
     * movement illegal if it causes a check.
     * @return Returns false if either of the following happens:
     *      There's a piece of the same color in the final position.
     *      We are checking for checks and the movement causes one.
     *      The final position is the same as the initial position.
     * @see
     *      {@link Chess#checkPieceSameColorAs(Piece, Position)}
     *      {@link Chess#checkIfMovementCausesCheck(Piece, Position)}
     */
    public boolean basicLegalityChecks(Position finPos, boolean checkCheck) {
        if (this.getGame().checkPieceSameColorAs(this, finPos)) return false;
        if (checkCheck && this.checkIfMovementCausesCheck(finPos)) return false;
        return !this.getPos().equals(finPos);
    }
    
    @Override
    public String toString() {
        return "" + Character.toUpperCase(this.getColor().name().charAt(0)) + Character.toUpperCase(this.getClass().getSimpleName().charAt(0));
    }
    
}