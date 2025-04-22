package alternative_versions;

import java.util.stream.Stream;

/**
 * Alternative version of the PositionEnum class but viewed as an enum class.
 * @author Alfonso Gallego
 */
public enum PositionEnum {

    /**
     *
     */
    A1(1, 1),

    /**
     *
     */
    A2(1, 2),

    /**
     *
     */
    A3(1, 3),

    /**
     *
     */
    A4(1, 4),

    /**
     *
     */
    A5(1, 5),

    /**
     *
     */
    A6(1, 6),

    /**
     *
     */
    A7(1, 7),

    /**
     *
     */
    A8(1, 8),

    /**
     *
     */
    B1(2, 1),

    /**
     *
     */
    B2(2, 2),

    /**
     *
     */
    B3(2, 3),

    /**
     *
     */
    B4(2, 4),

    /**
     *
     */
    B5(2, 5),

    /**
     *
     */
    B6(2, 6),

    /**
     *
     */
    B7(2, 7),

    /**
     *
     */
    B8(2, 8),

    /**
     *
     */
    C1(3, 1),

    /**
     *
     */
    C2(3, 2),

    /**
     *
     */
    C3(3, 3),

    /**
     *
     */
    C4(3, 4),

    /**
     *
     */
    C5(3, 5),

    /**
     *
     */
    C6(3, 6),

    /**
     *
     */
    C7(3, 7),

    /**
     *
     */
    C8(3, 8),

    /**
     *
     */
    D1(4, 1),

    /**
     *
     */
    D2(4, 2),

    /**
     *
     */
    D3(4, 3),

    /**
     *
     */
    D4(4, 4),

    /**
     *
     */
    D5(4, 5),

    /**
     *
     */
    D6(4, 6),

    /**
     *
     */
    D7(4, 7),

    /**
     *
     */
    D8(4, 8),

    /**
     *
     */
    E1(5, 1),

    /**
     *
     */
    E2(5, 2),

    /**
     *
     */
    E3(5, 3),

    /**
     *
     */
    E4(5, 4),

    /**
     *
     */
    E5(5, 5),

    /**
     *
     */
    E6(5, 6),

    /**
     *
     */
    E7(5, 7),

    /**
     *
     */
    E8(5, 8),

    /**
     *
     */
    F1(6, 1),

    /**
     *
     */
    F2(6, 2),

    /**
     *
     */
    F3(6, 3),

    /**
     *
     */
    F4(6, 4),

    /**
     *
     */
    F5(6, 5),

    /**
     *
     */
    F6(6, 6),

    /**
     *
     */
    F7(6, 7),

    /**
     *
     */
    F8(6, 8),

    /**
     *
     */
    G1(7, 1),

    /**
     *
     */
    G2(7, 2),

    /**
     *
     */
    G3(7, 3),

    /**
     *
     */
    G4(7, 4),

    /**
     *
     */
    G5(7, 5),

    /**
     *
     */
    G6(7, 6),

    /**
     *
     */
    G7(7, 7),

    /**
     *
     */
    G8(7, 8),

    /**
     *
     */
    H1(8, 1),

    /**
     *
     */
    H2(8, 2),

    /**
     *
     */
    H3(8, 3),

    /**
     *
     */
    H4(8, 4),

    /**
     *
     */
    H5(8, 5),

    /**
     *
     */
    H6(8, 6),

    /**
     *
     */
    H7(8, 7),

    /**
     *
     */
    H8(8, 8);
    
    private final int x;
    private final int y;
    
    PositionEnum(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Getter for the x attribute.
     * @return The X coordinate of {@code this} PositionEnum.
     */
    public int x() {return x;}

    /**
     * Getter for the y attribute.
     * @return The Y coordinate of {@code this} PositionEnum.
     */
    public int y() {return y;}
    
    /**
     * Checks whether a pair of integers is a valid coordinate of the chess
     * board.
     * @param x The x coordinate of the supposed PositionEnum.
     * @param y The y coordinate of the supposed PositionEnum.
     * @return Returns true if both x and y are between (and including) 1 and 8.
     */
    public static boolean isValid(int x, int y) {return (x >= 1 && x <= 8 && y >= 1 && y <= 8);}
    
    /**
     * Returns the chess board PositionEnum represented by the given String.
     * @param pos String representing the position.
     * @return Returns the enum value whose name is the same as the given 
     * String, if avaliable. If the String has a different length than 2 or if
     * there's no enum value representing it, prints an error message and
     * returns {@code null}.
     */
    public static PositionEnum of(String pos) {
        if (pos.length()!=2) {
            System.out.println("Error occurred while trying to create the position with value: " + pos);
            System.out.println("The specified String doesn't have length 2.");
            return null;
        }
        try {
            return PositionEnum.valueOf(pos);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error occurred while trying to create the position with value: " + pos);
            return null;
        }
    }
    
    /**
     * Returns the chess board PositionEnum whose X and Y coordinates are the given
     * x and y parameters.
     * @param x Value of the X coordinate.
     * @param y Value of the Y coordinate.
     * @return Returns the enum value whose X and Y coordinates are x and y
     * respectively. If there's none, prints an error message and returns
     * {@code null}.
     */
    public static PositionEnum of(int x, int y) {
        try {
            return Stream.of(PositionEnum.values())
                .filter(position -> position.x() == x && position.y() == y)
                .findAny()
                .get();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error occurred while trying to create the position with values: "+x+", "+y);
            return null;
        }
    }
}
