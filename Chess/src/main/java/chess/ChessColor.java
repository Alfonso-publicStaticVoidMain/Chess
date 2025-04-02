package chess;

/**
 * Enum class representing the two colors of chess: black and white. It has
 * int attributes storing the value of the initial row of the pieces in general
 * and of the pawns in particular of that color, the direction the pawns of 
 * that color move, and the row they must reach in order to be able to crown.
 * @author Alfonso Gallego
 */
public enum ChessColor {

    /**
     * WHITE color of chess.
     */
    WHITE(1, 2, 1, 8),

    /**
     * BLACK color of chess.
     */
    BLACK(-1, 7, 8, 1);
    
    /**
     * Integer representing the direction the pawns of this color moves.
     * 1 represents forwards (for whites) and -1 backwards (for blacks).
     */
    private final int yDirection;
    
    /**
     * Integer representing the initial row of the Pawns of this color.
     */
    private final int initRowPawn;
    
    /**
     * Integer representing the initial row of non-Pawns of this color.
     */
    private final int initRow;
    
    /**
     * Integer representing the crowning row of Pawns of this color.
     */
    private final int crowningRow;
    
    ChessColor(int yDirection, int initRowPawn, int initRow, int crowningRow) {
        this.yDirection = yDirection;
        this.initRowPawn = initRowPawn;
        this.initRow = initRow;
        this.crowningRow = crowningRow;
    }

    /**
     * Getter for the Y direction attribute.
     * @return The direction that {@link Pawn}s of this color move towards, ie,
     * 1 for {@code WHITE} and -1 for {@code BLACK}.
     */
    public int yDirection() {return this.yDirection;}
    /**
     * Getter for the initial row of Pawns attribute.
     * @return The initial row of {@link Pawn}s of this color, ie, 2 for
     * WHITE and 7 for BLACK.
     */
    public int initRowPawn() {return this.initRowPawn;}
    /**
     * Getter for the initial row of non-Pawns attribute.
     * @return The initial row of non-{@link Pawn} {@link Piece}s of this color,
     * ie, 1 for WHITE and 8 for BLACK.
     */
    public int initRow() {return this.initRow;}
    /**
     * Getter for the crowning row of Pawns attribute.
     * @return The row {@link Pawn}s of this color must reach in order to crown,
     * ie, 8 for WHITE and 1 for BLACK.
     */
    public int crowningRow() {return this.crowningRow;}
    
    /**
     * Returns the opposite color of {@code this}.
     * @return Returns the oppositve of {@code this} color, ie, BLACK if
     * {@code this} was WHITE, and WHITE if {@code this} was BLACK.
     */
    public ChessColor opposite() {
        return this==WHITE ? BLACK : WHITE;
    }
    
}
