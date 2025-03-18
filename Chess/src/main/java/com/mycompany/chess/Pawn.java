package com.mycompany.chess;

public class Pawn extends Piece {

    @Override
    public boolean checkLegalMovement(Position finPos) {
        return true;
    }

    @Override
    public boolean move(Position finPos) {
        return false;
    }
    
}
