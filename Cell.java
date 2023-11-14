package ttt;

/*
 * usage of this class:
 * storage pieces
 *
 *
 *
 * */
public class Cell {
    private Pieces pieces;
    private int row;
    private int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.pieces = new Pieces();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getPtest() {
        return pieces.getPiece_Ptest();
    }

    public void setPtest(Pieces pieces) {
        this.pieces = pieces;
    }

    @Override
    public String toString() {
        return getPtest();
    }


}