package com.mycompany.chess;

import java.util.*;
import java.util.stream.IntStream;

public class Chess {
    
    /**
     * List containing all pieces of the game.
     */
    private List<Piece> pieces = new ArrayList<>();
    /**
     * List containing all Plays of the game.
     */
    private List<Play> playRecord = new LinkedList<>();
    /**
     * Two map attributes representing the current ability of each color to do
     * left and right castling respectivaly.
     */
    private Map<ChessColor, Boolean> leftCastlingAvaliability = new HashMap<>();
    private Map<ChessColor, Boolean> rightCastlingAvaliability = new HashMap<>();
    
    public Map<ChessColor, Boolean> getLeftCastlingAvaliability() {return leftCastlingAvaliability;}
    public Map<ChessColor, Boolean> getRightCastlingAvaliability() {return rightCastlingAvaliability;}
    public List<Piece> getPieces() {return this.pieces;}
    public List<Play> getPlayRecord() {return this.playRecord;}
    
    public Play getLastPlay() {
        return this.getPlayRecord().isEmpty() ? null : this.getPlayRecord().get(this.getPlayRecord().size()-1);
    }

    /**
     * <p>
     * Empty constructor that simply adds the default true castling
     * avaliabilities for each color.
     * </p>
     */
    public Chess() {
        this.leftCastlingAvaliability.put(ChessColor.WHITE, true);
        this.leftCastlingAvaliability.put(ChessColor.BLACK, true);
        this.rightCastlingAvaliability.put(ChessColor.WHITE, true);
        this.rightCastlingAvaliability.put(ChessColor.BLACK, true);
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
     *      {@link ChessColor#initRow}
     *      {@link ChessColor#initRowPawn}
     *      {@link Chess#linkPieces}
     */
    public void addStandardPieces() {
        for (ChessColor color : ChessColor.values()) {
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
            System.out.println(numberOfPiecesInPosition+" pieces were found.");
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
    public King findKing(ChessColor color) {
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
        result.playRecord.addAll(this.playRecord);
        result.linkPieces();
        return result;
    }
    
    /**
     * <p>
     * Checks the collision along a path following a Rook or Bishop-like
     * movement, ie, in a straight line or a diagonal.
     * </p>
     * @param initPos Initial Position of the movement.
     * @param finPos Final Position of the movement.
     * @return Returns true if there's no {@link Piece} along the trajectory
     * of the movement from initPos to finPos, both exclusive.
     * The method will return false if the movement isn't on a straight line or
     * diagonal.
     * @see
     *      {@link Chess#checkPiece}
     */
    public boolean isPathClear(Position initPos, Position finPos) {
        int Xmovement = Position.xDist(initPos, finPos);
        int Ymovement = Position.yDist(initPos, finPos);
        
        if (Xmovement*Ymovement!=0 && Math.abs(Xmovement)!=Math.abs(Ymovement)) {
            //System.out.println("[DEBUG] Error on the isPathClear method: Input positions "+ initPos + " and " + finPos + " do not match movement of a Rook or Bishop.");
            return false;
        }
        
        int Xdirection = (Xmovement > 0) ? 1 : (Xmovement < 0) ? -1 : 0;
        int Ydirection = (Ymovement > 0) ? 1 : (Ymovement < 0) ? -1 : 0;
        int steps = Math.max(Math.abs(Xmovement), Math.abs(Ymovement));
        
        return IntStream.range(1, steps)
            .mapToObj(i -> Position.of(initPos.x() + i*Xdirection, initPos.y() + i*Ydirection))
            .noneMatch(position -> this.checkPiece(position));
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
     * 
     * @see 
     *      {@link King#checkCheck()}
     *      {@link Piece#checkLegalMovement(Position)}
     *      {@link Chess#findPiece}
     *      {@link Chess#findKing}
     *      {@link Chess#copyGame}
     */
    public boolean checkMate(ChessColor color, boolean checkCheck) {
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
                        auxPiece.move(finPos, false, true);
                        if (!auxKing.checkCheck()) {
                            //System.out.println("[DEBUG] Piece " + auxPiece + " moving to " + finPos + " doesn't cause a check!");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public boolean checkMate(ChessColor color) {
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
     * @deprecated Now that the castling avaliability is tracked within the
     * {@code Chess} class itself and updated on each movement in the
     * {@link Piece#move(Position, boolean)} method, it shouldn't be necessary
     * to track whether there's a play recorded from a certain Position.
     */
    @Deprecated
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
     * 
     * @see 
     *      {@link Chess#checkPiece}
     */
    public boolean checkLeftCastling(ChessColor color) {
//        Position kingInitPos = Position.of(5, color.initRow());
//        Position leftRookInitPos = Position.of(1, color.initRow());
//        if (this.checkHistoryOfMovementsFromPosition(kingInitPos)
//            || this.checkHistoryOfMovementsFromPosition(leftRookInitPos)
//        ) return false;
        if (!this.getLeftCastlingAvaliability().get(color)) return false;
        King king = this.findKing(color);
        if (king.checkCheck(Position.of(4, color.initRow())) || king.checkCheck(Position.of(3, color.initRow()))) return false;
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
     * 
     * @see 
     *      {@link Chess#checkPiece}
     */
    public boolean checkRightCastling(ChessColor color) {
//        Position kingInitPos = Position.of(5, color.initRow());
//        Position rightRookInitPos = Position.of(8, color.initRow());
//        if (this.checkHistoryOfMovementsFromPosition(kingInitPos)
//            || this.checkHistoryOfMovementsFromPosition(rightRookInitPos)
//        ) return false;
        if (!this.getRightCastlingAvaliability().get(color)) return false;
        King king = this.findKing(color);
        if (king.checkCheck(Position.of(6, color.initRow())) || king.checkCheck(Position.of(7, color.initRow()))) return false;
        return IntStream.rangeClosed(6, 7)
            .allMatch(i -> !this.checkPiece(Position.of(i, color.initRow())));
    }
    
    /**
     * <p>
     * If left castling is legal, reassigns the positions of the appropiate
     * King and Rook to do that castling.
     * </p>
     * @param color Color of the player doing the castling.
     * @return Returns true if the castling was done succesfully, false if it
     * wasn't a legal play.
     * @see
     *      {@link Chess#checkLeftCastling}
     */
    public boolean doLeftCastling(ChessColor color) {
        if (!this.checkLeftCastling(color)) return false;
        Piece king = this.findPiece(Position.of(5, color.initRow()));
        Piece leftRook = this.findPiece(Position.of(1, color.initRow()));
        king.setPos(Position.of(3, color.initRow()));
        leftRook.setPos(Position.of(4, color.initRow()));
        this.getLeftCastlingAvaliability().put(color, false);
        this.getRightCastlingAvaliability().put(color, false);
        return true;
    }
    
    /**
     * <p>
     * If right castling is legal, reassigns the positions of the appropiate
     * King and Rook to do that castling.
     * </p>
     * @param color Color of the player doing the castling.
     * @return Returns true if the castling was done succesfully, false if it
     * wasn't a legal play.
     * @see
     *      {@link Chess#checkRightCastling}
     */
    public boolean doRightCastling(ChessColor color) {
        if (!this.checkRightCastling(color)) return false;
        Piece king = this.findPiece(Position.of(5, color.initRow()));
        Piece rightRook = this.findPiece(Position.of(8, color.initRow()));
        king.setPos(Position.of(7, color.initRow()));
        rightRook.setPos(Position.of(6, color.initRow()));
        this.getLeftCastlingAvaliability().put(color, false);
        this.getRightCastlingAvaliability().put(color, false);
        return true;
    }
    
    /**
     * <p>
     * Crowns a Pawn and transforms it to a new type of Piece.
     * </p>
     * @param piece Piece to crown. Must be a Pawn.
     * @param newType New type to convert the Pawn to. Not case sensitive.
     * @return Returns true if the crowning was succesful, false otherwise.
     */
    public boolean crownPawn(Piece piece, String newType) throws IllegalArgumentException {
        if (!(piece instanceof Pawn)) return false;
        if (piece.getPos().y() != piece.getColor().crowningRow()) return false;
        this.pieces.remove(piece);
        switch (newType.toLowerCase()) {
            case "knight" -> {
                Piece newKnight = new Knight(piece.getPos(), piece.getColor());
                newKnight.setGame(this);
                this.pieces.add(newKnight);
                return true;
            }
            case "bishop" -> {
                Piece newBishop = new Bishop(piece.getPos(), piece.getColor());
                newBishop.setGame(this);
                this.pieces.add(newBishop);
                return true;
            }
            case "rook" -> {
                Piece newRook = new Rook(piece.getPos(), piece.getColor());
                newRook.setGame(this);
                this.pieces.add(newRook);
                return true;
            }
            case "queen" -> {
                Piece newQueen = new Queen(piece.getPos(), piece.getColor());
                newQueen.setGame(this);
                this.pieces.add(newQueen);
                return true;
            }
            case "chancellor" -> {
                Piece newChancellor = new Chancellor(piece.getPos(), piece.getColor());
                newChancellor.setGame(this);
                this.pieces.add(newChancellor);
                return true;
            }
        }
        throw new IllegalArgumentException(newType+" wasn't a legal type to crown a pawn into.");
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
            default -> throw new IllegalArgumentException("Invalid letter to convert to number: "+letter);
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
            default -> throw new IllegalArgumentException("Invalid number to convert to letter: "+num);
        };
    }
}
