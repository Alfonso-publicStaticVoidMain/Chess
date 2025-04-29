package chess_model;

import graphic_resources.ChessImages;
import javax.swing.ImageIcon;

/**
 * Class representing the Amazon piece, a nonstandard chess Piece, that moves
 * either like a Knight or Queen.
 * @author Alfonso Gallego
 */
public class Amazon extends Piece {    
    
    public Amazon(Position pos, ChessColor color) {
        super(pos, color);
    }
    
    /**
     * Checks if the proposed position would be a legal movement of the 
     * Amazon within the {@code Chess} game it's in.
     * @param finPos Position the Amazon is attempting to move to.
     * @param checkCheck State parameter to determine if we will declare the
     * movement illegal if it causes a check of its own King.
     * @return True if the proposed final Position is a legal movement for
     * {@code this} Amazon, performing the following checks:
     * <br><br>
     * First, some common legality checks are performed within the method 
     * {@link Piece#basicLegalityChecks}: If there's a piece of the same color 
     * in the final Position, if we're checking for checks and the movement
     * would cause one, or if the final Position is the same as the initial one,
     * false is returned.
     * <br><br>
     * Then, it is checked whether or not the proposed final Position would
     * match the movement of a Knight, ie, if the absolute value of the 
     * movement in the X axis plus in the Y axis is exactly equal to 3, and
     * both absolute values are within 1 and 2 (inclusive). In that case, true
     * is returned.
     * <br><br>
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
        if (isKnightLikePath(this.getPos(), finPos)) return true;
        return this.getGame().isPathClear(this.getPos(), finPos);
    }

    /**
     * Copies this Amazon as a new Amazon object.
     * @return A Amazon object with the same {@link Position} and
     * {@link ChessColor}, but with no game associated to it.
     */
    @Override
    public Piece copy() {
        return new Amazon(this.getPos(), this.getColor());
    }
    
    @Override
    public ImageIcon toIcon() {
        return this.getColor() == ChessColor.WHITE ? ChessImages.WHITEAMAZON : ChessImages.BLACKAMAZON;
    }
}
