package com.mycompany.chess;

public abstract class Piece {
    private Position pos;
    private Color color;
    private Chess game;

    public abstract boolean checkLegalMovement(Position finPos);
    public abstract boolean move(Position finPos);
    
    public Position getPos() {return this.pos;}
    public void setPos(Position pos) {this.pos = pos;}
    public Color getColor() {return this.color;}
    public void setColor(Color color) {this.color = color;}

    @Override
    public String toString() {
        return "Piece{" + "pos=" + pos + ", color=" + color + '}';
    }
}