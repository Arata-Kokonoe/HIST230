package main;

import java.util.Random;

import enemies.ENE_sansCulotte;
import entity.Entity;
import object.OBJ_Exp_Crystal;

public class EnemySpawner {

    GamePanel gp;
    int maxEnemies, difficulty, spawnTimer; //maybe implement scaling based on difficulty later
    UtilityTool utool = new UtilityTool();

    public EnemySpawner(GamePanel gp){
        this.gp = gp;
        maxEnemies = 30;
        difficulty = 1;
    }

    public int spawnEnemy(){//spawns an enemy if a certain amount of frames have passed, can change based on difficulty
        //spawns an enemy slightly offscreen based on direction player is moving
        spawnTimer++;

        if(spawnTimer >= 100/difficulty && gp.enemies.size() != maxEnemies){
            gp.enemies.add(new ENE_sansCulotte(gp));
            int i = gp.enemies.size()-1;

            int x, y;
            
            Random rand = new Random();
            int randomNum = rand.nextInt(3); 
            
            switch (gp.player.direction) {
                case "upleft":
                    if (randomNum == 0){ //spawn in top left corner
                        x = gp.player.worldX - gp.screenWidth/2 - gp.tileSize;
                        y = gp.player.worldY - gp.screenHeight/2 - gp.tileSize;
                    }
                    else if (randomNum == 1){ //spawn in top left area
                        x = rand.nextInt(gp.player.worldX - (gp.player.worldX - gp.screenWidth/2)) + gp.player.worldX - gp.screenWidth/2;
                        y = gp.player.worldY - gp.screenHeight/2 - gp.tileSize;
                    }
                    else { //spawn in left top area
                        x = gp.player.worldX - gp.screenWidth/2 - gp.tileSize;
                        y = rand.nextInt(gp.player.worldY - (gp.player.worldY - gp.screenHeight/2)) + gp.player.worldY - gp.screenHeight/2;
                    }
                    break;
                case "upright": 
                    if (randomNum == 0){ //spawn in top right corner
                        x = gp.player.worldX + gp.screenWidth/2 + gp.tileSize;
                        y = gp.player.worldY - gp.screenHeight/2 - gp.tileSize;
                    }
                    else if (randomNum == 1){ //spawn in top right area
                        x = rand.nextInt((gp.player.worldX + gp.screenWidth/2) - gp.player.worldX) + gp.player.worldX;
                        y = gp.player.worldY - gp.screenHeight/2 - gp.tileSize;
                    }
                    else { //spawn in right top area
                        x = gp.player.worldX + gp.screenWidth/2 + gp.tileSize;
                        y = rand.nextInt(gp.player.worldY - (gp.player.worldY - gp.screenHeight/2)) + gp.player.worldY - gp.screenHeight/2;
                    }
                    break;
                case "downleft":
                    if (randomNum == 0){ //spawn in bot left corner
                        x = gp.player.worldX - gp.screenWidth/2 - gp.tileSize;
                        y = gp.player.worldY + gp.screenHeight/2 + gp.tileSize;
                    }
                    else if (randomNum == 1){ //spawn in bot left area
                        x = rand.nextInt(gp.player.worldX - (gp.player.worldX - gp.screenWidth/2)) + gp.player.worldX - gp.screenWidth/2;
                        y = gp.player.worldY + gp.screenHeight/2 + gp.tileSize;
                    }
                    else { //spawn in left bot area
                        x = gp.player.worldX - gp.screenWidth/2 - gp.tileSize;
                        y = rand.nextInt((gp.player.worldY + gp.screenHeight/2) - gp.player.worldY) + gp.player.worldY;
                    }
                    break;
                case "downright":
                    if (randomNum == 0){ //spawn in bot right corner
                        x = gp.player.worldX + gp.screenWidth/2 + gp.tileSize;
                        y = gp.player.worldY + gp.screenHeight/2 + gp.tileSize;
                    }
                    else if (randomNum == 1){ //spawn in bot right area
                        x = rand.nextInt((gp.player.worldX + gp.screenWidth/2) - gp.player.worldX) + gp.player.worldX;
                        y = gp.player.worldY + gp.screenHeight/2 + gp.tileSize;
                    }
                    else { //spawn in right bot area
                        x = gp.player.worldX + gp.screenWidth/2 + gp.tileSize;
                        y = rand.nextInt((gp.player.worldY + gp.screenHeight/2) - gp.player.worldY) + gp.player.worldY;
                    }
                    break;
                case "up":
                    x = rand.nextInt(gp.player.worldX + gp.screenWidth/2 - (gp.player.worldX - gp.screenWidth/2)) + (gp.player.worldX - gp.screenWidth/2);
                    y = gp.player.worldY - gp.screenHeight/2 - gp.tileSize;
                    break;
                case "down":
                    x = rand.nextInt(gp.player.worldX + gp.screenWidth/2 - (gp.player.worldX - gp.screenWidth/2)) + (gp.player.worldX - gp.screenWidth/2);
                    y = gp.player.worldY + gp.screenHeight/2 + gp.tileSize;
                    break;
                case "left":
                    x = gp.player.worldX - gp.screenWidth/2 - gp.tileSize;
                    y = rand.nextInt(gp.player.worldY + gp.screenHeight/2 - (gp.player.worldY - gp.screenHeight/2)) + (gp.player.worldY - gp.screenHeight/2);
                    break;
                case "right":
                    x = gp.player.worldX + gp.screenWidth/2 + gp.tileSize;
                    y = rand.nextInt(gp.player.worldY + gp.screenHeight/2 - (gp.player.worldY - gp.screenHeight/2)) + (gp.player.worldY - gp.screenHeight/2);
                    break;
                default:
                    x = 0;
                    y = 0;
                    break;
            }

            gp.enemies.get(i).worldX = x;
            gp.enemies.get(i).worldY = y;

            spawnTimer = 0;
            return i;
        }
        return -1;
    }

    public int spawnEnemyFast(){ //same as spawnEnemy but without the spawnTimer

        if(gp.enemies.size() != maxEnemies){
            gp.enemies.add(new ENE_sansCulotte(gp));
            int i = gp.enemies.size()-1;

            int x, y;
            
            Random rand = new Random();
            int randomNum = rand.nextInt(3); 
            
            switch (gp.player.direction) {
                case "upleft":
                    if (randomNum == 0){ //spawn in top left corner
                        x = gp.player.worldX - gp.screenWidth/2 - gp.tileSize;
                        y = gp.player.worldY - gp.screenHeight/2 - gp.tileSize;
                    }
                    else if (randomNum == 1){ //spawn in top left area
                        x = rand.nextInt(gp.player.worldX - (gp.player.worldX - gp.screenWidth/2)) + gp.player.worldX - gp.screenWidth/2;
                        y = gp.player.worldY - gp.screenHeight/2 - gp.tileSize;
                    }
                    else { //spawn in left top area
                        x = gp.player.worldX - gp.screenWidth/2 - gp.tileSize;
                        y = rand.nextInt(gp.player.worldY - (gp.player.worldY - gp.screenHeight/2)) + gp.player.worldY - gp.screenHeight/2;
                    }
                    break;
                case "upright": 
                    if (randomNum == 0){ //spawn in top right corner
                        x = gp.player.worldX + gp.screenWidth/2 + gp.tileSize;
                        y = gp.player.worldY - gp.screenHeight/2 - gp.tileSize;
                    }
                    else if (randomNum == 1){ //spawn in top right area
                        x = rand.nextInt((gp.player.worldX + gp.screenWidth/2) - gp.player.worldX) + gp.player.worldX;
                        y = gp.player.worldY - gp.screenHeight/2 - gp.tileSize;
                    }
                    else { //spawn in right top area
                        x = gp.player.worldX + gp.screenWidth/2 + gp.tileSize;
                        y = rand.nextInt(gp.player.worldY - (gp.player.worldY - gp.screenHeight/2)) + gp.player.worldY - gp.screenHeight/2;
                    }
                    break;
                case "downleft":
                    if (randomNum == 0){ //spawn in bot left corner
                        x = gp.player.worldX - gp.screenWidth/2 - gp.tileSize;
                        y = gp.player.worldY + gp.screenHeight/2 + gp.tileSize;
                    }
                    else if (randomNum == 1){ //spawn in bot left area
                        x = rand.nextInt(gp.player.worldX - (gp.player.worldX - gp.screenWidth/2)) + gp.player.worldX - gp.screenWidth/2;
                        y = gp.player.worldY + gp.screenHeight/2 + gp.tileSize;
                    }
                    else { //spawn in left bot area
                        x = gp.player.worldX - gp.screenWidth/2 - gp.tileSize;
                        y = rand.nextInt((gp.player.worldY + gp.screenHeight/2) - gp.player.worldY) + gp.player.worldY;
                    }
                    break;
                case "downright":
                    if (randomNum == 0){ //spawn in bot right corner
                        x = gp.player.worldX + gp.screenWidth/2 + gp.tileSize;
                        y = gp.player.worldY + gp.screenHeight/2 + gp.tileSize;
                    }
                    else if (randomNum == 1){ //spawn in bot right area
                        x = rand.nextInt((gp.player.worldX + gp.screenWidth/2) - gp.player.worldX) + gp.player.worldX;
                        y = gp.player.worldY + gp.screenHeight/2 + gp.tileSize;
                    }
                    else { //spawn in right bot area
                        x = gp.player.worldX + gp.screenWidth/2 + gp.tileSize;
                        y = rand.nextInt((gp.player.worldY + gp.screenHeight/2) - gp.player.worldY) + gp.player.worldY;
                    }
                    break;
                case "up":
                    x = rand.nextInt(gp.player.worldX + gp.screenWidth/2 - (gp.player.worldX - gp.screenWidth/2)) + (gp.player.worldX - gp.screenWidth/2);
                    y = gp.player.worldY - gp.screenHeight/2 - gp.tileSize;
                    break;
                case "down":
                    x = rand.nextInt(gp.player.worldX + gp.screenWidth/2 - (gp.player.worldX - gp.screenWidth/2)) + (gp.player.worldX - gp.screenWidth/2);
                    y = gp.player.worldY + gp.screenHeight/2 + gp.tileSize;
                    break;
                case "left":
                    x = gp.player.worldX - gp.screenWidth/2 - gp.tileSize;
                    y = rand.nextInt(gp.player.worldY + gp.screenHeight/2 - (gp.player.worldY - gp.screenHeight/2)) + (gp.player.worldY - gp.screenHeight/2);
                    break;
                case "right":
                    x = gp.player.worldX + gp.screenWidth/2 + gp.tileSize;
                    y = rand.nextInt(gp.player.worldY + gp.screenHeight/2 - (gp.player.worldY - gp.screenHeight/2)) + (gp.player.worldY - gp.screenHeight/2);
                    break;
                default:
                    x = 0;
                    y = 0;
                    break;
            }

            gp.enemies.get(i).worldX = x;
            gp.enemies.get(i).worldY = y;

            return i;
        }
        return -1;
    }

    public void spawnExp(int x, int y){ //creates exp crystal, takes x and y position parameters
        int freeIndex = utool.checkEntityArr(gp.obj);

        if (freeIndex != -1){
            gp.obj[freeIndex] = new OBJ_Exp_Crystal(gp);
            gp.obj[freeIndex].worldX = x + 18;
            gp.obj[freeIndex].worldY = y + 36;
        }
    }

}
