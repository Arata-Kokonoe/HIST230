package main;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entity.Entity;
import items.Armor;
import items.Badge;
import items.Boots;
import items.Cake;
import items.Manual;
import items.Spoon;
import items.Sword;

public class ItemHandler{
    BufferedImage icon;
    GamePanel gp;
    public int weaponType;
    final public int maxTypes = 6;
    UtilityTool utool = new UtilityTool();
    
    public ItemHandler (GamePanel gp){
        this.gp = gp;
    }

    public Entity createWeapon(int type){ //based on type passed in, create a weapon
        Entity weapon;

        switch(type){
            case 0:
                weapon = new Sword(gp);
                break;
            case 1:
                weapon = new Badge(gp);
                break;
            case 2:
                weapon = new Sword(gp);
                break;
            case 3:
                weapon = new Badge(gp);
                break;
            case 4:
                weapon = new Sword(gp);
                break;
            case 5:
                weapon = new Badge(gp);
                break;
            default:
                System.out.println("Unexpected result in createWeapon");
                weapon = new Sword(gp);
        }

        return weapon;
    }

    public Entity createPassive(int type){ //based on type passed in, create a passive

        Entity passive;

        switch(type){
            case 0:
                passive = new Armor(gp);
                break;
            case 1:
                passive = new Boots(gp);
                break;
            case 2:
                passive = new Cake(gp);
                break;
            case 3:
                passive = new Manual(gp);
                break;
            case 4:
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
