package chess;

import board.Board;
import board.Piece;
import board.Position;
import chess.enums.Color;
import chess.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {
    private int turn;
    private Color currentPlayer;
    private Board board;

    private List<Piece> boardPieces = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch(){
        board = new Board(8, 8);
        initialSetup();
        this.turn = 1;
        this.currentPlayer = Color.WHITE;
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


    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessPiece performNewMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);

        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);

        nextTurn();

        return (ChessPiece) capturedPiece;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();

        validateSourcePosition(position);

        return board.getPiece(position).possibleMoves();
    }

    private Piece makeMove(Position sourcePosition, Position targetPosition){
        Piece movedPiece = board.removePiece(sourcePosition);
        Piece capturedPiece = board.removePiece(targetPosition);
        board.placePiece(movedPiece, targetPosition);

        if(capturedPiece != null){
            boardPieces.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    private void validateSourcePosition(Position position) {

        if(!board.thereIsAPiece(position)){
            throw new ChessException("There is no piece at source position");
        }

        if(currentPlayer != ((ChessPiece) board.getPiece(position)).getColor()){

            throw new ChessException("The chosen piece is not yours");
        }

        if(!board.getPiece(position).isThereAnyPossibleMove()){
            throw new ChessException("There is no possibles movies for this piece");
        }
    }

    private void validateTargetPosition(Position sourcePosition, Position targetPosition) {
        if(!board.getPiece(sourcePosition).possibleMoves(targetPosition)){
            throw new ChessException("This piece cannot move to target position");
        }
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        this.board.placePiece(piece, new ChessPosition(column, row).toPosition());
        this.boardPieces.add(piece);
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

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;

    }
}
