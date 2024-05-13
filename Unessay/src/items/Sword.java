package items;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entity.Entity;
import entity.Item;
import main.GamePanel;
import main.UtilityTool;

public class Sword extends Item{

    BufferedImage rightSlash, leftSlash;

    public GamePanel gp;
    int baseAttackFrame = 0;
    public int level;

    UtilityTool utool = new UtilityTool();

    public Sword(GamePanel gp){
        super(gp);
        this.gp = gp;
        hitbox = new Rectangle[2];

        level = 1;
        damage = 5;
        baseSize = 48;


        name = "Executioner's Sword";
        description = "Slashes to your left and right!\nThe three holes at its tip are said to make the sword\n\"sing\" as it is swung.";
        //source: https://www.academia.edu/3220162/Executoner_s_Swords_their_Form_and_Development._Brief_summary
        //and from https://www.youtube.com/watch?v=nyZxA9soDzE

        passOrWeap = WEAPON_NUM;
        weapType = SWORD_WEAPTYPE;

        getImage();
        hitbox[0] = new Rectangle();
        hitbox[1] = new Rectangle();
    }

    public void getImage(){

        icon = setup("/items/sword/swordIcon");
        rightSlash = setup("/items/sword/rightSlash");
        leftSlash = setup("/items/sword/leftSlash");

    }

    public void attack(){
        attackCounter++;
        for(int i = 0; i < enemiesHit.size(); i++){
            enemiesHitTimer.set(i, enemiesHitTimer.get(i) + 1);
            if(enemiesHitTimer.get(i) >= 10){
                enemiesHit.remove(i);
                enemiesHitTimer.remove(i);
                if(i >= enemiesHit.size()) break;
            }
        }

        if(attackCounter == (int)(180 *(1-gp.player.cooldownReduction))){
            attacking = true;
            switch (level) {
                case 1:
                    
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

        
                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                
            }
            hitbox[0].x = (int)(gp.player.worldX - baseSize*gp.player.sizeMultiplier); //48 because rn base size is 48
            hitbox[0].width = (int)(baseSize*gp.player.sizeMultiplier*2 + gp.tileSize);  //width = 2 slashes + size of player which is gp.tilesize
            hitbox[0].height = (int)(gp.tileSize * gp.player.sizeMultiplier);
            hitbox[0].y = (gp.player.worldY + gp.tileSize - hitbox[0].height);

            if(level >= 4){
                hitbox[1].x = (int)(gp.player.worldX - baseSize*gp.player.sizeMultiplier); //48 because rn base size is 48
                hitbox[1].width = (int)(baseSize*gp.player.sizeMultiplier*2 + gp.tileSize);  //width = 2 slashes + size of player which is gp.tilesize
                hitbox[1].height = (int)(gp.tileSize * gp.player.sizeMultiplier);
                hitbox[1].y = (hitbox[0].y - hitbox[1].height);
            }

            gp.cChecker.checkSwordHit(this, gp.enemies);
            baseAttackFrame = attackCounter;
        }

        if (baseAttackFrame != 0 && attackCounter >= baseAttackFrame + 15){
            attacking = false;
            attackCounter = 0;
            baseAttackFrame = 0;
        }
        
    }

    public void checkLevelUp(){
        if(level < 8) {
            level++;
            applied = false;
        }
        if(level == 8){
            gp.itemH.availableUpgrades.remove(gp.itemH.availableUpgrades.indexOf(this));
            //DEBUG
            System.out.println("sword reached level 8\n");
            for (Entity e: gp.itemH.availableUpgrades) {
                System.out.println("item: " + e.name);
            }
            System.out.println("\n");
        }
    }

    public void scaleImages(){
        leftSlash = utool.scaleImage(leftSlash, (int)(baseSize*gp.player.sizeMultiplier), (int)(gp.tileSize * gp.player.sizeMultiplier));
        rightSlash = utool.scaleImage(rightSlash, (int)(baseSize*gp.player.sizeMultiplier), (int)(gp.tileSize * gp.player.sizeMultiplier));
    }

    public void draw(Graphics2D g2){

        if(attacking == true){

            int screenX = (int)(gp.player.screenX - baseSize*gp.player.sizeMultiplier);
            int screenY = gp.player.screenY + gp.tileSize - hitbox[0].height;

            //System.out.println("weapon x and y = " + screenX + " " + screenY);
            //System.out.println("player x and y = " + gp.player.screenX + " " + gp.player.screenY);

            g2.drawImage(leftSlash, screenX, screenY, null);
            screenX += 48*gp.player.sizeMultiplier + gp.tileSize;
            g2.drawImage(rightSlash, screenX, screenY, null);

            if (level >= 4){
                screenX -= 48*gp.player.sizeMultiplier + gp.tileSize;
                screenY -= hitbox[1].height;

                g2.drawImage(leftSlash, screenX, screenY, null);
                screenX += 48*gp.player.sizeMultiplier + gp.tileSize;
                g2.drawImage(rightSlash, screenX, screenY, null);
            }

        }
        
    }

    public int getWidth(){
        return (int)(baseSize*gp.player.sizeMultiplier*2 + gp.tileSize);
    }

    public int getHeight(){
        return (int)(gp.tileSize * gp.player.sizeMultiplier);
    }

    public int getHitboxX(){
        return (int)(gp.player.worldX - baseSize*gp.player.sizeMultiplier);
    }

    public int getHitboxY(int hitboxNum){
        if(hitboxNum == 0) return (int)(gp.player.worldY + gp.tileSize - hitbox[0].height);
        else if (hitboxNum == 1) return (int)(hitbox[0].y - hitbox[1].height);
        else{
            System.out.println("error in sword hitbox method");
            return -1;
        }
    }

}
