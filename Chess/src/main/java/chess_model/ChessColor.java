package chess_model;

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
    WHITE(1),

    /**
     * BLACK color of chess.
     */
    BLACK(-1);
    
    /**
     * Integer representing the direction the pawns of this color moves.
     * 1 represents forwards (for whites) and -1 backwards (for blacks).
     */
    private final int yDirection;
    
    ChessColor(int yDirection) {
        this.yDirection = yDirection;
    }

    /**
     * Getter for the Y direction attribute.
     * @return The direction that {@link Pawn}s of this color move towards, ie,
     * 1 for {@code WHITE} and -1 for {@code BLACK}.
     */
    public int yDirection() {return this.yDirection;}
    
    public int initRow(GameConfiguration config) {
        return config.initRow().get(this);
    }
    
    public int initRowPawn(GameConfiguration config) {
        return config.initRowPawn().get(this);
    }
    
    public int crowningRow(GameConfiguration config) {
        return config.crowningRow().get(this);
    }
    
    /**
     * Returns the opposite color of {@code this}.
     * @return Returns the oppositve of {@code this} color, ie, BLACK if
     * {@code this} was WHITE, and WHITE if {@code this} was BLACK.
     */
    public ChessColor opposite() {
        return this==WHITE ? BLACK : WHITE;
    }
    
}
