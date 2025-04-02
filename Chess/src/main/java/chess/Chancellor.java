package chess;

/**
 * Class representing the Chancellor piece, a nonstandard chess Piece in the
 * Almost Chess variant, that moves either like a Knight or Rook and substitutes
 * the Queen.
 * @author Alfonso Gallego
 */
public class Chancellor extends Piece {    
    
    public Chancellor(Position pos, ChessColor color) {
        super(pos, color);
    }
    
    /**
     * Checks if the proposed position would be a legal movement of the 
     * Chancellor within the {@code Chess} game it's in.
     * @param finPos Position the Rook is attempting to move to.
     * @param checkCheck State parameter to determine if we will declare the
     * movement illegal if it causes a check of its own King.
     * @return Returns true if the proposed movement is a legal position for
     * {@code this} Chancellor, performing the following checks:
     * 
     * First, some common legality checks are performed within the method 
     * {@link Piece#basicLegalityChecks}: If there's a piece of the same color 
     * in the final Position, if we're checking for checks and the movement
     * would cause one, or if the final Position is the same as the initial one,
     * false is returned.
     * 
     * Then, it is checked whether or not the proposed final Position would
     * match the movement of a Knight, ie, if the absolute value of the 
     * movement in the X axis plus in the Y axis is exactly equal to 3, and
     * both absolute values are within 1 and 2 (inclusive). In that case, true
     * is returned.
     * 
     * Then, it is checked whether or not the proposed final Position would
     * match the movement of a Rook, ie, if exactly one of the movements along
     * each of the axis is zero. If both are nonzero, false is returned.
     * 
     * Finally, the method {@link Chess#isPathClear} is called to check whether
     * or not there's any Piece in the path between the initial and final
     * Position (both exclusive), returning false if there is.
     */
    @Override
    public boolean checkLegalMovement(Position finPos, boolean checkCheck) {
        if (!this.basicLegalityChecks(finPos, checkCheck)) return false;
        Position initPos = this.getPos();
        int Xmovement = Position.xDist(initPos, finPos);
        int Ymovement = Position.yDist(initPos, finPos);
        if (Math.abs(Xmovement)+Math.abs(Ymovement)==3
            && Math.abs(Xmovement)<=2 && Math.abs(Xmovement)>=1
            && Math.abs(Ymovement)<=2 && Math.abs(Ymovement)>=1) return true;
        if (Xmovement != 0 && Ymovement != 0) return false;
        return this.getGame().isPathClear(initPos, finPos);
    }
    
    /**
     * Overloaded version of the {@link Chancellor#checkLegalMovement(Position, boolean)}
     * method, defaulting the value of boolean checkCheck to true.
     * @param finPos Position we're attempting to move {@code this} Chancellor
     * to.
     * @return True if the movement is legal for the Chancellor and doing it
     * wouldn't cause a check for its color, false otherwise.
     */
    @Override
    public boolean checkLegalMovement(Position finPos) {
        return checkLegalMovement(finPos, true);
    }

    /**
     * Copies this Chancellor as a new Chancellor object.
     * @return A Chancellor object with the same {@link Position} and
     * {@link ChessColor}, but with no game associated to it.
     */
    @Override
    public Piece copy() {
        return new Chancellor(this.getPos(), this.getColor());
    }
    
}
