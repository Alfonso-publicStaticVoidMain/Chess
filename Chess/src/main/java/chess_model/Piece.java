package chess_model;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * Abstract class representing chess Pieces, to be extended by child classes
 * of each piece type.
 * @author Alfonso Gallego
 */
public abstract class Piece implements Serializable {
    /**
     * The {@link Position} the Piece has in the chess board.
     */
    private Position pos;
    /**
     * {@link ChessColor} of the Piece, storing certain qualities of that Piece.
     */
    private final ChessColor color;
    /**
     * {@link Chess} game where the Piece is being played at.
     */
    private Chess game;
    
    /**
     * Protected constructor, to be called by the constructor of each subclass.
     * @param pos {@link Position} of the Piece.
     * @param color {@link ChessColor} of the Piece.
     */
    protected Piece(Position pos, ChessColor color) {
        this.pos = pos;
        this.color = color;
    }

    /**
     * Checks if the given {@link Position} would be a legal movement for
     * this Piece.
     * @param finPos Position we're attempting to move the Piece to.
     * @param checkCheck State parameter to track if we want to declare a
     * movement illegal if it causes a check.
     * @return True if the movement is legal for the Piece, false otherwise.
     * <br><br>
     * Each class implementing Piece will account for the specific limitations
     * of that Piece.
     * <br><br>
     * If the {@code checkCheck} parameter is set to false, this method will
     * return true if the movement is legal, even if it'd cause a check for its
     * player.
     */
    public abstract boolean checkLegalMovement(Position finPos, boolean checkCheck);
    
    /**
     * Overloaded version of the {@link Piece#checkLegalMovement(Position, boolean)}
     * method, defaulting the value of {@code checkCheck} to true.
     * @param finPos Position we're attempting to move {@code this} Piece to.
     * @return True if the movement is legal for the Piece and doing it wouldn't
     * cause a check for its player, false otherwise.
     */
    public boolean checkLegalMovement(Position finPos) {return this.checkLegalMovement(finPos, true);}
    
    /**
     * Copies {@code this} Piece as a new Piece of its type.
     * @return A new Piece of the appropiate class, with the same {@link Position}
     * and {@link ChessColor}, but with no game associated to it.
     */
    public abstract Piece copy();
    
    /**
     * Returns an ImageIcon representing this piece.
     * @return An ImageIcon created from a .png in src\main\resources\ChessPieces
     * representing this Piece.
     */
    public abstract ImageIcon toIcon();
    
    /**
     * Getter for the position attribute of {@code this} Piece.
     * @return The {@link Position} of the Piece on the chess board.
     */
    public Position getPos() {return this.pos;}

    /**
     * Setter for the position attribute of the Piece.
     * @param pos {@link Position} to set as the new position attribute.
     */
    protected void setPos(Position pos) {this.pos = pos;}

    /**
     * Getter for the color attribute of {@code this} Piece.
     * @return The {@link ChessColor} of the Piece.
     */
    public ChessColor getColor() {return this.color;}

    /**
     * Getter for the game attribute of {@code this} Piece.
     * @return The {@link Chess} game this Piece is currently on.
     */
    public Chess getGame() {return this.game;}

    /**
     * Setter for the {@link Chess} game attribute of this Piece.
     * @param game Game to set as {@code this} Piece's game.
     */
    protected void setGame(Chess game) {this.game = game;}

    /**
     * The initial row of {@code this} Piece on the board.
     * @return Returns an integer representing the initial row of the Piece,
     * as is stored in that Piece's Color.
     * The method will be overwritten in the {@link Pawn} class, returning
     * the attribute {@code initRowPawn} of its color.
     */
    public int initRow() {
        if (this instanceof Pawn) return color.initRowPawn(game.getConfig());
        return color.initRow(game.getConfig());
    }
    
    /**
     * If the movement is legal, moves {@code this} Piece to the specified
     * Position.
     * @param finPos Position we're trying to move {@code this} Piece to.
     * @param checkCheck State parameter to check if the movement should be
     * declared illegal if it'd cause a check.
     * @param recordMovement State parameter to track if the movement should
     * be added to the playHistory attribute of the Chess game of {@code this}.
     * @return True if the movement was sucessfully done. In that case,
     * updates {@code this}'s position, and if there was a Piece previously in
     * that position, removes it from its game's pieces List, also taking
     * into account the possiblity of an En Passant capture for Pawns.
     * <br><br>
     * If {@code recordMovement} is set to true, the movement is recorded into
     * {@code this}'s game's {@code playHistory} attribute.
     * @see Piece#checkLegalMovement(Position, boolean)
     * @see Pawn#xDirEnPassant
     * @see Chess#findPiece(Position)
     */
    public boolean move(Position finPos, boolean checkCheck, boolean recordMovement) {
        Chess chessGame = this.getGame();
        Position initPos = this.getPos();
        Piece capturedPiece;
        if (this.checkLegalMovement(finPos, checkCheck)) {
            if (chessGame.checkPiece(finPos)) {
                capturedPiece = chessGame.findPiece(finPos);
                chessGame.getPieces().remove(capturedPiece);
                if (recordMovement) {
                    chessGame.getPlayHistory().add(new Play(this, initPos, finPos, capturedPiece));
                }
            } else if (this instanceof Pawn pawn && pawn.checkLegalEnPassant() && pawn.xDirEnPassant() == Position.xDist(initPos, finPos)) {
                capturedPiece = chessGame.getLastPlay().piece();
                chessGame.getPieces().remove(capturedPiece);
                if (recordMovement) chessGame.getPlayHistory().add(new Play(this, initPos, finPos, capturedPiece));
                
            } else {
                if (recordMovement) {
                    chessGame.getPlayHistory().add(new Play(this, initPos, finPos, 0));
                }
            }
            this.setPos(finPos);
            
            if (chessGame.getLeftCastlingAvaliability().get(color) &&
                ((this instanceof King && initPos.equals(chessGame.initKingPosition(color)))
                ||
                ((this instanceof Rook || this instanceof Chancellor) && initPos.equals(chessGame.initLeftRookPosition(color))))
            ) {
                chessGame.getLeftCastlingAvaliability().put(color, false);
            }
            
            if (chessGame.getRightCastlingAvaliability().get(color) &&
                ((this instanceof King && initPos.equals(chessGame.initKingPosition(color)))
                ||
                ((this instanceof Rook || this instanceof Chancellor) && initPos.equals(chessGame.initRightRookPosition(color))))
            ) {
                chessGame.getRightCastlingAvaliability().put(color, false);
            }
        }
        return false;
    }

    /**
     * Overloaded version of {@link Piece#move(Position, boolean, boolean)},
     * defaulting {@code recordMovement} to true.
     * @param finPos Position we're trying to move {@code this} Piece to.
     * @param checkCheck State parameter to check if the movement should be
     * declared illegal if it'd cause a check.
     * @return True if the movement was sucessfully done. In that case,
     * updates {@code this}'s position, and if there was a Piece previously in
     * that position, eliminates it from its game's pieces List, also taking
     * into account the possiblity of an En Passant capture for Pawns.
     * @see Piece#move(Position, boolean, boolean) 
     */
    public boolean move(Position finPos, boolean checkCheck) {
        return this.move(finPos, checkCheck, true);
    }
    
    /**
     * Overloaded version of {@link Piece#move(Position, boolean, boolean)},
     * defaulting {@code checkCheck} and {@code recordMovement} to true.
     * @param finPos Position we're attempting {@code this} Piece to.
     * @return True if the movement was sucessfully done. In that case,
     * updates {@code this}'s position, and if there was a Piece previously in
     * that position, eliminates it from its game's pieces List, also taking
     * into account the possiblity of an En Passant capture for Pawns.
     * @see Piece#move(Position, boolean, boolean) 
     */
    public boolean move(Position finPos) {
        return this.move(finPos, true, true);
    }
    
    /**
     * Checks if the movement of {@code this} Piece to the given
     * {@link Position} causes the {@link King} of that Piece's color to be in
     * check.
     * @param finPos Position we're attempting to move the Piece to.
     * @return True if the movement causes the {@link King} of the same color
     * as the {@code this} to be in check after moving it to {@code finPos}.
     * To achieve this, it copies the game into an auxiliary game, performs the
     * movement there, and then checks if the auxiliary King is in check.
     * @see Chess#findPiece
     * @see Chess#findKing
     * @see Chess#copyGame
     * @see Piece#move(Position, boolean)
     * @see King#checkCheck()
     */
    public boolean checkIfMovementCausesCheck(Position finPos) {
        Chess auxGame = this.getGame().copyGame();
        Piece copyOfPiece = auxGame.findPiece(this.getPos());
        copyOfPiece.move(finPos, false, true);
        return auxGame.findKing(copyOfPiece.getColor()).checkCheck();
    }
    
    /**
     * Performs some common legality checks on a proposed piece movement.
     * It is intended to be referenced by the implementations of the
     * {@link Piece#checkLegalMovement(Position, boolean)} method
     * on each of the child classes of Piece.
     * @param finPos Position we want to move {@code this} piece to.
     * @param checkCheck State parameter to track if we need to declare the 
     * movement illegal if it causes a check.
     * @return Returns false if either of the following happens:
     * <ul>
     * <li>There's a piece of the same color in the final position.</li>
     * <li>We are checking for checks ({@code checkCheck} is set to true) and
     * the movement causes one.</li>
     * <li>The final position is the same as the initial position.</li>
     * </ul>
     * @see Chess#checkPieceSameColorAs(Piece, Position)
     * @see Piece#checkIfMovementCausesCheck(Position)
     */
    public boolean basicLegalityChecks(Position finPos, boolean checkCheck) {
        return !(
            this.getGame().checkPieceSameColorAs(this, finPos) ||
            (checkCheck && this.checkIfMovementCausesCheck(finPos)) ||
            this.getPos().equals(finPos)
        );
    }
    
    /**
     * Checks if the proposed movement follows a straight path, like a Rook
     * would.
     * @param Xmovement Signed distance travelled in the X axis.
     * @param Ymovement Signed distance travelled in the Y axis.
     * @return False if both Xmovement and Ymovement are 0, true otherwise.
     */
    public static boolean isRookLikePath(int Xmovement, int Ymovement) {
        return !(Xmovement != 0 && Ymovement != 0);
    }
    
    /**
     * Overloaded version of {@link Chess#isRookLikePath(int, int)}, calculating
     * the X and Y movements from the coordinates of the given positions.
     * @param initPos Initial position of the movement.
     * @param finPos Final position of the movement.
     * @return False if both Xmovement and Ymovement are 0, true otherwise.
     */
    public static boolean isRookLikePath(Position initPos, Position finPos) {
        int Xmovement = Position.xDist(initPos, finPos);
        int Ymovement = Position.yDist(initPos, finPos);
        return isRookLikePath(Xmovement, Ymovement);
    }
    
    /**
     * Checks if the proposed movement follows a diagonal path, like a Bishop
     * would.
     * @param Xmovement Signed distance travelled in the X axis.
     * @param Ymovement Signed distance travelled in the Y axis.
     * @return True if the absolute value of both X and Y movements is the same,
     * false otherwise.
     */
    public static boolean isBishopLikePath(int Xmovement, int Ymovement) {
        return Math.abs(Xmovement) == Math.abs(Ymovement);
    }
    
    /**
     * Overloaded version of {@link Chess#isBishopLikePath(int, int)},
     * calculating the X and Y movements from the coordinates of the given
     * positions.
     * @param initPos Initial position of the movement.
     * @param finPos Final position of the movement.
     * @return True if the absolute value of both X and Y movements is the same,
     * false otherwise.
     */
    public static boolean isBishopLikePath(Position initPos, Position finPos) {
        int Xmovement = Position.xDist(initPos, finPos);
        int Ymovement = Position.yDist(initPos, finPos);
        return isBishopLikePath(Xmovement, Ymovement);
    }
    
    /**
     * Checks if the proposed movement matches the movement of a Knight.
     * @param Xmovement Signed distance travelled in the X axis.
     * @param Ymovement Signed distance travelled in the Y axis.
     * @return True if the absolute value of the sum of the X and Y movements
     * is exactly 3, and each of those absolute values is between 1 and 2, both
     * inclusive. False otherwise.
     */
    public static boolean isKnightLikePath(int Xmovement, int Ymovement) {
        return Math.abs(Xmovement)+Math.abs(Ymovement)==3
            && Math.abs(Xmovement)<=2 && Math.abs(Xmovement)>=1
            && Math.abs(Ymovement)<=2 && Math.abs(Ymovement)>=1;
    }
    
    /**
     * Overloaded version of {@link Chess#isKnightLikePath(int, int)},
     * calculating the X and Y movements from the coordinates of the given
     * positions.
     * @param initPos Initial position of the movement.
     * @param finPos Final position of the movement.
     * @return True if the absolute value of the sum of the X and Y movements
     * is exactly 3, and each of those absolute values is between 1 and 2, both
     * inclusive. False otherwise.
     */
    public static boolean isKnightLikePath(Position initPos, Position finPos) {
        int Xmovement = Position.xDist(initPos, finPos);
        int Ymovement = Position.yDist(initPos, finPos);
        return isKnightLikePath(Xmovement, Ymovement);
    }
    
    /**
     * Represents {@code this} Piece as a 2-character string summarizing its
     * color and type.
     * @return A concatenation of a character being the first letter of
     * {@code this}'s color with the first letter of {@code this}'s class,
     * differentiating Kings and Knights by using 'K' for the former and 'k'
     * for the latter.
     * @deprecated This method is only used to play the game by console, which
     * is itself a deprecated way of doing so.
     */
    @Deprecated
    public String twoCharName() {
        char typeChar;
        if (this instanceof Knight) typeChar = 'k';
        else typeChar = Character.toUpperCase(this.getClass().getSimpleName().charAt(0));
        return "" + Character.toUpperCase(this.getColor().name().charAt(0)) + typeChar;
    }
    
    /**
     * Returns String representing {@code this}'s color and type.
     * @return A concatenation of the name of the color of {@code this} Piece,
     * a blank space, and the simple name of {@code this}'s class.
     */
    public String getSimpleName() {
        return this.getColor() + " " + this.getClass().getSimpleName();
    }
    
}