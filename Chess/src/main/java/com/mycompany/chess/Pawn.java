package com.mycompany.chess;

public class Pawn extends Piece {

    public Pawn(Position pos, Color color) {
        this.setPos(pos);
        this.setColor(color);
    }
    
    @Override
    public boolean checkLegalMovement(Position finPos) {
        return true;
    }

    @Override
    public boolean move(Position finPos) {
        return false;
    }

    @Override
    public Piece copy() {
        return new Pawn(this.getPos(), this.getColor());
    }

    @Override
    public boolean checkLegalMovement(Position finPos, boolean checkCheck) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
