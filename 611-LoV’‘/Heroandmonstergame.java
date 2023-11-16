package ttt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Heroandmonstergame extends Quoridor{
    protected Random random;
    protected HeroTeam heroTeam;
    protected int c_team_hero;

    public void init() throws IOException {

        System.out.println("how many heroes you want in the team(enter 1 or 2 or 3)");
        c_team_hero=this.CheckSetUp(1,3);

        System.out.println("Here is the list of heroes and their starting statistics. You make pick " + c_team_hero + "  heroes.");
        int c_hero_round=1;
        System.out.println("Please note that the order in which you pick your heroes will be the order in which they fight in battle");
        System.out.println();

        Hero[] heroList = Filereader.ListOfHeroes();
        System.out.println("   Name/mana/strength/agility/dexterity/starting money/starting experience/Type");
        for (int i = 1; i <= heroList.length; i++) {
            System.out.print(i + ". ");
            System.out.println(heroList[i-1].get_name()+" "+heroList[i-1].getMana()+" "+heroList[i-1].getStrength()+" "+heroList[i-1].getAgility()+" "+heroList[i-1].getDexterity()+" "+heroList[i-1].getStarting_money()+" "+heroList[i-1].getExp()+" "+heroList[i-1].getType());
        }
        System.out.println("Now pick a hero,please:");

        List avoid_repeat=new ArrayList();
        this.heroTeam=new HeroTeam();
        while (c_hero_round<=c_team_hero){
            int c_h=this.CheckSetUp(1,heroList.length+1);

            if (avoid_repeat.contains(c_h)){
                System.out.println("has this hero,choose again");
            }else {
                avoid_repeat.add(c_h);
                System.out.println(heroList[c_h-1].get_name()+" "+heroList[c_h-1].getMana()+" "+heroList[c_h-1].getStrength()+" "+heroList[c_h-1].getAgility()+" "+heroList[c_h-1].getDexterity()+" "+heroList[c_h-1].getStarting_money()+" "+heroList[c_h-1].getExp());

                HeroFactory heroFactory=new HeroFactory();
                Hero hero=heroFactory.makeHero(heroList[c_h-1].getType(),heroList[c_h-1].get_name(),heroList[c_h-1].getMana(),heroList[c_h-1].getStrength(),heroList[c_h-1].getAgility(),heroList[c_h-1].getDexterity(),heroList[c_h-1].getStarting_money(),heroList[c_h-1].getExp());


                heroTeam.initTeam(c_hero_round,hero);
                c_hero_round=c_hero_round+1;

            }

        }


        System.out.println(" ");
        System.out.println("team information:");
        System.out.println(heroTeam);
        this.current_player=heroTeam.getFirst();


        //Warrior warrior=new Warrior(heroList[c_h-1].get_name(),heroList[c_h-1].getMana(),heroList[c_h-1].getStrength(),heroList[c_h-1].getAgility(),heroList[c_h-1].getDexterity(),heroList[c_h-1].getStarting_money(),heroList[c_h-1].getExp());

        ///////////////up can be reuse



    }
    public void start() throws IOException {
        System.out.println("Welcome to Heroes and Monsters");
        this.init();

        this.board=new Board();
        board.SetBoardSize(8,8);
        this.total_M=8;
        this.total_N=8;
        this.actualBoard=new Board();
        actualBoard.SetBoardSize(8,8);

        //current_player=warrior;
        current_player.setPlayer_current_row(0);
        current_player.setPlayer_current_col(0);
        this.randomizeBoard((Hero) current_player);

        boolean is_move = false;
        while (!is_move) {
            System.out.println(board);
            System.out.println("hi,"  + this.current_player.get_piece().getPiece_Ptest()  + " enter:" +
                    "\n1 for up" +
                    "\n2 for down" +
                    "\n3 for left" +
                    "\n4 for right" +
                    "\n5 for check bag");
            int c = this.CheckSetUp(1, 5);


            if (c==5){
                heroTeam.check_big();
            }else if (check_direction(c, current_player.getPlayer_current_row(), current_player.getPlayer_current_col())) {
                move_hero(c,current_player.getPlayer_current_row(),current_player.getPlayer_current_col(),actualBoard);
                System.out.println(board);
                if(actualBoard.position_info(current_player.getPlayer_current_row(),current_player.getPlayer_current_col()).equals("M")){
                    System.out.println("enter market?(0-enter,1-no)");
                    int c_cm=CheckSetUp(0,1);
                    if (c_cm==0){
                        this.shopping(heroTeam);
                    }
                }else {
                    random=new Random();
                    float roll = random.nextFloat();
                    if(roll<0.7){
                        Battle b=new Battle();
                        b.init(heroTeam);
                    }

                }
            } else {
                System.out.println("Cannot move to that direction, please choose again.");
            }

        }

    }
    public void shopping(HeroTeam heroTeam) throws IOException {
        System.out.println("welcome to market");
        Market market=new Market();
        market.shopping(heroTeam);
        System.out.println(board);

    }
    public boolean check_direction(int i, int now_i, int now_j) {
        if (i == 1) {//up
            if (now_i - 1 >= 0) {
                return !this.board.position_info(now_i - 1, now_j).equals("/");
            } else {
                return false;
            }

        } else if (i == 2) {//down
            if (now_i + 1 <= this.total_N - 1) {
                return !this.board.position_info(now_i + 1, now_j).equals("/");
            } else {
                return false;
            }

        } else if (i == 3) {//left
            if (now_j - 1 >= 0) {
                return !this.board.position_info(now_i, now_j - 1).equals("/");
            } else {
                return false;
            }

        } else if (i == 4) {//right
            if (now_j + 1 <= this.total_M - 1) {
                return !this.board.position_info(now_i, now_j + 1).equals("/");
            } else {
                return false;
            }
        }
        return false;
    }
    private void randomizeBoard(Hero hero) throws IOException {
        for (int i = 0; i < board.getN(); i++) {
            for (int j = 0; j < board.getM(); j++) {

                random=new Random();
                float roll = random.nextFloat();
                int[] coord = new int[] {i,j};
                Pieces pieces=new Pieces();
                if (roll < 0.20) {
                    Block block=new Block();
                    pieces.setPtest(block.getmahBlock());
                }
                else if (roll < 0.50) {
                    Market market=new Market();
                    pieces.setPtest(market.getmahMarket());
                }
                else {
                    pieces.setPtest(" ");
                }
                this.board.set_p(i,j,pieces);
                this.actualBoard.set_p(i,j,pieces);

            }
        }
        this.board.set_p(0,0,hero.get_piece());
        this.actualBoard.set_p(0,0,hero.get_piece());
        //actualBoard(位于quoridor protected)不动，当h移走，判断actualBoard==M？是则重新Market market=new Market();pieces.setPtest(market.getmahMarket());




    }

    public void move_hero(int i, int now_i, int now_j,Board actualBoard) {
        if (i == 1) {//up
            board.clear_p(now_i, now_j);
            board.set_p(now_i - 1, now_j, this.current_player.get_piece());
            this.current_player.setPlayer_current_row(now_i - 1);
        } else if (i == 2) {//down
            board.clear_p(now_i, now_j);
            board.set_p(now_i + 1, now_j, this.current_player.get_piece());
            this.current_player.setPlayer_current_row(now_i + 1);
        } else if (i == 3) {//left
            board.clear_p(now_i, now_j);
            board.set_p(now_i, now_j - 1, this.current_player.get_piece());
            this.current_player.setPlayer_current_col(now_j - 1);
        } else if (i == 4) {//right
            board.clear_p(now_i, now_j);
            board.set_p(now_i, now_j + 1, this.current_player.get_piece());
            this.current_player.setPlayer_current_col(now_j + 1);
        }

        if (actualBoard.cell[now_i][now_j].getPtest().equals("M")){
            Pieces pieces=new Pieces();
            Market market=new Market();
            pieces.setPtest(market.getmahMarket());
            board.set_p(now_i, now_j ,pieces);

        }
    }


}
