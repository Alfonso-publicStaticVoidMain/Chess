package alternative_versions;

import chess_model.Chess;
import java.util.List;

/**
 * Class representing the valid positions of a chess board. Contains two
 * int attributes x and y, representing the X and Y coordinate of the
 * position, respectively.
 * @author Alfonso Gallego
 */
//record PositionClass(int x, int y) {
public class PositionClass {
    /**
     * The X coordinate of the position.
     */
    private final int x;
    /**
     * The Y coordinate of the position.
     */
    private final int y;
    
    /**
     * Field storing the A1 position of the chess board.
     */
    public static final PositionClass A1 = PositionClass.of("A1");
    /**
     * Field storing the A2 position of the chess board.
     */
    public static final PositionClass A2 = PositionClass.of("A2");
    /**
     * Field storing the A3 position of the chess board.
     */
    public static final PositionClass A3 = PositionClass.of("A3");
    /**
     * Field storing the A3 position of the chess board.
     */
    public static final PositionClass A4 = PositionClass.of("A4");
    /**
     * Field storing the A4 position of the chess board.
     */
    public static final PositionClass A5 = PositionClass.of("A5");
    /**
     * Field storing the A5 position of the chess board.
     */
    public static final PositionClass A6 = PositionClass.of("A6");
    /**
     * Field storing the A6 position of the chess board.
     */
    public static final PositionClass A7 = PositionClass.of("A7");
    /**
     * Field storing the A7 position of the chess board.
     */
    public static final PositionClass A8 = PositionClass.of("A8");

    /**
     * Field storing the B1 position of the chess board.
     */
    public static final PositionClass B1 = PositionClass.of("B1");
    /**
     * Field storing the B2 position of the chess board.
     */
    public static final PositionClass B2 = PositionClass.of("B2");
    /**
     * Field storing the B3 position of the chess board.
     */
    public static final PositionClass B3 = PositionClass.of("B3");
    /**
     * Field storing the B4 position of the chess board.
     */
    public static final PositionClass B4 = PositionClass.of("B4");
    /**
     * Field storing the B5 position of the chess board.
     */
    public static final PositionClass B5 = PositionClass.of("B5");
    /**
     * Field storing the B6 position of the chess board.
     */
    public static final PositionClass B6 = PositionClass.of("B6");
    /**
     * Field storing the B7 position of the chess board.
     */
    public static final PositionClass B7 = PositionClass.of("B7");
    /**
     * Field storing the B8 position of the chess board.
     */
    public static final PositionClass B8 = PositionClass.of("B8");

    /**
     * Field storing the C1 position of the chess board.
     */
    public static final PositionClass C1 = PositionClass.of("C1");
    /**
     * Field storing the C2 position of the chess board.
     */
    public static final PositionClass C2 = PositionClass.of("C2");
    /**
     * Field storing the C3 position of the chess board.
     */
    public static final PositionClass C3 = PositionClass.of("C3");
    /**
     * Field storing the C4 position of the chess board.
     */
    public static final PositionClass C4 = PositionClass.of("C4");
    /**
     * Field storing the C5 position of the chess board.
     */
    public static final PositionClass C5 = PositionClass.of("C5");
    /**
     * Field storing the C6 position of the chess board.
     */
    public static final PositionClass C6 = PositionClass.of("C6");
    /**
     * Field storing the C7 position of the chess board.
     */
    public static final PositionClass C7 = PositionClass.of("C7");
    /**
     * Field storing the C8 position of the chess board.
     */
    public static final PositionClass C8 = PositionClass.of("C8");

    /**
     * Field storing the D1 position of the chess board.
     */
    public static final PositionClass D1 = PositionClass.of("D1");
    /**
     * Field storing the D2 position of the chess board.
     */
    public static final PositionClass D2 = PositionClass.of("D2");
    /**
     * Field storing the D3 position of the chess board.
     */
    public static final PositionClass D3 = PositionClass.of("D3");
    /**
     * Field storing the D4 position of the chess board.
     */
    public static final PositionClass D4 = PositionClass.of("D4");
    /**
     * Field storing the D5 position of the chess board.
     */
    public static final PositionClass D5 = PositionClass.of("D5");
    /**
     * Field storing the D6 position of the chess board.
     */
    public static final PositionClass D6 = PositionClass.of("D6");
    /**
     * Field storing the D7 position of the chess board.
     */
    public static final PositionClass D7 = PositionClass.of("D7");
    /**
     * Field storing the D8 position of the chess board.
     */
    public static final PositionClass D8 = PositionClass.of("D8");

    /**
     * Field storing the E1 position of the chess board.
     */
    public static final PositionClass E1 = PositionClass.of("E1");
    /**
     * Field storing the E2 position of the chess board.
     */
    public static final PositionClass E2 = PositionClass.of("E2");
    /**
     * Field storing the E3 position of the chess board.
     */
    public static final PositionClass E3 = PositionClass.of("E3");
    /**
     * Field storing the E4 position of the chess board.
     */
    public static final PositionClass E4 = PositionClass.of("E4");
    /**
     * Field storing the E5 position of the chess board.
     */
    public static final PositionClass E5 = PositionClass.of("E5");
    /**
     * Field storing the E6 position of the chess board.
     */
    public static final PositionClass E6 = PositionClass.of("E6");
    /**
     * Field storing the E7 position of the chess board.
     */
    public static final PositionClass E7 = PositionClass.of("E7");
    /**
     * Field storing the E8 position of the chess board.
     */
    public static final PositionClass E8 = PositionClass.of("E8");

    /**
     * Field storing the F1 position of the chess board.
     */
    public static final PositionClass F1 = PositionClass.of("F1");
    /**
     * Field storing the F2 position of the chess board.
     */
    public static final PositionClass F2 = PositionClass.of("F2");
    /**
     * Field storing the F3 position of the chess board.
     */
    public static final PositionClass F3 = PositionClass.of("F3");
    /**
     * Field storing the F4 position of the chess board.
     */
    public static final PositionClass F4 = PositionClass.of("F4");
    /**
     * Field storing the F5 position of the chess board.
     */
    public static final PositionClass F5 = PositionClass.of("F5");
    /**
     * Field storing the F6 position of the chess board.
     */
    public static final PositionClass F6 = PositionClass.of("F6");
    /**
     * Field storing the F7 position of the chess board.
     */
    public static final PositionClass F7 = PositionClass.of("F7");
    /**
     * Field storing the F8 position of the chess board.
     */
    public static final PositionClass F8 = PositionClass.of("F8");

    /**
     * Field storing the G1 position of the chess board.
     */
    public static final PositionClass G1 = PositionClass.of("G1");
    /**
     * Field storing the G2 position of the chess board.
     */
    public static final PositionClass G2 = PositionClass.of("G2");
    /**
     * Field storing the G3 position of the chess board.
     */
    public static final PositionClass G3 = PositionClass.of("G3");
    /**
     * Field storing the G4 position of the chess board.
     */
    public static final PositionClass G4 = PositionClass.of("G4");
    /**
     * Field storing the G5 position of the chess board.
     */
    public static final PositionClass G5 = PositionClass.of("G5");
    /**
     * Field storing the G6 position of the chess board.
     */
    public static final PositionClass G6 = PositionClass.of("G6");
    /**
     * Field storing the G7 position of the chess board.
     */
    public static final PositionClass G7 = PositionClass.of("G7");
    /**
     * Field storing the G8 position of the chess board.
     */
    public static final PositionClass G8 = PositionClass.of("G8");

    /**
     * Field storing the H1 position of the chess board.
     */
    public static final PositionClass H1 = PositionClass.of("H1");
    /**
     * Field storing the H2 position of the chess board.
     */
    public static final PositionClass H2 = PositionClass.of("H2");
    /**
     * Field storing the H3 position of the chess board.
     */
    public static final PositionClass H3 = PositionClass.of("H3");
    /**
     * Field storing the H4 position of the chess board.
     */
    public static final PositionClass H4 = PositionClass.of("H4");
    /**
     * Field storing the H5 position of the chess board.
     */
    public static final PositionClass H5 = PositionClass.of("H5");
    /**
     * Field storing the H6 position of the chess board.
     */
    public static final PositionClass H6 = PositionClass.of("H6");
    /**
     * Field storing the H7 position of the chess board.
     */
    public static final PositionClass H7 = PositionClass.of("H7");
    /**
     * Field storing the H8 position of the chess board.
     */
    public static final PositionClass H8 = PositionClass.of("H8");

    /**
     * List containing all possible chess positions.
     */
    private static final List<PositionClass> validChessPositions = List.of(
    A1, A2, A3, A4, A5, A6, A7, A8,
    B1, B2, B3, B4, B5, B6, B7, B8,
    C1, C2, C3, C4, C5, C6, C7, C8,
    D1, D2, D3, D4, D5, D6, D7, D8,
    E1, E2, E3, E4, E5, E6, E7, E8,
    F1, F2, F3, F4, F5, F6, F7, F8,
    G1, G2, G3, G4, G5, G6, G7, G8,
    H1, H2, H3, H4, H5, H6, H7, H8
    );
    
    /**
     * Getter for the x attribute of the PositionClass.
     * @return The X coordinate of the PositionClass.
     */
    public int x() {return x;}
    
    /**
     * Getter for the y attribute of the PositionClass.
     * @return The Y coordinate of the PositionClass.
     */
    public int y() {return y;}
    
    /**
     * Static method to obtain all possible chess positions.
     * @return The List of all possible chess positions.
     */
    public static List<PositionClass> validPositions() {return PositionClass.validChessPositions;}
    
    /**
     * Private constructor of the factory class Position. It only allows for
     * the creation of Positions that are within the Chess board.
     * @param x X coordinate of the position.
     * @param y Y coordinate of the position.
     * @throws IllegalArgumentException if the x and y values represent a
     * position that'd be outside of the chess board.
     */
    private PositionClass(int x, int y) throws IllegalArgumentException {
        if (!isValid(x, y)) throw new IllegalArgumentException("Invalid position on the board.");
        this.x = x;
        this.y = y;
    }
    
    /**
     * Static factory method to create new Positions from a String representing
     * the algebraic notation of the position (A1, A2, etc.)
     * @param pos String representing the position.
     * @return Returns a new PositionClass constructed by converting the first char
 of the String to an integer, to store it as the x coordinate of the
 position, and storing the second as the y coordinate.
 If the String doesn't have length 2, or if the position it'd represent
 is not within the Chess board, an error message is printed and 
 {@code null} is returned.
     */
    public static PositionClass of(String pos) {
        if (pos.length()!=2) {
            System.out.println("Error occurred while trying to create the position with value: " + pos);
            System.out.println("The specified String doesn't have length 2.");
            return null;
        }
        try {
            return new PositionClass(Chess.convertLetterToNumber(pos.charAt(0)), Integer.parseInt(""+pos.charAt(1)));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("Error occurred while trying to create the position with value: " + pos);
            return null;
        }
    }
    
    /**
     * Static factory method to create new Positions.In this case, from a
 pair of integers representing the X and Y coordinates of the Position.
     * @param x X coordinate of the new PositionClass.
     * @param y Y coordinate of the new PositionClass.
     * @return Returns a new PositionClass constructed by storing the two integers
 as the X and Y coordinate.
 If supposed new PositionClass is not within the Chess board, an error message
 is printed and {@code null} is returned.
     */
    public static PositionClass of(int x, int y) {
        try {
            return new PositionClass(x, y);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("Error occurred while trying to create the position with values: "+x+", "+y);
            return null;
        }
    }
    
    /**
     * Checks whether a pair of integers is a valid coordinate of the chess
     * board.
     * @param x The x coordinate of the supposed PositionClass.
     * @param y The y coordinate of the supposed PositionClass.
     * @return Returns true if both x and y are between (and including) 1 and 8.
     */
    public static boolean isValid(int x, int y) {return (x >= 1 && x <= 8 && y >= 1 && y <= 8);}
    
    /**
     * Calculates the signed distance in the X axis between two positions.
     * @param initPos The initial position.
     * @param finPos The final position.
     * @return The x coordinate of the final position minus the x coordinate
     * of the initial position.
     */
    public static int xDist(PositionClass initPos, PositionClass finPos) {
        return finPos.x - initPos.x;
    }
    
    /**
     * Calculates the signed distance in the Y axis between two positions.
     * @param initPos The initial position.
     * @param finPos The final position.
     * @return The y coordinate of the final position minus the y coordinate
     * of the initial position.
     */
    public static int yDist(PositionClass initPos, PositionClass finPos) {
        return finPos.y - initPos.y;
    }
    
    /**
     * Compares {@code this} to another object.
     * @param obj Object to compare {@code this} to.
     * @return True if {@code obj} is a PositionClass with the same values of x and
 y, false otherwise.
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
        final PositionClass other = (PositionClass) obj;
        return (this.x == other.x && this.y == other.y);
//        if (this.x != other.x) {
//            return false;
//        }
//        return this.y == other.y;
    }

    /**
     * Represents the PositionClass using the algebraic notation, A1, A2, etc.
     * @return Returns a 2-character String with the first being the x
 coordinate of the PositionClass converted to a letter (1 -> A, up to
 8 -> H) and the second being the digit of the y coordinate.
     */
    @Override
    public String toString() {
        return "" + Chess.convertNumberToLetter(this.x) + this.y;
    }

}
