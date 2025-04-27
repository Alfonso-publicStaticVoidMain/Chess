package graphic_resources;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Alfonso Gallego
 */
public class ChessImages {
/*
    // Standard Chess Pieces (White)
    public static final ImageIcon whiteKing = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/white_king.png"));
    public static final ImageIcon whiteQueen = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/white_queen.png"));
    public static final ImageIcon whiteRook = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/white_rook.png"));
    public static final ImageIcon whiteBishop = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/white_bishop.png"));
    public static final ImageIcon whiteKnight = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/white_knight.png"));
    public static final ImageIcon whitePawn = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/white_pawn.png"));

    // Nonstandard Chess Pieces (White)
    public static final ImageIcon whiteAmazon = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/white_amazon.png"));
    public static final ImageIcon whiteChancellor = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/white_chancellor.png"));
    public static final ImageIcon whiteArchbishop = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/white_archbishop.png"));
    public static final ImageIcon whiteMann = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/white_mann.png"));

    // Standard Chess Pieces (Black)
    public static final ImageIcon blackKing = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/black_king.png"));
    public static final ImageIcon blackQueen = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/black_queen.png"));
    public static final ImageIcon blackRook = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/black_rook.png"));
    public static final ImageIcon blackBishop = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/black_bishop.png"));
    public static final ImageIcon blackKnight = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/black_knight.png"));
    public static final ImageIcon blackPawn = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/black_pawn.png"));

    // Nonstandard Chess Pieces (Black)
    public static final ImageIcon blackAmazon = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/black_amazon.png"));
    public static final ImageIcon blackChancellor = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/black_chancellor.png"));
    public static final ImageIcon blackArchbishop = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/black_archbishop.png"));
    public static final ImageIcon blackMann = new ImageIcon(ChessImages.class.getClassLoader().getResource("resources/black_mann.png"));
*/
    
    // Standard Chess Pieces (White)
    public static final ImageIcon whiteKing = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/white_king.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon whiteQueen = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/white_queen.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon whiteRook = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/white_rook.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon whiteBishop = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/white_bishop.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon whiteKnight = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/white_knight.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon whitePawn = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/white_pawn.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));

    // Nonstandard Chess Pieces (White)
    public static final ImageIcon whiteAmazon = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/white_amazon.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon whiteChancellor = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/white_chancellor.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon whiteArchbishop = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/white_archbishop.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon whiteMann = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/white_mann.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));

    // Standard Chess Pieces (Black)
    public static final ImageIcon blackKing = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/black_king.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon blackQueen = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/black_queen.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon blackRook = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/black_rook.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon blackBishop = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/black_bishop.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon blackKnight = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/black_knight.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon blackPawn = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/black_pawn.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));

    // Nonstandard Chess Pieces (Black)
    public static final ImageIcon blackAmazon = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/black_amazon.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon blackChancellor = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/black_chancellor.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon blackArchbishop = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/black_archbishop.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
    public static final ImageIcon blackMann = new ImageIcon(new ImageIcon(ChessImages.class.getClassLoader().getResource("ChessPieces/black_mann.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));


    // Method to resize images
    private static Image getScaledImage(Image srcImg, int width, int height) {
        return srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

}
