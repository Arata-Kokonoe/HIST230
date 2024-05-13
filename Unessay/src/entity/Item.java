package entity;

import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import main.GamePanel;

public class Item extends Entity{

    public ArrayList<Entity> enemiesHit = new ArrayList<Entity>();
    public ArrayList<Integer> enemiesHitTimer = new ArrayList<Integer>();
    public RoundRectangle2D.Double roundHitbox;

    //STATE
    public boolean applied = false;

    //COUNTER
    public int attackCounter = 0;

    //ATTRIBUTE
    public int passOrWeap;
    public final int PASSIVE_NUM = 0;
    public final int WEAPON_NUM = 1;
    public int weapType;
    public final int SWORD_WEAPTYPE = 0;
    public final int BADGE_WEAPTYPE = 1;
    public final int GUILLOTINE_WEAPTYPE = 2;
    public final int FLINTLOCK_WEAPTYPE = 3;
    public int passType;
    public final int ARMOR_PASSTYPE = 0;
    public final int BOOTS_PASSTYPE = 1;
    public final int CAKE_PASSTYPE = 2;
    public final int MANUAL_PASSTYPE = 3;
    public final int SPOON_PASSTYPE = 4;
    public String description;

    public Item(GamePanel gp){
        super(gp);
    }

    public void addEffect(){
        System.out.println("this item (" + this.name + ") has no addEffect method, overwrite method");
    }
    public void attack(){
        System.out.println("this item (" + this.name + ") has no attack method, overwrite method");
    }

    public void checkLevelUp(){
        System.out.println("this item (" + this.name + ") has no checkLevelUp method, overwrite method");
    }

    public void scaleImages(){
        System.out.println("this item (" + this.name + ") has no scaleImages method, overwrite method");
    }

    public int getWidth(){
        System.out.println("this item (" + this.name + ") has no getWidth method, overwrite method");
        return -1;
    }

    public int getHeight(){
        System.out.println("this item (" + this.name + ") has no getHeight method, overwrite method");
        return -1;
    }

    public int getHitboxX(){
        System.out.println("this item (" + this.name + ") has no getHitboxX method, overwrite method");
        return -1;
    }

    public int getHitboxY(){
        System.out.println("this item (" + this.name + ") has no getHitboxY method, overwrite method");
        return -1;
    }

}
