package ttt;

import java.io.IOException;
import java.util.*;

public class Market extends Pieces{
    private List<Grocery> storage=new ArrayList<>();

    public String getmahMarket(){
        return "M";
    }
    public void shopping(HeroTeam heroTeam) throws IOException {

        System.out.println("shopping");
        Grocery[] List = Filereader.ListOfGroceries();

        int n_g=1;
        Random random=new Random();
        for (int i = 1; i <= List.length; i++) {
            float roll = random.nextFloat();
            if(roll<0.4){
                System.out.print(n_g + ". ");
                n_g=n_g+1;
                if (List[i-1].getType().equals("Armory")){
                    Grocery firstItem = List[i-1];
                    if (firstItem instanceof Armory) {
                        Armory armory = (Armory) firstItem;
                        storage.add(armory);
                        System.out.println(armory.getType()+":"+armory.getName()+" cost:"+armory.getCost()+" requiredLevel:"+armory.getRequiredLevel()+" damageReduction:"+armory.getDamageReduction());
                    }
                }
                if (List[i-1].getType().equals("Weaponry")){
                    Grocery firstItem = List[i-1];
                    if (firstItem instanceof Weaponry) {
                        Weaponry a = (Weaponry) firstItem;
                        storage.add(a);
                        System.out.println(a.getType()+":"+a.getName()+" cost:"+a.getCost()+" requiredLevel:"+a.getRequiredLevel()+" damage:"+a.getDamage()+" requiredHands:"+a.getRequiredHands());
                    }
                }
                if (List[i-1].getType().equals("Potions")){
                    Grocery firstItem = List[i-1];
                    if (firstItem instanceof Potions) {
                        Potions a = (Potions) firstItem;
                        storage.add(a);
                        System.out.println(a.getType()+":"+a.getName()+" cost:"+a.getCost()+" requiredLevel:"+a.getRequiredLevel()+" increase:"+a.getIncrease()+" affected:"+a.getAffected());
                    }
                }
                if (List[i-1].getType().equals("FireSpells")){
                    Grocery firstItem = List[i-1];
                    if (firstItem instanceof FireSpells) {
                        FireSpells a = (FireSpells) firstItem;
                        storage.add(a);
                        System.out.println(a.getType()+":"+a.getName()+" cost:"+a.getCost()+" requiredLevel；"+a.getRequiredLevel()+" damage:"+a.getDamage()+" manaCost:"+a.getManaCost());
                    }
                }
                if (List[i-1].getType().equals("IceSpells")){
                    Grocery firstItem = List[i-1];
                    if (firstItem instanceof IceSpells) {
                        IceSpells a = (IceSpells) firstItem;
                        storage.add(a);
                        System.out.println(a.getType()+":"+a.getName()+" cost:"+a.getCost()+" requiredLevel；"+a.getRequiredLevel()+" damage:"+a.getDamage()+" manaCost:"+a.getManaCost());
                    }
                }
                if (List[i-1].getType().equals("LightningSpells")){
                    Grocery firstItem = List[i-1];
                    if (firstItem instanceof LightningSpells) {
                        LightningSpells a = (LightningSpells) firstItem;
                        storage.add(a);
                        System.out.println(a.getType()+":"+a.getName()+" cost:"+a.getCost()+" requiredLevel；"+a.getRequiredLevel()+" damage:"+a.getDamage()+" manaCost:"+a.getManaCost());
                    }
                }

            }


        }
        System.out.println("Now pick a grocery,please(enter No. for shopping,enter 0 for quit,-1 for sell):");
        Generalboardgame c=new Generalboardgame();
        int p_g=c.CheckSetUp(-1,storage.size());

        while(p_g!=0){
            if(p_g==-1){
                System.out.println("which hero want to sell equipment(enter 1 or 2 or 3):");
                System.out.println(heroTeam);
                int p_h=c.CheckSetUp(1,heroTeam.getTeamsize());
                heroTeam.getHero(p_h).sellgrocery();

                System.out.println("Now pick a grocery,please(enter No. for shopping,enter 0 for quit,-1 for sell):");
                p_g=c.CheckSetUp(-1,storage.size());
            }else {
                System.out.println("which hero buy(enter 1 or 2 or 3):");
                System.out.println(heroTeam);
                int p_h=c.CheckSetUp(1,heroTeam.getTeamsize());
                heroTeam.getHero(p_h).buygrocery(storage.get(p_g-1));

                System.out.println("Now pick a grocery,please(enter No. for shopping,enter 0 for quit,-1 for sell):");
                p_g=c.CheckSetUp(-1,storage.size());
            }



        }
        System.out.println("bye-bye");
        heroTeam.check_big();









    }
}
