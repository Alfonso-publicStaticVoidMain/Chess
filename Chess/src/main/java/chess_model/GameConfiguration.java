package chess_model;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author Alfonso Gallego
 */
public record GameConfiguration(
    Map<ChessColor, Integer> initRow,
    Map<ChessColor, Integer> initRowPawn,
    Map<ChessColor, Integer> crowningRow,
    int kingInitCol,
    int leftRookInitCol,
    int rightRookInitCol,
    int leftCastlingCol,
    int rightCastlingCol,
    String[] crownablePieces
) implements Serializable {

    public static GameConfiguration standardFactory(int maxRows, int maxCols, int movementWhenCastling, int kingInitCol, String[] crownablePieces) {
        Map<ChessColor, Integer> initRowMap = new EnumMap<>(ChessColor.class);
        initRowMap.put(ChessColor.WHITE, 1);
        initRowMap.put(ChessColor.BLACK, maxRows);
        
        Map<ChessColor, Integer> initRowPawnMap = new EnumMap<>(ChessColor.class);
        initRowPawnMap.put(ChessColor.WHITE, 2);
        initRowPawnMap.put(ChessColor.BLACK, maxRows - 1);
        
        Map<ChessColor, Integer> crowningRowMap = new EnumMap<>(ChessColor.class);
        crowningRowMap.put(ChessColor.WHITE, maxRows);
        crowningRowMap.put(ChessColor.BLACK, 1);
        
        return new GameConfiguration(initRowMap, initRowPawnMap, crowningRowMap, kingInitCol, 1, maxCols, kingInitCol - movementWhenCastling, kingInitCol + movementWhenCastling, crownablePieces);
    }
    
    public static GameConfiguration standardGameConfig = standardFactory(8, 8, 2, 5, new String[] {"Queen", "Knight", "Rook", "Bishop"});
    public static GameConfiguration almostChessConfig = standardFactory(8, 8, 2, 5, new String[] {"Chancellor", "Knight", "Rook", "Bishop"});
}
