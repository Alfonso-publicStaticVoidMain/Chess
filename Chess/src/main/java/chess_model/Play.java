package chess_model;

import java.io.Serializable;

/**
 * Record representing a Play in a chess game, storing the Piece that was moved,
 * its initial and final Positions, and the Piece that was captured, if any.
 * @author Alfonso Gallego
 * @param piece {@link Piece} that was moved in the Play.
 * @param initPos Initial {@link Position} of the movement.
 * @param finPos Final {@link Position} of the movement.
 * @param pieceCaptured {@link Piece} captured in the Play, if any.
 */
//public class Play {
public record Play(Piece piece, Position initPos, Position finPos, Piece pieceCaptured, int castlingInfo) implements Serializable {

    /**
     * 4-parameter constructor, setting the pieceCaptured attribute to
     * {@code null}.
     * @param piece {@link Piece} moved.
     * @param initPos Initial {@link Position}.
     * @param finPos Final {@link Position}.
     */
    public Play(Piece piece, Position initPos, Position finPos, int castlingInfo) {
        this(piece, initPos, finPos, null, castlingInfo);
    }
    
    /**
     * 4-parameter constructor, storing a captured Piece and setting castlingInfo
     * to 0, since capturing is incompatible with castling.
     * @param piece {@link Piece} moved.
     * @param initPos Initial {@link Position}.
     * @param finPos Final {@link Position}.
     * @param pieceCaptured {@link Piece} captured.
     */
    public Play(Piece piece, Position initPos, Position finPos, Piece pieceCaptured) {
        this(piece, initPos, finPos, pieceCaptured, 0);
    }
    
    /**
     * 5-parameter constructor, storing a captured Piece.
     * @param piece {@link Piece} moved.
     * @param initPos Initial {@link Position}.
     * @param finPos Final {@link Position}.
     * @param pieceCaptured {@link Piece} captured.
     * @param castlingInfo Information about if castling was done or not.
     * 0 means no castling was done. -1 that left castling was done and 1 that
     * right castling was done.
     */
    public Play(Piece piece, Position initPos, Position finPos, Piece pieceCaptured, int castlingInfo) {
        this.piece = piece;
        this.initPos = initPos;
        this.finPos = finPos;
        this.pieceCaptured = pieceCaptured;
        this.castlingInfo = castlingInfo;
    }
    
}
