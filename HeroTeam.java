package ttt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class HeroTeam extends Team{
    private Hero hero1=null;
    private Hero hero2=null;
    private Hero hero3=null;
    private Hero battleHero;

    private int teamsize;
    private List<Hero> list=new ArrayList<>();
    private Queue<Hero> queue=new ArrayDeque<>();

    public void initTeam(int i,Hero hero){
        if (i==1){
            hero1=hero;
            list.add(hero1);
        }else if (i==2){
            hero2=hero;
            list.add(hero2);
        }else {
            hero3=hero;
            list.add(hero3);
        }
    }
    public Hero getFirst(){
        return hero1;
    }
    public Hero getHero(int i){
        if (i==1){
            return hero1;
        }else if (i==2){
            return hero2;
        }else {
            return hero3;
        }
    }
    public int getTeamsize(){
        int n=0;
        if (hero1!=null){
            if (hero1.getHp()!=0){
                n=n+1;
            }
        }
        if (hero2!=null){
            if (hero2.getHp()!=0){
                n=n+1;
            }
        }
        if (hero3!=null){
            if (hero3.getHp()!=0){
                n=n+1;
            }
        }

        return n;
    }

    @Override
    public String toString() {
        return hero1+"\n"+hero2+"\n"+hero3;
    }
    public void check_big(){
        int check_bag=0;
        while (check_bag<this.getTeamsize()){
            this.getHero(check_bag+1).getBag();
            check_bag=check_bag+1;
        }
        System.out.println("do you want to equip hero(0-yes,1-no) ");
        Generalboardgame g =new Generalboardgame();
        int c=g.CheckSetUp(0,1);
        if (c!=1){
            System.out.println("which hero");
            System.out.println(this);
            int c1=g.CheckSetUp(1,this.getTeamsize());
            this.getHero(c1).getBag();

            this.getHero(c1).equip();

        }
    }

    public int inBattle_leftHero(){
        int n=0;
        if (hero1!=null){
            if (hero1.getHp_inBattle()!=0){
                n=n+1;
            }
        }
        if (hero2!=null){
            if (hero2.getHp_inBattle()!=0){
                n=n+1;
            }
        }
        if (hero3!=null){
            if (hero3.getHp_inBattle()!=0){
                n=n+1;
            }
        }

        return n;
    }
    public Boolean inBattle_pickHero(int i){
        if (i==1){
            if (hero1.hp_inBattle!=0){
                System.out.println(hero1.get_name()+" fight");
                return true;
            }else {
                System.out.println("this hero can't fight");
                return false;
            }
        }else if (i==2){
            if (hero2.hp_inBattle!=0){
                System.out.println(hero2.get_name()+" fight");
                return true;
            }else {
                System.out.println("this hero can't fight");
                return false;
            }
        }else {
            if (hero3.hp_inBattle!=0){
                System.out.println(hero3.get_name()+" fight");
                return true;
            }else {
                System.out.println("this hero can't fight");
                return false;
            }
        }
    }
    public void prepareBattle(){
        if (hero1!=null){
            hero1.setHp_inBattle(hero1.getHp());
            hero1.setMana_inBattle(hero1.getMana());
        }
        if (hero2!=null){
            hero2.setHp_inBattle(hero2.getHp());
            hero2.setMana_inBattle(hero2.getMana());
        }
        if (hero3!=null){
            hero3.setHp_inBattle(hero3.getHp());
            hero3.setMana_inBattle(hero3.getMana());
        }

    }


}
