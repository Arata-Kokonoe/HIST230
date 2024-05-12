package items;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;
import main.ItemHandler;

public class Sword extends Entity{

    BufferedImage rightSlash, leftSlash;

    public GamePanel gp;
    public int level;
    public int attackCounter = 0;
    public Rectangle hitbox2 = new Rectangle();

    public Sword(GamePanel gp){
        super(gp);
        this.gp = gp;
        level = 1;
        damage = 1;
        name = "Executioner's Sword";
        description = "Slashes to your left and right!\nThe three holes at its tip are said to make the sword\n\"sing\" as it is swung.";
        //source: https://www.academia.edu/3220162/Executoner_s_Swords_their_Form_and_Development._Brief_summary
        //and from https://www.youtube.com/watch?v=nyZxA9soDzE
        passOrWeap = 1;
        weapType = 0;
        getImage();
    }

    public void getImage(){

        icon = setup("/items/sword/swordIcon");
        rightSlash = setup("/items/sword/rightSlash");
        leftSlash = setup("/items/sword/leftSlash");

    }

    public void attack(){
        attackCounter++;
        //System.out.println("attackCounter = " + attackCounter);

        if(attackCounter == (int)(180 *(1-gp.player.cooldown))){
            switch (level) {
                case 1:
                    System.out.println("Attack!");
                    hitbox.x = (int)(gp.player.worldX - 48*gp.player.size); //48 because rn base size is 48
                    hitbox.width = (int)(48*gp.player.size*2 + gp.tileSize);  //width = 2 slashes + size of player which is gp.tilesize
                    hitbox.y = (gp.player.worldY);
                    hitbox.height = gp.tileSize;
        
                    gp.cChecker.checkWeaponHit(this, gp.enemies);
                    gp.entityList.add(this);
                    attackCounter = 0;
                    break;
                case 2:
                    System.out.println("Attack!");
                    hitbox.x = (int)(gp.player.worldX - 48*gp.player.size); //48 because rn base size is 48
                    hitbox.width = (int)(48*gp.player.size*2 + gp.tileSize);  //width = 2 slashes + size of player which is gp.tilesize
                    hitbox.y = (gp.player.worldY);
                    hitbox.height = gp.tileSize;
        
                    gp.cChecker.checkWeaponHit(this, gp.enemies);
                    gp.entityList.add(this);
                    attackCounter = 0;
                    break;
                case 3:
                    System.out.println("Attack!");
                    hitbox.x = (int)(gp.player.worldX - 48*gp.player.size); //48 because rn base size is 48
                    hitbox.width = (int)(48*gp.player.size*2 + gp.tileSize);  //width = 2 slashes + size of player which is gp.tilesize
                    hitbox.y = (gp.player.worldY);
                    hitbox.height = gp.tileSize;
        
                    gp.cChecker.checkWeaponHit(this, gp.enemies);
                    gp.entityList.add(this);
                    attackCounter = 0;
                    break;
                case 4:
                    System.out.println("Attack!");
                    hitbox.x = (int)(gp.player.worldX - 48*gp.player.size); //48 because rn base size is 48
                    hitbox.width = (int)(48*gp.player.size*2 + gp.tileSize);  //width = 2 slashes + size of player which is gp.tilesize
                    hitbox.y = (gp.player.worldY);
                    hitbox.height = gp.tileSize;

                    hitbox2.x = (int)(gp.player.worldX - 48*gp.player.size);
                    hitbox2.width = (int)(48*gp.player.size*2 + gp.tileSize);
                    hitbox2.y = (gp.player.worldY) - hitbox.height;
                    hitbox2.height = gp.tileSize;
        
                    gp.cChecker.checkWeaponHit(this, gp.enemies);
                    gp.entityList.add(this);
                    attackCounter = 0;
                    break;
                case 5:
                    System.out.println("Attack!");
                    hitbox.x = (int)(gp.player.worldX - 48*gp.player.size); //48 because rn base size is 48
                    hitbox.width = (int)(48*gp.player.size*2 + gp.tileSize);  //width = 2 slashes + size of player which is gp.tilesize
                    hitbox.y = (gp.player.worldY);
                    hitbox.height = gp.tileSize;

                    hitbox2.x = (int)(gp.player.worldX - 48*gp.player.size);
                    hitbox2.width = (int)(48*gp.player.size*2 + gp.tileSize);
                    hitbox2.y = (gp.player.worldY) - hitbox.height;
                    hitbox2.height = gp.tileSize;
        
                    gp.cChecker.checkWeaponHit(this, gp.enemies);
                    gp.entityList.add(this);
                    attackCounter = 0;
                    break;
                case 6:
                    System.out.println("Attack!");
                    hitbox.x = (int)(gp.player.worldX - 48*gp.player.size); //48 because rn base size is 48
                    hitbox.width = (int)(48*gp.player.size*2 + gp.tileSize);  //width = 2 slashes + size of player which is gp.tilesize
                    hitbox.y = (gp.player.worldY);
                    hitbox.height = gp.tileSize;

                    hitbox2.x = (int)(gp.player.worldX - 48*gp.player.size);
                    hitbox2.width = (int)(48*gp.player.size*2 + gp.tileSize);
                    hitbox2.y = (gp.player.worldY) - hitbox.height;
                    hitbox2.height = gp.tileSize;
        
                    gp.cChecker.checkWeaponHit(this, gp.enemies);
                    gp.entityList.add(this);
                    attackCounter = 0;
                    break;
                case 7:
                    System.out.println("Attack!");
                    hitbox.x = (int)(gp.player.worldX - 48*gp.player.size); //48 because rn base size is 48
                    hitbox.width = (int)(48*gp.player.size*2 + gp.tileSize);  //width = 2 slashes + size of player which is gp.tilesize
                    hitbox.y = (gp.player.worldY);
                    hitbox.height = gp.tileSize;

                    hitbox2.x = (int)(gp.player.worldX - 48*gp.player.size);
                    hitbox2.width = (int)(48*gp.player.size*2 + gp.tileSize);
                    hitbox2.y = (gp.player.worldY) - hitbox.height;
                    hitbox2.height = gp.tileSize;
        
                    gp.cChecker.checkWeaponHit(this, gp.enemies);
                    gp.entityList.add(this);
                    attackCounter = 0;
                    break;
                case 8:
                    System.out.println("Attack!");
                    hitbox.x = (int)(gp.player.worldX - 48*gp.player.size); //48 because rn base size is 48
                    hitbox.width = (int)(48*gp.player.size*2 + gp.tileSize);  //width = 2 slashes + size of player which is gp.tilesize
                    hitbox.y = (gp.player.worldY);
                    hitbox.height = gp.tileSize;

                    hitbox2.x = (int)(gp.player.worldX - 48*gp.player.size);
                    hitbox2.width = (int)(48*gp.player.size*2 + gp.tileSize);
                    hitbox2.y = (gp.player.worldY) - hitbox.height;
                    hitbox2.height = gp.tileSize;
        
                    gp.cChecker.checkWeaponHit(this, gp.enemies);
                    gp.entityList.add(this);
                    attackCounter = 0;
                    break;
            }
            
        }
    }

    public void checkLevelUp(){
        if(level < 8) {
            level++;
            applied = false;
        }
        if(level == 8){
            gp.player.availableUpgrades.remove(gp.player.availableUpgrades.indexOf(this));
            //DEBUG
            System.out.println("sword reached level 8\n");
            for (Entity e: gp.player.availableUpgrades) {
                System.out.println("item: " + e.name);
            }
            System.out.println("\n");
        }
    }

    public void draw(Graphics2D g2){
        System.out.println("weapon drawn");

        int screenX = (int)(gp.player.screenX - 48*gp.player.size);
        int screenY = (int)(gp.player.screenY);

        g2.drawImage(leftSlash, screenX, screenY, null);
        screenX += 48*gp.player.size + gp.tileSize;
        g2.drawImage(rightSlash, screenX, screenY, null);

        if (level >= 4){
            screenX -= 48*gp.player.size + gp.tileSize;
            screenY -= hitbox.height;

            g2.drawImage(leftSlash, screenX, screenY, null);
            screenX += 48*gp.player.size + gp.tileSize;
            g2.drawImage(rightSlash, screenX, screenY, null);
        }
    }

}
