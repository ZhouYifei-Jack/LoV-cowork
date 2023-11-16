package ttt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LegendsofValorgame extends Heroandmonstergame{
    private Random random;
    protected List<Hero> PlayerOfHeroList=new ArrayList<>();



    public void start() throws IOException {
        System.out.println("Welcome to Legends of Valor");
        this.init();


        this.board=new Board();
        this.total_N=8;
        this.total_M=this.c_team_hero*3;
        board.SetBoardSize(this.total_N,this.total_M);
        this.actualBoard=new Board();
        actualBoard.SetBoardSize(total_N,total_M);
        current_player.setPlayer_current_row(this.total_N-1);
        current_player.setPlayer_current_col(0);
        this.randomizeBoard((Hero) current_player,this.c_team_hero);

        //update these two line when moving
        this.board.set_p(this.total_N-1,0,current_player.get_piece());
        this.actualBoard.set_p(this.total_N-1,0,current_player.get_piece());

        System.out.println(board.toString());


        System.out.println("select hero");
        //this.CheckSetUp(1,);
    }
    private void randomizeBoard(Hero hero,int num_heros) throws IOException {
        for (int i = 0; i < board.getN(); i++) {
            for (int j = 0; j < board.getM(); j++) {

                random=new Random();
                float roll = random.nextFloat();
                int[] coord = new int[] {i,j};
                Pieces pieces=new Pieces();
                if ((j+1)%3==0){
                    Block block=new Block();
                    pieces.setPtest(block.getmahBlock());
                }else {
                    if (i==this.total_N-1){
                        Market market=new Market();
                        pieces.setPtest(market.getmahMarket());
                    }else {
                        ////set other cells
                        if (roll < 0.20) {
                            //Block block=new Block();
                            pieces.setPtest("a");
                        }
                        else if (roll < 0.50) {
                            //Market market=new Market();
                            pieces.setPtest("b");
                        }
                        else {
                            pieces.setPtest(" ");
                        }
                    }

                }

                this.board.set_p(i,j,pieces);
                this.actualBoard.set_p(i,j,pieces);

            }
        }


    }

}
