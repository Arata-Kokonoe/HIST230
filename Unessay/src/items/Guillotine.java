package items;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entity.Entity;
import entity.Item;
import main.GamePanel;
import main.UtilityTool;

public class Guillotine extends Item{

    
    BufferedImage image0, image1;

    public GamePanel gp;
    int baseAttackFrame = 0;
    public int level;
    public int radius;
    public double angle;
    public int duration;

    UtilityTool utool = new UtilityTool();

    public Guillotine(GamePanel gp){
        super(gp);
        this.gp = gp;
        hitbox = new Rectangle[1];

        level = 1;
        damage = 10;
        baseSize = 48;
        angle = 0.0;
        duration = 240; //5 seconds
        radius = gp.tileSize*3;

        name = "The Guillotine";
        description = "Creates a flying guillotine that circles around you!\nGuillotines were a symbol of post-revolutionary\nFrance, so much so that some people started\nwearing guillotine earrings!";

        passOrWeap = WEAPON_NUM;
        weapType = GUILLOTINE_WEAPTYPE;

        getImage();
        hitbox[0] = new Rectangle();
    }

    public void getImage(){

        icon = setup("/items/guillotine/guillotineIcon");
        image0 = setup("/items/guillotine/guillotine0");
        image1 = setup("/items/guillotine/guillotine1");

    }

    public void attack(){
        attackCounter++;
        angle += Math.PI/180;

        for(int i = 0; i < enemiesHit.size(); i++){
            enemiesHitTimer.set(i, enemiesHitTimer.get(i) + 1);
            if(enemiesHitTimer.get(i) >= 80){
                enemiesHit.remove(i);
                enemiesHitTimer.remove(i);
                if(i >= enemiesHit.size()) break;
            }
        }

        if(attackCounter == (int)(360 *(1-gp.player.cooldownReduction))){
            //System.out.println("guillotine is now attacking");
            attacking = true;

            hitbox[0].width = (int)(baseSize*gp.player.sizeMultiplier);
            hitbox[0].height = (int)(baseSize*gp.player.sizeMultiplier);
            

            //gp.cChecker.checkWeaponHit(this, gp.enemies);
            baseAttackFrame = attackCounter;
        }

        if(attacking == true){
            int originX = gp.player.worldX + gp.tileSize/2;
            int originY = gp.player.worldY + gp.tileSize/2;

            hitbox[0].x = (int)(originX + Math.cos(angle) * radius);
            hitbox[0].y = (int)(originY + Math.sin(angle) * radius);
            //https://gamedev.stackexchange.com/questions/9607/moving-an-object-in-a-circular-path#:~:text=You%20can%20do%20that%20using,radius%20is%20its%20radius.
            //^ how to calculate x + y based on angle for a circular path

            gp.cChecker.checkWeaponHit(this, gp.enemies);
        }

        if (baseAttackFrame != 0 && attackCounter >= baseAttackFrame + duration){
            attacking = false;
            attackCounter = 0;
            baseAttackFrame = 0;
        }

        if(angle >= 2*Math.PI){
            angle = 0;
        }
    }

    public void checkLevelUp(){
        if(level < 8) {
            level++;
            applied = false;
            switch (level) {
                case 2:
                    damage++;
                    description = "Level " + level + ": increases damage!";
                    break;
                case 3:
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
        }
        if(level == 8){
            gp.itemH.availableUpgrades.remove(gp.itemH.availableUpgrades.indexOf(this));
            //DEBUG
            System.out.println("guillotine reached level 8\n");
            for (Entity e: gp.itemH.availableUpgrades) {
                System.out.println("item: " + e.name);
            }
            System.out.println("\n");
        }
    }

    public void scaleImages(){
        image0 = utool.scaleImage(image0, (int)(baseSize*gp.player.sizeMultiplier), (int)(baseSize*gp.player.sizeMultiplier));
        image1 = utool.scaleImage(image1, (int)(baseSize*gp.player.sizeMultiplier), (int)(baseSize*gp.player.sizeMultiplier));
    }

    public void draw(Graphics2D g2){
        spriteCounter++;

        if(attacking == true){

            int screenX = (int)(gp.player.screenX + Math.cos(angle)*radius);
            int screenY = (int)(gp.player.screenY + Math.sin(angle)*radius);

            if(spriteCounter <= 12)
                g2.drawImage(image0, screenX, screenY, null);
            if(spriteCounter > 12 && spriteCounter <= 24){
                g2.drawImage(image1, screenX, screenY, null);
            }

        }

        if(spriteCounter > 24) spriteCounter = 0;
    }

    public int getWidth(){
        return (int)(baseSize*gp.player.sizeMultiplier);
    }

    public int getHeight(){
        return (int)(baseSize*gp.player.sizeMultiplier);
    }

    public int getHitboxX(){
        return (int)(gp.player.worldX + gp.tileSize/2 + Math.cos(angle) * radius);
    }

    public int getHitboxY(){
        return (int)(gp.player.worldY + gp.tileSize/2 + Math.sin(angle) * radius);
    }
}
