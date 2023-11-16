package ttt;
/*
 * usage of this class:
 * game of ttt
 *
 *
 *
 * */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Tictactoe extends Generalboardgame{
    public void init(Player player1,Player player2) {
        this.player1=player1;
        this.player2=player2;
        //set board for ttt
        System.out.println("set N,please enter a number(from 3 to 10):");
        this.N=this.CheckSetUp(3,10);
        System.out.println("set M,please enter a number(from 3 to 10):");
        this.M=this.CheckSetUp(3,10);

        Board t_b=new Board();
        this.board=t_b;
        this.board.SetBoardSize(this.N,this.M);
        this.board.print_with_location();




        //set win condition
        System.out.println("set win_condition,it should <= Shortest length of board");/////////////////////补充循环判断
        boolean w_valid=false;
        ArrayList<String> w_s=new ArrayList<>(Arrays.asList("3","4","5","6","7","8","9","10"));
        while(!w_valid){
            Scanner sc_w=new Scanner(System.in);
            String jadge_w=sc_w.nextLine();
            if (w_s.contains(jadge_w)){
                if (Integer.valueOf(jadge_w).intValue()<=Math.min(this.N,this.M)){
                    this.wincondition=Integer.valueOf(jadge_w).intValue()-1;
                    w_valid=true;
                    System.out.println("win_condition set up,win if "+Integer.valueOf(jadge_w).intValue()+" pieces in a line");
                }else {
                    System.out.println("invalid,too big");
                }
            }else {
                System.out.println("invalid");
            }

        }


        this.StartPlay();

    }
    public void StartPlay() {


        this.current_player=this.player1;
        //this.finish=false;
        //this.sclocation=new Scanner(System.in);

        System.out.println("Start play!!");
        this.board.print_with_location();
        while(!this.finish) {
            //PrintBoard();
            //check if the coordinate is legal
            int[]coord=new int[2];
            coord=this.CheckValidationOfCoordinate(-1);
            int row=coord[0];
            int col=coord[1];
            this.board.set_p(row,col,this.current_player.get_piece());
            this.board.print_with_location();
            this.CheckWin(row, col,this.current_player.get_piece());

            if(this.finish) {
                System.out.println("player "+this.current_player.get_name()+" win");
                this.current_player.add_win();
                this.current_player.get_win();
                System.out.println(" ");
                System.out.println(" ");
                //PrintBoard();
            }else if(this.CheckFilled()){
                System.out.println("game end,no one win");
                System.out.println(" ");
                System.out.println(" ");
            }else{
                this.current_player=(this.current_player==this.player1)?this.player2:this.player1;
            }
        }
        System.out.println("Do you want to play again?");

    }

    public void set_board_for_sttt(Board board){
        this.board=board;
        this.N=board.getN();
        this.M=board.getM();
    }

}
