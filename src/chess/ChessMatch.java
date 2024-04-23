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
    private boolean isInCheck;
    private boolean isInCheckMate;
    private ChessPiece enPassantVulnerable;

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


    public boolean isInCheck() {
        return isInCheck;
    }

    public boolean isInCheckMate() {
        return isInCheckMate;
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessPiece getEnPassantVulnerable() {
        return enPassantVulnerable;
    }

    public ChessPiece performNewMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);

        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);

        if(checkTest(currentPlayer)){
            undoMove(source, target, (ChessPiece) capturedPiece);
            throw new ChessException("You cant put yourself in check");
        }

        ChessPiece movedPiece = (ChessPiece) board.getPiece(target);



        if(checkTest(opponent(currentPlayer))){
            isInCheck = true;
        }

        if(checkMateTest(opponent(currentPlayer))){
            isInCheckMate = true;

        } else {
            nextTurn();
        }

        //En passant

        if(movedPiece instanceof Pawn && (target.getRow() == sourcePosition.getRow()-2 || target.getRow() == sourcePosition.getRow()+2)){
            enPassantVulnerable = movedPiece;
        }
        enPassantVulnerable = null;

        return (ChessPiece) capturedPiece;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();

        validateSourcePosition(position);

        return board.getPiece(position).possibleMoves();
    }

    private Piece makeMove(Position sourcePosition, Position targetPosition){
        ChessPiece movedPiece = (ChessPiece) board.removePiece(sourcePosition);
        movedPiece.increaseMoveCount();
        Piece capturedPiece = board.removePiece(targetPosition);
        board.placePiece(movedPiece, targetPosition);

        if(capturedPiece != null){
            boardPieces.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        //Roque torre direita
        if(movedPiece instanceof King && targetPosition.getColumn() == sourcePosition.getColumn()+2){
            Position sourcePositionTower = new Position(sourcePosition.getRow(), sourcePosition.getColumn()+3);
            Position targetPositionTower = new Position(sourcePosition.getRow(), sourcePosition.getColumn()+1);

            ChessPiece rook = (ChessPiece) board.removePiece(sourcePositionTower);
            board.placePiece(rook, targetPositionTower);
            rook.increaseMoveCount();
        }

        //Roque torre esquerda
        if(movedPiece instanceof King && targetPosition.getColumn() == sourcePosition.getColumn()-2){
            Position sourcePositionTower = new Position(sourcePosition.getRow(), sourcePosition.getColumn()-4);
            Position targetPositionTower = new Position(sourcePosition.getRow(), sourcePosition.getColumn()-1);

            ChessPiece rook = (ChessPiece) board.removePiece(sourcePositionTower);
            board.placePiece(rook, targetPositionTower);
            rook.increaseMoveCount();
        }
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, ChessPiece capturedPiece){
        ChessPiece movedPiece = (ChessPiece) board.removePiece(target);
        movedPiece.decraseMoveCount();
        board.placePiece(movedPiece, source);

        if(capturedPiece != null){
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            boardPieces.add(capturedPiece);
        }

        //Roque torre direita
        if(movedPiece instanceof King && target.getColumn() == source.getColumn()+2){
            Position sourcePositionTower = new Position(source.getRow(), source.getColumn()+3);
            Position targetPositionTower = new Position(source.getRow(), source.getColumn()+1);

            ChessPiece rook = (ChessPiece) board.removePiece(targetPositionTower);
            board.placePiece(rook, sourcePositionTower);
            rook.decraseMoveCount();
        }

        //Roque torre esquerda
        if(movedPiece instanceof King && target.getColumn() == source.getColumn()-2){
            Position sourcePositionTower = new Position(source.getRow(), source.getColumn()-4);
            Position targetPositionTower = new Position(source.getRow(), source.getColumn()-1);

            ChessPiece rook = (ChessPiece) board.removePiece(targetPositionTower);
            board.placePiece(rook, sourcePositionTower);
            rook.decraseMoveCount();
        }
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
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE, this));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));


        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK, this));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));

    }

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;

    }

    private Color opponent(Color playerColor){
        return  (playerColor == Color.WHITE) ? Color.BLACK  : Color.WHITE;
    }

    private ChessPiece getKing(Color color){
        List<Piece> pieces = boardPieces.stream().filter(piece -> ((ChessPiece)piece).getColor() == color).toList();

        for(Piece piece: pieces){
            if(piece instanceof King){
                return (ChessPiece) piece;
            }
        }

        throw new IllegalStateException("There is no king of color " + color.toString() + " on the board");
    }

    private boolean checkTest(Color color){
        Position kingPosition = getKing(color).getChessPosition().toPosition();
        List<Piece> pieces = boardPieces.stream().filter(piece -> ((ChessPiece)piece).getColor() == opponent(color)).toList();

        for(Piece piece : pieces){
            boolean[][] possibleMoves = piece.possibleMoves();

            if(possibleMoves[kingPosition.getRow()][kingPosition.getColumn()]){
                return true;
            }
        }


        return false;
    }

    private boolean checkMateTest(Color color){
        if(!checkTest(color)){
            return false;
        }
        List<Piece> pieces = boardPieces.stream().filter(piece -> ((ChessPiece)piece).getColor() == color).toList();
        for(Piece piece : pieces){
            boolean[][] possibleMovies = piece.possibleMoves();
            for(int i = 0; i<board.getRows(); i++){
                for(int j = 0; j< board.getColumns(); j++){
                    if(possibleMovies[i][j]){
                        Position source = ((ChessPiece)piece).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = checkTest(color);
                        undoMove(source, target, (ChessPiece) capturedPiece);

                        if(!testCheck){
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

}
