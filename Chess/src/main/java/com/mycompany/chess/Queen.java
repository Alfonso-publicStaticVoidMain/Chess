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
        if (this.getGame().checkPieceSameColorAs(this, finPos)) return false;
        Position initPos = this.getPos();
        if (checkCheck && this.getGame().checkIfMovementCausesCheck(this, finPos)) return false;
        int Xmovement = Position.xDist(initPos, finPos);
        int Ymovement = Position.yDist(initPos, finPos);
        
        
        
        return false;
    }

    @Override
    public boolean move(Position finPos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Piece copy() {
        return new Queen(this.getPos(), this.getColor());
    }
    
}
