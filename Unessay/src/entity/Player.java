package entity;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Shield;
import object.OBJ_Sword_Normal;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int standCounter;
    public boolean attackCanceled = false;

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

        attackArea.width = 48;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues(){

        worldX = gp.tileSize * 50;
        worldY = gp.tileSize * 50;
        speed = 4;
        direction = "right";
        leftOrRight = "right";

        //PLAYER STATUS
        level = 1;
        maxLife = 100;
        life = maxLife;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield(gp);
        attack = getAttack(); //total attack value determined by strength * weaponValue
        defense = getDefense(); //total defense value determined by dexterity * shieldValue
    }

    public int getAttack(){
        return strength * currentWeapon.attackValue;
    }

    public int getDefense(){
        return dexterity * currentShield.defenseValue;
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

    public void getPlayerAttackImage(){

        attackLeft1 = setup("/player/faceless_CHS_attackLeft1", 96, 48);
        attackLeft2 = setup("/player/faceless_CHS_attackLeft2", 96, 48);
        attackRight1 = setup("/player/faceless_CHS_attackRight1", 96, 48);
        attackRight2 = setup("/player/faceless_CHS_attackRight2", 96, 48);

    }

    public void update(){

        if(attacking == true){
            attacking();
        }

        else if(keyH.upPressed == true || keyH.downPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true || keyH.interactPressed == true){

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

            // CHECK ENEMY COLLISION
            int enemyIndex = gp.cChecker.checkEntity(this, gp.enemies);
            contactEnemy(enemyIndex);

            //CHECK EVENT COLLISION
            gp.eHandler.checkEvent();

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
                        else gp.enemies[enemyIndex].worldY += speed;
                        break;
                    case "right": 
                        gp.enemies[enemyIndex].worldX += speed;
                        if(otherCenterY > centerY) gp.enemies[enemyIndex].worldY -= speed;
                        else gp.enemies[enemyIndex].worldY += speed;
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
            }*/

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false && keyH.interactPressed != true){
                switch(direction){
                case "up": 
                    worldY -= speed;
                    if (enemyIndex != 999) {
                        gp.enemies[enemyIndex].worldY -= speed; 
                        /*if (gp.enemies[enemyIndex].worldX)
                        int playerCenterX = (int)(gp.player.worldX + gp.player.hitbox.width/2);
                        int playerCenterY = (int)(gp.player.worldY + gp.player.hitbox.height/2);
                        int enemyCenterX = (int)(worldX + hitbox.width/2);
                        int enemyCenterY = (int)(worldY + hitbox.height/2);*/
                    }
                    break;
                case "down": 
                    worldY += speed; 
                    if (enemyIndex != 999) gp.enemies[enemyIndex].worldY += speed; 
                    break;
                case "left": 
                    worldX -= speed; 
                    if (enemyIndex != 999) gp.enemies[enemyIndex].worldX -= speed; 
                    break;
                case "right": 
                    worldX += speed; 
                    if (enemyIndex != 999) gp.enemies[enemyIndex].worldX += speed; 
                    break;
                case "upleft": 
                    worldY -= speed;
                    worldX -= speed; 
                    if (enemyIndex != 999) gp.enemies[enemyIndex].worldX -= speed; 
                    if (enemyIndex != 999) gp.enemies[enemyIndex].worldY -= speed; 
                    break;
                case "upright": 
                    worldY -= speed;
                    worldX += speed; 
                    if (enemyIndex != 999) gp.enemies[enemyIndex].worldX += speed; 
                    if (enemyIndex != 999) gp.enemies[enemyIndex].worldY -= speed; 
                    break;
                case "downleft": 
                    worldY += speed;
                    worldX -= speed; 
                    if (enemyIndex != 999) gp.enemies[enemyIndex].worldX -= speed; 
                    if (enemyIndex != 999) gp.enemies[enemyIndex].worldY += speed; 
                    break;
                case "downright": 
                    worldY += speed;
                    worldX += speed; 
                    if (enemyIndex != 999) gp.enemies[enemyIndex].worldX += speed; 
                    if (enemyIndex != 999) gp.enemies[enemyIndex].worldY += speed; 
                    break;
                }
            }

            if(keyH.interactPressed == true && attackCanceled == false){
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gp.keyH.interactPressed = false; //turn off interact pressed outside of check methods so it doesnt constantly turn off

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
        else {
            standCounter++;
            if(standCounter == 20){
                spriteNum = 1;
                standCounter = 0;
            }
        }
        //This needs to be outside of key if statement
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 10){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void updateDialogue(){
        if(gp.keyH.interactPressed == true){
            if(gp.talkingEntity != null) gp.talkingEntity.speak();
        }
        gp.keyH.interactPressed = false; 
        System.out.println("interactPressed = false");
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

    public void attacking(){
        spriteCounter++;

        if (spriteCounter <= 5){
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;

            //save current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int hitboxWidth = hitbox.width;
            int hitboxHeight = hitbox.height;

            //Adjust player's worldX/Y for the attack
            switch(leftOrRight){
                case "left":
                    worldX += hitbox.x - attackArea.width;
                    break;
                case "right":
                    worldX += gp.tileSize - hitbox.x;
                    break;
            }

            //player's hitbox becomes the attack hitbox
            hitbox.width = attackArea.width;
            hitbox.height = attackArea.height;

            //check enemy collision with the updated variables
            int enemyIndex = gp.cChecker.checkEntity(this, gp.enemies);
            damageEnemy(enemyIndex);
            
            worldX = currentWorldX;
            worldY = currentWorldY;
            hitbox.width = hitboxWidth;
            hitbox.height = hitboxHeight;

        }
        if (spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }

    }

    public void interactNPC(int i){
        if (i != 999){
            if(gp.keyH.interactPressed == true){
                attackCanceled = true;
                gp.talkingEntity = gp.npc[i];
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        } //if there is no npc nearby and player presses interact (space), then it attacks instead
        
    }

    public void contactEnemy(int i){
        if (i != 999){
            if(invincible == false && gp.enemies[i].alive == true && gp.enemies[i].dying == false){
                gp.playSE(5);

                int damage = gp.enemies[i].attack - defense;
                if(damage < 0) damage = 0;

                life -= damage;
                invincible = true;
            }
        }
    } //when player walks into enemy

    public void damageEnemy(int i){
        if (i != 999){
            if(gp.enemies[i].invincible == false && gp.enemies[i].dying == false){
                
                gp.playSE(5);

                int damage = attack - gp.enemies[i].defense;
                if(damage < 0) damage = 0;

                gp.enemies[i].life -= damage;
                gp.ui.addMessage(damage + " damage!");
                
                gp.enemies[i].invincible = true;

                if(gp.enemies[i].life <= 0){
                    gp.enemies[i].dying = true;
                    gp.ui.addMessage("killed the " + gp.enemies[i].name + "!");
                }
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch(leftOrRight){
            case "left":
                if(attacking == false){
                    if (spriteNum == 1) image = left0;
                    else if (spriteNum == 2) image = left1;
                    else if (spriteNum == 3) image = left2;
                    else if (spriteNum == 4) image = left3;
                }
                if(attacking == true){
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) image = attackLeft1;
                    else if (spriteNum == 2) image = attackLeft2;
                    else image = attackLeft2;
                }
                break;
            case "right":
                if(attacking == false){
                    if (spriteNum == 1) image = right0;
                    else if (spriteNum == 2) image = right1;
                    else if (spriteNum == 3) image = right2;
                    else if (spriteNum == 4) image = right3;
                }
                if(attacking == true){
                    if (spriteNum == 1) image = attackRight1;
                    else if (spriteNum == 2) image = attackRight2;
                    else image = attackRight2;
                }
                break;
        } //end of switch

        if (invincible == true){ //although it may seem contradictory, I use this invinvible time to signal that the player took damage (so he turns red)
            //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            //opacity of 0.3 (aka 70% transparent)

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
            g2.drawImage(tinted, tempScreenX, tempScreenY, null);
            //code to tint an image taken from https://stackoverflow.com/questions/14225518/tinting-image-in-java-improvement?noredirect=1&lq=1
            
            //later, add code for blood particles
        }
        else{
            changeAlpha(g2, 1f);
            g2.drawImage(image, tempScreenX, tempScreenY, null); //draw player
        }
        //reset alpha
        //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    } //end of draw method

} //end of Player class
