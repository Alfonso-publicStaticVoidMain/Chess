package com.mycompany.chess;

public class Bishop extends Piece {

    public Bishop(Position pos, Color color) {
        this.setPos(pos);
        this.setColor(color);
    }
    
    @Override
    public boolean checkLegalMovement(Position finPos, boolean checkCheck) {
        if (!Piece.basicLegalityChecks(this, finPos, checkCheck)) return false;
        Position initPos = this.getPos();
        int Xmovement = Position.xDist(initPos, finPos);
        int Ymovement = Position.yDist(initPos, finPos);
        
        if (Math.abs(Xmovement) != Math.abs(Ymovement)) return false;
        
        int Xdirection = 0;
        int Ydirection = 0;
        
        if (Xmovement > 1) Xdirection = 1;
        else if (Xmovement < -1) Xdirection = -1;
        if (Ymovement > 1) Ydirection = 1;
        else if (Ymovement < -1) Ydirection = -1;
        
        if (Xdirection != 0 && Ydirection != 0) {
            for (int i = 1; i < Math.abs(Ymovement); i++) {
                if (this.getGame().checkPiece(Position.of(initPos.x() + i * Xdirection, initPos.y() + i * Ydirection))) return false;
            }
            return true;
        }        
        return false;
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
