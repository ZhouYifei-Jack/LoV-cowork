package ttt;
/*
 * usage of this class:
 * provide Board for every game
 * achieve function for every Board,such as
 * set and print
 * check full
 * return information of Cell
 * */

public class Board {
    protected Cell[][] cell;
    private Cell[][] ActualCell;
    //private int BoardLength;
    protected int N;
    protected int M;

    private static final String BLUE = "\u001B[34m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";
    public void SetBoardSize(int N,int M) {
        this.N=N;
        this.M=M;
        this.cell=new Cell[N][M];

        for (int row =0;row<this.N;row++) {
            for (int col =0;col<this.M;col++) {
                Pieces a=new Pieces();
                a.setPtest(" ");
                Cell c=new Cell(N,M);
                c.setPtest(a);
                this.cell[row][col]=c;
            }
        }
    }
    public void print_with_location(){
        int i=1;
        for (int row =0;row<this.N;row++) {
            for (int col =0;col<this.M;col++) {
                System.out.print("["+this.cell[row][col].getPtest()+"]"+i);
                i++;
            }
            System.out.println();
        }
    }
    public void set_p(int row, int col, Pieces Pieces){
        this.cell[row][col].setPtest(Pieces);
    }
    public void clear_p(int row, int col){
        Pieces p=new Pieces();
        p.setPtest(" ");
        this.cell[row][col].setPtest(p);
    }
    public boolean check_full(int row,int col){
        if(row<this.N&&col<this.M&&row>=0&&col>=0) {
            if(this.cell[row][col].getPtest().equals(" ")) {
                return true;
            }else {
                System.out.println("this Cell is filled,pleas enter again");
                return false;
            }
        }else {

            System.out.println("this location is out of bound,pleas enter again");
            return false;
        }
    }
    public String position_info(int row,int col){
        return cell[row][col].getPtest();
    }

    public int getN(){
        return N;
    }

    public int getM(){
        return M;
    }
    public String toString() {
        String all_t_board = "";
        int i = 1;
        String l_b = RESET + "[" + RESET;
        String r_b = RESET + "]" + RESET;

        for (int row = 0; row < this.N; row++) {
            for (int col = 0; col < this.M; col++) {
                String s_blank = " ";
                if (this.cell[row][col].getPtest().equals(" ")) {
                    if (i <= 9) {
                        s_blank = i + "  ";
                    } else if (i <= 99) {
                        s_blank = i + " ";
                    } else {
                        s_blank = i + "";
                    }
                } else {
                    if (this.cell[row][col].getPtest().equals("H")){
                        s_blank = BLUE+this.cell[row][col].getPtest()+RESET + "  ";
                    }if (this.cell[row][col].getPtest().equals("/")){
                        s_blank = RED+this.cell[row][col].getPtest()+RESET + "  ";
                    }if (this.cell[row][col].getPtest().equals("M")){
                        s_blank = YELLOW+this.cell[row][col].getPtest()+RESET + "  ";
                    }
                }
                all_t_board = all_t_board + l_b + s_blank + r_b;
                i++;

            }
            all_t_board = all_t_board + "\n";
        }
        return all_t_board;
    }












    public String toString(Board actualboard) {
        String all_t_board = "";
        int i = 1;
        String l_b = RESET + "[" + RESET;
        String r_b = RESET + "]" + RESET;

        for (int row = 0; row < this.N; row++) {
            for (int col = 0; col < this.M; col++) {
                String s_blank = " ";
                if (actualboard.cell[row][col].getPtest().equals(" ")) {
                    if (i <= 9) {
                        s_blank = i + "  ";
                    } else if (i <= 99) {
                        s_blank = i + " ";
                    } else {
                        s_blank = i + "";
                    }
                } else if (this.cell[row][col].getPtest().equals("")){
                    if (actualboard.cell[row][col].getPtest().equals(" ")){

                    }else if (actualboard.cell[row][col].getPtest().equals(" ")){

                    }
                    s_blank = this.cell[row][col].getPtest() + "  ";
                }
                all_t_board = all_t_board + l_b + s_blank + r_b;
                i++;

            }
            all_t_board = all_t_board + "\n";
        }
        return all_t_board;
    }
}
