package com.mycompany.chess;

import java.util.*;

public class Chess {

    private List<Piece> pieces = new ArrayList<>();
    
    public boolean checkPiece(Position pos) {
        long numberOfPiecesInPosition = this.pieces.stream()
                .filter(piece -> piece.getPos().equals(pos))
                .count();
        if (numberOfPiecesInPosition != 0 && numberOfPiecesInPosition != 1) {
            System.out.println("Illegal number of pieces found in position " + pos);
            return false;
        }
        return numberOfPiecesInPosition == 1;
    }
    
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
    
    public boolean checkPieceSameColorAs(Piece piece, Position pos) {
        if (!this.checkPiece(pos)) return false;
        if (this.findPiece(pos).getColor() != piece.getColor()) return false;
        return true;
    }
    
    public boolean checkPieceDiffColorAs(Piece piece, Position pos) {
        if (!this.checkPiece(pos)) return false;
        if (this.findPiece(pos).getColor() == piece.getColor()) return false;
        return true;
    }
    
    public Piece findKing(Color color) {
        return this.pieces.stream()
            .filter(piece -> piece.getClass().getSimpleName().equals("King") && piece.getColor() == color)
            .findAny()
            .get();
    }
    
    public void printTable() {
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
    
    public static int convertLetterToNumber(char letter) {
        return switch (Character.toLowerCase(letter)) {
            case 'a' -> 1;
            case 'b' -> 2;
            case 'c' -> 3;
            case 'd' -> 4;
            case 'e' -> 5;
            case 'f' -> 6;
            case 'g' -> 7;
            case 'h' -> 8;
            default -> -1;
        };
    }
    
    public static char convertNumberToLetter(int num) {
        return switch (num) {
            case 1 -> 'A';
            case 2 -> 'B';
            case 3 -> 'C';
            case 4 -> 'D';
            case 5 -> 'E';
            case 6 -> 'F';
            case 7 -> 'G';
            case 8 -> 'H';
            default -> ' ';
        };
    }
    
    public void linkPieces() {
        this.pieces.stream()
            .forEach(piece -> piece.setGame(this));
    }
    
    public Chess copyGame() {
        // TO DO
        return this;
    }
    
    public static void main(String[] args) {
        Rook torre = new Rook(Position.of(1, 1), Color.BLACK);
        System.out.println(torre);
        System.out.println(torre.getClass().getSimpleName());
    }
}
