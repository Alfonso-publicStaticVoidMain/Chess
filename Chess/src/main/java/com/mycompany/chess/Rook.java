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
    public Piece copy() {
        return new Rook(this.getPos(), this.getColor());
    }
    
}
