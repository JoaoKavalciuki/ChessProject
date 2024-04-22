package chess;

import board.Board;
import board.Piece;
import board.Position;
import chess.enums.Color;

public abstract class ChessPiece extends Piece {

    private Color color;
    private int moveCount;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void increaseMoveCount(){
        moveCount++;
    }
    public void decraseMoveCount(){
        moveCount--;
    }

    protected boolean isThereOpponentPiece(Position position){
        ChessPiece sourcePiece = (ChessPiece) this.getBoard().getPiece(position);
        return sourcePiece != null && sourcePiece.getColor() != this.getColor();
    }

    private ChessPosition getChessPosition(Position position){
        return ChessPosition.fromPosition(position);
    }
}
