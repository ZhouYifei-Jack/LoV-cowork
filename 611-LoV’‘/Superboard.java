package ttt;
/*
 * usage of this class:
 * for sttt
 * N*M for both big board and small board
 *
 *
 *
 *
 * */
public class Superboard {
    private supercell[][] board;
    private int o_N;
    private int o_M;
    private int i_N;
    private int i_M;


    public void set_size(int o_N,int o_M,int i_N,int i_M,int in_w_condition ){
        this.i_N=i_N;
        this.i_M=i_M;
        this.o_N=o_N;
        this.o_M=o_M;

        board=new supercell[o_N][o_M];



        for (int row =0;row<o_N;row++) {
            //System.out.println("//////////////////////////////////////////");
            for (int col =0;col<o_M;col++) {
                supercell t=new supercell();
                board[row][col]=t;
                t.init(i_N,i_M,in_w_condition);
            }
            //System.out.println("//////////////////////////////////////////");
        }
        //System.out.println(board[0][0]+""+board[0][1]+""+board[0][2]);
        //this.toString();
        //System.out.println("ok");--ok
        //superTictactoe s=new superTictactoe();

    }


    @Override
    public String toString(){
        String all_t_board="";
        for (int outer_row=0;outer_row<this.o_N;outer_row++){

            for(int iner_row=0;iner_row<this.i_N;iner_row++){

                for(int outer_col=0;outer_col<this.o_M;outer_col++){

                    for(int iner_col=0;iner_col<this.i_M;iner_col++){
                        String loc=" ";
                        if ((iner_col==(this.i_M-1))&&(iner_row==(this.i_N-1))){
                            loc="board "+(outer_row*this.o_M+outer_col+1);
                        }
                        all_t_board=all_t_board+"["+board[outer_row][outer_col]
                                .getIner_ttt().board.position_info(iner_row,iner_col)+"]"+loc;
                    }
                    if (iner_row<(this.i_N-1)){
                        all_t_board=all_t_board+"        ";
                    }else {
                        all_t_board=all_t_board+"  ";
                    }

                }
                all_t_board=all_t_board+"\n";
            }
            all_t_board=all_t_board+"\n";
        }
        return all_t_board;
    }

    public supercell get_one_board(int row, int col){
        //System.out.println(row);
        //System.out.println(col);
        return this.board[row][col];
    }

}
