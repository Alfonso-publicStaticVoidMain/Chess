package com.mycompany.chess;

public class Knight extends Piece {

    public Knight(Position pos, Color color) {
        this.setPos(pos);
        this.setColor(color);
    }
    
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
    
    @Override
    public boolean checkLegalMovement(Position finPos) {
        return this.checkLegalMovement(finPos, true);
    }

    @Override
    public Piece copy() {
        return new Knight(this.getPos(), this.getColor());
    }
    
}
