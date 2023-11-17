package ttt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LegendsofValorgame extends Heroandmonstergame{
    private Random random;
    protected List<Hero> PlayerOfHeroList=new ArrayList<>();
    private MonsterTeam monsterTeam;




    public void start() throws IOException {
        System.out.println("Welcome to Legends of Valor");
        this.initHero();
        this.initMonster();



        ///////
        this.current_player=heroTeam.getHero(1);
        ///////



        this.randomizeBoard((Hero) current_player,this.c_team_hero);
        //update these two line when moving
        //this.board.set_p(this.total_N-1,0,current_player.get_piece());
        //this.actualBoard.set_p(this.total_N-1,0,current_player.get_piece());
        System.out.println(board.toString());



        while (!this.finish){
            boolean is_move = false;
            while (!is_move) {
                System.out.println("select hero");

                int c_h=this.CheckSetUp(1,heroTeam.getTeamsize());
                current_player=heroTeam.getHero(c_h);
                System.out.println("hi,"  + this.current_player.get_name()  + " enter:" +
                        "\n1 for up" +
                        "\n2 for down" +
                        "\n3 for left" +
                        "\n4 for right" +
                        "\n5 for check bag");
                int c = this.CheckSetUp(1, 5);


                if (c==5){
                    heroTeam.check_big();
                }else if (check_direction(c, current_player.getPlayer_current_row(), current_player.getPlayer_current_col())) {
                    System.out.println("hero moving");
                    move_hero(c,current_player.getPlayer_current_row(),current_player.getPlayer_current_col(),actualBoard);
                    System.out.println(board);
                    is_move=true;
                    if(actualBoard.position_info(current_player.getPlayer_current_row(),current_player.getPlayer_current_col()).equals("M")){
                        System.out.println("enter market?(0-enter,1-no)");
                        int c_cm=CheckSetUp(0,1);
                        if (c_cm==0){
                            this.shopping(heroTeam);
                        }
//                }else {
//                    random=new Random();
//                    float roll = random.nextFloat();
//                    if(roll<0.7){
//                        Battle b=new Battle();
//                        b.init(heroTeam);
//                    }

                    }
                } else {
                    System.out.println("Cannot move to that direction, please choose again.");
                }
            }

            //after hero moving,check whether the hero go to the monsters' nest
            if (this.check_win(current_player)){
                System.out.println("Heroes win");
                this.finish=true;
            }else {
                //monster move
                this.current_player=monsterTeam.randompickmonster();
                //monster moving:first check moving down,if can't,moving right...
                System.out.println("monster moving(monster is picked by this rule:m1 will keep moving until be killed,then m2,then m3)");
                if (check_direction(2, current_player.getPlayer_current_row(), current_player.getPlayer_current_col())) {
                    move_hero(2,current_player.getPlayer_current_row(),current_player.getPlayer_current_col(),actualBoard);
                    System.out.println(board);
                }else if (check_direction(4, current_player.getPlayer_current_row(), current_player.getPlayer_current_col())) {
                    move_hero(4,current_player.getPlayer_current_row(),current_player.getPlayer_current_col(),actualBoard);
                    System.out.println(board);
                }else if (check_direction(3, current_player.getPlayer_current_row(), current_player.getPlayer_current_col())) {
                    move_hero(3,current_player.getPlayer_current_row(),current_player.getPlayer_current_col(),actualBoard);
                    System.out.println(board);
                }else if (check_direction(1, current_player.getPlayer_current_row(), current_player.getPlayer_current_col())) {
                    move_hero(1,current_player.getPlayer_current_row(),current_player.getPlayer_current_col(),actualBoard);
                    System.out.println(board);
                }

            }

            ////
            //Random random1=new Random();
            //this.current_player=monsterTeam.randompickmonster();
            //move
        }

    }
    public void initHero() throws IOException {
        this.total_N=8;

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

                hero.setPlayer_current_row(this.total_N-1);
                hero.setPlayer_current_col((c_hero_round-1)*3);
                hero.setFinish_position_for_player(0);

                heroTeam.initTeam(c_hero_round,hero);
                c_hero_round=c_hero_round+1;

            }

        }


        System.out.println(" ");
        System.out.println("team information:");
        System.out.println(heroTeam);


        this.board=new Board();
        this.total_M=this.c_team_hero*3;
        board.SetBoardSize(this.total_N,this.total_M);
        this.actualBoard=new Board();
        actualBoard.SetBoardSize(total_N,total_M);



    }
    public void initMonster() throws  IOException{
        int c_team_hero=heroTeam.getTeamsize();
        System.out.println("Because you choose "+c_team_hero+" heroes in LoV,");
        System.out.println("you will fight " + c_team_hero + "  monsters.");
        System.out.println("random create monster...please wait:");
        System.out.println();
        Monster[] MonsterList = Filereader.ListOfMonsters();
        System.out.println("   Name/level/damage/defense/dodge chance/Type");
        for (int i = 1; i <= MonsterList.length; i++) {
            System.out.print(i + ". ");
            System.out.println(MonsterList[i-1]);
        }
        System.out.println(" ");
        System.out.println("random create monster...please wait:");


        int c_monster_round=1;
        List avoid_repeat=new ArrayList();
        this.monsterTeam=new MonsterTeam();
        while (c_monster_round<=c_team_hero){
            Random rand = new Random();
            int min = 1;
            int max = MonsterList.length;
            int c_h= rand.nextInt((max - min) + 1) + min;


            if (avoid_repeat.contains(c_h)){
                System.out.println("wait");
            }else {
                avoid_repeat.add(c_h);
                HeroFactory heroFactory=new HeroFactory();
                Monster monster=heroFactory.makemonster(MonsterList[c_h-1].getType(),MonsterList[c_h-1].getName(),MonsterList[c_h-1].getLevel(),MonsterList[c_h-1].getDamage(),MonsterList[c_h-1].getDefense(),MonsterList[c_h-1].getDodgechance());
                monster.setPlayer_current_row(0);
                monster.setPlayer_current_col((c_monster_round-1)*3+1);



                monsterTeam.initTeam(c_monster_round,monster);
                c_monster_round=c_monster_round+1;

            }

        }
        System.out.println("Monster team information:");
        System.out.println(monsterTeam);
    }
    private void randomizeBoard(Hero hero, int num_heros) throws IOException {
        for (int i = 0; i < board.getN(); i++) {
            for (int j = 0; j < board.getM(); j++) {
                Pieces pieces = new Pieces();

                // For the bottom row
                if (i == this.total_N - 1) {
                    if ((j + 1) % 3 == 0) {
                        // Place a block at every third position
                        Block block = new Block();
                        pieces.setPtest(block.getmahBlock());
                    } else {
                        // Alternate between Hero and Market in other positions
                        if (j % 3 == 0) {
                            pieces.setPtest("H"); // Hero
                        } else {
                            Market market = new Market();
                            pieces.setPtest(market.getmahMarket()); // Market
                        }
                    }
                } else if(i==0){
                    if((j-1)%3==0){
                        pieces.setPtest("X");
                    }else if((j + 1) % 3 == 0){
                        Block block = new Block();
                        pieces.setPtest(block.getmahBlock());
                    }else {
                        pieces.setPtest(" ");
                    }

                }else {
                    // Randomization or other logic for the rest of the board
                    random = new Random();

                    float roll = random.nextFloat();
                    if ((j + 1) % 3 == 0) {
                        Block block = new Block();
                        pieces.setPtest(block.getmahBlock());
                    } else {
                        if (roll < 0.20) {
                            pieces.setPtest("a");
                        } else if (roll < 0.50) {
                            pieces.setPtest("b");
                        } else {
                            pieces.setPtest(" ");
                        }
                    }
                }

                this.board.set_p(i, j, pieces);
                this.actualBoard.set_p(i, j, pieces);
            }
        }
    }
}
