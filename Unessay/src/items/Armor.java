package items;

import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Armor extends Entity{
    public int level;
    public GamePanel gp;

    public Armor(GamePanel gp){
        super(gp);

        this.gp = gp;

        name = "Armor";
        description = "Reduces your damage taken by 20%!";
        level = 1;
        passType = 0;
        passOrWeap = 0;
        getImage();
    }

    public void getImage(){

        icon = setup("/items/armorIcon");

    }

    public void addEffect(){
        if(applied == false){
            switch(level){
                case 1:
                    gp.player.damageReduction += 0.1;
                    break;
                case 2:
                    gp.player.damageReduction += 0.1;
                    break;
                case 3:
                    gp.player.damageReduction += 0.1;
                    break;
                case 4:
                    gp.player.damageReduction += 0.1;
                    break;
                case 5:
                    gp.player.damageReduction += 0.05;
                    break;
            }
            applied = true;
        }
    }

    public void checkLevelUp(){
        if(level < 5) {
            level++;
            applied = false;
        }
        if(level == 4){
            gp.player.availableUpgrades.remove(gp.player.availableUpgrades.indexOf(this));
            //DEBUG
            System.out.println("armor reached level 4\n");
            for (Entity e: gp.player.availableUpgrades) {
                System.out.println("item: " + e.name);
            }
            System.out.println("\n");
        }
    }
}