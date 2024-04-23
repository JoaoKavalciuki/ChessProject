package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.enums.Color;

public class Pawn extends ChessPiece {

    private  ChessMatch chessMatch;
    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] auxMatrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position auxPosition = new Position(0, 0);

        if(getColor() == Color.WHITE){
            auxPosition.setValues(position.getRow()-1, position.getColumn());
            if(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)){
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }

            auxPosition.setValues(position.getRow()-2, position.getColumn());
            Position targetPostion = new Position(position.getRow()-1, position.getColumn());

            if (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(targetPostion) && getBoard().positionExists(targetPostion) && !getBoard().thereIsAPiece(targetPostion) && getMoveCount() == 0) {
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }

            //diagonal esquerda
            auxPosition.setValues(position.getRow()-1, position.getColumn()-1);
            if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }

            //diagonal direita
            auxPosition.setValues(position.getRow()-1, position.getColumn()+1);
            if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }

            //En Passant white
            if(position.getRow() == 3){
                Position left = new Position(position.getRow(), position.getColumn()-1);

                if(getBoard().positionExists(left) && isThereOpponentPiece(left)
                        && getBoard().getPiece(left) == chessMatch.getEnPassantVulnerable())
                {
                    auxMatrix[left.getRow()-1][left.getColumn()] = true;
                }

                Position right = new Position(position.getRow(), position.getColumn()+1);

                if(getBoard().positionExists(right) && isThereOpponentPiece(right)
                        && getBoard().getPiece(right) == chessMatch.getEnPassantVulnerable())
                {
                    auxMatrix[left.getRow()-1][right.getColumn()] = true;
                }
            }
        } else {
            auxPosition.setValues(position.getRow()+1, position.getColumn());
            if(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)){
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }

            auxPosition.setValues(position.getRow()+2, position.getColumn());
            Position targetPostion = new Position(position.getRow()+1, position.getColumn());
            if (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(targetPostion) && getBoard().positionExists(targetPostion) && !getBoard().thereIsAPiece(targetPostion) && getMoveCount() == 0) {
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }


            //diagonal esquerda
            auxPosition.setValues(position.getRow()+1, position.getColumn()-1);
            if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }
            auxPosition.setValues(position.getRow()+1, position.getColumn()+1);
            if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
                auxMatrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }

            if(position.getRow() == 4){
                Position left = new Position(position.getRow(), position.getColumn()-1);

                if(getBoard().positionExists(left) && isThereOpponentPiece(left)
                        && getBoard().getPiece(left) == chessMatch.getEnPassantVulnerable())
                {
                    auxMatrix[left.getRow()+1][left.getColumn()] = true;
                }

                Position right = new Position(position.getRow(), position.getColumn()+1);

                if(getBoard().positionExists(right) && isThereOpponentPiece(right)
                        && getBoard().getPiece(right) == chessMatch.getEnPassantVulnerable())
                {
                    auxMatrix[left.getRow()+1][right.getColumn()] = true;
                }
            }
        }

        return  auxMatrix;
    }
}
