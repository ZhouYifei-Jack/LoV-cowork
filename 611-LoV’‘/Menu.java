package ttt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
 * usage of this class:
 * set player information
 * choose game
 * guide player to play game
 *
 *
 * */
public class Menu {

    private int choose;
    private Player player1;
    private Player player2;

    public Menu() {
        System.out.println("Welcome ot board game,you can play all king of board game here.");
        System.out.println("Firstly,let us set players information");
        this.player_and_pieces_set_up();
        System.out.println("set up!!");
        this.start();

    }

    public void start() {
        //this.choose=choose;
        System.out.println("Please choose a game");

        while(true) {
            System.out.println("enter 1 for TicTacToe"
                    + "\nenter 2 for OrderAndChaos:"
                    + "\nenter 3 for SuperTictactoe"
                    + "\nenter 4 for Quoridor"
                    + "\nenter 5 for exit game");
            ArrayList<String> as=new ArrayList<>(Arrays.asList("1","2","3","4","5"));
            Scanner sc_choose = new Scanner(System.in);

            String c=sc_choose.nextLine();
            if(as.contains(c)) {
                this.choose=Integer.valueOf(c).intValue();
                this.choose();
            }else {
                System.out.println("please enter a valid number");
            }
        }


    }
    public void choose() {
        if(this.choose==1) {
            System.out.println("Hello,welcome to TicTacToe");
            Tictactoe ttt=new Tictactoe();
            ttt.init(this.player1,this.player2);
        }else if(this.choose==2){
            System.out.println("Hello,welcome to OrderAndChaos");
            Orderandchaosgame oac=new Orderandchaosgame();
            oac.Init(this.player1,this.player2);
        }else if(this.choose==3) {
            System.out.println("Hello,welcome to SuperTictactoe");
            superTictactoe st=new superTictactoe();
            st.init(this.player1,this.player2);
            //System.out.println(st);
        }else if(this.choose==4){
            System.out.println("Hello,welcome to quoridor");
            Quoridor q=new Quoridor();
            q.Init(this.player1,this.player2);
        }else if(this.choose==5) {
            System.out.println("bye-bye");
            System.out.println("Player : "+this.player1.get_name());
            this.player1.get_win();
            System.out.println("Player : "+this.player2.get_name());
            this.player2.get_win();
            System.exit(0);
        }
    }

    public void player_and_pieces_set_up(){
        System.out.println("hi, player1");
        Player a=new Player();
        a.create_a_player();
        this.player1=a;

        System.out.println("hi, player2");
        Player b=new Player();
        b.create_a_player();
        this.player2=b;
        System.out.println("OK,set up"
                + "\n");

        boolean piece_different=false;
        Generalboardgame m=new Generalboardgame();
        Pieces p1 = new Pieces();
        System.out.println("set piece for "+this.player1.get_name());
        p1.createPtest();
        System.out.println("what color of piece "+this.player1.get_name()+" want?(-1_default,0_red,1_yellow,2_blue)");
        int c_color1=m.CheckSetUp(-1,2);
        p1.set_color(c_color1);
        System.out.println("set up");
        System.out.println("set piece for "+this.player2.get_name());
        while (!piece_different){

            Pieces p2 = new Pieces();
            p2.createPtest();
            if (!p1.get_o_p().equals(p2.get_o_p())){
                piece_different=true;
                a.set_pieces(p1);
                b.set_pieces(p2);
                System.out.println("what color of piece of player "+this.player1.get_name()+" want?(-1_default,0_red,1_yellow,2_blue)");
                int c_color2=m.CheckSetUp(-1,2);
                p2.set_color(c_color2);
            }else {
                System.out.println("pieces should not be same,enter again for the second piece");
            }
        }



    }


}
