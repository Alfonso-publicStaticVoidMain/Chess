package com.mycompany.chess;

public class Rook extends Piece {

    public Rook(Position pos, ChessColor color) {
        super(pos, color);
    }
    
    /**
     * <p>
     * Checks if the proposed position would be a legal movement of the Rook
     * within the {@code Chess} game it's in.
     * </p>
     * @param finPos Position the Rook is attempting to move to.
     * @param checkCheck State parameter to determine if we will declare the
     * movement illegal if it causes a check of its own King.
     * @return Returns true if the proposed movement is a legal position for
     * {@code this} Rook, performing the following checks:
     * 
     * First, some common legality checks are performed within the method 
     * {@link Piece#basicLegalityChecks}: If there's a piece of the same color 
     * in the final Position, if we're checking for checks and the movement
     * would cause one, or if the final Position is the same as the initial one,
     * false is returned.
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
        if (Xmovement != 0 && Ymovement != 0) return false;
        return this.getGame().isPathClear(initPos, finPos);
//        int Xdirection = (Xmovement > 0) ? 1 : (Xmovement < 0) ? -1 : 0;
//        int Ydirection = (Ymovement > 0) ? 1 : (Ymovement < 0) ? -1 : 0;
//        if (Xmovement != 0) {
//            for (int i = 1; i < Math.abs(Xmovement); i++) {
//                if (this.getGame().checkPiece(Position.of(initPos.x() + i * Xdirection, initPos.y()))) return false;
//            }
//            return true;
//        }
//        
//        if (Ymovement != 0) {
//            for (int i = 1; i < Math.abs(Ymovement); i++) {
//                //System.out.println("Position "+Position.of(initPos.x(), initPos.y() + i * Ydirection) + "| Is there a Piece? "+ this.getGame().checkPiece(Position.of(initPos.x(), initPos.y() + i * Ydirection)));
//                if (this.getGame().checkPiece(Position.of(initPos.x(), initPos.y() + i * Ydirection))) return false;
//            }
//        }
//        return true;
    }
    
    @Override
    public boolean checkLegalMovement(Position finPos) {
        return checkLegalMovement(finPos, true);
    }

    @Override
    public Piece copy() {
        return new Rook(this.getPos(), this.getColor());
    }
    
}
