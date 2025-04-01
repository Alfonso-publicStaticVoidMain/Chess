package com.mycompany.chess;

public abstract class Piece {
    /**
     * The {@link Position} the Piece has in the chess board.
     */
    private Position pos;
    /**
     * {@link Color} of the Piece, storing certain qualities of that Piece.
     */
    private ChessColor color;
    /**
     * {@link Chess} game where the Piece is being played at.
     */
    private Chess game;
    
    protected Piece(Position pos, ChessColor color) {
        this.pos = pos;
        this.color = color;
    }

    /**
     * Method to check if the proposed Position would be a legal movement for
     * the Piece.
     * 
     * @param finPos Position we're attempting to move the Piece to.
     * @param checkCheck State parameter to track if we want to declare a
     * movement illegal if it causes a check.
     * @return True if the movement is legal for the Piece, false Otherwise.
     * Each class implementing Piece will account for the specific conditions
     * that Piece has limiting its movement.
     */
    public abstract boolean checkLegalMovement(Position finPos, boolean checkCheck);
    /**
     * Overloaded version of the {@link Piece#checkLegalMovement(Position, boolean)}
     * method, defaulting the value of boolean checkCheck to true.
     * @param finPos Position we're attempting to move {@code this} Piece to.
     * @return True if the movement is legal for the Piece and doing it wouldn't
     * cause a check for its color, false otherwise.
     */
    public abstract boolean checkLegalMovement(Position finPos);
    /**
     * Copies {@code this} Piece as a new Piece of its type.
     * @return A new Piece of the appropiate class, with the same {@link Position}
     * and {@link ChessColor}, but with no game associated to it.
     */
    public abstract Piece copy();
    
    public Position getPos() {return this.pos;}
    public void setPos(Position pos) {this.pos = pos;}
    public ChessColor getColor() {return this.color;}
    public void setColor(ChessColor color) {this.color = color;}
    public Chess getGame() {return this.game;}
    public void setGame(Chess game) {this.game = game;}

    /**
     * The initial row of {@code this} Piece on the board.
     * @return Returns an integer representing the initial row of the Piece,
     * as is stored in that Piece's Color.
     * The method will be overwritten in the {@link Pawn} class, returning
     * the attribute initRowPawn of its Color.
     */
    public int initRow() {
        return this.color.initRow();
    }
    
    /**
     * 
     * If the movement is legal, moves {@code this} Piece to the specified
     * Position.
     * 
     * move(Position, boolean) defaults recordMovement to true.
     * move(Position) defaults both recordMovement and checkCheck to true.
     * 
     * @param finPos Position we're trying to move the Piece to.
     * @param checkCheck State parameter to check if the movement should be
     * declared illegal if it'd cause a check.
     * @param recordMovement State parameter to track if the movement should
     * be added to the playRecord attribute of the Chess game of {@code this}.
     * @return Returns true if the movement was sucessfully done. In that case,
     * updates {@code this}'s position, and if there was a Piece previously in
     * that position, eliminates it from its game's playRecord.
     * Then the movement is recorded into {@code this}'s game's playRecord
     * attribute.
     * @see Piece#checkLegalMovement(Position, boolean)
     * @see Pawn#xDirEnPassant
     * @see Chess#findPiece(Position)
     */
    public boolean move(Position finPos, boolean checkCheck, boolean recordMovement) {
        Chess chessGame = this.getGame();
        Position initPos = this.getPos();
        Piece eatenPiece;
        boolean playRecorded = false;
        if (this.checkLegalMovement(finPos, checkCheck)) {
            if (chessGame.checkPiece(finPos)) {
                eatenPiece = chessGame.findPiece(finPos);
                chessGame.getPieces().remove(eatenPiece);
                if (recordMovement && !playRecorded) {
                    chessGame.getPlayRecord().add(new Play(this, initPos, finPos, eatenPiece));
                    playRecorded = true;
                }
            } else if (this instanceof Pawn pawn) {
                int Xmovement = Position.xDist(initPos, finPos);
                if (pawn.checkLegalEnPassant() && Xmovement == pawn.xDirEnPassant()) {
                    eatenPiece = chessGame.getLastPlay().getPiece();
                    chessGame.getPieces().remove(eatenPiece);
                    if (recordMovement && !playRecorded) chessGame.getPlayRecord().add(new Play(this, initPos, finPos, eatenPiece));
                }
            }
            this.setPos(finPos);
            if (recordMovement && !playRecorded) {
                chessGame.getPlayRecord().add(new Play(this, initPos, finPos));
                playRecorded = true;
            }
            
            if ((this instanceof King || this instanceof Rook) &&
                chessGame.getLeftCastlingAvaliability().get(this.getColor()) && (
                initPos.equals(Position.of(1, this.getColor().initRow()))
                || initPos.equals(Position.of(5, this.getColor().initRow())))
                ) chessGame.getLeftCastlingAvaliability().put(this.getColor(), false);
            
            if ((this instanceof King || this instanceof Rook) &&
                chessGame.getRightCastlingAvaliability().get(this.getColor()) && (
                initPos.equals(Position.of(8, this.getColor().initRow()))
                || initPos.equals(Position.of(5, this.getColor().initRow())))
                ) chessGame.getRightCastlingAvaliability().put(this.getColor(), false);
        }
        return false;
    }

    public boolean move(Position finPos, boolean checkCheck) {
        return this.move(finPos, checkCheck, true);
    }
    
    public boolean move(Position finPos) {
        return this.move(finPos, true, true);
    }
    
    /**
     * 
     * Checks if the movement of a certain Piece to a certain Position causes
     * the King of that Piece's Color to be in check.
     * 
     * @param finPos Position we're attempting to move the Piece to.
     * @return Returns true if the movement causes the King of the same color
     * as the {@code piece} to be in check after moving it to {@code finPos}.
     * To achieve this, it copies the game into an auxiliary game, performs the
     * movement there, and then checks if the auxiliary King is in check.
     * @see Chess#findPiece
     * @see Chess#findKing
     * @see Piece#move(Position, boolean)
     * @see King#checkCheck()
     */
    public boolean checkIfMovementCausesCheck(Position finPos) {
        Chess auxGame = this.getGame().copyGame();
        Piece copyOfPiece = auxGame.findPiece(this.getPos());
        copyOfPiece.move(finPos, false, true);
        //System.out.println("[DEBUG] Position of Piece "+copyOfPiece+" = "+copyOfPiece.getPos());
        return auxGame.findKing(copyOfPiece.getColor()).checkCheck();
    }
    
    /**
     * Method that performs basic legality checks on a piece movement.
     * It is intended to be referenced by the implementations of the
     * {@link Piece#checkLegalMovement(Position, boolean)} method
     * on each of the child classes of Piece.
     * 
     * @param finPos Position we want to move the piece to.
     * @param checkCheck State parameter to track if we need to declare the 
     * movement illegal if it causes a check.
     * @return Returns false if either of the following happens:
     *      There's a piece of the same color in the final position.
     *      We are checking for checks and the movement causes one.
     *      The final position is the same as the initial position.
     * @see Chess#checkPieceSameColorAs(Piece, Position)
     * @see Piece#checkIfMovementCausesCheck(Position)
     */
    public boolean basicLegalityChecks(Position finPos, boolean checkCheck) {
        if (this.getGame().checkPieceSameColorAs(this, finPos)) return false;
        if (checkCheck && this.checkIfMovementCausesCheck(finPos)) return false;
        return !this.getPos().equals(finPos);
    }
    
    /**
     * Represents {@code this} Piece as a 2-character string summarizing its
     * color and type.
     * @return A concatenation of a character being the first letter of the
     * color of {@code this} with the first letter of {@code this}'s class,
     * differentiating Kings and Knights by using 'K' for the former and 'k'
     * for the latter.
     */
    @Override
    public String toString() {
        char typeChar;
        if (this instanceof Knight) typeChar = 'k';
        else typeChar = Character.toUpperCase(this.getClass().getSimpleName().charAt(0));
        return "" + Character.toUpperCase(this.getColor().name().charAt(0)) + typeChar;
    }
    
    /**
     * Returns a name of {@code this} representing its color and its type
     * of piece.
     * @return A concatenation of the name of the color of {@code this} Piece,
     * a blank space, and the simple name of {@code this}'s class.
     */
    public String getSimpleName() {
        return this.getColor() + " " + this.getClass().getSimpleName();
    }
    
}