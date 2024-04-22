package chess.pieces;

import board.Board;
import chess.ChessPiece;
import chess.enums.Color;

public class Bishop extends ChessPiece {
    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {
        return new boolean[0][];
    }
}
