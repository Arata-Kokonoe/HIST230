package items;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

import entity.Entity;
import main.GamePanel;

public class Badge extends Entity{

    public GamePanel gp;
    public int level;
    public int attackCounter = 0;

    public Badge(GamePanel gp){
        super(gp);
        this.gp = gp;
        roundHitbox = new RoundRectangle2D.Double(0, 0, 0, 0, 100, 100);

        level = 1;
        damage = 1;
        baseSize = 48;

        name = "Executioner's Badge";
        description = "Creates a damaging zone around you!\nNo one would want to touch an executioner lest they\nwant to get cursed for a lifetime...";

        passOrWeap = WEAPON_NUM;
        weapType = BADGE_WEAPTYPE;

        getImage();
    }

    public void getImage(){

        icon = setup("/items/badgeIcon");

    }

    public void attack(){
        attackCounter++;
        weaponHitCounter++;
        for(int i = 0; i < enemiesHit.size(); i++){
            enemiesHitTimer.set(i, enemiesHitTimer.get(i) + 1);
            if(enemiesHitTimer.get(i) >= 14){
                enemiesHit.remove(i);
                enemiesHitTimer.remove(i);
                if(i >= enemiesHit.size()) break;
            }
        }

        if(attackCounter == 15){
            switch (level){
                case 1:
                    System.out.println("Attack!");
                    roundHitbox.x = (gp.player.worldX - baseSize*gp.player.sizeMultiplier); 
                    roundHitbox.width = (baseSize*gp.player.sizeMultiplier*3);  
                    roundHitbox.y = (gp.player.worldY - baseSize*gp.player.sizeMultiplier);
                    roundHitbox.height = (baseSize*gp.player.sizeMultiplier*3);
                    break;
                case 2:
                    damage++;
                    roundHitbox.x = (gp.player.worldX - baseSize*gp.player.sizeMultiplier); 
                    roundHitbox.width = (baseSize*gp.player.sizeMultiplier*3);  
                    roundHitbox.y = (gp.player.worldY - baseSize*gp.player.sizeMultiplier);
                    roundHitbox.height = (baseSize*gp.player.sizeMultiplier*3);
                    break;
                case 3:
                    damage++;
                    roundHitbox.x = (gp.player.worldX - baseSize*gp.player.sizeMultiplier); 
                    roundHitbox.width = (baseSize*gp.player.sizeMultiplier*3);  
                    roundHitbox.y = (gp.player.worldY - baseSize*gp.player.sizeMultiplier);
                    roundHitbox.height = (baseSize*gp.player.sizeMultiplier*3);
                    break;
                case 4:
                    roundHitbox.x = (gp.player.worldX - baseSize*gp.player.sizeMultiplier); 
                    roundHitbox.width = (baseSize*gp.player.sizeMultiplier*3);  
                    roundHitbox.y = (gp.player.worldY - baseSize*gp.player.sizeMultiplier);
                    roundHitbox.height = (baseSize*gp.player.sizeMultiplier*3);
                    break;
                case 5:
                    roundHitbox.x = (gp.player.worldX - baseSize*gp.player.sizeMultiplier); 
                    roundHitbox.width = (baseSize*gp.player.sizeMultiplier*3);  
                    roundHitbox.y = (gp.player.worldY - baseSize*gp.player.sizeMultiplier);
                    roundHitbox.height = (baseSize*gp.player.sizeMultiplier*3);
                    break;
                case 6:
                    roundHitbox.x = (gp.player.worldX - baseSize*gp.player.sizeMultiplier); 
                    roundHitbox.width = (baseSize*gp.player.sizeMultiplier*3);  
                    roundHitbox.y = (gp.player.worldY - baseSize*gp.player.sizeMultiplier);
                    roundHitbox.height = (baseSize*gp.player.sizeMultiplier*3);
                    break;
                case 7:
                    roundHitbox.x = (gp.player.worldX - baseSize*gp.player.sizeMultiplier); 
                    roundHitbox.width = (baseSize*gp.player.sizeMultiplier*3);  
                    roundHitbox.y = (gp.player.worldY - baseSize*gp.player.sizeMultiplier);
                    roundHitbox.height = (baseSize*gp.player.sizeMultiplier*3);
                    break;
                case 8:
                    roundHitbox.x = (gp.player.worldX - baseSize*gp.player.sizeMultiplier); 
                    roundHitbox.width = (baseSize*gp.player.sizeMultiplier*3);  
                    roundHitbox.y = (gp.player.worldY - baseSize*gp.player.sizeMultiplier);
                    roundHitbox.height = (baseSize*gp.player.sizeMultiplier*3);
                    break;
            }

            gp.cChecker.checkRoundWeaponHit(this, gp.enemies);
            attackCounter = 0;
        }

        if (weaponHitCounter >= 10) weaponHitCounter = 0;

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
        int screenX = (int)(gp.player.screenX - baseSize*gp.player.sizeMultiplier);
        int screenY = (int)(gp.player.screenY - baseSize*gp.player.sizeMultiplier);

        g2.setColor(new Color(0, 0, 0, 125));
        g2.fillOval(screenX, screenY, (int)(baseSize*gp.player.sizeMultiplier*3), (int)(48*gp.player.sizeMultiplier*3));
    }

}
