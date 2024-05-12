package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;
import main.ItemHandler;

public class Sword extends Entity{

    BufferedImage rightSlash, leftSlash;

    public GamePanel gp;
    public int cd; 
    public int level;
    public int attackCounter = 10;

    public Sword(GamePanel gp){
        super(gp);
        this.gp = gp;
        level = 1;
        damage = 1;
        name = "Executioner's Sword";
        description = "Slashes to your left and right!\nThe three holes at its tip are said to make the sword\n\"sing\" as it is swung.";
        //source: https://www.academia.edu/3220162/Executoner_s_Swords_their_Form_and_Development._Brief_summary
        //and from https://www.youtube.com/watch?v=nyZxA9soDzE
        cd = 600;
        itemType = 1;
        getImage();
    }

    public void getImage(){

        icon = setup("/items/sword/swordIcon");
        rightSlash = setup("/items/sword/rightSlash");
        leftSlash = setup("/items/sword/leftSlash");

    }

    public void attack(){
        attackCounter++;
        System.out.println("attackCounter = " + attackCounter);

        if(attackCounter >= 180 && attackCounter <= 181){
            System.out.println("Attack!");
            hitbox.x = (int)(gp.player.worldX - 48*size); //48 because rn base size is 48
            hitbox.width = (int)(48*size*2 + gp.tileSize);  //width = 2 slashes + size of player which is gp.tilesize
            hitbox.y = (gp.player.worldY);
            hitbox.height = gp.tileSize;

            gp.cChecker.checkWeaponHit(this, gp.enemies);
            gp.entityList.add(this);

        }
        if(attackCounter > 181) attackCounter = 0;
    }

    public void draw(Graphics2D g2){
        System.out.println("weapon drawn");

        int screenX = (int)(gp.player.screenX - 48*size);
        int screenY = (int)(gp.player.screenY);

        g2.drawImage(leftSlash, screenX, screenY, null);
        screenX += 48*size + gp.tileSize;
        g2.drawImage(rightSlash, screenX, screenY, null);
    }

}
