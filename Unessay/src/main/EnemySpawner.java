package main;

import java.util.Random;

import enemies.ENE_sanCulotte;

public class EnemySpawner {

    GamePanel gp;
    int maxEnemies, difficulty, spawnTimer; //maybe implement scaling based on difficulty later

    public EnemySpawner(GamePanel gp){
        this.gp = gp;
        maxEnemies = 10;
        difficulty = 1;
    }

    public int checkEnemyArr(){
        for (int i = 0; i < maxEnemies; i++){
            if (gp.enemies[i] == null) return i; //find and return empty index in enemy array
        }

        return -1;  //if none, return -1
    }

    public int spawnEnemy(){
        int freeIndex = checkEnemyArr();
        spawnTimer++;

        if(spawnTimer == 600/difficulty && freeIndex != -1){
            gp.enemies[freeIndex] = new ENE_sanCulotte(gp);

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

            gp.enemies[freeIndex].worldX = x;
            gp.enemies[freeIndex].worldY = y;

            spawnTimer = 0;
            return freeIndex;
        }
        return -1;
    }

    public int spawnEnemyFast(){
        int freeIndex = checkEnemyArr();

        if(freeIndex != -1){
            gp.enemies[freeIndex] = new ENE_sanCulotte(gp);

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

            gp.enemies[freeIndex].worldX = x;
            gp.enemies[freeIndex].worldY = y;

            return freeIndex;
        }
        return -1;
    }

}
