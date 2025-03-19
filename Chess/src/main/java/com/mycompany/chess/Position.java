package com.mycompany.chess;

public class Position {
    private final int x;
    private final int y;
    
    private Position(int x, int y) {
        if (!isValid(x, y)) throw new IllegalArgumentException("Invalid position on the board.");
        this.x = x;
        this.y = y;
    }
    
    public static Position of(String pos) {
        if (pos.length()!=2) return null;
        try {
            return new Position(Chess.convertLetterToNumber(Character.toUpperCase(pos.charAt(0))), Integer.parseInt(""+pos.charAt(1)));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("Error occurred while trying to create the position with value: " + pos);
            return null;
        }
    }
    
    public static Position of(int x, int y) {
        try {
            return new Position(x, y);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("Error occurred while trying to create the position with values: "+x+", "+y);
            return null;
        }
    }
    
    public static boolean isValid(int x, int y) {return (x >= 1 && x <= 8 && y >= 1 && y <= 8);}
    public int x() {return x;}
    public int y() {return y;}
    
    public Position move(int xDist, int yDist) {
        try {
            return new Position(this.x+xDist, this.y+yDist);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static int xDist(Position initPos, Position finPos) {
        return finPos.x - initPos.x;
    }
    
    public static int yDist(Position initPos, Position finPos) {
        return finPos.y - initPos.y;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        return (this.x == other.x && this.y == other.y);
//        if (this.x != other.x) {
//            return false;
//        }
//        return this.y == other.y;
    }

    @Override
    public String toString() {
        return "" + Chess.convertNumberToLetter(this.x) + this.y;
    }

}
