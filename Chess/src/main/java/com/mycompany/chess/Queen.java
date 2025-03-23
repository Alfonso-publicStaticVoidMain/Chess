package com.mycompany.chess;

public class Queen extends Piece {

    public Queen(Position pos, Color color) {
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
        return this.getGame().isPathClear(initPos, finPos);
//        int Xdirection = 0;
//        int Ydirection = 0;
//        
//        if (Math.abs(Xmovement) == Math.abs(Ymovement)) {
//            if (Xmovement > 1) Xdirection = 1;
//            else if (Xmovement < -1) Xdirection = -1;
//            if (Ymovement > 1) Ydirection = 1;
//            else if (Ymovement < -1) Ydirection = -1;
//
//            if (Xdirection != 0 && Ydirection != 0) {
//                for (int i = 1; i < Math.abs(Ymovement); i++) {
//                    if (this.getGame().checkPiece(Position.of(initPos.x() + i * Xdirection, initPos.y() + i * Ydirection))) return false;
//                }
//            }
//        } else if (Xmovement == 0 || Ymovement == 0) {
//            if (Xmovement > 1) {
//                Xdirection = 1;
//            } else if (Xmovement < -1) {
//                Xdirection = -1;
//            }
//
//            if (Xdirection != 0) {
//                for (int i = 1; i < Math.abs(Xmovement); i++) {
//                    if (this.getGame().checkPiece(Position.of(initPos.x() + i * Xdirection, initPos.y()))) return false;
//                }
//            }
//
//            if (Ymovement > 1) {
//                Ydirection = 1;
//            } else if (Ymovement < -1) {
//                Ydirection = -1;
//            }
//
//            if (Ydirection != 0) {
//                for (int i = 1; i < Math.abs(Ymovement); i++) {
//                    if (this.getGame().checkPiece(Position.of(initPos.x(), initPos.y() + i * Ydirection))) return false;
//                }
//            }
//        } else return false;
//        return true;
    }

    @Override
    public Piece copy() {
        return new Queen(this.getPos(), this.getColor());
    }
    
}
