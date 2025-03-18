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
            return new Position(
                switch (Character.toUpperCase(pos.charAt(0))) {
                    case 'A' -> 1;
                    case 'B' -> 2;
                    case 'C' -> 3;
                    case 'D' -> 4;
                    case 'E' -> 5;
                    case 'F' -> 6;
                    case 'G' -> 7;
                    case 'H' -> 8;
                    default -> 0;
                },
                Integer.parseInt(""+pos.charAt(1))
            );
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
        return Math.abs(finPos.x - initPos.x);
    }
    
    public static int yDist(Position initPos, Position finPos) {
        return Math.abs(finPos.y - initPos.y);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }

    @Override
    public String toString() {
        return switch (this.x) {
            case 1 -> "A";
            case 2 -> "B";
            case 3 -> "C";
            case 4 -> "D";
            case 5 -> "E";
            case 6 -> "F";
            case 7 -> "G";
            case 8 -> "H";
            default -> "";
        } + this.y;
    }
    
    
}
