package ttt;

public class QuoridorBoard extends Board {
    private static final String BLUE = "\u001B[34m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public void setQuoridorBoard(int N, int M) {
        this.SetBoardSize(2 * N - 1, 2 * M - 1);
    }

    public String toString() {
        String all_t_board = "";
        int i = 1;
        String l_b = BLUE + "[" + RESET;
        String r_b = BLUE + "]" + RESET;

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
                } else if (this.cell[row][col].getPtest().equals("-")) {
                    s_blank = RED + this.cell[row][col].getPtest() + "--" + RESET;
                } else if (this.cell[row][col].getPtest().equals("|")) {
                    s_blank = RED + this.cell[row][col].getPtest() + "  " + RESET;
                } else {
                    s_blank = this.cell[row][col].getPtest() + "  ";
                }

                if ((row % 2 == 1) | (col % 2 == 1)) {
                    all_t_board = all_t_board + " " + s_blank + " ";
                } else {
                    all_t_board = all_t_board + l_b + s_blank + r_b;
                }
                i++;

            }
            all_t_board = all_t_board + "\n";
        }
        return all_t_board;
    }

    public void set_block() {
    }

//    public int getN() {
//        return this.N;
//    }
//
//    public int getM() {
//        return this.M;
//    }
}
