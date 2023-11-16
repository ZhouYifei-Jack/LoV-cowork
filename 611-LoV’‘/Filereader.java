package ttt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Filereader {
    public static String[] reader(String filename) throws IOException{

        BufferedReader txtreader=new BufferedReader(new FileReader(filename));
        ArrayList<String> arrayList=new ArrayList<>();
        String info_row;
        while ((info_row=txtreader.readLine())!=null){
            arrayList.add(info_row);
        }
        String[] result = new String[arrayList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = arrayList.get(i);
        }
        txtreader.close();
        return result;
    }
    public static Hero[] ListOfHeroes() throws IOException {
        String[] Warrior = reader("info/Warriors.txt");
        String[] Sorcerer = reader("info/Sorcerers.txt");
        String[] Paladin = reader("info/Paladins.txt");

        Hero[] hero = new Hero[Warrior.length + Sorcerer.length + Paladin.length-3 ];

        String[] splitRow;
        int Number = 0;
        for (int i = 1; i < Warrior.length; i++) {
            splitRow = Warrior[i].split("\\s+");
            hero[Number] = new Warrior(splitRow[0], Integer.parseInt(splitRow[1]), Integer.parseInt(splitRow[2]), Integer.parseInt(splitRow[3]), Integer.parseInt(splitRow[4]), Integer.parseInt(splitRow[5]), Integer.parseInt(splitRow[6]));
            Number++;
        }
        for (int i = 1; i < Sorcerer.length; i++) {
            //System.out.println(Sorcerer[i]);

            splitRow = Sorcerer[i].split("\\s+");
            hero[Number] = new Sorcerer(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), Integer.valueOf(splitRow[4]), Integer.valueOf(splitRow[5]), Integer.valueOf(splitRow[6]));
            Number++;
        }
        for (int i = 1; i < Paladin.length; i++) {
            splitRow = Paladin[i].split("\\s+");
            hero[Number] = new Paladin(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), Integer.valueOf(splitRow[4]), Integer.valueOf(splitRow[5]), Integer.valueOf(splitRow[6]));
            Number++;
        }
        return hero;
    }

    public static Grocery[] ListOfGroceries() throws IOException {
        String[] Armory = reader("info/Armory.txt");
        String[] Weapon = reader("info/Weaponry.txt");
        String[] Potion = reader("info/Potions.txt");
        String[] IceSpell =reader("info/IceSpells.txt");
        String[] FireSpell = reader("info/FireSpells.txt");
        String[] LightningSpell =reader("info/LightningSpells.txt");

        Grocery[] G = new Grocery[Armory.length + Weapon.length + Potion.length + IceSpell.length + FireSpell.length + LightningSpell.length - 6];
        String[] splitRow;
        int resultIndex = 0;
        for (int i = 1; i < Armory.length; i++) {
            splitRow = Armory[i].split("\\s+");
            G[resultIndex] = new Armory(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]));
            resultIndex++;
        }
        for (int i = 1; i < Weapon.length; i++) {
            splitRow = Weapon[i].split("\\s+");
            G[resultIndex] = new Weaponry(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]),Integer.valueOf(splitRow[4]));
            resultIndex++;
        }
        for (int i = 1; i < Potion.length; i++) {
            splitRow = Potion[i].split("\\s+");
            G[resultIndex] = new Potions(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), splitRow[4]);
            resultIndex++;
        }
        for (int i = 1; i < FireSpell.length; i++) {
            splitRow = FireSpell[i].split("\\s+");
            G[resultIndex] = new FireSpells(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), Integer.valueOf(splitRow[4]));
            resultIndex++;
        }
        for (int i = 1; i < IceSpell.length; i++) {
            splitRow = IceSpell[i].split("\\s+");
            G[resultIndex] = new IceSpells(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), Integer.valueOf(splitRow[4]));
            resultIndex++;
        }
        for (int i = 1; i < LightningSpell.length; i++) {
            splitRow = LightningSpell[i].split("\\s+");
            G[resultIndex] = new LightningSpells(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), Integer.valueOf(splitRow[4]));
            resultIndex++;
        }
        return G;

    }


    public static Monster[] ListOfMonsters() throws IOException {
        String[] Dragons = reader("info/Dragons.txt");
        String[] Exoskeletons = reader("info/Exoskeletons.txt");

        Monster[] monster = new Monster[Dragons.length + Exoskeletons.length -2 ];

        String[] splitRow;
        int Number = 0;
        for (int i = 1; i < Dragons.length; i++) {
            splitRow = Dragons[i].split("\\s+");
            monster[Number] = new Dragons(splitRow[0], Integer.parseInt(splitRow[1]), Integer.parseInt(splitRow[2]), Integer.parseInt(splitRow[3]), Integer.parseInt(splitRow[4]));
            Number++;
        }
        for (int i = 1; i < Exoskeletons.length; i++) {
            //System.out.println(Sorcerer[i]);

            splitRow = Exoskeletons[i].split("\\s+");
            monster[Number] = new Exoskeletons(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), Integer.valueOf(splitRow[4]));
            Number++;
        }

        return monster;
    }


}
