package com.mycompany.chess;

import java.util.*;

public class Chess {

    private List<Piece> pieces = new ArrayList<>();
    
    public boolean checkPiece(Position pos) {
        long numberOfPiecesInPosition = this.pieces.stream()
                .filter(piece -> piece.getPos().equals(pos))
                .count();
        if (numberOfPiecesInPosition != 0 && numberOfPiecesInPosition != 1) {
            System.out.println("Illegal number of pieces found in position " + pos);
            return false;
        }
        return numberOfPiecesInPosition == 1;
    }
    
    public Piece findPiece(Position pos) {
        if (this.checkPiece(pos)) {
            return this.pieces.stream()
                .filter(piece -> piece.getPos().equals(pos))
                .findAny()
                .get();
        }
        System.out.println("No pieces found in position " + pos);
        return null;
    }
    
    public boolean checkPieceSameColorAs(Piece piece, Position pos) {
        if (!this.checkPiece(pos)) return false;
        if (this.findPiece(pos).getColor() != piece.getColor()) return false;
        return true;
    }
    
    
    public static void main(String[] args) {
        Rook torre = new Rook(Position.of(1, 1), Color.BLACK);
        System.out.println(torre);
        System.out.println(torre.getClass().getSimpleName());
    }
}
