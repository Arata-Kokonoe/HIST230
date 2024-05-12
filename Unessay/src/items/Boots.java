package items;

import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Boots extends Entity{
    public int level;
    public GamePanel gp;

    public Boots(GamePanel gp){
        super(gp);
        this.gp = gp;
        level = 1;

        name = "Boots";
        description = "Increases your movespeed!";
        passType = 1;
        passOrWeap = 0;
        getImage();
    }

    public void getImage(){

        icon = setup("/items/bootsIcon");
    }

    public void addEffect(){
        if(applied == false){
            switch(level){
                case 1:
                    gp.player.speed += 1;
                    break;
                case 2:
                    gp.player.speed += 1;
                    break;
                case 3:
                    gp.player.speed += 1;
                    break;
                case 4:
                    gp.player.speed += 1;
                    break;
                case 5:
                    gp.player.speed += 1;
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
            System.out.println("boots reached level 4\n");
            for (Entity e: gp.player.availableUpgrades) {
                System.out.println("item: " + e.name);
            }
            System.out.println("\n");
        }
    }
}