package chess;

/**
 * Class representing the Pawn piece.
 * @author Alfonso Gallego
 */
public class Pawn extends Piece {

    public Pawn(Position pos, ChessColor color) {
        super(pos, color);
    }
    
    /**
     * Getter for the initial row of this pawn, as dictated by its color.
     * @return 2 if this Pawn's {@link ChessColor} is WHITE, 7 if its BLACK.
     */
    @Override
    public int initRow() {
        return this.getColor().initRowPawn();
    }
    
    /**
     * Getter for the Y direction of this pawn, as dictated by its color.
     * @return 1 if this Pawn's {@link ChessColor} is WHITE, -1 if its BLACK.
     */
    public int yDirection() {
        return this.getColor().yDirection();
    }
    
    /**
     * Overloaded version of the {@link Pawn#checkLegalMovement(Position, boolean)}
     * method, defaulting the value of boolean checkCheck to true.
     * @param finPos Position we're attempting to move {@code this} Pawn to.
     * @return True if the movement is legal for the Pawn and doing it wouldn't
     * cause a check for its color, false otherwise.
     */
    @Override
    public boolean checkLegalMovement(Position finPos) {
        return checkLegalMovement(finPos, true);
    }
    
    /**
     * Checks if the proposed position would be a legal movement of the Pawn
     * within the {@code Chess} game it's in.
     * 
     * @param finPos Position the Pawn is attempting to move to.
     * @param checkCheck State parameter to determine if we will declare the
     * movement illegal if it causes a check for its player.
     * @return Returns true if the proposed movement is a legal position for
     * {@code this} Pawn, performing the following checks:
     * 
     * First, some common legality checks are performed within the method 
     * {@link Piece#basicLegalityChecks}: If there's a piece of the same color 
     * in the final Position, if we're checking for checks (checkCheck is true)
     * and the movement would cause one, or if the final Position is the same
     * as the initial one, false is returned.
     * 
     * Then we check if the movement in the Y axis is in the same direction
     * as the Pawn's movement, dictated by its color. If this doesn't happen,
     * ie, if the direction and the movement have different signs, false
     * is returned.
     * 
     * If there isn't a Piece in the final Position we check if the Pawn is
     * trying to move En Passant. Otherwise, if the movement in the X axis
     * isn't 0, false is returned.
     * 
     * Then, if the movement in the Y axis in absolute value is greater than 2,
     * or if it's greater than 1 while the starting row isn't the same as the
     * initial row of the Pawn, or if the movement is 2 and there's a Piece in
     * the middle, false is returned.
     * 
     * If there's a Piece in the final Position (which must be of a different
     * color because of the previous legality checks), we check if the
     * absolute value of the movement in the X axis is 1 and the movement in the
     * Y axis is the same as the Pawn's direction. If this isn't the case, false
     * is returned.
     */
    @Override
    public boolean checkLegalMovement(Position finPos, boolean checkCheck) {
        if (!this.basicLegalityChecks(finPos, checkCheck)) return false;
        Position initPos = this.getPos();
        int Xmovement = Position.xDist(initPos, finPos);
        int Ymovement = Position.yDist(initPos, finPos);
        int Ydirection = this.yDirection();
        int initRow = this.initRow();
        if (Ymovement * Ydirection < 0) return false;
        if (!this.getGame().checkPiece(finPos)) {
            if (this.checkLegalEnPassant() && Xmovement == this.xDirEnPassant() && Ymovement == Ydirection) return true;
            if (Xmovement != 0) return false;
            
            if (Math.abs(Ymovement) > 2) return false;
            
            if (initPos.y() != initRow
                && Math.abs(Ymovement) > 1
            ) return false;
            
            if (Math.abs(Ymovement) == 2
                && this.getGame().checkPiece(Position.of(initPos.x(), initPos.y()+Ydirection))
            ) return false;
        } else {
            if (Ymovement != Ydirection
                || Math.abs(Xmovement) != 1
            ) return false;
        }
        return true;
    }
    
    /**
     * Checks if {@code this} Pawn can make a legal En Passant capture,
     * taking into account the last play recorded in its game.
     * @return Returns true if the {@link Pawn} is able to do an En Passant
     * move given the last play stored in the {@code playRecord} attribute
     * of {@code this.game}.
     * 
     * False will be returned in any of the following cases:
     * <ul>
     * <li>{@code this}'s game doesn't have at least one {@link Play} stored.</li>
     * <li>The last {@link Piece} that was moved ins't a Pawn.</li>
     * <li>The last Pawn moved's color is the same as {@code this}.</li>
     * <li>The last Pawn moved didn't move 2 units in the Y axis.</li>
     * <li>The last Pawn moved's position isn't in the same Y coordinate as
     * {@code this}.</li>
     * <li>The last Pawn moved's position isn't within distance 1 in the X axis
     * to {@code this}.</li>
     * </ul>
     */
    public boolean checkLegalEnPassant() {
        if (this.getGame().getPlayRecord().isEmpty()) return false;
        Play lastPlay = this.getGame().getLastPlay();
        if (!(lastPlay.getPiece() instanceof Pawn)) return false;
        if (lastPlay.getPiece().getColor() == this.getColor()) return false;
        if (Math.abs(Position.yDist(lastPlay.getInitPos(), lastPlay.getFinPos())) != 2) return false;
        if (Math.abs(Position.yDist(lastPlay.getFinPos(), this.getPos())) != 0) return false;
        if (Math.abs(Position.xDist(this.getPos(), lastPlay.getFinPos())) != 1) return false;
        return true;
    }
    
    /**
     * Determines the direction in the X axis {@code this} must move to
     * succesfully make an En Passant capture, if able to do so.
     * @return Returns the direction {@code this} Pawn must move to make an
     * En Passant capture on the last moved Pawn, if able, ie, the distance in
     * the X axis between {@code this}'s current position and the final position
     * of the last moved Pawn in the {@link Chess} game's {@code playRecord}
     * attribute. If unable to move En Passant, returns 0.
     */
    public int xDirEnPassant() {
        if (this.checkLegalEnPassant()) return Position.xDist(this.getPos(), this.getGame().getPlayRecord().get(this.getGame().getPlayRecord().size()-1).getFinPos());
        return 0;
    }

    /**
     * Copies this Pawn as a new Pawn object.
     * @return A Pawn object with the same {@link Position} and {@link ChessColor},
     * but with no game associated to it.
     */
    @Override
    public Piece copy() {
        return new Pawn(this.getPos(), this.getColor());
    }
    
}
