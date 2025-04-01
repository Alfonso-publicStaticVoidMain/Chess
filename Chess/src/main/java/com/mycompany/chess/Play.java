package com.mycompany.chess;

public class Play {
    /**
     * The {@link Piece} that was moved.
     */
    private final Piece piece;
    /**
     * The initial {@link Position} from which that {@link Piece} was moved.
     */
    private final Position initPos;
    /**
     * The final {@link Position} the {@link Piece} was moved to.
     */
    private final Position finPos;
    /**
     * The {@link Piece} that was eaten, or {@code null} if none was eaten.
     */
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

    public Piece getPiece() {return this.piece;}
    public Position getInitPos() {return this.initPos;}
    public Position getFinPos() {return this.finPos;}
    public Piece getPieceEaten() {return this.pieceEaten;}
    
     @Override
    public String toString() {
         return "Play{" + "piece=" + piece + ", initPos=" + initPos + ", finPos=" + finPos + ", pieceEaten=" + pieceEaten + '}';
    }
    
}
