package chess;

/**
 * Class representing the Knight piece.
 * @author Alfonso Gallego
 */
public class Knight extends Piece {

    public Knight(Position pos, ChessColor color) {
        super(pos, color);
    }
    
    /**
     * Checks if the proposed position would be a legal movement of the Knight
     * within the {@code Chess} game it's in.
     * @param finPos Position the Knight is attempting to move to.
     * @param checkCheck State parameter to determine if we will declare the
     * movement illegal if it causes a check of its own King.
     * @return True if the proposed final Position is a legal movement for
     * {@code this} Knight, performing the following checks:
     * <br><br>
     * First, some common legality checks are performed within the method 
     * {@link Piece#basicLegalityChecks}: If there's a piece of the same color 
     * in the final Position, if we're checking for checks and the movement
     * would cause one, or if the final Position is the same as the initial one,
     * false is returned.
     * <br><br>
     * Then, it is checked whether or not the proposed final Position would
     * match the movement of a Knight, ie, if the sum of the absolute values of
     * the movements in the X and Y axis is exactly 3, and each of those
     * absolute values are within 1 and 2 (both inclusive).
     * <br><br>
     * There's no need to do any collision checks, because the Knight is unique
     * in its property of being able to jump over other pieces.
     */
    @Override
    public boolean checkLegalMovement(Position finPos, boolean checkCheck) {
        if (!this.basicLegalityChecks(finPos, checkCheck)) return false;
        Position initPos = this.getPos();
        int absXmovement = Math.abs(Position.xDist(initPos, finPos));
        int absYmovement = Math.abs(Position.yDist(initPos, finPos));
        
        return (
            absYmovement + absXmovement == 3
            && absYmovement >= 1
            && absYmovement <= 2
            && absXmovement >= 1
            && absXmovement <= 2
        );
    }

    /**
     * Copies this Knight as a new Knight object.
     * @return A Knight object with the same {@link Position} and {@link ChessColor},
     * but with no game associated to it.
     */
    @Override
    public Piece copy() {
        return new Knight(this.getPos(), this.getColor());
    }
    
}
