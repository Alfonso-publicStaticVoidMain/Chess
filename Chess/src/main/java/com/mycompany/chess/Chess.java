package com.mycompany.chess;

import java.util.*;
import java.util.stream.IntStream;

public class Chess {
    
    private List<Piece> pieces = new ArrayList<>();
    private List<Play> playRecord = new LinkedList<>();

    public List<Piece> getPieces() {
        return pieces;
    }

    public List<Play> getPlayRecord() {
        return playRecord;
    }
    
    /**
     * <p>
     * Sets the game of each piece in {@code this} game's piece list
     * to {@code this}.
     * </p>
     */
    public void linkPieces() {
        this.pieces.stream()
            .forEach(piece -> piece.setGame(this));
    }
    
    /**
     * <p>
     * Adds the standard Chess pieces to {@code this} game's pieces list.
     * </p>
     * @see 
     *      {@link Position#of(int, int)}
     *      {@link Color#initRow}
     *      {@link Color#initRowPawn}
     *      {@link Chess#linkPieces}
     */
    public void addStandardPieces() {
        for (Color color : Color.values()) {
            // Add Pawns
            IntStream.rangeClosed(1, 8)
                .forEach(x -> this.pieces.add(new Pawn(Position.of(x, color.initRowPawn()), color)));
            // Add Rooks
            this.pieces.add(new Rook(Position.of(1, color.initRow()), color));
            this.pieces.add(new Rook(Position.of(8, color.initRow()), color));
            // Add Knights
            this.pieces.add(new Knight(Position.of(2, color.initRow()), color));
            this.pieces.add(new Knight(Position.of(7, color.initRow()), color));
            // Add Bishops
            this.pieces.add(new Bishop(Position.of(3, color.initRow()), color));
            this.pieces.add(new Bishop(Position.of(6, color.initRow()), color));
            // Add Queen
            this.pieces.add(new Queen(Position.of(4, color.initRow()), color));
            // Add King
            this.pieces.add(new King(Position.of(5, color.initRow()), color));
        }
        this.linkPieces();
    }
    
    /**
     * <p>
     * Checks whether's there's a piece or not in the specified position.
     * </p>
     * @param pos Position to check.
     * @return Returns true if there's a {@link Piece} in the {@link Position},
     * false otherwise. If somehow there's 2 or more pieces in the position,
     * it prints an error message and returns false.
     */
    public boolean checkPiece(Position pos) {
        long numberOfPiecesInPosition = this.pieces.stream()
            .filter(piece -> piece.getPos().equals(pos))
            .count();
        if (numberOfPiecesInPosition > 1) {
            System.out.println("Illegal number of pieces found in position " + pos);
            return false;
        }
        return numberOfPiecesInPosition == 1;
    }
    
    /**
     * <p>
     * Returns the Piece present in the specified position, if there's one.
     * </p>
     * @param pos Position to get the Piece from.
     * @return Returns the {@link Piece} found in the {@link Position}, if one
     * can be found there. Otherwise, it returns {@code null} and prints an
     * error message.
     */
    public Piece findPiece(Position pos) {
        if (this.checkPiece(pos)) {
            return this.pieces.stream()
                .filter(piece -> piece.getPos().equals(pos))
                .findAny()
                .get();
        }
        System.out.println("No pieces found in position " + pos);
        return null;
    }
    
    /**
     * <p>
     * Checks whether there's a Piece in the specified position that is the
     * same color as another Piece.
     * </p>
     * @param piece Piece whose color we will compare to.
     * @param pos Position we'll check for a Piece of that color.
     * @return Returns true if in the {@link Position} there's a
     * {@link Piece} whose color is the same as {@code piece}. False if there's
     * no piece or if there's a piece of a different color.
     * @see
     *      {@link Chess#checkPiece}
     *      {@link Chess#findPiece}
     */
    public boolean checkPieceSameColorAs(Piece piece, Position pos) {
        if (!this.checkPiece(pos)) return false;
        return this.findPiece(pos).getColor() == piece.getColor();
    }

    /**
     * <p>
     * Checks whether there's a Piece in the specified position that is the
     * opposite color as another Piece.
     * </p>
     * @param piece Piece whose color we will compare to.
     * @param pos Position we'll check for a Piece of that color.
     * @return Returns true if in the {@link Position} there's a
     * {@link Piece} whose color is different than {@code piece}'s.
     * False if there's no piece or if there's a piece of the same color.
     * @see
     *      {@link Chess#checkPiece}
     *      {@link Chess#findPiece}
     */    
    public boolean checkPieceDiffColorAs(Piece piece, Position pos) {
        if (!this.checkPiece(pos)) return false;
        return this.findPiece(pos).getColor() != piece.getColor();
    }
    
    /**
     * <p>
     * Finds and returns the King piece of the specified color.
     * </p>
     * @param color Color of the seeked King.
     * @return Returns the {@link Piece} object in the Pieces Set that is the
     * only {@link King} of that color.
     */
    public King findKing(Color color) {
        return (King) this.pieces.stream()
            .filter(piece -> (piece instanceof King) && piece.getColor() == color)
            .findAny()
            .get();
    }
    
    /**
     * <p>
     * Prints the current state of the board.
     * </p>
     */
    public void printBoard() {
        for (int y = 8; y >= 0; y--) {
            System.out.print(y + " ");
            for (int x = 1; x <= 8; x++) {
                boolean printedPiece = false;
                for (Piece p : this.pieces) {
                    if (y != 0 && p.getPos().equals(Position.of(x, y))) {
                        printedPiece = true;                       
                        System.out.print("|" + p + "|");
                    }
                }
                if (!printedPiece && y != 0) System.out.print("|__|");
                else if (y == 0) System.out.print("  " + convertNumberToLetter(x) + " ");
            }
            System.out.printf("%n");
        }  
    }
    
    /**
     * <p>
     * Returns a copy of {@code this} Chess game.
     * </p>
     * @return Returns a copy of {@code this} Chess game.
     * @see
     *      {@link Chess#linkPieces}
     */
    public Chess copyGame() {
        Chess result = new Chess();
        this.pieces.stream()
            .forEach(piece -> result.pieces.add(piece.copy()));
        result.linkPieces();
        return result;
    }
    
    /**
     * <p>
     * Checks if a movement of a certain Piece to a certain Position causes
     * the King of that color to be in check.
     * </p>
     * @param piece Piece we're attempting to move.
     * @param finPos Position we're attempting to move the Piece to.
     * @return Returns true if the movement causes the King of the same color
     * as the {@code piece} to be in check after moving it to {@code finPos}.
     * To achieve this, it copies the game into an auxiliary game, performs the
     * movement there, and then checks if the auxiliary King is in check.
     * @see
     *      {@link Chess#findPiece}
     *      {@link Chess#findKing}
     *      {@link Piece#move(Position, boolean)}
     *      {@link King#checkCheck()}
     */
    public boolean checkIfMovementCausesCheck(Piece piece, Position finPos) {
        Chess auxGame = this.copyGame();
        Piece copyOfPiece = auxGame.findPiece(piece.getPos());
        copyOfPiece.move(finPos, false);
        return auxGame.findKing(copyOfPiece.getColor()).checkCheck();
    }
    
    /**
     * <p>
     * Checks if the King of the given Color is in checkmate.
     * </p>
     * @param color Color we want to check checkmate for.
     * @param checkCheck State parameter to track whether or not we want to
     * take into account if the King is currently in check. Setting it to false
     * will mean that this method will return true if the King is not in check
     * but every possible movement for its color would put it in check.
     * @return If checkCheck is set to true, the method will return true if
     * the King is currently in check and no possible movement of its color
     * would solve that.
     * 
     * If checkCheck is set to false, the method will return true if every
     * possible movement of the King's color would put it in check, regardless
     * of if it's currently in check or not.
     */
    public boolean checkMate(Color color, boolean checkCheck) {
        King king = this.findKing(color);
        if (checkCheck && !king.checkCheck()) return false;
        for (Piece p : this.pieces.stream()
            .filter(piece -> piece.getColor() == color)
            .toList()) {
            for (int x = 1; x <= 8; x++) {
                for (int y = 1; y <= 8; y++) {
                    Position finPos = Position.of(x, y);
                    if (p.checkLegalMovement(finPos)) {
                        Chess auxGame = this.copyGame();
                        King auxKing = auxGame.findKing(color);
                        Piece auxPiece = auxGame.findPiece(p.getPos());
                        auxPiece.move(finPos);
                        if (!auxKing.checkCheck()) return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean checkMate(Color color) {
        return this.checkMate(color, true);
    }
    
    /**
     * <p>
     * Checks whether there's a recorded Play whose initial position is the
     * specified Position.
     * </p>
     * @param pos
     * @return Returns true if in the {@code playRecord} list of {@link Play}s
     * there's one that has initial position {@code pos}.
     */
    public boolean checkHistoryOfMovementsFromPosition(Position pos) {
        return this.playRecord.stream()
            .anyMatch(play -> play.getInitPos().equals(pos));
    }
    
    /**
     * <p>
     * Checks if left castling is possible in this Chess game.
     * </p>
     * @param color Color for which to check castling.
     * @return Returns true if the castling is possible for the {@code Color}
     * parameter, performing the following checks:
     * 
     * If there is any recorded movement from the initial position of the King
     * or left Rook, returns false.
     * 
     * If there's any Piece between the King and left Rook, returns false.
     */
    public boolean checkLeftCastling(Color color) {
        // TO DO
        Position kingInitPos = Position.of(5, color.initRow());
        Position leftRookInitPos = Position.of(1, color.initRow());
        if (this.checkHistoryOfMovementsFromPosition(kingInitPos)
            || this.checkHistoryOfMovementsFromPosition(leftRookInitPos)
        ) return false;
        return IntStream.rangeClosed(2, 4)
            .allMatch(i -> !this.checkPiece(Position.of(i, color.initRow())));
    }
    
    /**
     * <p>
     * Checks if right castling is possible in this Chess game.
     * </p>
     * @param color Color for which to check castling.
     * @return Returns true if the castling is possible for the {@code Color}
     * parameter, performing the following checks:
     * 
     * If there is any recorded movement from the initial position of the King
     * or right Rook, returns false.
     * 
     * If there's any Piece between the King and right Rook, returns false.
     */
    public boolean checkRightCastling(Color color) {
        // TO DO
        Position kingInitPos = Position.of(5, color.initRow());
        Position leftRookInitPos = Position.of(8, color.initRow());
        if (this.checkHistoryOfMovementsFromPosition(kingInitPos)
            || this.checkHistoryOfMovementsFromPosition(leftRookInitPos)
        ) return false;
        return IntStream.rangeClosed(6, 7)
            .allMatch(i -> !this.checkPiece(Position.of(i, color.initRow())));
    }
    
    public static int convertLetterToNumber(char letter) throws IllegalArgumentException {
        return switch (Character.toLowerCase(letter)) {
            case 'a' -> 1;
            case 'b' -> 2;
            case 'c' -> 3;
            case 'd' -> 4;
            case 'e' -> 5;
            case 'f' -> 6;
            case 'g' -> 7;
            case 'h' -> 8;
            default -> throw new IllegalArgumentException("Invalid letter to convert to number.");
        };
    }
    
    public static char convertNumberToLetter(int num) throws IllegalArgumentException {
        return switch (num) {
            case 1 -> 'A';
            case 2 -> 'B';
            case 3 -> 'C';
            case 4 -> 'D';
            case 5 -> 'E';
            case 6 -> 'F';
            case 7 -> 'G';
            case 8 -> 'H';
            default -> throw new IllegalArgumentException("Invalid number to convert to letter.");
        };
    }
    
    public static void main(String[] args) {
        Rook torre = new Rook(Position.of(1, 1), Color.BLACK);
        Chess chessGame = new Chess();
        chessGame.pieces.add(torre);
        chessGame.printBoard();
    }
}
