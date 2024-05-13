package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.text.Utilities;

import main.GamePanel;
import main.UtilityTool;

public class Projectile extends Entity{

    Entity user;
    public BufferedImage up0, up1, down0, down1;
    UtilityTool utool = new UtilityTool();

    public Projectile(GamePanel gp){
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;

        hitbox[0] = new Rectangle(0, 0, (int)(user.baseSize * gp.player.sizeMultiplier), (int)(user.baseSize*gp.player.sizeMultiplier));
    }

    public void update(){

        if(user.name.contentEquals("Flintlock")){
            int monsterIndex = gp.cChecker.checkEntity(this, gp.enemies);
            if(monsterIndex != 999){
                double damage = user.damage * gp.player.damageMultiplier * (1-gp.enemies.get(monsterIndex).damageReduction);
                System.out.println("damage of " + user.name + " = " + damage);
                if(damage < 0) damage = 0;
                gp.enemies.get(monsterIndex).life -= damage;
                //gp.ui.addMessage(damage + " damage!");

                gp.enemies.get(monsterIndex).hit = true;
                gp.enemies.get(monsterIndex).lastDamageTaken = (int)damage;
                alive = false;
            }
        }
        if(!(user == gp.player)){

        }

        switch(direction){
        case "up": worldY -= speed; break;
        case "down": worldY += speed; break;
        case "left": worldX -= speed; break;
        case "right": worldX += speed; break;
        case "upright":
                worldY -= speed;
                worldX += speed;
                break;
            case "upleft":
                worldY -= speed;
                worldX -= speed;
                break;
            case "downright":
                worldY += speed;
                worldX += speed;
                break;
            case "downleft":
                worldY += speed;
                worldX -= speed;
                break;
        }
        
        life--;
        if(life <= 0){
            alive = false;
        }

        spriteCounter++;
        if(spriteCounter>12){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if (spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }


    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;  
        
        scaleImage(user);

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

            switch(direction){
            case "left":
                if (spriteNum == 1) image = left0;
                else if (spriteNum == 2) image = left1;
                break;
            case "right":
                if (spriteNum == 1) image = right0;
                else if (spriteNum == 2) image = right1;
                break;
            case "up":
                if (spriteNum == 1) image = up0;
                else if (spriteNum == 2) image = up1;
                break;
            case "down":
                if (spriteNum == 1) image = down0;
                else if (spriteNum == 2) image = down1;
                break;
            case "upright":
                if (spriteNum == 1) image = up0;
                else if (spriteNum == 2) image = right1;
                break;
            case "upleft":
                if (spriteNum == 1) image = up0;
                else if (spriteNum == 2) image = left1;
                break;
            case "downright":
                if (spriteNum == 1) image = down0;
                else if (spriteNum == 2) image = right1;
                break;
            case "downleft":
                if (spriteNum == 1) image = down0;
                else if (spriteNum == 2) image = left1;
                break;
            } //end of switch

            changeAlpha(g2, 1f);
            g2.drawImage(image, screenX, screenY, null);
        }
    }

    public void scaleImage(Entity user){
        left0 = utool.scaleImage(left0, user.getWidth(), user.getHeight());
        left1 = utool.scaleImage(left1, user.getWidth(), user.getHeight());
        right0 = utool.scaleImage(right0, user.getWidth(), user.getHeight());
        right1 = utool.scaleImage(right1, user.getWidth(), user.getHeight());
        up0 = utool.scaleImage(up0, user.getWidth(), user.getHeight());
        up1 = utool.scaleImage(up1, user.getWidth(), user.getHeight());
        down0 = utool.scaleImage(down0, user.getWidth(), user.getHeight());
        down1 = utool.scaleImage(down1, user.getWidth(), user.getHeight());
    }
}
