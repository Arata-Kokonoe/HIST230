package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
   
    GamePanel gp;
    public BufferedImage left0, left1, left2, left3, right0, right1, right2, right3;
    public BufferedImage attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage icon;
    public BufferedImage image;
    public Rectangle hitbox = new Rectangle(0, 0, 48, 48);
    public RoundRectangle2D.Double roundHitbox;
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int hitboxDefaultX, hitboxDefaultY;
    public boolean collision = false;
    String dialogues[] = new String[20];
    
    //STATE
    public int worldX;
    public int worldY;
    public String direction = "right";
    public String leftOrRight = "right";
    public int spriteNum = 1;
    public int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean applied = false;

    //COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int offscreenCounter = 0;
    int dyingCounter = 0;
    
    //CHARACTER ATTRIBUTE
    public int type; // 0 = player, 1= npc, 2 = enemy
    public int speed;
    public String name;
    public int maxLife;
    public int life;
    public int level;
    public int damageMultiplier;
    public int damage;
    public double damageReduction;
    public int cooldown;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int luck;

    //ITEM ATTRIBUTE
    public int passOrWeap;
    public int weapType;
    public int passType;
    public String description;
    public double size = 1.0;

    public Entity (GamePanel gp){
        this.gp = gp;
    }

    public void setAction(){}
    public void speak(){
        if(dialogues[dialogueIndex] == null){
            gp.gameState = gp.playState;
            gp.talkingEntity.dialogueIndex = 0;
            gp.talkingEntity = null;
            gp.ui.currentCloseup = null;
            return;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        gp.ui.currentCloseup = icon;

        dialogueIndex++;

        switch (gp.player.leftOrRight) {
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    
    public void update(){
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.enemies);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true && alive == true && dying == false){
            if(gp.player.invincible == false){
                //gp.playSE(5);
                double incomingDamage = damage * (1-gp.player.damageReduction);
                if(incomingDamage < 0) incomingDamage = 0;

                gp.player.life -= incomingDamage;
                gp.player.invincible = true;
            }
        }

        if(this.type == 3 && contactPlayer == true){
            gp.player.exp++;
            gp.player.checkLevelUp();
            this.alive = false;
        }

        if(collisionOn == false){
            switch(direction){
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
            case "upleft": 
                worldY -= speed;
                worldX -= speed; 
                break;
            case "upright": 
                worldY -= speed;
                worldX += speed; 
                break;
            case "downleft": 
                worldY += speed;
                worldX -= speed; 
                break;
            case "downright": 
                worldY += speed;
                worldX += speed; 
                break;
            }

        }

        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if (spriteNum == 2){
                spriteNum = 3;
            }
            else if (spriteNum == 3){
                spriteNum = 4;
            }
            else if (spriteNum == 4){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }


    }
    
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;    

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

            switch(leftOrRight){
                case "left":
                    if (spriteNum == 1) image = left0;
                    else if (spriteNum == 2) image = left1;
                    else if (spriteNum == 3) image = left2;
                    else if (spriteNum == 4) image = left3;
                    break;
                case "right":
                    if (spriteNum == 1) image = right0;
                    else if (spriteNum == 2) image = right1;
                    else if (spriteNum == 3) image = right2;
                    else if (spriteNum == 4) image = right3;
                    break;
            } //end of switch

            if (invincible == true && dying != true){
                //first, generate mask
                int imgWidth = image.getWidth();
                int imgHeight = image.getHeight();

                BufferedImage imgMask = new BufferedImage(imgWidth, imgHeight, BufferedImage.TRANSLUCENT);
                Graphics2D g1 = imgMask.createGraphics();

                g1.drawImage(image, 0, 0, null);
                g1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 0.5f));
                g1.setColor(Color.red);

                g1.fillRect(0, 0, image.getWidth(), image.getHeight());
                g1.dispose();

                //then, generate tinted image
                BufferedImage tinted = new BufferedImage(imgWidth, imgHeight, BufferedImage.TRANSLUCENT);
                Graphics2D g3 = tinted.createGraphics();
                g3.drawImage(image, 0, 0, null);
                g3.drawImage(imgMask, 0, 0, null);
                g3.dispose();


                //finally, draw the tinted image
                g2.drawImage(tinted, screenX, screenY, null);

                //code to tint an image taken from https://stackoverflow.com/questions/14225518/tinting-image-in-java-improvement?noredirect=1&lq=1
                
                //later, add code for blood particles
            } 
            
            else if (dying == true){
                dyingAnimation(g2);
                g2.drawImage(image, screenX, screenY, null);
            }
            else {
                changeAlpha(g2, 1f);
                g2.drawImage(image, screenX, screenY, null);

                //DEBUG
                g2.setColor(Color.red);
                g2.drawRect(screenX + 12, screenY + 12, 29, 41);
            }
        }
    }

    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;

        int i = 5;

        //switch sprites instead of changing alpha if you want better animation
        if(dyingCounter <= i){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > i && dyingCounter <= i*2){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > i*2 && dyingCounter <= i*3){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > i*3 && dyingCounter <= i*4){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > i*4 && dyingCounter <= i*5){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > i*5 && dyingCounter <= i*6){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > i*6 && dyingCounter <= i*7){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > i*7 && dyingCounter <= i*8){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > i*8){
            dying = false;
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res" + imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    public BufferedImage setup(String imagePath, int width, int height){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res" + imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    public boolean checkOffscreen(){
        if(!(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)){
            offscreenCounter++;
        }
        if(offscreenCounter == 300) return true;
        else return false;
    }

    public void addEffect(){
        System.out.println("this entity has no effect, overwrite method" + "\nEntity name: " + this.name);
    }
    public void attack(){
        System.out.println("this entity has no attack, overwrite method" + "\nEntity name: " + this.name);
    }

    public void checkLevelUp(){
        System.out.println("this entity shouldn't level up, overwrite method" + "\nEntity name: " + this.name);
    }

    public boolean equals(Entity other){
        if (name.contentEquals(other.name)) return true;
        else return false;
    }
}
