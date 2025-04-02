package chess;

/**
 * Class representing the King piece.
 * @author Alfonso Gallego
 */
public class King extends Piece {

    public King(Position pos, ChessColor color) {
        super(pos, color);
    }
    
    /**
     * Checks if the proposed position would be a legal movement of the King
     * within the {@code Chess} game it's in.
     * @param finPos Position the King is attempting to move to.
     * @param checkCheck State parameter to determine if we will declare the
     * movement illegal if it causes a check of its own King.
     * @return True if the proposed final Position is a legal movement for
     * {@code this} King, performing the following checks:
     * <br><br>
     * First, some common legality checks are performed within the method 
     * {@link Piece#basicLegalityChecks}: If there's a piece of the same color 
     * in the final Position, if we're checking for checks and the movement
     * would cause one, or if the final Position is the same as the initial one,
     * false is returned.
     * <br><br>
     * Then, it is checked whether or not the proposed final Position would
     * match the movement of a King, ie, if the absolute value of the
     * movement in the X axis and in the Y axis both are at most 1, and if
     * the final Position isn't adjacent to the King of the opposing color.
     * <br><br>
     * If for any reason we wanted to include a King Piece in a Chess game
     * where there's no King of the opposing color, an extra check for when
     * the {@link Chess#findKing} method returns null has been included for
     * consistency.
     */
    @Override
    public boolean checkLegalMovement(Position finPos, boolean checkCheck) {
        if (!this.basicLegalityChecks(finPos, checkCheck)) return false;     
        Position initPos = this.getPos();
        int absXmovement = Math.abs(Position.xDist(initPos, finPos));
        int absYmovement = Math.abs(Position.yDist(initPos, finPos));
        if (absXmovement <= 1 && absYmovement <= 1) {
            King enemyKing = this.getGame().findKing(this.getColor().opposite());
            if (enemyKing == null) return true;
            return Math.abs(Position.xDist(this.getPos(), enemyKing.getPos())) > 1
            || Math.abs(Position.yDist(this.getPos(), enemyKing.getPos())) > 1;
        }
        return false;
    }
    
    /**
     * Checks whether {@code this} King would be in check if it moved to the
     * specified position.
     * @param finPos The position the King is trying to move to.
     * @return Returns true if no piece of the opposing color can make a legal
     * move to the piece {@code this} is attempting to move to, with the ability
     * to eat (accounting for Pawns different movement when eating).
     */
    public boolean checkCheck(Position finPos) {    
        return this.getGame().pieces().stream()
            .filter(piece -> piece.getColor() != this.getColor())
            .anyMatch(piece -> 
                (piece instanceof Pawn) ?
                    Position.yDist(piece.getPos(), finPos) == piece.getColor().yDirection()
                    && Math.abs(Position.xDist(piece.getPos(), finPos)) == 1
                : piece.checkLegalMovement(finPos, false)
            );
    }
    
    /**
     * Overloaded version of the {@link King#checkCheck(Position)} method,
     * defaulting the finPos argument to the current Position of {@code this}.
     * @return Returns whether or not {@code this} King is in check in its
     * current Position.
     */
    public boolean checkCheck() {
        return this.checkCheck(this.getPos());
    }

    /**
     * Copies this King as a new King object.
     * @return A King object with the same {@link Position} and {@link ChessColor},
     * but with no game associated to it.
     */
    @Override
    public Piece copy() {
        return new King(this.getPos(), this.getColor());
    }
    
}
