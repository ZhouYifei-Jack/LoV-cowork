package ttt;

public abstract class Monster extends Pieces{
    private String Name;
    private int level;
    private int damage;
    private int defense;
    private int dodgechance;
    protected String type;

    //private int inBattle_damage;

    public Monster(String Name,int level,int damage,int defense,int dodgechance){
        this.Name=Name;
        this.level=level;
        this.damage=damage;
        this.defense=defense;
        this.dodgechance=dodgechance;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDodgechance() {
        return dodgechance;
    }

    public void setDodgechance(int dodgechance) {
        this.dodgechance = dodgechance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString(){
        String a="";
        a=a+"Monster name:"+this.Name+", level:"+level+", damage:"+damage+", defense:"+defense+" ,dodgeChance:"+dodgechance+", type:"+type;
        return a;
    }

    public int attack(){
        return damage;
    }
    public void attacked(int s){
        defense=defense-s;
        if (defense<=0){
            defense=0;
            System.out.println("this monster is killed");
        }
    }
}
