package chess_model;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

/**
 * Record storing certain information about the chess game being played.
 * @author Alfonso Gallego
 * @param initRow Map attribute mapping each {@link ChessColor} to the initial
 * row for regular pieces of that color.
 * @param initRowPawn Map attribute mapping each {@link ChessColor} to the
 * initial row for Pawns of that color.
 * @param crowningRow Map attribute mapping each {@link ChessColor} to the
 * row that Pawns of that color need to reach in order to crown.
 * @param rows Number of rows of the board.
 * @param cols Number of columns of the board.
 * @param kingInitCol Initial column of the King.
 * @param leftRookInitCol Initial column of the left Rook (the piece in that
 * column might not be a Rook!)
 * @param rightRookInitCol Initial column of the right Rook (the piece in that
 * column might not be a Rook!)
 * @param leftCastlingCol Column the King ends up in after doing left castling.
 * @param rightCastlingCol Column the King ends up in after doing right castling.
 * @param crownablePieces Array of piece names that contain all pieces a Pawn
 * can crown into.
 * @param typeOfGame String representing the name of the chess variant being
 * played.
 */
public record GameConfiguration(
    Map<ChessColor, Integer> initRow,
    Map<ChessColor, Integer> initRowPawn,
    Map<ChessColor, Integer> crowningRow,
    int rows,
    int cols,
    int kingInitCol,
    int leftRookInitCol,
    int rightRookInitCol,
    int leftCastlingCol,
    int rightCastlingCol,
    String[] crownablePieces,
    String typeOfGame
) implements Serializable {

    private static GameConfiguration configFactory(int maxRows, int maxCols, int leftMovementWhenCastling, int rightMovementWhenCastling, int kingInitCol, String[] crownablePieces, String typeOfGame) {
        Map<ChessColor, Integer> initRowMap = new EnumMap<>(ChessColor.class);
        initRowMap.put(ChessColor.WHITE, 1);
        initRowMap.put(ChessColor.BLACK, maxRows);
        
        Map<ChessColor, Integer> initRowPawnMap = new EnumMap<>(ChessColor.class);
        initRowPawnMap.put(ChessColor.WHITE, 2);
        initRowPawnMap.put(ChessColor.BLACK, maxRows - 1);
        
        Map<ChessColor, Integer> crowningRowMap = new EnumMap<>(ChessColor.class);
        crowningRowMap.put(ChessColor.WHITE, maxRows);
        crowningRowMap.put(ChessColor.BLACK, 1);
        
        return new GameConfiguration(initRowMap, initRowPawnMap, crowningRowMap, maxRows, maxCols, kingInitCol, 1, maxCols, kingInitCol - leftMovementWhenCastling, kingInitCol + rightMovementWhenCastling, crownablePieces, typeOfGame);
    }
    
    public static GameConfiguration standardGameConfig = configFactory(8, 8, 2, 2, 5, new String[] {"Queen", "Knight", "Rook", "Bishop"}, "Standard Chess");
    public static GameConfiguration almostChessConfig = configFactory(8, 8, 2, 2, 5, new String[] {"Chancellor", "Knight", "Rook", "Bishop"}, "Almost Chess");
    public static GameConfiguration capablancaConfig = configFactory(8, 10, 3, 3, 6, new String[] {"Queen", "Chancellor", "ArchBishop", "Knight", "Rook", "Bishop"}, "Capablanca Chess");
    public static GameConfiguration gothicConfig = configFactory(8, 10, 3, 3, 6, new String[] {"Queen", "Chancellor", "ArchBishop", "Knight", "Rook", "Bishop"}, "Gothic Chess");
    public static GameConfiguration janusConfig = configFactory(8, 10, 3, 4, 5, new String[] {"Queen", "ArchBishop", "Knight", "Rook", "Bishop"}, "Janus Chess");
    public static GameConfiguration modernConfig = configFactory(9, 9, 2, 2, 5, new String[] {"Queen", "ArchBishop", "Knight", "Rook", "Bishop"}, "Modern Chess");
    public static GameConfiguration tuttiFruttiConfig = configFactory(8, 8, 2, 2, 5, new String[] {"Amazon", "Queen", "Chancellor", "ArchBishop", "Knight", "Rook", "Bishop"}, "Tutti Frutti Chess");

}
