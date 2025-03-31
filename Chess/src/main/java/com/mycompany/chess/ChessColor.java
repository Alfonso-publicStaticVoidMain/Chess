package com.mycompany.chess;

public enum ChessColor {
    WHITE(1, 2, 1, 8),
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

    public int yDirection() {return this.yDirection;}
    public int initRowPawn() {return this.initRowPawn;}
    public int initRow() {return this.initRow;}
    public int crowningRow() {return this.crowningRow;}
    
    /**
     * @return Returns the oppositve of {@code this} color.
     */
    public ChessColor opposite() {
        return this==WHITE ? BLACK : WHITE;
    }
    
}
