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
        itemType = 0;
        getImage();
    }

    public void getImage(){

        icon = setup("/items/armor/armorIcon");

    }

    public void addEffect(){
        if(applied == false){
            gp.player.damageReduction = 0.1;
        }
    }
}
