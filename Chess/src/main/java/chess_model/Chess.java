package chess_model;

import controller.ChessController;
import java.io.Serializable;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Contains information regarding a standard chess game.
 * @author Alfonso Gallego
 * @version 1.0
 */
public class Chess implements Serializable {
    
    /**
     * List containing all pieces currently in the game.
     */
    private final List<Piece> pieces = new ArrayList<>();
    
    /**
     * List containing all Plays done in the game thus far.
     */
    private final List<Play> playHistory = new LinkedList<>();
    
    /**
     * Map representing the avaliability of left castling for each player.
     */
    private final Map<ChessColor, Boolean> leftCastlingAvaliability = new EnumMap<>(ChessColor.class);
    
    /**
     * Map representing the avaliability of right castling for each player.
     */
    private final Map<ChessColor, Boolean> rightCastlingAvaliability = new EnumMap<>(ChessColor.class);
    
    /**
     * Color of the currently active player.
     */
    private ChessColor activePlayer = ChessColor.WHITE;

    /**
     * Boolean attribute to track if the game has ended.
     */
    private boolean gameFinished = false;
    
    /**
     * Boolean attribute to tracj uf tge game has started.
     */
    private boolean gameStarted = false;
    
    /**
     * Integer attribute to track the seconds left for white player.
     */
    private int whiteSeconds = 300;
    
    /**
     * Integer attribute to track the seconds left for black player.
     */
    private int blackSeconds = 300;
    
    private GameConfiguration config;

    public GameConfiguration getConfig() {return config;}

    /**
     * Informs if the game has started yet.
     * @return True if the game has started, false otherwise.
     */
    public boolean isGameStarted() {return gameStarted;}
    
    /**
     * Sets the {@code gameStarted} attribute to true.
     */
    public void startGame() {gameStarted = true;}

    /**
     * Getter for the {@code whiteSeconds} attribute.
     * @return The number of seconds left for white player.
     */
    public int getWhiteSeconds() {return whiteSeconds;}
    
    /**
     * Substracts one second from the seconds remaining for white player.
     */
    public void consumeWhiteSecond() {whiteSeconds--;}

    /**
     * Getter for the {@code blackSeconds} attribute.
     * @return The number of seconds left for black player.
     */
    public int getBlackSeconds() {return blackSeconds;}
    
    /**
     * Substracts one second from the seconds remaining for black player.
     */
    public void consumeBlackSecond() {blackSeconds--;}

    /**
     * Getter for the {@code gameFinished} attribute.
     * @return True if the game has ended, false otherwise.
     */
    public boolean isGameFinished() {return gameFinished;}
    
    /**
     * Sets the {@code gameFinished} attribute to true, ending the game.
     */
    public void finishGame() {this.gameFinished = true;}
    
    public void nullLeftCastlingAvaliability(ChessColor player) {
        leftCastlingAvaliability.put(player, false);
    }
    
    public void nullRightCastlingAvaliability(ChessColor player) {
        rightCastlingAvaliability.put(player, false);
    }
    
    /**
     * Getter for the active player attribute.
     * @return The color of the active player.
     */
    public ChessColor getActivePlayer() {return activePlayer;}
    
    /**
     * If the active player is white, it changes to black and viceversa.
     */
    public void changeActivePlayer() {this.activePlayer = this.activePlayer.opposite();}
    
    /**
     * Getter for the left castling avaliability attribute.
     * @return The Map of right castling avaliability of {@code this} game,
     * mapping each color to a boolean representing if it still has the ability
     * to do a left castling.
     */
    public Map<ChessColor, Boolean> getLeftCastlingAvaliability() {return leftCastlingAvaliability;}

    /**
     * Getter for the right castling avaliability attribute.
     * @return The Map of right castling avaliability of {@code this} game,
     * mapping each color to a boolean representing if it still has the ability
     * to do a right castling.
     */
    public Map<ChessColor, Boolean> getRightCastlingAvaliability() {return rightCastlingAvaliability;}

    public void setTrueCastlingAvaliabilities() {
        this.leftCastlingAvaliability.put(ChessColor.WHITE, true);
        this.leftCastlingAvaliability.put(ChessColor.BLACK, true);
        this.rightCastlingAvaliability.put(ChessColor.WHITE, true);
        this.rightCastlingAvaliability.put(ChessColor.BLACK, true);
    }
    
    /**
     * Getter for the pieces attribute.
     * @return The List of Pieces of {@code this} game.
     */
    public List<Piece> getPieces() {return pieces;}

    /**
     * Getter for the playHistory attribute.
     * @return The List of Plays of {@code this} game.
     */
    public List<Play> getPlayHistory() {return playHistory;}
    
    /**
     * Gets the last {@link Play} stored in the {@code playHistory} attribute.
     * @return Returns the last {@link Play} in the {@code playHistory}
     * attirbute of {@code this}, or {@code null} if that attribute is still
     * empty.
     */
    public Play getLastPlay() {
        return playHistory.isEmpty() ? null : playHistory.get(playHistory.size()-1);
    }

    /**
     * Empty constructor.
     */
    private Chess() {
    }
    
    /**
     * Factory method to create a standard game.
     * @return A game with all the standard game pieces on their respective
     * starting position.
     */
    public static Chess standardGame() {
        Chess chess = new Chess();
        chess.config = GameConfiguration.standardGameConfig;
        chess.addStandardPieces();
        chess.setTrueCastlingAvaliabilities();
        return chess;
    }
    
    public static Chess almostChessGame() {
        Chess chess = new Chess();
        chess.config = GameConfiguration.almostChessConfig;
        chess.addAlmostChessPieces();
        chess.setTrueCastlingAvaliabilities();
        return chess;
    }
    
    public static Chess capablancaGame() {
        Chess chess = new Chess();
        chess.config = GameConfiguration.capablancaConfig;
        chess.addCapablancaPieces();
        chess.setTrueCastlingAvaliabilities();
        return chess;
    }
    
    public static Chess janusGame() {
        Chess chess = new Chess();
        chess.config = GameConfiguration.janusConfig;
        chess.addJanusPieces();
        chess.setTrueCastlingAvaliabilities();
        return chess;
    }
    
    public static Chess modernGame() {
        Chess chess = new Chess();
        chess.config = GameConfiguration.modernConfig;
        chess.addModernPieces();
        chess.setTrueCastlingAvaliabilities();
        return chess;
    }
    
    public static Chess tuttiFruttiGame() {
        Chess chess = new Chess();
        chess.config = GameConfiguration.tuttiFruttiConfig;
        chess.addTuttiFruttiPieces();
        chess.setTrueCastlingAvaliabilities();
        return chess;
    }
    
    /**
     * Sets the game of each piece in {@code this} game's piece list
     * to {@code this}.
     */
    public void linkPieces() {
        this.pieces.stream()
            .forEach(piece -> piece.setGame(this));
    }
    
    /**
     * Adds the standard Chess pieces to {@code this} game's pieces list.
     * @see Position#of(int, int)
     * @see Chess#linkPieces
     */
    public void addStandardPieces() {
        for (ChessColor color : ChessColor.values()) {
            int initRow = color.initRow(config);
            int initRowPawn = color.initRowPawn(config);
            
            // Add Pawns
            IntStream.rangeClosed(1, 8)
                .forEach(x -> this.pieces.add(new Pawn(Position.of(x, initRowPawn), color)));
            // Add Rooks
            this.pieces.add(new Rook(Position.of(1, initRow), color));
            this.pieces.add(new Rook(Position.of(8, initRow), color));
            // Add Knights
            this.pieces.add(new Knight(Position.of(2, initRow), color));
            this.pieces.add(new Knight(Position.of(7, initRow), color));
            // Add Bishops
            this.pieces.add(new Bishop(Position.of(3, initRow), color));
            this.pieces.add(new Bishop(Position.of(6, initRow), color));
            // Add Queen
            this.pieces.add(new Queen(Position.of(4, initRow), color));
            // Add King
            this.pieces.add(new King(Position.of(5, initRow), color));
        }
        this.linkPieces();
    }
    
    /**
     * Adds the Almost Chess pieces to {@code this} game's pieces list.
     * @see Position#of(int, int)
     * @see Chess#linkPieces
     */
    public void addAlmostChessPieces() {
        for (ChessColor color : ChessColor.values()) {
            int initRow = color.initRow(config);
            int initRowPawn = color.initRowPawn(config);
            
            // Add Pawns
            IntStream.rangeClosed(1, 8)
                .forEach(x -> this.pieces.add(new Pawn(Position.of(x, initRowPawn), color)));
            // Add Rooks
            this.pieces.add(new Rook(Position.of(1, initRow), color));
            this.pieces.add(new Rook(Position.of(8, initRow), color));
            // Add Knights
            this.pieces.add(new Knight(Position.of(2, initRow), color));
            this.pieces.add(new Knight(Position.of(7, initRow), color));
            // Add Bishops
            this.pieces.add(new Bishop(Position.of(3, initRow), color));
            this.pieces.add(new Bishop(Position.of(6, initRow), color));
            // Add Chancellor
            this.pieces.add(new Chancellor(Position.of(4, initRow), color));
            // Add King
            this.pieces.add(new King(Position.of(5, initRow), color));
        }
        this.linkPieces();
    }
    
    public void addCapablancaPieces() {
        for (ChessColor color : ChessColor.values()) {
            int initRow = color.initRow(config);
            int initRowPawn = color.initRowPawn(config);
            
            // Add Pawns
            IntStream.rangeClosed(1, 10)
                .forEach(x -> this.pieces.add(new Pawn(Position.of(x, initRowPawn), color)));
            // Add Rooks
            this.pieces.add(new Rook(Position.of(1, initRow), color));
            this.pieces.add(new Rook(Position.of(10, initRow), color));
            // Add Knights
            this.pieces.add(new Knight(Position.of(2, initRow), color));
            this.pieces.add(new Knight(Position.of(9, initRow), color));
            // Add Bishops
            this.pieces.add(new Bishop(Position.of(4, initRow), color));
            this.pieces.add(new Bishop(Position.of(7, initRow), color));
            // Add Chancellor
            this.pieces.add(new Chancellor(Position.of(8, initRow), color));
            // Add ArchBishop
            this.pieces.add(new ArchBishop(Position.of(3, initRow), color));
            // Add Queen
            this.pieces.add(new Queen(Position.of(5, initRow), color));
            // Add King
            this.pieces.add(new King(Position.of(6, initRow), color));
        }
        this.linkPieces();
    }
    
    public void addJanusPieces() {
        for (ChessColor color : ChessColor.values()) {
            int initRow = color.initRow(config);
            int initRowPawn = color.initRowPawn(config);
            
            // Add Pawns
            IntStream.rangeClosed(1, 10)
                .forEach(x -> this.pieces.add(new Pawn(Position.of(x, initRowPawn), color)));
            // Add Rooks
            this.pieces.add(new Rook(Position.of(1, initRow), color));
            this.pieces.add(new Rook(Position.of(10, initRow), color));
            // Add Knights
            this.pieces.add(new Knight(Position.of(3, initRow), color));
            this.pieces.add(new Knight(Position.of(8, initRow), color));
            // Add Bishops
            this.pieces.add(new Bishop(Position.of(4, initRow), color));
            this.pieces.add(new Bishop(Position.of(7, initRow), color));
            // Add ArchBishops
            this.pieces.add(new ArchBishop(Position.of(2, initRow), color));
            this.pieces.add(new ArchBishop(Position.of(9, initRow), color));
            // Add Queen
            this.pieces.add(new Queen(Position.of(6, initRow), color));
            // Add King
            this.pieces.add(new King(Position.of(5, initRow), color));
        }
        this.linkPieces();
    }
    
    public void addModernPieces() {
        for (ChessColor color : ChessColor.values()) {
            int initRow = color.initRow(config);
            int initRowPawn = color.initRowPawn(config);
            
            // Add Pawns
            IntStream.rangeClosed(1, 9)
                .forEach(x -> this.pieces.add(new Pawn(Position.of(x, initRowPawn), color)));
            // Add Rooks
            this.pieces.add(new Rook(Position.of(1, initRow), color));
            this.pieces.add(new Rook(Position.of(9, initRow), color));
            // Add Knights
            this.pieces.add(new Knight(Position.of(2, initRow), color));
            this.pieces.add(new Knight(Position.of(8, initRow), color));
            // Add Bishops
            this.pieces.add(new Bishop(Position.of(3, initRow), color));
            this.pieces.add(new Bishop(Position.of(7, initRow), color));
            // Add ArchBishops
            this.pieces.add(new ArchBishop(Position.of(6, initRow), color));
            // Add Queen
            this.pieces.add(new Queen(Position.of(4, initRow), color));
            // Add King
            this.pieces.add(new King(Position.of(5, initRow), color));
        }
        this.linkPieces();
    }
    
    public void addTuttiFruttiPieces() {
        for (ChessColor color : ChessColor.values()) {
            int initRow = color.initRow(config);
            int initRowPawn = color.initRowPawn(config);
            
            // Add Pawns
            IntStream.rangeClosed(1, 8)
                .forEach(x -> this.pieces.add(new Pawn(Position.of(x, initRowPawn), color)));
            // Add Chancellor
            this.pieces.add(new Chancellor(Position.of(1, initRow), color));
            // Add Rook
            this.pieces.add(new Rook(Position.of(8, initRow), color));
            // Add Knight
            this.pieces.add(new Knight(Position.of(2, initRow), color));
            // Add ArchBishop
            this.pieces.add(new ArchBishop(Position.of(7, initRow), color));
            // Add Bishop
            this.pieces.add(new Bishop(Position.of(3, initRow), color));
            // Add Amazon
            this.pieces.add(new Amazon(Position.of(4, initRow), color));
            // Add Queen
            this.pieces.add(new Queen(Position.of(6, initRow), color));
            // Add King
            this.pieces.add(new King(Position.of(5, initRow), color));
        }
        this.linkPieces();
    }

    public int initKingCol() {
        return config.kingInitCol();
    }
    
    public Position initKingPosition(ChessColor color) {
        return Position.of(config.kingInitCol(), color.initRow(config));
    }
    
    public int initLeftRookCol() {
        return config.leftRookInitCol();
    }
    
    public Position initLeftRookPosition(ChessColor color) {
        return Position.of(config.leftRookInitCol(), color.initRow(config));
    }
    
    public int initRightRookCol() {
        return config.rightRookInitCol();
    }
    
    public Position initRightRookPosition(ChessColor color) {
        return Position.of(config.rightRookInitCol(), color.initRow(config));
    }
 
    public int leftCastlingKingCol() {
        return config.leftCastlingCol();
    }
    
    public Position leftCastlingKingPosition(ChessColor color) {
        return Position.of(config.leftCastlingCol(), color.initRow(config));
    }
    
    public int leftCastlingRookCol() {
        return config.leftCastlingCol()+1;
    }
    
    public Position leftCastlingRookPosition(ChessColor color) {
        return Position.of(config.leftCastlingCol()+1, color.initRow(config));
    }
    
    public int rightCastlingKingCol() {
        return config.rightCastlingCol();
    }
    
    public Position rightCastlingKingPosition(ChessColor color) {
        return Position.of(config.rightCastlingCol(), color.initRow(config));
    }
    
    public int rightCastlingRookCol() {
        return config.rightCastlingCol()-1;
    }
    
    public Position rightCastlingRookPosition(ChessColor color) {
        return Position.of(config.rightCastlingCol()-1, color.initRow(config));
    }
    
    /**
     * Checks whether's there's a piece or not in the specified position.
     * @param pos Position to check.
     * @return Returns true if there's a {@link Piece} in the given 
     * {@link Position}, false otherwise.
     */
    public boolean checkPiece(Position pos) {
        return this.pieces.stream()
            .anyMatch(piece -> piece.getPos().equals(pos));
    }
    
    /**
     * Returns the Piece present in the specified position, if there's one.
     * @param pos Position to get the Piece from.
     * @return The {@link Piece} found in the {@link Position}, if one can be
     * found there. Otherwise, it returns {@code null} and prints an error
     * message.
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
     * Checks whether there's a Piece in the specified position that is the
     * same color as another Piece.
     * @param piece Piece whose color we will compare to.
     * @param pos Position we'll check for a Piece of that color.
     * @return Returns true if in the {@link Position} there's a
     * {@link Piece} whose color is the same as {@code piece}. False if there's
     * no piece or if there's a piece of a different color.
     * @see Chess#checkPiece
     * @see Chess#findPiece
     */
    public boolean checkPieceSameColorAs(Piece piece, Position pos) {
        if (!this.checkPiece(pos)) return false;
        return this.findPiece(pos).getColor() == piece.getColor();
    }

    /**
     * Checks whether there's a Piece in the specified position that is the
     * opposite color as another Piece.
     * @param piece Piece whose color we will compare to.
     * @param pos Position we'll check for a Piece of that color.
     * @return Returns true if in the {@link Position} there's a
     * {@link Piece} whose color is different than {@code piece}'s.
     * False if there's no piece or if there's a piece of the same color.
     * @see Chess#checkPiece
     * @see Chess#findPiece
     */    
    public boolean checkPieceDiffColorAs(Piece piece, Position pos) {
        if (!this.checkPiece(pos)) return false;
        return this.findPiece(pos).getColor() != piece.getColor();
    }
    
    /**
     * Finds and returns the King piece of the specified color.
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
     * Prints the current state of the board.
     * @deprecated Method only used to play the game via console.
     * Honestly, not recommended, it's really boring.
     */
    @Deprecated
    public void printBoard() {
        for (int y = 8; y >= 0; y--) {
            System.out.print(y + " ");
            for (int x = 1; x <= 8; x++) {
                boolean printedPiece = false;
                for (Piece p : this.pieces) {
                    if (y != 0 && p.getPos().equals(Position.of(x, y))) {
                        printedPiece = true;                       
                        System.out.print("|" + p.twoCharName() + "|");
                    }
                }
                if (!printedPiece && y != 0) System.out.print("|__|");
                else if (y == 0) System.out.print("  " + ChessController.convertNumberToLetter(x) + " ");
            }
            System.out.printf("%n");
        }  
    }
    
    /**
     * Returns a copy of {@code this} Chess game.
     * @return Returns a copy of {@code this} Chess game, in which each of its
     * Pieces is a copy of each Piece in the original game, using the
     * {@link Piece#copy} method.
     * @see Chess#linkPieces
     * @see Piece#copy
     */
    public Chess copyGame() {
        Chess result = new Chess();
        result.config = this.config;
        result.leftCastlingAvaliability.putAll(leftCastlingAvaliability);
        result.rightCastlingAvaliability.putAll(rightCastlingAvaliability);
        this.pieces.stream()
            .forEach(piece -> result.pieces.add(piece.copy()));
        result.playHistory.addAll(this.playHistory);
        result.linkPieces();
        return result;
    }
    
    /**
     * Checks the collision along a path following a Rook or Bishop-like
     * movement, ie, in a straight line or a diagonal.
     * @param initPos Initial Position of the movement.
     * @param finPos Final Position of the movement.
     * @return Returns true if there's no {@link Piece} along the trajectory
     * of the movement from {@code initPos} to {@code finPos}, both exclusive.
     * The method will return false if the movement isn't on a straight line or
     * diagonal.
     * @see Chess#checkPiece
     */
    public boolean isPathClear(Position initPos, Position finPos) {
        int Xmovement = Position.xDist(initPos, finPos);
        int Ymovement = Position.yDist(initPos, finPos);
        return isPathClear(initPos.x(), initPos.y(), Xmovement, Ymovement);
    }
    
    public boolean isPathClear(int initXPos, int initYPos, int Xmovement, int Ymovement) {
        if (!Piece.isBishopLikePath(Xmovement, Ymovement) && !Piece.isRookLikePath(Xmovement, Ymovement)) return false;
        
        int Xdirection = (Xmovement > 0) ? 1 : (Xmovement < 0) ? -1 : 0;
        int Ydirection = (Ymovement > 0) ? 1 : (Ymovement < 0) ? -1 : 0;
        int steps = Math.max(Math.abs(Xmovement), Math.abs(Ymovement));
        
        return IntStream.range(1, steps)
            .mapToObj(n -> Position.of(initXPos + n*Xdirection, initYPos + n*Ydirection))
            .noneMatch(position -> this.checkPiece(position));
    }
    
    /**
     * Checks if the player of the given Color is in checkmate.
     * @param color Color we want to check checkmate for.
     * @param checkCheck State parameter to track whether or not we want to
     * take into account if the King is currently in check. Setting it to false
     * will mean that this method will return true if the King is not in check
     * but every possible movement for its color would put it in check.
     * @return If {@code checkCheck} is set to true, the method will return true
     * if the King is currently in check and no possible movement of its color
     * would change that.
     * 
     * If {@code checkCheck} is set to false, the method will return true if 
     * every possible movement of the King's color would put it in check,
     * regardless of if it's currently in check or not.
     * 
     * @see King#checkCheck()
     * @see Piece#checkLegalMovement(Position)
     * @see Chess#findPiece
     * @see Chess#findKing
     * @see Chess#copyGame
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
                        if (!auxKing.checkCheck()) return false;
                    }
                }
            }
        }
        return true;
    }
    
    /**
     * Overloaded version of {@link Chess#checkMate(chess.ChessColor, boolean)},
     * defaulting the {@code checkCheck} argument to true.
     * @param color Color of the player to check checkmate for.
     * @return True if that player is in checkmate, false otherwise.
     * @see Chess#checkMate(ChessColor, boolean)
     */
    public boolean checkMate(ChessColor color) {
        return this.checkMate(color, true);
    }
    
    /**
     * Checks whether there's a recorded Play whose initial position is the
     * specified Position.
     * @param pos {@link Position} to check movements from.
     * @return Returns true if in the {@code playHistory} list of {@link Play}s
     * there's one that has initial position {@code pos}.
     * @deprecated Now that the castling avaliability is tracked within the
     * {@code Chess} class itself and updated on each movement in the
     * {@link Piece#move(Position, boolean)} method, it shouldn't be necessary
     * to track whether there's a play recorded from a certain Position.
     */
    @Deprecated
    public boolean checkHistoryOfMovementsFromPosition(Position pos) {
        return this.playHistory.stream()
            .anyMatch(play -> play.initPos().equals(pos));
    }
    
    /**
     * Checks if left castling is possible in this Chess game for a given
     * player.
     * @param color Color for which to check castling.
     * @return True if the castling is possible for the {@code Color} player,
     * performing the following checks:
     * <br><br>
     * If there's any Piece between the King and left Rook, or if the King 
     * would be in check in any of the Positions it has to move through,
     * returns false.
     * <br><br>
     * @see Chess#findKing 
     * @see Chess#checkPiece
     */
    public boolean checkLeftCastling(ChessColor color) {
        if (!this.getLeftCastlingAvaliability().get(color)) return false;
        King king = this.findKing(color);
        int initRow = color.initRow(config);
        if (
            IntStream.rangeClosed(leftCastlingKingCol(), king.getPos().x())
                .anyMatch(x -> king.checkCheck(Position.of(x, initRow)))
        ) return false;
        return IntStream.rangeClosed(initLeftRookCol()+1, leftCastlingRookCol())
            .allMatch(x -> !this.checkPiece(Position.of(x, initRow)));
    }
    
    /**
     * Checks if right castling is possible in this Chess game for a given
     * player.
     * @param color Color for which to check castling.
     * @return True if the castling is possible for the {@code Color} player,
     * performing the following checks:
     * <br><br>
     * If there's any Piece between the King and right Rook, or if the King 
     * would be in check in any of the Positions it has to move through,
     * returns false.
     * <br><br>
     * @see Chess#findKing 
     * @see Chess#checkPiece
     */
    public boolean checkRightCastling(ChessColor color) {
        if (!this.getRightCastlingAvaliability().get(color)) return false;
        King king = this.findKing(color);
        int initRow = color.initRow(config);
        if (
            IntStream.rangeClosed(king.getPos().x(), rightCastlingKingCol())
                .anyMatch(x -> king.checkCheck(Position.of(x, initRow)))
        ) return false;
        return IntStream.rangeClosed(rightCastlingRookCol(), initRightRookCol()-1)
            .allMatch(i -> !this.checkPiece(Position.of(i, initRow)));
    }
    
    /**
     * If left castling is legal for the given player, reassigns the positions
     * of the appropiate King and Rook to do it.
     * @param color Player doing the castling.
     * @return True if the castling was done succesfully, false if it wasn't a
     * legal play.
     * @see Chess#checkLeftCastling
     * @see Chess#findPiece
     */
    public boolean doLeftCastling(ChessColor color) {
        if (!this.checkLeftCastling(color)) return false;
        Piece king = this.findPiece(initKingPosition(color));
        Piece leftRook = this.findPiece(initLeftRookPosition(color));
        king.setPos(leftCastlingKingPosition(color));
        leftRook.setPos(leftCastlingRookPosition(color));
        this.getLeftCastlingAvaliability().put(color, false);
        this.getRightCastlingAvaliability().put(color, false);
        this.playHistory.add(new Play(king, initKingPosition(color), leftCastlingKingPosition(color), -1));
        return true;
    }
    
    /**
     * If right castling is legal for the given player, reassigns the positions
     * of the appropiate King and Rook to do it.
     * @param color Player doing the castling.
     * @return True if the castling was done succesfully, false if it wasn't a
     * legal play.
     * @see Chess#checkLeftCastling
     * @see Chess#findPiece
     */
    public boolean doRightCastling(ChessColor color) {
        if (!this.checkRightCastling(color)) return false;
        int initRow = color.initRow(config);
        Piece king = this.findPiece(initKingPosition(color));
        Piece rightRook = this.findPiece(initRightRookPosition(color));
        king.setPos(rightCastlingKingPosition(color));
        rightRook.setPos(rightCastlingRookPosition(color));
        this.getLeftCastlingAvaliability().put(color, false);
        this.getRightCastlingAvaliability().put(color, false);
        this.playHistory.add(new Play(king, initKingPosition(color), rightCastlingKingPosition(color), 1));
        return true;
    }
    
    /**
     * Crowns a Pawn and transforms it to a new type of Piece.
     * @param piece Piece to crown. Must be a Pawn.
     * @param newType New type to convert the Pawn to. Not case sensitive.
     * @return True if the crowning was succesful, false otherwise.
     * If the Piece argument wasn't a Pawn, returns false.
     * @throws IllegalArgumentException If the new type to convert the Pawn
     * into isn't the simple class name of any of the implemented Piece
     * subclasses, which currently are:
     * <ul>
     * <li>Knight</li>
     * <li>Bishop</li>
     * <li>Rook</li>
     * <li>Queen</li>
     * <li>Chancellor</li>
     * </ul>
     */
    public boolean crownPawn(Piece piece, String newType) throws IllegalArgumentException {
        if (!(piece instanceof Pawn)) throw new IllegalArgumentException("[DEBUG] Chess@crownPawn: "+piece.getSimpleName()+" in position "+piece.getPos()+" isn't a pawn.");
        if (piece.getPos().y() != piece.getColor().crowningRow(config)) throw new IllegalArgumentException("[DEBUG] Chess@crownPawn: "+piece.getSimpleName()+" in position "+piece.getPos()+" is a Pawn but can't be crowned");
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
            case "amazon" -> {
                Piece newAmazon = new Amazon(piece.getPos(), piece.getColor());
                newAmazon.setGame(this);
                this.pieces.add(newAmazon);
                return true;
            }
            case "archbishop" -> {
                Piece newArchBishop = new ArchBishop(piece.getPos(), piece.getColor());
                newArchBishop.setGame(this);
                this.pieces.add(newArchBishop);
                return true;
            }
            case "mann" -> {
                Piece newMann = new Mann(piece.getPos(), piece.getColor());
                newMann.setGame(this);
                this.pieces.add(newMann);
                return true;
            }
        }
        throw new IllegalArgumentException(newType+" wasn't a legal type to crown a pawn into.");
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.pieces);
        hash = 71 * hash + Objects.hashCode(this.playHistory);
        hash = 71 * hash + Objects.hashCode(this.leftCastlingAvaliability);
        hash = 71 * hash + Objects.hashCode(this.rightCastlingAvaliability);
        hash = 71 * hash + Objects.hashCode(this.activePlayer);
        hash = 71 * hash + (this.gameFinished ? 1 : 0);
        return hash;
    }

}