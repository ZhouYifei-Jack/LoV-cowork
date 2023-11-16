package ttt;

public class Potions extends Grocery{
    protected int increase;


    protected String affected;

    public Potions(String name, int cost, int requiredLevel, int increase,String  affected) {
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
        this.increase = increase;
        this.affected=affected;
        this.type = "Potions";
    }
    public int getIncrease() {
        return increase;
    }

    public void setIncrease(int increase) {
        this.increase = increase;
    }

    public String getAffected() {
        return affected;
    }

    public void setAffected(String affected) {
        this.affected = affected;
    }


}
