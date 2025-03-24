package com.mycompany.chess;

import java.util.List;

public class Position {
    private final int x;
    private final int y;
    
    private static final List<Position> validChessPositions = List.of(
        Position.of("A1"), Position.of("A2"), Position.of("A3"), Position.of("A4"), Position.of("A5"), Position.of("A6"), Position.of("A7"), Position.of("A8"),
        Position.of("B1"), Position.of("B2"), Position.of("B3"), Position.of("B4"), Position.of("B5"), Position.of("B6"), Position.of("B7"), Position.of("B8"),
        Position.of("C1"), Position.of("C2"), Position.of("C3"), Position.of("C4"), Position.of("C5"), Position.of("C6"), Position.of("C7"), Position.of("C8"),
        Position.of("D1"), Position.of("D2"), Position.of("D3"), Position.of("D4"), Position.of("D5"), Position.of("D6"), Position.of("D7"), Position.of("D8"),
        Position.of("E1"), Position.of("E2"), Position.of("E3"), Position.of("E4"), Position.of("E5"), Position.of("E6"), Position.of("E7"), Position.of("E8"),
        Position.of("F1"), Position.of("F2"), Position.of("F3"), Position.of("F4"), Position.of("F5"), Position.of("F6"), Position.of("F7"), Position.of("F8"),
        Position.of("G1"), Position.of("G2"), Position.of("G3"), Position.of("G4"), Position.of("G5"), Position.of("G6"), Position.of("G7"), Position.of("G8"),
        Position.of("H1"), Position.of("H2"), Position.of("H3"), Position.of("H4"), Position.of("H5"), Position.of("H6"), Position.of("H7"), Position.of("H8")
    );
    
    /**
     * <p>
     * Private constructor of the factory class Position. It only allows for
     * the creation of Positions that are within the Chess board.
     * </p>
     * @param x X coordinate of the position.
     * @param y Y coordinate of the position.
     * @throws Throws an IllegalArgumentException if the x and y values
     * attempted represent a position that'd be outside of the Chess board.
     */
    private Position(int x, int y) throws IllegalArgumentException {
        if (!isValid(x, y)) throw new IllegalArgumentException("Invalid position on the board.");
        this.x = x;
        this.y = y;
    }
    
    /**
     * <p>
     * Static factory method to create new Positions. In this case, from a
     * String representing the algebraic notation of the position (A1, A2, etc.)
     * </p>
     * @param pos String representing the position.
     * @return Returns a new Position constructed by converting the first char
     * of the String to an integer, to store it as the x coordinate of the
     * position, and storing the second as the y coordinate.
     * If the String doesn't have length 2, or if the position it'd represent
     * is not within the Chess board, an error message is printed and 
     * {@code null} is returned.
     */
    public static Position of(String pos) {
        if (pos.length()!=2) {
            System.out.println("Error occurred while trying to create the position with value: " + pos);
            System.out.println("The specified String doesn't have length 2.");
            return null;
        }
        try {
            return new Position(Chess.convertLetterToNumber(pos.charAt(0)), Integer.parseInt(""+pos.charAt(1)));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("Error occurred while trying to create the position with value: " + pos);
            return null;
        }
    }
    
    /**
     * <p>
     * Static factory method to create new Positions. In this case, from a
     * pair of integers representing the X and Y coordinates of the Position.
     * </p>
     * @param x X coordinate of the new Position.
     * @param y Y coordinate of the new Position.
     * @return Returns a new Position constructed by storing the two integers
     * as the X and Y coordinate.
     * If supposed new Position is not within the Chess board, an error message
     * is printed and {@code null} is returned.
     */
    public static Position of(int x, int y) {
        try {
            return new Position(x, y);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("Error occurred while trying to create the position with values: "+x+", "+y);
            return null;
        }
    }
       
    public int x() {return x;}
    public int y() {return y;}
    public List<Position> getValidPositions() {return Position.validChessPositions;}
    
    /**
     * <p>
     * Checks whether a pair of integers is a valid coordinate of the chess
     * board.
     * </p>
     * @param x The x coordinate of the supposed Position.
     * @param y The y coordinate of the supposed Position.
     * @return Returns true if both x and y are between (and including) 1 and 8.
     */
    public static boolean isValid(int x, int y) {return (x >= 1 && x <= 8 && y >= 1 && y <= 8);}
    
    /**
     * <p>
     * Returns a new Position constructed by moving {@code this} Position 
     * the specified distances in the X and Y axis.
     * </p>
     * @param xDist Distance in the X axis to move.
     * @param yDist Distance in the Y axis to move.
     * @return If it is within the Chess board, returns the Position
     * constructed by moving {@code this} the desired distances. Otherwise,
     * prints the exception thrown by the Position constructor and an error
     * message.
     */
    public Position move(int xDist, int yDist) {
        try {
            return new Position(this.x+xDist, this.y+yDist);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return null;
        }
    }
    
    /**
     * <p>
     * Calculates the signed distance in the X axis between two positions,
     * </p>
     * @param initPos The initial position.
     * @param finPos The final position.
     * @return The x coordinate of the final position minus the x coordinate
     * of the initial position.
     */
    public static int xDist(Position initPos, Position finPos) {
        return finPos.x - initPos.x;
    }
    
    /**
     * <p>
     * Calculates the signed distance in the Y axis between two positions,
     * </p>
     * @param initPos The initial position.
     * @param finPos The final position.
     * @return The y coordinate of the final position minus the y coordinate
     * of the initial position.
     */
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

    /**
     * <p>
     * Represents the Position using the algebraic notation, A1, A2, etc.
     * </p>
     * @return Returns a 2-character String with the first being the x
     * coordinate of the Position converted to a letter (1 -> A, up to
     * 8 -> H) and the second being the digit of the y coordinate.
     */
    @Override
    public String toString() {
        return "" + Chess.convertNumberToLetter(this.x) + this.y;
    }

}
