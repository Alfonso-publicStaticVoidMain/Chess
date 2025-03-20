package com.mycompany.chess;

public class Pawn extends Piece {

    public Pawn(Position pos, Color color) {
        this.setPos(pos);
        this.setColor(color);
    }
    
    @Override
    public int initRow() {
        return this.getColor().initRowPawn();
    }
    
    public int yDirection() {
        return this.getColor().yDirection();
    }
    
    @Override
    public boolean checkLegalMovement(Position finPos) {
        return checkLegalMovement(finPos, true);
    }
    
    @Override
    public boolean checkLegalMovement(Position finPos, boolean checkCheck) {
        if (!Piece.basicLegalityChecks(this, finPos, checkCheck)) return false;
        Position initPos = this.getPos();
        int Xmovement = Position.xDist(initPos, finPos);
        int Ymovement = Position.yDist(initPos, finPos);
        int Ydirection = this.yDirection();
        int initRow = this.initRow();
        if (Ymovement * Ydirection < 0) return false;
        
        if (!this.getGame().checkPiece(finPos)) {
            if (Xmovement == this.xDirEnPassant()) return true;
            if (Xmovement != 0) return false;
            
            if (Math.abs(Ymovement) > 2) return false;
            
            if (initPos.y() != initRow
                && Math.abs(Ymovement) > 1
            ) return false;
            
            if (Math.abs(Ymovement) == 2
                && this.getGame().checkPiece(Position.of(initPos.x(), initPos.y()+Ydirection))
            ) return false;
        } else {
            if (Ymovement != Ydirection
                || Math.abs(Xmovement) != 1
            ) return false;
        }
        return true;
    }
    
    /**
     * <p>
     * Checks if {@code this} Pawn can make a legal En Passant move, according
     * to the last play recorded in the game.
     * </p>
     * @return Returns true if the {@link Pawn} is able to do an En Passant
     * move given the last play stored in the {@code playRecord} attribute
     * of {@code this.game}.
     */
    public boolean checkLegalEnPassant() {
        // TO DO
        Play lastPlay = this.getGame().getPlayRecord().get(this.getGame().getPlayRecord().size()-1);
        if (!(lastPlay.getPiece() instanceof Pawn)) return false;
        if (Math.abs(Position.yDist(lastPlay.getInitPos(), lastPlay.getFinPos())) != 2) return false;
        if (Math.abs(Position.xDist(this.getPos(), lastPlay.getFinPos())) != 1) return false;
        return true;
    }
    
    /**
     * <p>
     * If {@code this} Pawn can make a legal En Passant move, returns the
     * direction in the X axis it must move to succesfully make that move.
     * </p>
     * @return Returns the direction {@code this} Pawn must move to make an
     * En Passant move on the last moved Pawn, if able. If unable, returns 0.
     */
    public int xDirEnPassant() {
        if (this.checkLegalEnPassant()) return Position.xDist(this.getPos(), this.getGame().getPlayRecord().get(this.getGame().getPlayRecord().size()-1).getFinPos());
        return 0;
    }

    @Override
    public Piece copy() {
        return new Pawn(this.getPos(), this.getColor());
    }

    
    
}
