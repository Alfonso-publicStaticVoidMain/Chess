package chess;

import java.util.List;

/**
 * Record representing the valid positions of a chess board. Contains two
 * int attributes x and y, representing the X and Y coordinate of the
 * position, respectively.
 * @author Alfonso Gallego
 */
public record Position(int x, int y) {
//public class Position {
//    /**
//     * The X coordinate of the position.
//     */
//    private final int x;
//    /**
//     * The Y coordinate of the position.
//     */
//    private final int y;
    
    /**
     * Field storing the A1 position of the chess board.
     */
    public static final Position A1 = Position.of("A1");
    /**
     * Field storing the A2 position of the chess board.
     */
    public static final Position A2 = Position.of("A2");
    /**
     * Field storing the A3 position of the chess board.
     */
    public static final Position A3 = Position.of("A3");
    /**
     * Field storing the A3 position of the chess board.
     */
    public static final Position A4 = Position.of("A4");
    /**
     * Field storing the A4 position of the chess board.
     */
    public static final Position A5 = Position.of("A5");
    /**
     * Field storing the A5 position of the chess board.
     */
    public static final Position A6 = Position.of("A6");
    /**
     * Field storing the A6 position of the chess board.
     */
    public static final Position A7 = Position.of("A7");
    /**
     * Field storing the A7 position of the chess board.
     */
    public static final Position A8 = Position.of("A8");

    /**
     * Field storing the B1 position of the chess board.
     */
    public static final Position B1 = Position.of("B1");
    /**
     * Field storing the B2 position of the chess board.
     */
    public static final Position B2 = Position.of("B2");
    /**
     * Field storing the B3 position of the chess board.
     */
    public static final Position B3 = Position.of("B3");
    /**
     * Field storing the B4 position of the chess board.
     */
    public static final Position B4 = Position.of("B4");
    /**
     * Field storing the B5 position of the chess board.
     */
    public static final Position B5 = Position.of("B5");
    /**
     * Field storing the B6 position of the chess board.
     */
    public static final Position B6 = Position.of("B6");
    /**
     * Field storing the B7 position of the chess board.
     */
    public static final Position B7 = Position.of("B7");
    /**
     * Field storing the B8 position of the chess board.
     */
    public static final Position B8 = Position.of("B8");

    /**
     * Field storing the C1 position of the chess board.
     */
    public static final Position C1 = Position.of("C1");
    /**
     * Field storing the C2 position of the chess board.
     */
    public static final Position C2 = Position.of("C2");
    /**
     * Field storing the C3 position of the chess board.
     */
    public static final Position C3 = Position.of("C3");
    /**
     * Field storing the C4 position of the chess board.
     */
    public static final Position C4 = Position.of("C4");
    /**
     * Field storing the C5 position of the chess board.
     */
    public static final Position C5 = Position.of("C5");
    /**
     * Field storing the C6 position of the chess board.
     */
    public static final Position C6 = Position.of("C6");
    /**
     * Field storing the C7 position of the chess board.
     */
    public static final Position C7 = Position.of("C7");
    /**
     * Field storing the C8 position of the chess board.
     */
    public static final Position C8 = Position.of("C8");

    /**
     * Field storing the D1 position of the chess board.
     */
    public static final Position D1 = Position.of("D1");
    /**
     * Field storing the D2 position of the chess board.
     */
    public static final Position D2 = Position.of("D2");
    /**
     * Field storing the D3 position of the chess board.
     */
    public static final Position D3 = Position.of("D3");
    /**
     * Field storing the D4 position of the chess board.
     */
    public static final Position D4 = Position.of("D4");
    /**
     * Field storing the D5 position of the chess board.
     */
    public static final Position D5 = Position.of("D5");
    /**
     * Field storing the D6 position of the chess board.
     */
    public static final Position D6 = Position.of("D6");
    /**
     * Field storing the D7 position of the chess board.
     */
    public static final Position D7 = Position.of("D7");
    /**
     * Field storing the D8 position of the chess board.
     */
    public static final Position D8 = Position.of("D8");

    /**
     * Field storing the E1 position of the chess board.
     */
    public static final Position E1 = Position.of("E1");
    /**
     * Field storing the E2 position of the chess board.
     */
    public static final Position E2 = Position.of("E2");
    /**
     * Field storing the E3 position of the chess board.
     */
    public static final Position E3 = Position.of("E3");
    /**
     * Field storing the E4 position of the chess board.
     */
    public static final Position E4 = Position.of("E4");
    /**
     * Field storing the E5 position of the chess board.
     */
    public static final Position E5 = Position.of("E5");
    /**
     * Field storing the E6 position of the chess board.
     */
    public static final Position E6 = Position.of("E6");
    /**
     * Field storing the E7 position of the chess board.
     */
    public static final Position E7 = Position.of("E7");
    /**
     * Field storing the E8 position of the chess board.
     */
    public static final Position E8 = Position.of("E8");

    /**
     * Field storing the F1 position of the chess board.
     */
    public static final Position F1 = Position.of("F1");
    /**
     * Field storing the F2 position of the chess board.
     */
    public static final Position F2 = Position.of("F2");
    /**
     * Field storing the F3 position of the chess board.
     */
    public static final Position F3 = Position.of("F3");
    /**
     * Field storing the F4 position of the chess board.
     */
    public static final Position F4 = Position.of("F4");
    /**
     * Field storing the F5 position of the chess board.
     */
    public static final Position F5 = Position.of("F5");
    /**
     * Field storing the F6 position of the chess board.
     */
    public static final Position F6 = Position.of("F6");
    /**
     * Field storing the F7 position of the chess board.
     */
    public static final Position F7 = Position.of("F7");
    /**
     * Field storing the F8 position of the chess board.
     */
    public static final Position F8 = Position.of("F8");

    /**
     * Field storing the G1 position of the chess board.
     */
    public static final Position G1 = Position.of("G1");
    /**
     * Field storing the G2 position of the chess board.
     */
    public static final Position G2 = Position.of("G2");
    /**
     * Field storing the G3 position of the chess board.
     */
    public static final Position G3 = Position.of("G3");
    /**
     * Field storing the G4 position of the chess board.
     */
    public static final Position G4 = Position.of("G4");
    /**
     * Field storing the G5 position of the chess board.
     */
    public static final Position G5 = Position.of("G5");
    /**
     * Field storing the G6 position of the chess board.
     */
    public static final Position G6 = Position.of("G6");
    /**
     * Field storing the G7 position of the chess board.
     */
    public static final Position G7 = Position.of("G7");
    /**
     * Field storing the G8 position of the chess board.
     */
    public static final Position G8 = Position.of("G8");

    /**
     * Field storing the H1 position of the chess board.
     */
    public static final Position H1 = Position.of("H1");
    /**
     * Field storing the H2 position of the chess board.
     */
    public static final Position H2 = Position.of("H2");
    /**
     * Field storing the H3 position of the chess board.
     */
    public static final Position H3 = Position.of("H3");
    /**
     * Field storing the H4 position of the chess board.
     */
    public static final Position H4 = Position.of("H4");
    /**
     * Field storing the H5 position of the chess board.
     */
    public static final Position H5 = Position.of("H5");
    /**
     * Field storing the H6 position of the chess board.
     */
    public static final Position H6 = Position.of("H6");
    /**
     * Field storing the H7 position of the chess board.
     */
    public static final Position H7 = Position.of("H7");
    /**
     * Field storing the H8 position of the chess board.
     */
    public static final Position H8 = Position.of("H8");

    /**
     * List containing all possible chess positions.
     */
    private static final List<Position> validChessPositions = List.of(
    A1, A2, A3, A4, A5, A6, A7, A8,
    B1, B2, B3, B4, B5, B6, B7, B8,
    C1, C2, C3, C4, C5, C6, C7, C8,
    D1, D2, D3, D4, D5, D6, D7, D8,
    E1, E2, E3, E4, E5, E6, E7, E8,
    F1, F2, F3, F4, F5, F6, F7, F8,
    G1, G2, G3, G4, G5, G6, G7, G8,
    H1, H2, H3, H4, H5, H6, H7, H8
    );
    
//    /**
//     * Getter for the x attribute of the Position.
//     * @return The X coordinate of the Position.
//     */
//    public int x() {return x;}
//    
//    /**
//     * Getter for the y attribute of the Position.
//     * @return The Y coordinate of the Position.
//     */
//    public int y() {return y;}
    
    /**
     * Static method to obtain all possible chess positions.
     * @return The List of all possible chess positions.
     */
    public static List<Position> validPositions() {return Position.validChessPositions;}
    
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
     * the algebraic notation of the position (A1, A2, etc.)
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
            return new Position(Chess.convertLetterToNumber(pos.charAt(0)), Integer.parseInt(""+pos.charAt(1)));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("Error occurred while trying to create the position with value: " + pos);
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
        if (x >= 1 && x <= 8 && y >= 1 && y <= 8) return new Position(x, y);
        System.out.println("Error occurred while trying to create the position with values: "+x+", "+y);
        return null;
//        try {
//            return new Position(x, y);
//        } catch (IllegalArgumentException e) {
//            System.out.println(e);
//            System.out.println("Error occurred while trying to create the position with values: "+x+", "+y);
//            return null;
//        }
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
        return "" + Chess.convertNumberToLetter(this.x) + this.y;
    }

}
