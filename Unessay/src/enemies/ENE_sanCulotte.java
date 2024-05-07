package enemies;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class ENE_sanCulotte extends Entity{
    public ENE_sanCulotte(GamePanel gp){
        super(gp);

        type = 2;
        name = "San Culotte";
        direction = "right";
        leftOrRight = "right";
        speed = 1;
        maxLife = 20;
        life = maxLife;

        hitbox.x = 8;
        hitbox.y = 0;
        hitbox.width = 30;
        hitbox.height = 48;
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
        
        int playerCenterX = (int)(gp.player.worldX + gp.player.hitbox.width/2);
        int playerCenterY = (int)(gp.player.worldY + gp.player.hitbox.height/2);
        int enemyCenterX = (int)(worldX + hitbox.width/2);
        int enemyCenterY = (int)(worldY + hitbox.height/2);
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


}
