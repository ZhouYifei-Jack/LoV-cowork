package ttt;

import java.io.IOException;

public class Warrior extends Hero{
    public Warrior(String Name,int mana,int strength,int agility,int dexterity,int starting_money,int starting_experience)throws IOException {
        super(Name,mana,strength,agility,dexterity,starting_money,starting_experience);
        this.type="Warrior";
    }

}
