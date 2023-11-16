package ttt;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Hero extends Player{
    protected String Number;
    protected int mana;
    protected int strength;
    protected int agility;
    protected int dexterity;
    protected int starting_money;
    protected int exp;
    protected String type;
    protected int level;
    protected int hp;

    protected int hp_inBattle;
    protected int mana_inBattle;




    protected boolean rightHand=false;
    protected boolean leftHand=false;
    protected boolean is_armory=false;
    private int damageReduction=0;
    private int damage=0;
    private int damage_right=0;
    private int damage_left=0;

    //private Grocery[] G ;
    //private int G_index;
    private List<Grocery>bag;
    private int bagCapacity=10;

    public Hero(String Name,int mana,int strength,int agility,int dexterity,int starting_money,int starting_experience) throws IOException {
        this.name=Name;
        this.mana=mana;
        this.strength=strength;
        this.agility=agility;
        this.dexterity=dexterity;
        //this.starting_money=starting_money;
        this.starting_money=10000;//set 10000 to make test for  function in Market.class easier
        this.exp =starting_experience;
        this.level=1;
        this.hp=100;

        //this.hp_inBattle=100;
        //this.mana_inBattle=mana;

        //G=new Grocery[10];
        //G_index=0;
        this.bag=new ArrayList<>();

        Pieces p=new Pieces();
        p.setPtest("H");
        this.set_pieces(p);
    }


    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getStarting_money() {
        return starting_money;
    }

    public void setStarting_money(int starting_money) {
        this.starting_money = starting_money;
    }

    public int getExp() {
        return exp;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int updateEXP(int newEXP) {
        this.exp = newEXP;
        if (this.exp > (this.level * 10)) {
            this.levelUp();
        }
        return exp;
    }
    public void levelUp() {
        this.level += 1;
        this.exp = 0;
        this.hp = level * 100;
        this.mana *= 1.1;
        this.strength *= 1.05;
        this.agility *= 1.05;
        this.dexterity *= 1.05;
    }



    public int getLevel() {
        return level;
    }


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public void attacked(int s){
        if (s<=damageReduction){
            System.out.println(this.get_name()+" protected by armory,no damage");
        }else {
            int d=s-damageReduction;
            System.out.println(this.get_name()+" attacked,hp-"+d);
            this.setHp_inBattle(this.getHp_inBattle()-d);
            if (hp_inBattle<=0){
                setHp_inBattle(0);
                System.out.println("this hero died");
            }
        }
    }

    public boolean isRightHand() {
        return rightHand;
    }

    public void setRightHand(boolean rightHand) {
        this.rightHand = rightHand;
    }

    public boolean isLeftHand() {
        return leftHand;
    }

    public void setLeftHand(boolean leftHand) {
        this.leftHand = leftHand;
    }
    public void buygrocery(Grocery grocery){
        int cost=grocery.getCost();
        if (cost>getStarting_money()){
            System.out.println("can't buy");
        }else {
            if(bag.size()<this.bagCapacity){
                System.out.println(this.get_name()+" "+"money before buying:"+getStarting_money());
                this.starting_money=this.getStarting_money()-cost;
                bag.add(grocery);

                String type=grocery.getType();
                if (type.equals("Armory")){
                    Armory a=(Armory) grocery;
                    System.out.println("buy a Armory: "+a.getName());
                }else if (type.equals("Weaponry")){
                    Weaponry a=(Weaponry)grocery;
                    System.out.println("buy a Weaponry: "+a.getName());

                }else if (type.equals("Potions")){
                    Potions a=(Potions)grocery;
                    System.out.println("buy a Potion: " +a.getName());

                }else if (type.equals("FireSpells")){
                    FireSpells a=(FireSpells)grocery;
                    System.out.println("buy a FireSpell: "+a.getName());

                }else if (type.equals("IceSpells")){
                    IceSpells a=(IceSpells)grocery;
                    System.out.println("buy a IceSpells: "+a.getName());

                }else{
                    LightningSpells a=(LightningSpells)grocery;
                    System.out.println("buy a LightningSpell: "+a.getName());

                }
                System.out.println(this.get_name()+" "+"money after buying:"+this.getStarting_money());


                //G[G_index]=grocery;
                //G_index=G_index+1;
            }else {
                System.out.println("bag has no room");
            }

        }
    }

    public void getBag(){
        System.out.println(this.name+"'s bag has:");
        if(bag.size()==0){
            System.out.println("nothing now");
        }
        int i=0;
        while (i<bag.size()){
            int n=i+1;
            System.out.println(n+"."+bag.get(i).getName()+" "+bag.get(i).getType());
            i++;
        }
    }
    public void equip(){
        System.out.println("ok");
        System.out.println("what to equip" +
                "\n(-2-remove armory," +
                "\n -1-remove weaponry," +
                "\n  0-quit," +
                "\n  No. of equipment-equip)");
        Generalboardgame g=new Generalboardgame();
        int c_equip=g.CheckSetUp(-2,bag.size());

        if(c_equip!=0){
            if(c_equip==-2){
                removeArmory();
            }else if (c_equip==-1){
                removeWeapon();
            }else if (bag.get(c_equip-1).getType().equals("Armory")){
                Armory a=(Armory) bag.get(c_equip-1);
                equipArmory(a);

            }else if (bag.get(c_equip-1).getType().equals("Weaponry")){
                Weaponry a=(Weaponry) bag.get(c_equip-1);
                equipWeaponry(a);
            }else{
                System.out.println("not for equipment,use this in battle");
            }
        }
        System.out.println("   ");

    }
    public void equipArmory(Armory armory){
        if (!is_armory){
            if (armory.getRequiredLevel()>this.getLevel()){
                System.out.println("not satisfy level requirement");
            }else {
                this.damageReduction=armory.getDamageReduction();
                is_armory=true;
                System.out.println(this);
            }
        }else {
            System.out.println("already has armory,remove first");
        }


    }
    public void removeArmory(){
        if (!is_armory){
            System.out.println("no armory now");
        }else {
            System.out.println("move armory");
            damageReduction=0;
            is_armory=false;
            System.out.println(this);

        }
    }
    public int attack(){
        return strength+damage;
    }

    public void equipWeaponry(Weaponry weaponry){
        if (!rightHand&&!leftHand){
            System.out.println("both hands can hold weapon now");
            if (weaponry.getRequiredLevel()>this.getLevel()){
                System.out.println("but not satisfy level requirement");
            }else {
                int w_hands=weaponry.getRequiredHands();
                System.out.println("this weaponry needs "+w_hands+" hands");
                if (w_hands==2){
                    this.damage=weaponry.getDamage();
                    rightHand=true;
                    leftHand=true;
                    System.out.println(this);
                    System.out.println("equip for both hands");
                }else {
                    System.out.println("which hands want to use(0-right,1-left)");
                    Generalboardgame g=new Generalboardgame();
                    int c_hand=g.CheckSetUp(0,1);
                    if (c_hand==0){
                        this.damage=this.damage+weaponry.getDamage();
                        rightHand=true;
                        System.out.println(this);
                        System.out.println("equip for right hands");
                    }else {
                        this.damage=this.damage+weaponry.getDamage();
                        leftHand=true;
                        System.out.println(this);
                        System.out.println("equip for left hands");
                    }
                }
            }
        }else if (rightHand&&!leftHand){
            System.out.println("left hand can hold weapon now");
            if (weaponry.getRequiredLevel()>this.getLevel()){
                System.out.println("but not satisfy level requirement");
            }else{
                int w_hands=weaponry.getRequiredHands();
                System.out.println("this weaponry needs "+w_hands+" hands");
                if (w_hands==2){
                    System.out.println("no enough hands,remove weaponry first");
                }else {
                    this.damage=this.damage+weaponry.getDamage();
                    leftHand=true;
                    System.out.println(this);
                    System.out.println("equip for left hands");
                }
            }
        }else if (!rightHand&&leftHand){
            System.out.println("right hand can hold weapon now");
            if (weaponry.getRequiredLevel()>this.getLevel()){
                System.out.println("but not satisfy level requirement");
            }else{
                int w_hands=weaponry.getRequiredHands();
                System.out.println("this weaponry needs "+w_hands+" hands");
                if (w_hands==2){
                    System.out.println("no enough hands,remove weaponry first");
                }else {
                    this.damage=this.damage+weaponry.getDamage();
                    rightHand=true;
                    System.out.println(this);
                    System.out.println("equip for right hands");
                }
            }
        }else{
            System.out.println("full equipped by weapon now,remove firstly if you want to equip new weaponry");
        }
    }
    public void removeWeapon(){
        if (!rightHand&&!leftHand){
            System.out.println("no weapon now");
        }else {
            System.out.println("move weapon");
            damage=0;
            damage_left=0;
            damage_right=0;
            rightHand=false;
            leftHand=false;
        }
    }

    public void setHpAndManaBeforeBattle(){
        this.hp_inBattle=getHp();
        this.mana_inBattle=getMana();
    }
    public int getHp_inBattle() {
        return hp_inBattle;
    }

    public void setHp_inBattle(int hp_inBattle) {
        this.hp_inBattle = hp_inBattle;
    }

    public int getMana_inBattle() {
        return mana_inBattle;
    }

    public void setMana_inBattle(int mana_inBattle) {
        this.mana_inBattle = mana_inBattle;
    }

    @Override
    public String toString() {
        String a="";
        a=a+"Hero name:"+this.name+", mana:"+mana+", strength:"+strength+", agility:"+agility+" ,dexterity:"+dexterity+", money:"+starting_money+", experience:"+exp+", type:"+type
                +"\ndamage reduction:"+damageReduction+", damage added by weaponry:"+damage;
        return a;
    }

    public List<Grocery> use_bag_inBattle(){
        return bag;
    }

    public void sellgrocery(){
        System.out.println(this.name+",which to sell(enter No. for selling,0 for back)");
        this.getBag();
        Generalboardgame g=new Generalboardgame();
        int c_s=g.CheckSetUp(0,bag.size());
        if(c_s==0){
            System.out.println(" ");
        }else{
            System.out.println(bag.get(c_s-1).getName());
            System.out.println("are you sure to sell this(0-yes,1-no)");
            int c_sure=g.CheckSetUp(0,1);
            if (c_sure==1){
                System.out.println(" ");
            }else {
                System.out.println("sold "+bag.get(c_s-1).getName()+",get "+bag.get(c_s-1).getCost()+" dollar");
                this.starting_money=this.starting_money+bag.get(c_s-1).getCost();
                bag.remove(c_s-1);
            }

        }
    }

    public void drinkpotion(Potions a){
        if(this.hp_inBattle+a.getIncrease()>this.getHp()){
            this.hp_inBattle=this.getHp();
            System.out.println("recover");
        }else {
            this.setHp_inBattle(this.getHp_inBattle()+a.getIncrease());
            System.out.println("recover");
        }
    }
}
