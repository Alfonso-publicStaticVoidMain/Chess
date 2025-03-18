package com.mycompany.chess;

public abstract class Piece {
    private Position pos;
    private Color color;
    private Chess game;

    public abstract boolean checkLegalMovement(Position finPos);
    public abstract boolean checkLegalMovement(Position finPos, boolean checkCheck);
    public abstract boolean move(Position finPos);
    public abstract Piece copy();
    
    public Position getPos() {return this.pos;}
    public void setPos(Position pos) {this.pos = pos;}
    public Color getColor() {return this.color;}
    public void setColor(Color color) {this.color = color;}
    public Chess getGame() {return this.game;}
    public void setGame(Chess game) {this.game = game;}

    @Override
    public String toString() {
        return "" + Character.toUpperCase(this.getClass().getSimpleName().charAt(0)) + Character.toUpperCase(this.getColor().name().charAt(0));
    }
    
    public static boolean basicLegalityChecks(Piece piece, Position finPos, boolean checkCheck) {
        if (piece.getGame().checkPieceSameColorAs(piece, finPos)) return false;
        if (checkCheck && piece.getGame().checkIfMovementCausesCheck(piece, finPos)) return false;
        if (piece.getPos().equals(finPos));
        return true;
    }
    
}