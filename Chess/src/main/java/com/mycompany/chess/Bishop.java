package com.mycompany.chess;

public class Bishop extends Piece {

    public Bishop(Position pos, ChessColor color) {
        super(pos, color);
    }
    
    /**
     * <p>
     * Checks if the proposed position would be a legal movement of the Bishop
     * within the {@code Chess} game it's in.
     * </p>
     * @param finPos Position the Bishop is attempting to move to.
     * @param checkCheck State parameter to determine if we will declare the
     * movement illegal if it causes a check of its own King.
     * @return Returns true if the proposed movement is a legal position for
     * {@code this} Bishop, performing the following checks:
     * 
     * First, some common legality checks are performed within the method 
     * {@link Piece#basicLegalityChecks}: If there's a piece of the same color 
     * in the final Position, if we're checking for checks and the movement
     * would cause one, or if the final Position is the same as the initial one,
     * false is returned.
     * 
     * Then, it is checked whether or not the proposed final Position would
     * match the movement of a Bishop, ie, if the absolute value of the
     * movement in the X axis is the same as in the Y axis. If this isn't the
     * case, false is returned.
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
        if (Math.abs(Xmovement) != Math.abs(Ymovement)) return false;
        return this.getGame().isPathClear(initPos, finPos);
//        int Xdirection = 0;
//        int Ydirection = 0;
//        
//        if (Xmovement > 1) Xdirection = 1;
//        else if (Xmovement < -1) Xdirection = -1;
//        if (Ymovement > 1) Ydirection = 1;
//        else if (Ymovement < -1) Ydirection = -1;
//        
//        if (Xdirection != 0 && Ydirection != 0) {
//            for (int i = 1; i < Math.abs(Ymovement); i++) {
//                if (this.getGame().checkPiece(Position.of(initPos.x() + i * Xdirection, initPos.y() + i * Ydirection))) return false;
//            }
//            return true;
//        }        
//        return false;
    }
    
    @Override
    public boolean checkLegalMovement(Position finPos) {
        return this.checkLegalMovement(finPos, true);
    }

    @Override
    public Piece copy() {
        return new Bishop(this.getPos(), this.getColor());
    }
    
}
