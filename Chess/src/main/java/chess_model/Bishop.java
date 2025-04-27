package chess_model;

import graphic_resources.ChessImages;
import javax.swing.ImageIcon;

/**
 * Class representing the Bishop piece.
 * @author Alfonso Gallego
 */
public class Bishop extends Piece {

    public Bishop(Position pos, ChessColor color) {
        super(pos, color);
    }
    
    /**
     * Checks if the proposed position would be a legal movement of the Bishop
     * within the {@code Chess} game it's in.
     * @param finPos Position the Bishop is attempting to move to.
     * @param checkCheck State parameter to determine if we will declare the
     * movement illegal if it causes a check of its own King.
     * @return True if the proposed final Position is a legal movement for
     * {@code this} Bishop, performing the following checks:
     * <br><br>
     * First, some common legality checks are performed within the method 
     * {@link Piece#basicLegalityChecks}: If there's a piece of the same color 
     * in the final Position, if we're checking for checks and the movement
     * would cause one, or if the final Position is the same as the initial one,
     * false is returned.
     * <br><br>
     * Then, it is checked whether or not the proposed final Position would
     * match the movement of a Bishop, ie, if the absolute value of the
     * movement in the X axis is the same as in the Y axis. If this isn't the
     * case, false is returned.
     * <br><br>
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
        if (Math.abs(Xmovement) != Math.abs(Ymovement)) return false;
        return this.getGame().isPathClear(initPos, finPos);
    }

    /**
     * Copies this Bishop as a new Bishop object.
     * @return A Bishop object with the same {@link Position} and {@link ChessColor},
     * but with no game associated to it.
     */
    @Override
    public Piece copy() {
        return new Bishop(this.getPos(), this.getColor());
    }
    
    @Override
    public String toString() {
        return this.getColor() == ChessColor.WHITE ? "♗" : "♝";
    }
    
    @Override
    public ImageIcon toIcon() {
        return this.getColor() == ChessColor.WHITE ? ChessImages.whiteBishop : ChessImages.blackBishop;
    }
    
}
