package ttt;

public class Weaponry extends Grocery{

    protected int requiredHands;
    protected int damage;

    public Weaponry(String name, int cost, int requiredLevel, int damage,int requiredHands) {
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
        this.damage = damage;
        this.requiredHands=requiredHands;
        this.type = "Weaponry";
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


    public int getRequiredHands() {
        return requiredHands;
    }

    public void setRequiredHands(int requiredHands) {
        this.requiredHands = requiredHands;
    }

}
