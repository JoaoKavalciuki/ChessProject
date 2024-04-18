package chess.pieces;

import board.Board;
import chess.ChessPiece;
import chess.enums.Color;

public class Queen extends ChessPiece {
    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "Q";
    }
}
