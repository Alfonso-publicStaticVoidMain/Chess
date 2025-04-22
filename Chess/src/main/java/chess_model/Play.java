package chess_model;

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
public record Play(Piece piece, Position initPos, Position finPos, Piece pieceCaptured) {
//    /**
//     * The {@link Piece} that was moved.
//     */
//    private final Piece piece;
//    
//    /**
//     * The initial {@link Position} from which that {@link Piece} was moved.
//     */
//    private final Position initPos;
//    
//    /**
//     * The final {@link Position} the {@link Piece} was moved to.
//     */
//    private final Position finPos;
//    
//    /**
//     * The {@link Piece} that was captured, or {@code null} if none was captured.
//     */
//    private final Piece pieceCaptured;

    /**
     * 3-parameter constructor, setting the pieceCaptured attribute to
     * {@code null}.
     * @param piece {@link Piece} moved.
     * @param initPos Initial {@link Position}.
     * @param finPos Final {@link Position}.
     */
    public Play(Piece piece, Position initPos, Position finPos) {
        this(piece, initPos, finPos, null);
//        this.piece = piece;
//        this.initPos = initPos;
//        this.finPos = finPos;
//        this.pieceCaptured = null;
    }
    
    /**
     * 4-parameter constructor, storing a captured Piece.
     * @param piece {@link Piece} moved.
     * @param initPos Initial {@link Position}.
     * @param finPos Final {@link Position}.
     * @param pieceCaptured {@link Piece} captured.
     */
    public Play(Piece piece, Position initPos, Position finPos, Piece pieceCaptured) {
        this.piece = piece;
        this.initPos = initPos;
        this.finPos = finPos;
        this.pieceCaptured = pieceCaptured;
    }
    
}
