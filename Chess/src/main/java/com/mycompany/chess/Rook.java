package com.mycompany.chess;

public class Rook extends Piece {

    public Rook(Position pos, Color color) {
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
    
}
