package board;

public abstract class Piece {
    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
    }
    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMoves(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove(){
        boolean[][] auxMatrix = possibleMoves();

        for(int i = 0; i<auxMatrix.length; i++){
            for(int j = 0; j<auxMatrix.length; j++){
                if(auxMatrix[i][j]){
                    return true;
                }
            }
        }

        return false;
    }
}
