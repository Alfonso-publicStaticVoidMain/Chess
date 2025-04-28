package chess_model;

import controller.ChessController;
import java.io.Serializable;
import java.util.List;

/**
 * Record representing the valid positions of a chess board. Contains two
 * int attributes x and y, representing the X and Y coordinate of the
 * position, respectively.
 * @author Alfonso Gallego
 * @param x X coordinate of the Position.
 * @param y Y coordinate of the Position.
 */
public record Position(int x, int y) implements Serializable {
    
    /**
     * Constructor of the record Position.
     * error message.
     * @param x X coordinate of the position.
     * @param y Y coordinate of the position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Static factory method to create new Positions from a String representing
     * the position in algebraic chess notation (A1, A2, etc.)
     * @param pos String representing the position.
     * @return Returns a new Position constructed by converting the first char
     * of the String to an integer, to store it as the x coordinate of the
     * position, and storing the second as the y coordinate.
     * If the String doesn't have length 2, or if its characters don't represent
     * a position in algebraic notation, an error message is printed and 
     * {@code null} is returned.
     */
    public static Position of(String pos) {
        if (pos.length()!=2) {
            System.out.println("Error occurred while trying to create the position with value: " + pos);
            System.out.println("The specified String doesn't have length 2.");
            return null;
        }
        try {
            final int x = ChessController.convertLetterToNumber(pos.charAt(0));
            final int y = Integer.parseInt(""+pos.charAt(1));
            if (x >= 1 && y >= 1) return new Position(x, y);
            else throw new IllegalArgumentException(pos+" represents coordinates ("+x+", "+y+"), which are outside the chess board");
        } catch (IllegalArgumentException e) {
            System.out.println("Error occurred while trying to create the position with value: " + pos);
            System.out.println(e);
            return null;
        }
    }
    
    /**
     * Static factory method to create new Positions from a pair of integers
     * representing the X and Y coordinates of the Position.
     * @param x X coordinate of the new Position.
     * @param y Y coordinate of the new Position.
     * @return Returns a new Position constructed by storing the two integers
     * as the X and Y coordinate.
     * If supposed new Position is not within the Chess board, an error message
     * is printed and {@code null} is returned.
     */
    public static Position of(int x, int y) {
        try {
            if (x >= 1 && y >= 1) return new Position(x, y);
            else throw new IllegalArgumentException("Coordinates ("+x+", "+y+") are outside the chess board");
        } catch (IllegalArgumentException e) {
            System.out.println("Error occurred while trying to create the position with values: ("+x+", "+y+")");
            System.out.println(e);
            return null;
        }
    }
    
    /**
     * Calculates the signed distance in the X axis between two positions.
     * @param initPos The initial position.
     * @param finPos The final position.
     * @return The x coordinate of the final position minus the x coordinate
     * of the initial position.
     */
    public static int xDist(Position initPos, Position finPos) {
        return finPos.x - initPos.x;
    }
    
    /**
     * Calculates the signed distance in the Y axis between two positions.
     * @param initPos The initial position.
     * @param finPos The final position.
     * @return The y coordinate of the final position minus the y coordinate
     * of the initial position.
     */
    public static int yDist(Position initPos, Position finPos) {
        return finPos.y - initPos.y;
    }
    
    /**
     * Compares {@code this} to another object.
     * @param obj Object to compare {@code this} to.
     * @return True if {@code obj} is a Position with the same values of x and
     * y, false otherwise.
     */
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
     * Represents the Position using the algebraic notation, A1, A2, etc.
     * @return Returns a 2-character String with the first being the x
     * coordinate of the Position converted to a letter (1 -> A, up to
     * 8 -> H) and the second being the digit of the y coordinate.
     */
    @Override
    public String toString() {
        return "" + ChessController.convertNumberToLetter(this.x) + this.y;
    }

}
