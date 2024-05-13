package main;

import java.util.ArrayList;
import java.util.Random;

import entity.Item;
import items.Armor;
import items.Badge;
import items.Boots;
import items.Cake;
import items.Flintlock;
import items.Guillotine;
import items.Manual;
import items.Spoon;
import items.Sword;

public class ItemHandler{

    GamePanel gp;
    UtilityTool utool = new UtilityTool();

    public int weaponType;
    final public int maxTypes = 6; //max weapons/passives
    
    //PLAYER UPGRADES
    public Item[] upgradeChoices;
    public ArrayList<Item> availableUpgrades;
    public final int MAX_WEAPONS = 6;
    public final int MAX_PASSIVES = 6;
    public final int MAX_UPGRADE_CHOICES = 4;

    //WEAPONS
    public final int SWORD_WEAPTYPE = 0;
    public final int BADGE_WEAPTYPE = 1;
    public final int GUILLOTINE_WEAPTYPE = 2;
    public final int FLINTLOCK_WEAPTYPE = 3;

    //PASSIVES
    public final int ARMOR_PASSTYPE = 0;
    public final int BOOTS_PASSTYPE = 1;
    public final int CAKE_PASSTYPE = 2;
    public final int MANUAL_PASSTYPE = 3;
    public final int SPOON_PASSTYPE = 4;
    
    public ItemHandler (GamePanel gp){
        this.gp = gp;
        upgradeChoices = new Item[MAX_UPGRADE_CHOICES];
        availableUpgrades = new ArrayList<Item>();

        for (int i = 0; i < maxTypes; i++){
            availableUpgrades.add(createPassive(i));
            availableUpgrades.add(createWeapon(i));
        }
    }

    public void setUpgrades(){
        Random rand = new Random();

        upgradeChoices[0] = (availableUpgrades.get(rand.nextInt(availableUpgrades.size())));
        Item temp1 = availableUpgrades.get(availableUpgrades.indexOf(upgradeChoices[0]));
        availableUpgrades.remove(availableUpgrades.indexOf(upgradeChoices[0]));

        upgradeChoices[1] = (availableUpgrades.get(rand.nextInt(availableUpgrades.size())));
        Item temp2 = availableUpgrades.get(availableUpgrades.indexOf(upgradeChoices[1]));
        availableUpgrades.remove(availableUpgrades.indexOf(upgradeChoices[1]));

        upgradeChoices[2] = (availableUpgrades.get(rand.nextInt(availableUpgrades.size())));
        Item temp3 = availableUpgrades.get(availableUpgrades.indexOf(upgradeChoices[2]));
        availableUpgrades.remove(availableUpgrades.indexOf(upgradeChoices[2]));

        if(gp.player.luck >= 1){
            upgradeChoices[3] = (availableUpgrades.get(rand.nextInt(availableUpgrades.size())));
        }
        
        availableUpgrades.add(temp1);
        availableUpgrades.add(temp2);
        availableUpgrades.add(temp3);
    }

    public Item createWeapon(int type){ //based on type passed in, create a weapon
        Item weapon;

        switch(type){
            case SWORD_WEAPTYPE:
                weapon = new Sword(gp);
                break;
            case BADGE_WEAPTYPE:
                weapon = new Badge(gp);
                break;
            case GUILLOTINE_WEAPTYPE:
                weapon = new Guillotine(gp);
                break;
            case FLINTLOCK_WEAPTYPE:
                weapon = new Flintlock(gp);
                break;
            case 4:
                weapon = new Flintlock(gp);
                break;
            case 5:
                weapon = new Flintlock(gp);
                break;
            default:
                System.out.println("Unexpected result in createWeapon");
                weapon = new Sword(gp);
        }

        return weapon;
    }

    public Item createPassive(int type){ //based on type passed in, create a passive
        Item passive;

        switch(type){
            case ARMOR_PASSTYPE:
                passive = new Armor(gp);
                break;
            case BOOTS_PASSTYPE:
                passive = new Boots(gp);
                break;
            case CAKE_PASSTYPE:
                passive = new Cake(gp);
                break;
            case MANUAL_PASSTYPE:
                passive = new Manual(gp);
                break;
            case SPOON_PASSTYPE:
                passive = new Spoon(gp);
                break;
            case 5:
                passive = new Boots(gp);
                break;
            default:
                System.out.println("Unexpected result in createWeapon");
                passive = new Armor(gp);
        }

        return passive;
    }

}
