package items;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import entity.Entity;
import entity.Item;
import main.GamePanel;

public class Badge extends Item{

    public GamePanel gp;
    public int level;

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

        for(int i = 0; i < enemiesHit.size(); i++){
            enemiesHitTimer.set(i, enemiesHitTimer.get(i) + 1);
            if(enemiesHitTimer.get(i) >= 14){
                enemiesHit.remove(i);
                enemiesHitTimer.remove(i);
                if(i >= enemiesHit.size()) break;
            }
        }

        if(attackCounter == 15){
            roundHitbox.x = (gp.player.worldX - baseSize*gp.player.sizeMultiplier); 
            roundHitbox.width = (baseSize*gp.player.sizeMultiplier*3);  
            roundHitbox.y = (gp.player.worldY - baseSize*gp.player.sizeMultiplier);
            roundHitbox.height = (baseSize*gp.player.sizeMultiplier*3);

            gp.cChecker.checkRoundWeaponHit(this, gp.enemies);
            attackCounter = 0;
        }


    }

    public void checkLevelUp(){
        if(level < 8) {
            level++;
            switch (level){
                case 2:
                    damage++;
                    break;
                case 3:
                    baseSize += 24;
                    damage++;
                    break;
                case 4:
                    damage++;
                    break;
                case 5:
                    damage++;
                    break;
                case 6:
                    damage++;
                    break;
                case 7:
                    damage++;
                    break;
                case 8:
                    damage++;
                    break;
            }
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

    public void draw(Graphics2D g2){
        int screenX = (int)(gp.player.screenX - baseSize*gp.player.sizeMultiplier);
        int screenY = (int)(gp.player.screenY - baseSize*gp.player.sizeMultiplier);

        g2.setColor(new Color(0, 0, 0, 125));
        g2.fillOval(screenX, screenY, (int)(baseSize*gp.player.sizeMultiplier*3), (int)(baseSize*gp.player.sizeMultiplier*3));
    }

    public void scaleImages(){
        
    }
}
