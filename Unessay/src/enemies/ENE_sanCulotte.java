package enemies;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class ENE_sanCulotte extends Entity{

    GamePanel gp;

    public ENE_sanCulotte(GamePanel gp){
        super(gp);

        this.gp = gp;

        type = 2;
        name = "San Culotte";
        direction = "right";
        leftOrRight = "right";
        speed = 1;
        maxLife = 5;
        life = maxLife;

        hitbox.x = 12;
        hitbox.y = 12;
        hitbox.width = 24;
        hitbox.height = 36;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;

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

    }

    /*public void update(){
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        int enemyIndex = gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.enemies);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true){
            if(gp.player.invincible == false){
                gp.player.life -=5;
                gp.player.invincible = true;
            }
        } //checks if enemy walks into player

        /*if(enemyIndex != 999){
            int otherCenterX = gp.enemies[enemyIndex].worldX + gp.tileSize/2;   
            int otherCenterY = gp.enemies[enemyIndex].worldY + gp.tileSize/2;
            int centerX = worldX + gp.tileSize/2;
            int centerY = worldY + gp.tileSize/2;

            switch(direction){
                case "up": 
                    gp.enemies[enemyIndex].worldY -= speed;
                    if(otherCenterX > centerX) gp.enemies[enemyIndex].worldX += speed;
                    else gp.enemies[enemyIndex].worldX -= speed;
                    break;
                case "down": 
                    gp.enemies[enemyIndex].worldY += speed;
                    if(otherCenterX > centerX) gp.enemies[enemyIndex].worldX += speed;
                    else gp.enemies[enemyIndex].worldX -= speed;
                    break;
                case "left": 
                    gp.enemies[enemyIndex].worldX -= speed;
                    if(otherCenterY > centerY) gp.enemies[enemyIndex].worldY -= speed;
                    else gp.enemies[enemyIndex].worldX += speed;
                    break;
                case "right": 
                    gp.enemies[enemyIndex].worldX += speed;
                    if(otherCenterY > centerY) gp.enemies[enemyIndex].worldY -= speed;
                    else gp.enemies[enemyIndex].worldX += speed;
                    break;
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
    }*/

}
