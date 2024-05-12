package items;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

import entity.Entity;
import main.GamePanel;

public class Badge extends Entity{
     BufferedImage rightSlash, leftSlash;

    public GamePanel gp;
    public int level;
    public int attackCounter = 0;

    public Badge(GamePanel gp){
        super(gp);
        this.gp = gp;
        roundHitbox = new RoundRectangle2D.Double(0, 0, 0, 0, 100, 100);
        level = 1;
        damage = 1;
        name = "Executioner's Badge";
        description = "Creates a damaging zone around you!\nNo one would want to touch an executioner lest they\nwant to get cursed for a lifetime...";
        passOrWeap = 1;
        weapType = 1;
        getImage();
    }

    public void getImage(){

        icon = setup("/items/badgeIcon");

    }

    public void attack(){
        attackCounter++;
        //System.out.println("attackCounter = " + attackCounter);

        if(attackCounter == 15){
            System.out.println("Attack!");
            roundHitbox.x = (gp.player.worldX - 48*gp.player.size); //48 because rn base size is 48
            roundHitbox.width = (48*gp.player.size*3);  //width = 2 slashes + size of player which is gp.tilesize
            roundHitbox.y = (gp.player.worldY - 48*gp.player.size);
            roundHitbox.height = (48*gp.player.size*3);

            gp.cChecker.checkRoundWeaponHit(this, gp.enemies);
            attackCounter = 0;
        }
    }

    public void draw(Graphics2D g2){
        System.out.println("weapon drawn");

        int screenX = (int)(gp.player.screenX - 48*gp.player.size);
        int screenY = (int)(gp.player.screenY - 48*gp.player.size);

        g2.setColor(new Color(0, 0, 0, 125));
        g2.fillOval(screenX, screenY, (int)roundHitbox.width, (int)roundHitbox.height);
    }

}
