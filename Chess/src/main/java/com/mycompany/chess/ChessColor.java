package com.mycompany.chess;

public enum ChessColor {
    WHITE(1, 2, 1, 8),
    BLACK(-1, 7, 8, 1);
    
    private final int yDirection;
    private final int initRowPawn;
    private final int initRow;
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
    
    public ChessColor opposite() {
        return this==WHITE ? BLACK : WHITE;
    }
    
}
