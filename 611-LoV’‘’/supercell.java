package ttt;
/*
 * usage of this class:
 * can play ttt in each small board
 *
 *
 *
 * */
public class supercell  {
    private Tictactoe iner_ttt;
    private boolean win=false;

    private Pieces ptest;




    public void init(int i_N,int i_M,int in_win_c){
        //this.iner_ttt=iner_ttt;
        Board t=new Board();
        t.SetBoardSize(i_N,i_M);

        iner_ttt=new Tictactoe();
        iner_ttt.set_board_for_sttt(t);
        iner_ttt.wincondition=in_win_c;
        //iner_ttt.finish=false;

        //iner_ttt.PrintBoard();
    }

    public int play_in_a_iboard(Player player){
        if(!win){
            this.iner_ttt.current_player=player;

            //this.iner_ttt.board.PrintBoard();
            this.iner_ttt.board.print_with_location();

            int[]coord=new int[2];
            coord=this.iner_ttt.CheckValidationOfCoordinate(1);
            //System.out.println(this.iner_ttt.finish);//////////////////////////
            if (coord[0]>-1){
                this.getIner_ttt().board.set_p(coord[0],coord[1],player.get_piece());
                this.if_this_board_has_winner(coord[0],coord[1],player.get_piece());
                return 0;
            }else {
                return coord[0];//-1back -2quit
            }
        }else {
            return 1;

        }
    }
    public void if_this_board_has_winner(int row, int col, Pieces Pieces){

        iner_ttt.CheckWin(row,col, Pieces);
        if (iner_ttt.finish==true){
            //使之后棋子不再可以下
            System.out.println("this board has winner,now it is a big "+ Pieces.getPiece_Ptest());

            win=true;
            //标志该棋盘记号
            this.ptest=Pieces;
            //打印该棋盘记号
            Board t=new Board();
            t.SetBoardSize(iner_ttt.N, iner_ttt.M);
            t.set_p(0,0, Pieces);
            this.iner_ttt.set_board_for_sttt(t);

        }else {
            win=false;
        }
    }
    public boolean get_win(){
        return win;
    }


    public Tictactoe getIner_ttt() {
        return iner_ttt;
    }




}
