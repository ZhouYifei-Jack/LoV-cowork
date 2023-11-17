package ttt;
/*
 * usage of this class:
 * game of sttt
 *
 *
 *
 * */
public class superTictactoe extends Tictactoe {
    private Superboard s_b;
    private supercell supercell_now=new supercell();


    private int o_N;
    private int o_M;
    private int i_N;
    private int i_M;
    private int s_w_d;





    public void init(Player player1,Player player2){
        this.player1=player1;
        this.player2=player2;
        System.out.println("the inner and outer size will default by 3x3 and 3x3," +
                "\nenter 0 to use the default rule(both big and small board are 3x3,win_condition both=3) ," +
                "\nenter 1 to set the size by yourself:");
        int choose=0;
        choose=this.CheckSetUp(0,1);
        if (choose==0){
            this.i_N=3;
            this.i_M=3;
            this.o_N=3;
            this.o_M=3;


            this.wincondition=2;
            this.s_w_d=2;
        } else if (choose==1) {
            System.out.println("enter N of outboard(please enter >=2 and <=5 for better experience)");
            this.o_N=this.CheckSetUp(2,5);//change parameter here to change the range of what you can enter
            System.out.println("enter M of outboard(please enter >=2 and <=5 for better experience)");
            this.o_M=this.CheckSetUp(2,5);
            System.out.println("enter N of inboard(please enter >=3 and <=9 for better experience)");
            this.i_N=this.CheckSetUp(3,9);
            System.out.println("enter M of outboard(please enter >=3 and <=9 for better experience)");
            this.i_M=this.CheckSetUp(3,9);
            System.out.println("ok,size set up");
            System.out.println("\n");


            System.out.println("enter win condition of big board(it should <= Shortest length of board)");
            this.wincondition=this.CheckSetUp(2,Math.min(this.o_M,this.o_N))-1;
            System.out.println("win_condition set up,win if "+(this.wincondition+1)+" pieces in a line");
            System.out.println("enter win condition of small board(it should <= Shortest length of board)");
            this.s_w_d=this.CheckSetUp(3,Math.min(this.i_N,this.i_M))-1;
            System.out.println("win_condition set up,win if "+(this.s_w_d+1)+" pieces in a line");

        }
        s_b=new Superboard();
        s_b.set_size(this.o_N,this.o_M,this.i_N,this.i_M,s_w_d);



        this.board=new Board();
        this.board.SetBoardSize(o_N,o_M);
        this.N=o_N;
        this.M=o_M;



        this.startplay();


    }

    public void startplay(){
        this.current_player=this.player1;
        System.out.println("Start play!!");
        System.out.println(this.s_b);

        while(!this.finish) {
            boolean set_b=false;
            int sb_num=0;


            int choose_b=-1;
            while(choose_b==-1){
                while(!set_b){
                    System.out.println(this.current_player.get_name()+",enter which board");
                    sb_num = this.CheckSetUp(1, this.o_N * this.o_M);
                    set_b=this.board.check_full((int) Math.floor((sb_num - 1) / this.o_M), ((sb_num-1) % this.o_M));
                }
                int set_up_b=sb_num;
                supercell_now = this.s_b.get_one_board((int) Math.floor((set_up_b - 1) / this.o_M), ((set_up_b-1) % this.o_M));

                choose_b=supercell_now.play_in_a_iboard(current_player);
                if (choose_b==-1){//rechoose big board
                    System.out.println(this.s_b);
                    set_b=false;
                }else if (choose_b==-2){//quit game
                    System.out.println("player " + this.current_player.get_name() + " give up");
                    this.current_player = (this.current_player == this.player1) ? this.player2 : this.player1;
                    System.out.println("player " + this.current_player.get_name() + " win");
                    this.finish=true;
                    System.out.println(" ");
                    System.out.println(" ");
                }


            }
            System.out.println(this.s_b);

            if (supercell_now.get_win()) {
                this.board.set_p((int) Math.floor((sb_num - 1) / this.o_M), ((sb_num- 1) % this.o_M) , current_player.get_piece());
                this.CheckWin((int) Math.floor((sb_num - 1) / this.o_M), ((sb_num - 1) % this.o_M), current_player.get_piece());
            }
            if (this.finish) {
                System.out.println("player " + this.current_player.get_name() + " win");
                this.current_player.add_win();
                this.current_player.get_win();
                System.out.println(" ");
                System.out.println(" ");
                //PrintBoard();
            } else if(this.CheckFilled()){
                System.out.println("game end,no one win");
                System.out.println(" ");
                System.out.println(" ");
            } else {
                this.current_player = (this.current_player == this.player1) ? this.player2 : this.player1;
            }
        }
            System.out.println("Do you want to play again?");
    }


}
