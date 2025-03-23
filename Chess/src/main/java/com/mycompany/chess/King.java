package com.mycompany.chess;

public class King extends Piece {

    public King(Position pos, Color color) {
        super(pos, color);
    }
    
    /**
     * <p>
     * Checks if the proposed position would be a legal movement of the King
     * within the {@code Chess} game it's in.
     * </p>
     * @param finPos Position the King is attempting to move to.
     * @param checkCheck State parameter to determine if we will declare the
     * movement illegal if it causes a check of its own King.
     * @return Returns true if the proposed movement is a legal position for
     * {@code this} King, performing the following checks:
     * 
     * First, some common legality checks are performed within the method 
     * {@link Piece#basicLegalityChecks}: If there's a piece of the same color 
     * in the final Position, if we're checking for checks and the movement
     * would cause one, or if the final Position is the same as the initial one,
     * false is returned.
     * 
     * Then, it is checked whether or not the proposed final Position would
     * match the movement of a King, ie, if the absolute value of the
     * movement in the X axis and in the Y axis both are at most 1, and if
     * the final Position isn't adjacent to the King of the opposing color.
     * 
     * If for any reason we wanted to include a King Piece in a Chess game
     * where there's no King of the opposing color, an extra check for when
     * the {@link Chess#findKing} method returns null has been included for
     * consistency.
     */
    @Override
    public boolean checkLegalMovement(Position finPos, boolean checkCheck) {
        if (!this.basicLegalityChecks(finPos, checkCheck)) return false;     
        Position initPos = this.getPos();
        int Xmovement = Position.xDist(initPos, finPos);
        int Ymovement = Position.yDist(initPos, finPos);
        if (Xmovement <= 1 && Ymovement <= 1) {
            King enemyKing = this.getGame().findKing(this.getColor().opposite());
            if (enemyKing == null) return true;
            return !(Position.xDist(this.getPos(), enemyKing.getPos()) <= 1
            || Position.yDist(this.getPos(), enemyKing.getPos()) <= 1);
        }
        return false;
    }
    
    @Override
    public boolean checkLegalMovement(Position finPos) {
        return this.checkLegalMovement(finPos, true);
    }
    
    /**
     * <p>
     * Checks whether {@code this} King would be in check if it moved to the
     * specified position.
     * </p>
     * @param finPos The position the King is trying to move to.
     * @return Returns true if no piece of the opposing color can make a legal
     * move to the piece {@code this} is attempting to move to, with the ability
     * to eat (accounting for Pawns different movement when eating).
     */
    public boolean checkCheck(Position finPos) {    
        return this.getGame().getPieces().stream()
            .filter(piece -> piece.getColor() != this.getColor())
            .filter(piece -> 
                (piece instanceof Pawn) ?
                    Position.yDist(piece.getPos(), finPos) == piece.getColor().yDirection()
                    && Math.abs(Position.xDist(piece.getPos(), finPos)) == 1
                : piece.checkLegalMovement(finPos, false)
            )
            .count()
            != 0;
    }
    
    public boolean checkCheck() {
        return this.checkCheck(this.getPos());
    }

    @Override
    public Piece copy() {
        return new King(this.getPos(), this.getColor());
    }
    
}
