package chess;

/**
 * Class representing a Play in a chess game, storing the Piece that was moved,
 * its initial and final Positions, and the Piece that was captured, if any.
 * @author Alfonso Gallego
 */
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
     * The {@link Piece} that was captured, or {@code null} if none was captured.
     */
    private final Piece pieceCaptured;

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

    /**
     * 3-parameter constructor, setting the pieceCaptured attribute to
     * {@code null}.
     * @param piece {@link Piece} moved.
     * @param initPos Initial {@link Position}.
     * @param finPos Final {@link Position}.
     */
    public Play(Piece piece, Position initPos, Position finPos) {
        this.piece = piece;
        this.initPos = initPos;
        this.finPos = finPos;
        this.pieceCaptured = null;
    }

    /**
     * Getter for the pieced moved attribute.
     * @return The Piece that was moved in {@code this} Play.
     */
    public Piece getPiece() {return this.piece;}

    /**
     * Getter for the initial position attribute.
     * @return Initial Position of {@code this} Play.
     */
    public Position getInitPos() {return this.initPos;}

    /**
     * Getter for the final position attribute.
     * @return Final Position of {@code this} Play.
     */
    public Position getFinPos() {return this.finPos;}

    /**
     * Getter for the piece captured attribute.
     * @return The Piece that was captured in {@code this} Play, if any. If
     * no Piece was captured, {@code null} is returned.
     */
    public Piece getPieceCaptured() {return this.pieceCaptured;}
    
    /**
     * Default implementation of the toString method.
     * @return A String representing all attributes of {@code this} Play.
     */
    @Override
    public String toString() {
         return "Play{" + "piece=" + piece + ", initPos=" + initPos + ", finPos=" + finPos + ", pieceCaptured=" + pieceCaptured + '}';
    }
    
}
