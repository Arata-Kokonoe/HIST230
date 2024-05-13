package enemies;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class ENE_sansCulotte extends Entity{

    GamePanel gp;
    final int spaceBetween = 12;

    public ENE_sansCulotte(GamePanel gp){
        super(gp);

        this.gp = gp;
        hitbox = new Rectangle[1];

        type = 2;
        name = "Sans-culotte";
        direction = "right";
        leftOrRight = "right";
        speed = 1;
        maxLife = 20;
        life = maxLife;
        damage = 5;
        damageMultiplier = 1;
        damageReduction = 0;

        hitbox[0] = new Rectangle(12, 12, 24, 36);
        hitboxDefaultX = hitbox[0].x;
        hitboxDefaultY = hitbox[0].y;

        getImage();
    }

    public void getImage(){

        left0 = setup("/enemy/SC_left0");
        left1 = setup("/enemy/SC_left0");
        left2 = setup("/enemy/SC_left0");
        left3 = setup("/enemy/SC_left0");
        right0 = setup("/enemy/SC_right0");
        right1 = setup("/enemy/SC_right0");
        right2 = setup("/enemy/SC_right0");
        right3 = setup("/enemy/SC_right0");

    }

    public void setAction(){

        int playerCenterX = (int)(gp.player.worldX + gp.tileSize/2);
        int playerCenterY = (int)(gp.player.worldY + gp.tileSize/2);
        int enemyCenterX = (int)(worldX + gp.tileSize/2);
        int enemyCenterY = (int)(worldY + gp.tileSize/2);

        if(playerCenterX > enemyCenterX && playerCenterY> enemyCenterY) {
            direction = "downright";
            leftOrRight = "right";
        }
        else if (playerCenterX < enemyCenterX && playerCenterY < enemyCenterY){ 
            direction = "upleft";
            leftOrRight = "left";
        }
        else if (playerCenterX > enemyCenterX && playerCenterY < enemyCenterY){
            direction = "upright";
            leftOrRight = "right";
        }
        else if (playerCenterX < enemyCenterX && playerCenterY > enemyCenterY){
            direction = "downleft";
            leftOrRight = "left";
        }
        else if (playerCenterX > enemyCenterX){
            direction = "right";
            leftOrRight = "right";
        }
        else if (playerCenterX < enemyCenterX){
            direction = "left";
            leftOrRight = "left";
        }
        else if (playerCenterY > enemyCenterY){
            direction = "down";
        }
        else if (playerCenterY < enemyCenterY){
            direction = "up";
        } 

        for(int i = 0; i < gp.enemies.size(); i++){
            int otherCenterX = (int)(gp.enemies.get(i).worldX + gp.tileSize/2);
            int otherCenterY = (int)(gp.enemies.get(i).worldY + gp.tileSize/2);

            int distanceToOther = (int)(Math.hypot(enemyCenterX-otherCenterX, enemyCenterY-otherCenterY));
            //distance formula from https://stackoverflow.com/questions/14431032/i-want-to-calculate-the-distance-between-two-points-in-java
            if(distanceToOther <= spaceBetween){
                if(enemyCenterX > otherCenterX && enemyCenterY > otherCenterY) {
                    direction = "downright";
                    leftOrRight = "right";
                }
                else if (enemyCenterX < otherCenterX && enemyCenterY < otherCenterY){ 
                    direction = "upleft";
                    leftOrRight = "left";
                }
                else if (enemyCenterX > otherCenterX && enemyCenterY < otherCenterY){
                    direction = "upright";
                    leftOrRight = "right";
                }
                else if (enemyCenterX < otherCenterX && enemyCenterY > otherCenterY){
                    direction = "downleft";
                    leftOrRight = "left";
                }
                else if (enemyCenterX > otherCenterX){
                    direction = "right";
                    leftOrRight = "right";
                }
                else if (enemyCenterX < otherCenterX){
                    direction = "left";
                    leftOrRight = "left";
                }
                else if (enemyCenterY > otherCenterY){
                    direction = "down";
                }
                else if (enemyCenterY < otherCenterY){
                    direction = "up";
                } 
            }
        }
    }

    public void update(){
        setAction();

        collisionOn = false;

        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(contactPlayer == true && alive == true && dying == false){
            if(gp.player.invincible == false){
                double incomingDamage = damage * (1-gp.player.damageReduction);
                if(incomingDamage < 0) incomingDamage = 0;

                gp.player.life -= incomingDamage;
                gp.player.invincible = true;
            }
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

        if(hit == true){
            hitCounter++;
            if(hitCounter > 20){
                hit = false;
                hitCounter = 0;
            }
        }

    }
}