package com.mycompany.chess;

public class King extends Piece {

    public King(Position pos, Color color) {
        this.setPos(pos);
        this.setColor(color);
    }
    
    @Override
    public boolean checkLegalMovement(Position finPos) {
        return this.checkLegalMovement(finPos, true);
    }
    
    @Override
    public boolean checkLegalMovement(Position finPos, boolean checkCheck) {
        if (!this.basicLegalityChecks(finPos, checkCheck)) return false;     
        Position initPos = this.getPos();
        int Xmovement = Position.xDist(initPos, finPos);
        int Ymovement = Position.yDist(initPos, finPos);
        
        // TO DO
        
        return false;
    }
    
    /**
     * <p>
     * Checks whether {@code this} King would be in check if it moved to the
     * specified position.
     * </p>
     * @param finPos The position the King is trying to move to.
     * @return Returns true if no piece of the opposing color can make a legal
     * move to the piece {@code this} is attempting to move to, with the ability
     * to eat (account for Pawns different movement when eating).
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
