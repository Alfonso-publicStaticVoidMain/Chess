package com.mycompany.chess;

public enum Color {
    WHITE(1, 2, 1),
    BLACK(-1, 7, 8);
    
    private final int yDirection;
    private final int initRowPawn;
    private final int initRow;
    
    Color(int yDirection, int initRowPawn, int initRow) {
        this.yDirection = yDirection;
        this.initRowPawn = initRowPawn;
        this.initRow = initRow;
    }

    public int yDirection() {return this.yDirection;}
    public int initRowPawn() {return this.initRowPawn;}
    public int initRow() {return this.initRow;}
    
}
