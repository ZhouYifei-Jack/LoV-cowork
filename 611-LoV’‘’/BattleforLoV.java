package ttt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleforLoV {
    private HeroTeam heroTeam;
    private Hero currentHero;
    private Monster currentMonster;
    private MonsterTeam monsterTeam;
    private boolean finish=false;

    public void init(HeroTeam heroTeam) throws IOException {
        this.heroTeam=heroTeam;
        this.heroTeam.prepareBattle();


        int c_team_hero=heroTeam.getTeamsize();

        System.out.println("Because you choose "+c_team_hero+" heroes in LoV,");
        System.out.println("you will fight " + c_team_hero + "  monsters.");
        int c_monster_round=1;
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

                monsterTeam.initTeam(c_monster_round,monster);
                c_monster_round=c_monster_round+1;

            }

        }


        System.out.println("Monster team information:");
        System.out.println(monsterTeam);

        pk();
    }


    public void pk(){
        pk:while (heroTeam.inBattle_leftHero()!=0&!finish){
            boolean isThisHeroAlive=false;
            while (!isThisHeroAlive){
                System.out.println(" ");
                System.out.println("pick a hero ");
                System.out.println(heroTeam);
                Generalboardgame g =new Generalboardgame();
                int b_h=g.CheckSetUp(1,heroTeam.getTeamsize());
                isThisHeroAlive=heroTeam.inBattle_pickHero(b_h);
                if (isThisHeroAlive){
                    currentHero=heroTeam.getHero(b_h);
                }
            }




            Boolean made_choose=false;
            while (!made_choose){
                System.out.println(currentHero);
                System.out.println("1-attack" +
                        "\n2-escape" +
                        "\n0- check bag to use spells or potions");
                Generalboardgame g =new Generalboardgame();
                int h_c=g.CheckSetUp(0,2);

                if (h_c==1){
                    made_choose=this.attack();
                }else if(h_c==2) {
                    System.out.println("escaped");
                    break pk;
                }else {
                    made_choose=this.equip();
                }
            }
            if(monsterTeam.inBattle_leftMonster()==0){
                System.out.println("heroes win");
                //
                finish=true;
            }else {
                currentMonster=monsterTeam.randompickmonster();
                System.out.println(" ");
                System.out.println("monster "+currentMonster.getName()+" now attack");
                currentHero.attacked(currentMonster.attack());
                //monsterTeam.getMonster(i).attack();
                if(heroTeam.inBattle_leftHero()==0){
                    System.out.println("monsters win");
                }


            }





        }
        System.out.println("battle end");
    }


    public Boolean attack(){
        boolean isThisMAlive=false;
        while (!isThisMAlive){
            System.out.println("attack which monster");
            System.out.println(monsterTeam);
            Generalboardgame g =new Generalboardgame();
            int b_h=g.CheckSetUp(1,heroTeam.getTeamsize());
            isThisMAlive=monsterTeam.inBattle_pickMonster(b_h);
            if (isThisMAlive){
                currentMonster=monsterTeam.getMonster(b_h);
            }
        }
        currentMonster.attacked(currentHero.attack());


        System.out.println(monsterTeam);
        return true;
    }

    public Boolean attackwithspell(FireSpells a){
        boolean isThisMAlive=false;
        while (!isThisMAlive){
            System.out.println("attack which monster");
            System.out.println(monsterTeam);
            Generalboardgame g =new Generalboardgame();
            int b_h=g.CheckSetUp(1,heroTeam.getTeamsize());
            isThisMAlive=monsterTeam.inBattle_pickMonster(b_h);
            if (isThisMAlive){
                currentMonster=monsterTeam.getMonster(b_h);
            }
        }
        currentMonster.attacked(a.getDamage());


        System.out.println(monsterTeam);
        return true;
    }
    public Boolean attackwithspell(IceSpells a){
        boolean isThisMAlive=false;
        while (!isThisMAlive){
            System.out.println("attack which monster");
            System.out.println(monsterTeam);
            Generalboardgame g =new Generalboardgame();
            int b_h=g.CheckSetUp(1,heroTeam.getTeamsize());
            isThisMAlive=monsterTeam.inBattle_pickMonster(b_h);
            if (isThisMAlive){
                currentMonster=monsterTeam.getMonster(b_h);
            }
        }
        currentMonster.attacked(a.getDamage());


        System.out.println(monsterTeam);
        return true;
    }
    public Boolean attackwithspell(LightningSpells a){
        boolean isThisMAlive=false;
        while (!isThisMAlive){
            System.out.println("attack which monster");
            System.out.println(monsterTeam);
            Generalboardgame g =new Generalboardgame();
            int b_h=g.CheckSetUp(1,heroTeam.getTeamsize());
            isThisMAlive=monsterTeam.inBattle_pickMonster(b_h);
            if (isThisMAlive){
                currentMonster=monsterTeam.getMonster(b_h);
            }
        }
        currentMonster.attacked(a.getDamage());


        System.out.println(monsterTeam);
        return true;
    }
    public Boolean equip(){
        System.out.println("ok");
        System.out.println("what to use" +
                "\nenter No. of equipment to use(only for spells and potions) " +
                "\nenter 0   to back to choose attack or use bag(in case no spells or potions)");
        currentHero.getBag();
        Generalboardgame g=new Generalboardgame();
        int c_equip=g.CheckSetUp(0,currentHero.use_bag_inBattle().size());

        if(c_equip!=0){
//            if (currentHero.use_bag_inBattle().get(c_equip-1).getType().equals("potions")){
//                Potions a=(Potions) currentHero.use_bag_inBattle().get(c_equip-1);
//                return true;
//            }else


            if(currentHero.use_bag_inBattle().get(c_equip-1).getType().contains("Spells")){
                if (currentHero.use_bag_inBattle().get(c_equip-1).getType().contains("Fire")){
                    FireSpells a=(FireSpells) currentHero.use_bag_inBattle().get(c_equip-1);
                    if(currentHero.getMana_inBattle()>=a.getManaCost()){
                        currentHero.setMana_inBattle(currentHero.getMana_inBattle()-a.getManaCost());
                        this.attackwithspell(a);
                        return true;
                    }else {
                        System.out.println("this hero no enough mana ");
                        return false;
                    }
                }else if (currentHero.use_bag_inBattle().get(c_equip-1).getType().contains("Ice")){
                    IceSpells a=(IceSpells) currentHero.use_bag_inBattle().get(c_equip-1);
                    if(currentHero.getMana_inBattle()>=a.getManaCost()){
                        currentHero.setMana_inBattle(currentHero.getMana_inBattle()-a.getManaCost());
                        this.attackwithspell(a);
                        return true;
                    }else {
                        System.out.println("this hero no enough mana ");
                        return false;
                    }
                }else{
                    LightningSpells a=(LightningSpells) currentHero.use_bag_inBattle().get(c_equip-1);
                    if(currentHero.getMana_inBattle()>=a.getManaCost()){
                        currentHero.setMana_inBattle(currentHero.getMana_inBattle()-a.getManaCost());
                        this.attackwithspell(a);
                        return true;
                    }else {
                        System.out.println("this hero no enough mana ");
                        return false;
                    }
                }

            }else if (currentHero.use_bag_inBattle().get(c_equip-1).getType().contains("Potion")){
                Potions a=(Potions) currentHero.use_bag_inBattle().get(c_equip-1);
                if(currentHero.getLevel()>=a.getRequiredLevel()){

                    currentHero.drinkpotion(a);
                    currentHero.use_bag_inBattle().remove(c_equip-1);


                    return true;
                }else {
                    System.out.println("this hero no enough level ");
                    return false;
                }
            } else{
                System.out.println("this is not for used in battle");
                return false;
            }



        }else {
            return false;
        }

    }
}
