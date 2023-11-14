package ttt;

public class FireSpells extends Grocery{


    protected int damage;
    protected int manaCost;

    public FireSpells(String name, int cost, int requiredLevel, int damage,int manaCost) {
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
        this.damage = damage;
        this.manaCost=manaCost;
        this.type = "FireSpells";
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

}
