package ttt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MonsterTeam extends Team{
    private Monster monster1 =null;
    private Monster monster2 =null;
    private Monster monster3 =null;
    private Monster battleHero;

    private int teamsize;
    private List<Monster> list=new ArrayList<>();
    private Queue<Monster> queue=new ArrayDeque<>();

    public void initTeam(int i,Monster hero){
        if (i==1){
            monster1 =hero;
            list.add(monster1);
        }else if (i==2){
            monster2 =hero;
            list.add(monster2);
        }else {
            monster3 =hero;
            list.add(monster3);
        }
    }
    public Monster getFirst(){
        return monster1;
    }
    public Monster getMonster(int i){
        if (i==1){
            return monster1;
        }else if (i==2){
            return monster2;
        }else {
            return monster3;
        }
    }


    @Override
    public String toString() {
        return monster1 +"\n"+ monster2 +"\n"+ monster3;
    }


    public int inBattle_leftMonster(){
        int n=0;
        if (monster1 !=null){
            if (monster1.getDefense()!=0){
                n=n+1;
            }
        }
        if (monster2 !=null){
            if (monster2.getDefense()!=0){
                n=n+1;
            }
        }
        if (monster3 !=null){
            if (monster3.getDefense()!=0){
                n=n+1;
            }
        }

        return n;
    }
    public Boolean inBattle_pickMonster(int i){
        if (i==1){
            if (monster1.getDefense()!=0){
                System.out.println(monster1.getName()+" be attacked");
                return true;
            }else {
                System.out.println("this monster already killed");
                return false;
            }
        }else if (i==2){
            if (monster2.getDefense()!=0){
                System.out.println(monster2.getName()+" be attacked");
                return true;
            }else {
                System.out.println("this monster already killed");
                return false;
            }
        }else {
            if (monster3.getDefense()!=0){
                System.out.println(monster3.getName()+" be attacked");
                return true;
            }else {
                System.out.println("this monster already killed");
                return false;
            }
        }
    }
    public Monster randompickmonster(){
        if (monster1!=null&monster1.getDefense()!=0){
            return monster1;
        }else if (monster2!=null&monster2.getDefense()!=0){
            return monster2;
        }else if (monster3!=null&monster3.getDefense()!=0){
            return monster3;
        }
        return monster1;
    }

}
