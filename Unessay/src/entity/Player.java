package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    //public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH){

        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        hitbox = new Rectangle();
        hitbox.x = 12;
        hitbox.y = 16;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;
        hitbox.width = 24;
        hitbox.height = 32;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "right";
        leftOrRight = "right";
    }

    public void getPlayerImage(){
        
        /*up1 = setup("faceless_CHS_right1");
        up2 = setup("faceless_CHS_right1");
        down1 = setup("faceless_CHS_right1");
        down2 = setup("faceless_CHS_right1");*/
        left0 = setup("/player/faceless_CHS_left0");
        left1 = setup("/player/faceless_CHS_left1");
        left2 = setup("/player/faceless_CHS_left2");
        left3 = setup("/player/faceless_CHS_left3");
        right0 = setup("/player/faceless_CHS_right0");
        right1 = setup("/player/faceless_CHS_right1");
        right2 = setup("/player/faceless_CHS_right2");
        right3 = setup("/player/faceless_CHS_right3");
        
        
        /*up1 = setup("CHS_up_1");
        up2 = setup("CHS_up_2");
        down1 = setup("CHS_down_1");
        down2 = setup("CHS_down_2");
        left1 = setup("CHS_left_1");
        left2 = setup("CHS_left_2");
        right1 = setup("CHS_right_1");
        right2 = setup("CHS_right_2");*/
    }

    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true
            || keyH.leftPressed == true || keyH.rightPressed == true){

            if (keyH.upPressed == true && keyH.leftPressed == true){
                direction = "upleft";
                leftOrRight = "left";
            }
            else if (keyH.upPressed == true && keyH.rightPressed == true){
                direction = "upright";
                leftOrRight = "right";
            }
            else if (keyH.downPressed == true && keyH.leftPressed == true){
                direction = "downleft";
                leftOrRight = "left";
            }
            else if (keyH.downPressed == true && keyH.rightPressed == true){
                direction = "downright";
                leftOrRight = "right";
            }
            else if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
                leftOrRight = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                leftOrRight = "right";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
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
        }
    }

    public void updateDialogue(){
        if(gp.keyH.interactPressed == true){
            gp.talkingEntity.speak();
        }
        gp.keyH.interactPressed = false; 
    }
    public void pickUpObject(int i){
        
        if(i != 999){

            /*String objectName = gp.obj[i].name;

            switch(objectName){
            case "Key":
                gp.playSE(1);
                hasKey++;
                gp.obj[i] = null;    
                gp.ui.showMessage("You got a key!");
                break;
            case "Door":
                if(hasKey > 0){
                    gp.playSE(3);
                    gp.obj[i] = null;
                    hasKey--;
                    gp.ui.showMessage("You opened the door!");
                }
                else{
                    gp.ui.showMessage("You need a key!");
                }
                break;
            case "Boots":
                gp.playSE(2);
                speed += 2;
                gp.obj[i] = null;
                gp.ui.showMessage("Speed up!");
                break;
            case "Chest":
                gp.ui.gameFinished = true;
                gp.stopMusic();
                gp.playSE(4);
                break;
            }*/
        }
    }

    public void interactNPC(int i){
        if (i != 999){
            
            if(gp.keyH.interactPressed == true){
                gp.talkingEntity = gp.npc[i];
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyH.interactPressed = false;
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch(direction){
            case "up":
                if(leftOrRight == "left"){
                    if(spriteNum == 1) image = left0;
                    if(spriteNum == 2) image = left1;
                    if(spriteNum == 3) image = left2;
                    if(spriteNum == 4) image = left3;
                }
                if(leftOrRight == "right"){
                    if(spriteNum == 1) image = right0;
                    if(spriteNum == 2) image = right1;
                    if(spriteNum == 3) image = right2;
                    if(spriteNum == 4) image = right3;
                }
                break;
            case "down":
                if(leftOrRight == "left"){
                    if(spriteNum == 1) image = left0;
                    if(spriteNum == 2) image = left1;
                    if(spriteNum == 3) image = left2;
                    if(spriteNum == 4) image = left3;
                }
                if(leftOrRight == "right"){
                    if(spriteNum == 1) image = right0;
                    if(spriteNum == 2) image = right1;
                    if(spriteNum == 3) image = right2;
                    if(spriteNum == 4) image = right3;
                }
                break;
            case "left":
                if(spriteNum == 1) image = left0;
                if(spriteNum == 2) image = left1;
                if(spriteNum == 3) image = left2;
                if(spriteNum == 4) image = left3;
                break;
            case "upleft":
                if(spriteNum == 1) image = left0;
                if(spriteNum == 2) image = left1;
                if(spriteNum == 3) image = left2;
                if(spriteNum == 4) image = left3;
                break;
            case "downleft":
                if(spriteNum == 1) image = left0;
                if(spriteNum == 2) image = left1;
                if(spriteNum == 3) image = left2;
                if(spriteNum == 4) image = left3;
                break;
            case "right":
                if(spriteNum == 1) image = right0;
                if(spriteNum == 2) image = right1;
                if(spriteNum == 3) image = right2;
                if(spriteNum == 4) image = right3;
                break;
            case "upright":
                if(spriteNum == 1) image = right0;
                if(spriteNum == 2) image = right1;
                if(spriteNum == 3) image = right2;
                if(spriteNum == 4) image = right3;
                break;
            case "downright":
                if(spriteNum == 1) image = right0;
                if(spriteNum == 2) image = right1;
                if(spriteNum == 3) image = right2;
                if(spriteNum == 4) image = right3;
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }

}
