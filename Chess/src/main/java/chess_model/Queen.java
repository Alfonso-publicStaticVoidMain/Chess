package chess_model;

/**
 * Class representing the Queen piece.
 * @author Alfonso Gallego
 */
public class Queen extends Piece {

    public Queen(Position pos, ChessColor color) {
        super(pos, color);
    }
    
    /**
     * Checks if the proposed position would be a legal movement of the Queen
     * within the {@code Chess} game it's in.
     * @param finPos Position the Queen is attempting to move to.
     * @param checkCheck State parameter to determine if we will declare the
     * movement illegal if it causes a check of its own King.
     * @return True if the proposed final Position is a legal movement for
     * {@code this} Queen, performing the following checks:
     * <br><br>
     * First, some common legality checks are performed within the method 
     * {@link Piece#basicLegalityChecks}: If there's a piece of the same color 
     * in the final Position, if we're checking for checks and the movement
     * would cause one, or if the final Position is the same as the initial one,
     * false is returned.
     * <br><br>
     * Unlike the Bishop and the Rook, we do not perform any check to verify
     * that the Queen is moving as befits her royal status, because that check
     * will be intrinsically done in the {@link Chess#isPathClear} method.
     * <br><br>
     * Finally, the method {@link Chess#isPathClear} is called to check whether
     * or not there's any Piece in the path between the initial and final
     * Position (both exclusive), returning false if there is.
     */
    @Override
    public boolean checkLegalMovement(Position finPos, boolean checkCheck) {
        if (!this.basicLegalityChecks(finPos, checkCheck)) return false;
        Position initPos = this.getPos();
        return this.getGame().isPathClear(initPos, finPos);
    }

    /**
     * Copies this Queen as a new Queen object.
     * @return A Queen object with the same {@link Position} and {@link ChessColor},
     * but with no game associated to it.
     */
    @Override
    public Piece copy() {
        return new Queen(this.getPos(), this.getColor());
    }

    @Override
    public String toString() {
        return this.getColor() == ChessColor.WHITE ? "♕" : "♛";
    }
    
}
