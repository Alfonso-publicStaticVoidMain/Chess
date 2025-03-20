package com.mycompany.chess;

public class Rook extends Piece {

    public Rook(Position pos, Color color) {
        this.setPos(pos);
        this.setColor(color);
    }
    
    @Override
    public boolean checkLegalMovement(Position finPos) {
        return checkLegalMovement(finPos, true);
    }
    
    @Override
    public boolean checkLegalMovement(Position finPos, boolean checkCheck) {
        if (!Piece.basicLegalityChecks(this, finPos, checkCheck)) return false;
        Position initPos = this.getPos();
        int Xmovement = Position.xDist(initPos, finPos);
        int Ymovement = Position.yDist(initPos, finPos);
        
        if (Xmovement != 0 && Ymovement != 0) return false;
        int Xdirection = 0;
        int Ydirection = 0;
        if (Xmovement > 1) Xdirection = 1;
        else if (Xmovement < -1) Xdirection = -1;
        else return true;
        if (Xdirection != 0) {
            for (int i = 1; i < Math.abs(Xmovement); i++) {
                if (this.getGame().checkPiece(Position.of(initPos.x() + i * Xdirection, initPos.y()))) return false;
            }
            return true;
        }
        if (Ymovement > 1) Ydirection = 1;
        else if (Ymovement < -1) Ydirection = -1;
        else return true;
        
        if (Ydirection != 0) {
            for (int i = 1; i < Math.abs(Ymovement); i++) {
                if (this.getGame().checkPiece(Position.of(initPos.x(), initPos.y() + i * Ydirection))) return false;
            }
        }
        return true;
    }

    @Override
    public Piece copy() {
        return new Rook(this.getPos(), this.getColor());
    }
    
}
