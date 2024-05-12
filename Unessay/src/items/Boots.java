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
        description = "Increases your movespeed by 20%!";
        itemType = 0;
        getImage();
    }

    public void getImage(){

        icon = setup("/items/boots/bootsIcon");
    }

    public void addEffect(){
        if(applied == false){
            gp.player.speed = 3;
        }
    }
}
