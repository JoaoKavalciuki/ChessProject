package chess;

import board.Board;
import board.Piece;
import board.Position;
import chess.enums.Color;
import chess.pieces.*;

public class ChessMatch {
    private Board board;

    public ChessMatch(){
        board = new Board(8, 8);
        initialSetup();
    }

    public ChessPiece[][] getPieces(){
        ChessPiece[][] matchPieces = new ChessPiece[board.getRows()][board.getColumns()];

        for(int i = 0; i< board.getRows(); i++){
            for(int j = 0; j< board.getColumns(); j++){
                matchPieces[i][j] = (ChessPiece) board.getPiece(i, j);
            }
        }

        return matchPieces;
    }

    public ChessPiece performNewMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);

        Piece capturedPiece = makeMove(source, target);

        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position sourcePosition, Position targetPosition){
        Piece movedPiece = board.removePiece(sourcePosition);
        Piece capturedPiece = board.removePiece(targetPosition);

        board.placePiece(movedPiece, targetPosition);
        return movedPiece;
    }

    private void validateSourcePosition(Position position) {
        if(!board.thereIsAPiece(position)){
            throw new ChessException("There is no piece at source position");
        }

        if(!board.getPiece(position).isThereAnyPossibleMove()){
            throw new ChessException("There is no possibles movies for this piece");
        }
    }


    private void placeNewPiece(char column, int row, ChessPiece piece){
        this.board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup(){
        board.placePiece(new Rook(this.board, Color.BLACK), new Position(0,0));
        board.placePiece(new Knight(this.board, Color.BLACK), new Position(0,1));
        board.placePiece(new Bishop(this.board, Color.BLACK), new Position(0,2));
        board.placePiece(new King(this.board, Color.BLACK), new Position(0,3));
        board.placePiece(new Queen(this.board, Color.BLACK), new Position(0,4));
        board.placePiece(new Bishop(this.board, Color.BLACK), new Position(0,5));
        board.placePiece(new Knight(this.board, Color.BLACK), new Position(0,6));
        board.placePiece(new Rook(this.board, Color.BLACK), new Position(0,7));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,0));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,1));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,2));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,3));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,4));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,5));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,6));
        board.placePiece(new Pawn(this.board, Color.BLACK), new Position(1,7));


        board.placePiece(new Rook(this.board, Color.WHITE), new Position(7,0));
        board.placePiece(new Knight(this.board, Color.WHITE), new Position(7,1));
        board.placePiece(new Bishop(this.board, Color.WHITE), new Position(7,2));
        board.placePiece(new King(this.board, Color.WHITE), new Position(7,3));
        board.placePiece(new Queen(this.board, Color.WHITE), new Position(7,4));
        board.placePiece(new Bishop(this.board, Color.WHITE), new Position(7,5));
        board.placePiece(new Knight(this.board, Color.WHITE), new Position(7,6));
        board.placePiece(new Rook(this.board, Color.WHITE), new Position(7,7));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,0));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,1));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,2));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,3));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,4));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,5));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,6));
        board.placePiece(new Pawn(this.board, Color.WHITE), new Position(6,7));



    }
}
