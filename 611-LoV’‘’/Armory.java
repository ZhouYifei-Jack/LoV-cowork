package ttt;

public class Armory extends Grocery{

    protected int damageReduction;

    public Armory(String name, int cost, int requiredLevel, int damage) {
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
        this.damageReduction = damage;
        this.type = "Armory";
    }
    public int getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(int damageReduction) {
        this.damageReduction = damageReduction;
    }

}

