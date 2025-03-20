package com.mycompany.chess;

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

    public int initRow() {
        return this.color.initRow();
    }
    
    public boolean move(Position finPos, boolean checkCheck) {
        return true;
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