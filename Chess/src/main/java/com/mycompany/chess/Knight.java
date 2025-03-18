package com.mycompany.chess;

public class Knight extends Piece {

    public Knight(Position pos, Color color) {
        this.setPos(pos);
        this.setColor(color);
    }
    
    @Override
    public boolean checkLegalMovement(Position finPos) {
        return this.checkLegalMovement(finPos, true);
    }
    
    @Override
    public boolean checkLegalMovement(Position finPos, boolean checkCheck) {
        if (this.getGame().checkPieceSameColorAs(this, finPos)) return false;
        Position initPos = this.getPos();
        if (checkCheck && this.getGame().checkIfMovementCausesCheck(this, finPos)) return false;
        int Xmovement = Position.xDist(initPos, finPos);
        int Ymovement = Position.yDist(initPos, finPos);
        
        return (
            Math.abs(Ymovement) + Math.abs(Xmovement) == 3
            && Math.abs(Ymovement) >= 1
            && Math.abs(Ymovement) <= 2
            && Math.abs(Ymovement) >= 1
            && Math.abs(Xmovement) <= 2
        );
    }

    @Override
    public boolean move(Position finPos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Piece copy() {
        return new Knight(this.getPos(), this.getColor());
    }

    
    
}
