package com.mycompany.chess;

import java.util.Optional;

public class Play {
    private final Piece piece;
    private final Position initPos;
    private final Position finPos;
    private final Piece pieceEaten;

    public Play(Piece piece, Position initPos, Position finPos, Piece pieceEaten) {
        this.piece = piece;
        this.initPos = initPos;
        this.finPos = finPos;
        this.pieceEaten = pieceEaten;
    }

    public Play(Piece piece, Position initPos, Position finPos) {
        this.piece = piece;
        this.initPos = initPos;
        this.finPos = finPos;
        this.pieceEaten = null;
    }

    @Override
    public String toString() {
        // TO DO
        
        return "Play{" + "piece=" + piece + ", initPos=" + initPos + ", finPos=" + finPos + ", pieceEaten=" + pieceEaten + '}';
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

    public Piece getPieceEaten() {
        return this.pieceEaten;
    }
}
