package com.mycompany.chess;

import java.util.Optional;

public class Play {
    private Piece piece;
    private Position initPos;
    private Position finPos;
    private Optional<Piece> pieceEaten = null;

    public Play(Piece piece, Position initPos, Position finPos, Optional<Piece> pieceEaten) {
        this.piece = piece;
        this.initPos = initPos;
        this.finPos = finPos;
        this.pieceEaten = pieceEaten;
    }

    public Play(Piece piece, Position initPos, Position finPos) {
        this.piece = piece;
        this.initPos = initPos;
        this.finPos = finPos;
    }

    @Override
    public String toString() {
        // TO DO
        return "";
        //return "Play{" + "piece=" + piece + ", initPos=" + initPos + ", finPos=" + finPos + ", pieceEaten=" + pieceEaten + '}';
    }

    public Piece getPiece() {
        return this.piece;
    }

    public Position getInitPos() {
        return this.initPos;
    }

    public Position getFinPos() {
        return this.finPos;
    }

    public Optional<Piece> getPieceEaten() {
        return this.pieceEaten;
    }
    
    public boolean pieceWasEaten() {
        return this.pieceEaten.isPresent();
    }
}
