package board;

public class Board {
    int rows;
    int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Piece getPiece(int row, int column){
        return pieces[row][column];
    }

    public Piece getPiece(Position position){
        return pieces[position.getRow()][position.getRow()];
    }

}
