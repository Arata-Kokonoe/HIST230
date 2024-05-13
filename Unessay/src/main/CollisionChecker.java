package main;

import java.awt.Rectangle;

import entity.Entity;

public class CollisionChecker {

    private GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    /*public void checkTile(Entity entity){
    
        int entityLeftWorldX = entity.worldX + entity.hitbox[0].x; //find left x of hitbox
        int entityRightWorldX = entity.worldX + entity.hitbox[0].x + entity.hitbox[0].width;  //find right x of hitbox
        int entityTopWorldY = entity.worldY + entity.hitbox[0].y;  //find top y of hitbox
        int entityBotWorldY = entity.worldY + entity.hitbox[0].y + entity.hitbox[0].height;   //find bottom y of hitbox

        int entityLeftCol = entityLeftWorldX/gp.tileSize;   //find which column the left x is in
        int entityRightCol = entityRightWorldX/gp.tileSize; //find which column the right x is in
        int entityTopRow = entityTopWorldY/gp.tileSize; //find which row the top y is in
        int entityBotRow = entityBotWorldY/gp.tileSize; //find which row the bot y is in

        int tileNum1, tileNum2, tileNum3; //only need to check two tiles in each direction

        switch(entity.direction){
        case "up":
            entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;    //what row is entity trying to move up into?
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];    //two possible tiles (since player could be between two tiles)
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;  //if either tile has collision on, change boolean to make entity unable to move into them
            }
            break;
            //rest of the directions follow this pattern
        case "down":
            entityBotRow = (entityBotWorldY + entity.speed)/gp.tileSize;    
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBotRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBotRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
            break;
        case "left":
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize; 
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBotRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
            break;
        case "right":
            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBotRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
            break;
        case "downright":
            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;    //more complex because player can move into 3 tiles diagonally
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityBotRow];
            entityBotRow = (entityBotWorldY + entity.speed)/gp.tileSize;
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBotRow];
            entityRightCol = entityRightWorldX/gp.tileSize;
            tileNum3 = gp.tileM.mapTileNum[entityRightCol][entityBotRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || 
               gp.tileM.tile[tileNum3].collision == true ){
                entity.collisionOn = true;
            }
            break;
        case "upright":
            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            entityRightCol = entityRightWorldX/gp.tileSize;
            tileNum3 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || 
            gp.tileM.tile[tileNum3].collision == true ){
                entity.collisionOn = true;
            }
            break;
        case "downleft":
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBotRow];
            entityBotRow = (entityBotWorldY + entity.speed)/gp.tileSize;
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBotRow];
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
            tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityBotRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || 
            gp.tileM.tile[tileNum3].collision == true ){
                entity.collisionOn = true;
            }
            break;
        case "upleft":
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize; 
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
            tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || 
            gp.tileM.tile[tileNum3].collision == true ){
                entity.collisionOn = true;
            }
            break;
        }
    }*/

    /*public void checkAura(Entity entity, Entity[] enemies){

        int initHitboxWidth, initHitboxHeight, otherInitHitboxWidth, otherInitHitboxHeight;

        entity.hitbox.x = entity.worldX + entity.hitbox.x;
        entity.hitbox.y = entity.worldY + entity.hitbox.y;
        initHitboxWidth = entity.hitbox.width;
        entity.hitbox.width += 5;
        initHitboxHeight = entity.hitbox.height;
        entity.hitbox.height += 5;

        for(int i = 0; i < gp.enemies.length; i++){
            if (gp.enemies[i] != null){
                gp.enemies[i].hitbox.x = gp.enemies[i].worldX + gp.enemies[i].hitbox.x;
                gp.enemies[i].hitbox.y = gp.enemies[i].worldY + gp.enemies[i].hitbox.y;
                otherInitHitboxWidth = gp.enemies[i].hitbox.width;
                gp.enemies[i].hitbox.width += 5;
                otherInitHitboxHeight = gp.enemies[i].hitbox.height;
                gp.enemies[i].hitbox.height += 5;

                if(entity.hitbox.intersects(gp.enemies[i].hitbox)){
                    if(enemies[i] != entity){
                        switch(entity.direction){
                            case "up":
                                gp.enemies[i].worldY--;
                                break;
                            case "down":
                                gp.enemies[i].worldY++;
                                break;
                            case "left":
                                gp.enemies[i].worldX--;
                                break;
                            case "right":
                                gp.enemies[i].worldX++;
                                break;
                            case "upleft":
                                gp.enemies[i].worldX--;
                                gp.enemies[i].worldY--;
                                break;
                            case "downleft":
                                gp.enemies[i].worldX--;
                                gp.enemies[i].worldY++;
                                break;
                            case "upright":
                                gp.enemies[i].worldX++;
                                gp.enemies[i].worldY--;
                                break;
                            case "downright":
                                gp.enemies[i].worldX++;
                                gp.enemies[i].worldY++;
                                break;
                            }
                    }
                }

                gp.enemies[i].hitbox.x = gp.enemies[i].hitboxDefaultX;
                gp.enemies[i].hitbox.y = gp.enemies[i].hitboxDefaultY;
                gp.enemies[i].hitbox.width = otherInitHitboxWidth;
                gp.enemies[i].hitbox.height = otherInitHitboxHeight;
            }
        }

        entity.hitbox.x = entity.hitboxDefaultX;
        entity.hitbox.y = entity.hitboxDefaultY;
        entity.hitbox.width = initHitboxWidth;
        entity.hitbox.height = initHitboxHeight;

    }*/

    public int checkObject(Entity entity, boolean player){
        int index = 999; //returns 999 if checkObject doesn't find anything

        for(int i = 0; i < gp.obj.length; i++){

            if(gp.obj[i] != null){

                // Get entity's hitbox position
                entity.hitbox[0].x = entity.worldX + entity.hitbox[0].x;
                entity.hitbox[0].y = entity.worldY + entity.hitbox[0].y;
                //Get the object's hitbox position
                gp.obj[i].hitbox[0].x = gp.obj[i].worldX + gp.obj[i].hitbox[0].x;
                gp.obj[i].hitbox[0].y = gp.obj[i].worldY + gp.obj[i].hitbox[0].y;

                switch(entity.direction){ //change hitbox position based on direction entity is moving
                case "up":
                    entity.hitbox[0].y -= entity.speed;
                    break;
                case "down":
                    entity.hitbox[0].y += entity.speed;
                    break;
                case "left":
                    entity.hitbox[0].x -= entity.speed;
                    break;
                case "right":
                    entity.hitbox[0].x += entity.speed;
                    break;
                case "upleft":
                    entity.hitbox[0].x -= entity.speed;
                    entity.hitbox[0].y -= entity.speed;
                    break;
                case "downleft":
                    entity.hitbox[0].x -= entity.speed;
                    entity.hitbox[0].y += entity.speed;
                    break;
                case "upright":
                    entity.hitbox[0].x += entity.speed;
                    entity.hitbox[0].y -= entity.speed;
                    break;
                case "downright":
                    entity.hitbox[0].x += entity.speed;
                    entity.hitbox[0].y += entity.speed;
                    break;
                default:
                    break;
                }

                if(entity.hitbox[0].intersects(gp.obj[i].hitbox[0])){ //if the newly moved hitbox intersects an object's hitbox
                    if(gp.obj[i].collision == true){
                        entity.collisionOn = true;  //turn collisionOn boolean to true
                    }
                    if(player == true){
                        index = i;  //if its the player that this method was called using, then return index of object
                        //otherwise, we wouldn't need it since other entities cannot pick up item
                    }
                }
                //reset the hitbox positions we changed in the method
                entity.hitbox[0].x = entity.hitboxDefaultX;
                entity.hitbox[0].y = entity.hitboxDefaultY;
                gp.obj[i].hitbox[0].x = gp.obj[i].hitboxDefaultX;
                gp.obj[i].hitbox[0].y = gp.obj[i].hitboxDefaultY;
            }
        }
        return index;
    }

    //NPC OR MONSTER
    public int checkEntity(Entity entity, Entity[] target){ //very similar to checkObj code, but mainly used for enemies

        int index = 999;

        for(int i = 0; i < target.length; i++){

            if(target[i] != null && target[i].dying != true){

                // Get entity's hitbox position
                entity.hitbox[0].x = entity.worldX + entity.hitbox[0].x;
                entity.hitbox[0].y = entity.worldY + entity.hitbox[0].y;
                //Get the object's hitbox position
                target[i].hitbox[0].x = target[i].worldX + target[i].hitbox[0].x;
                target[i].hitbox[0].y = target[i].worldY + target[i].hitbox[0].y;

                switch(entity.direction){
                case "up":
                    entity.hitbox[0].y -= entity.speed;
                    break;
                case "down":
                    entity.hitbox[0].y += entity.speed;
                    break;
                case "left":
                    entity.hitbox[0].x -= entity.speed;
                    break;
                case "right":
                    entity.hitbox[0].x += entity.speed;
                    break;
                case "upleft":
                    entity.hitbox[0].x -= entity.speed;
                    entity.hitbox[0].y -= entity.speed;
                    break;
                case "downleft":
                    entity.hitbox[0].x -= entity.speed;
                    entity.hitbox[0].y += entity.speed;
                    break;
                case "upright":
                    entity.hitbox[0].x += entity.speed;
                    entity.hitbox[0].y -= entity.speed;
                    break;
                case "downright":
                    entity.hitbox[0].x += entity.speed;
                    entity.hitbox[0].y += entity.speed;
                    break;
                }
                if(entity.hitbox[0].intersects(target[i].hitbox[0])){
                    if(target[i] != entity){
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                    

                entity.hitbox[0].x = entity.hitboxDefaultX;
                entity.hitbox[0].y = entity.hitboxDefaultY;
                target[i].hitbox[0].x = target[i].hitboxDefaultX;
                target[i].hitbox[0].y = target[i].hitboxDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity){ //used by enemies to check if they contacted player
        //similar code to checkObj and checkEntity

        boolean contactPlayer = false;

        // Get entity's hitbox position
        entity.hitbox[0].x = entity.worldX + entity.hitbox[0].x;
        entity.hitbox[0].y = entity.worldY + entity.hitbox[0].y;
        //Get the object's hitbox position
        gp.player.hitbox[0].x = gp.player.worldX + gp.player.hitbox[0].x;
        gp.player.hitbox[0].y = gp.player.worldY + gp.player.hitbox[0].y;

        switch(entity.direction){
        case "up":
            entity.hitbox[0].y -= entity.speed;
            break;
        case "down":
            entity.hitbox[0].y += entity.speed;
            break;
        case "left":
            entity.hitbox[0].x -= entity.speed;
            break;
        case "right":
            entity.hitbox[0].x += entity.speed;
            break;
        case "upleft":
            entity.hitbox[0].x -= entity.speed;
            entity.hitbox[0].y -= entity.speed;
            break;
        case "downleft":
            entity.hitbox[0].x -= entity.speed;
            entity.hitbox[0].y += entity.speed;
            break;
        case "upright":
            entity.hitbox[0].x += entity.speed;
            entity.hitbox[0].y -= entity.speed;
            break;
        case "downright":
            entity.hitbox[0].x += entity.speed;
            entity.hitbox[0].y += entity.speed;
            break;
        }
        if(entity.hitbox[0].intersects(gp.player.hitbox[0])){
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.hitbox[0].x = entity.hitboxDefaultX;
        entity.hitbox[0].y = entity.hitboxDefaultY;
        gp.player.hitbox[0].x = gp.player.hitboxDefaultX;
        gp.player.hitbox[0].y = gp.player.hitboxDefaultY;

        return contactPlayer;
    } 

    public void checkWeaponHit(Entity weapon, Entity[] enemies){

        for(Rectangle hitbox : weapon.hitbox){
            hitbox.x = (int)(gp.player.worldX - 48*weapon.size); //48 because rn base size is 48
            hitbox.width = (int)(48*weapon.size*2 + gp.tileSize);  //width = 2 slashes + size of player which is gp.tilesize
            hitbox.y = (gp.player.worldY);
            hitbox.height = gp.tileSize;

            for(int i = 0; i < enemies.length; i++){
                if(enemies[i] != null){
                    if(weapon.enemiesHit.indexOf(enemies[i]) == -1){
                        enemies[i].hitbox[0].x = enemies[i].worldX + enemies[i].hitbox[0].x;
                        enemies[i].hitbox[0].y = enemies[i].worldY + enemies[i].hitbox[0].y;
                        if(hitbox.intersects(enemies[i].hitbox[0])){
                            //gp.playSE(5);i
                            
                            double damage = weapon.damage * gp.player.damageMultiplier * (1-enemies[i].damageReduction);
                            if(damage < 0) damage = 0;
                            enemies[i].life -= damage;
                            gp.ui.addMessage(damage + " damage!");

                            enemies[i].hit = true;
                            enemies[i].lastDamageTaken = (int)damage;
                            weapon.enemiesHit.add(enemies[i]);
                            weapon.enemiesHitTimer.add(0);

                            if(gp.enemies[i].life <= 0){
                                gp.enemies[i].dying = true;
                                gp.ui.addMessage("killed the " + gp.enemies[i].name + "!");
                                gp.eSpawner.spawnExp(gp.enemies[i].worldX, gp.enemies[i].worldY);
                            }
                        }
                        enemies[i].hitbox[0].x = enemies[i].hitboxDefaultX;
                        enemies[i].hitbox[0].y = enemies[i].hitboxDefaultY;   
                    }
                }
            }
        }
        
    }

    public void checkRoundWeaponHit(Entity weapon, Entity[] enemies){

        weapon.roundHitbox.x = (gp.player.worldX - 48*weapon.size); //48 because rn base size is 48
        weapon.roundHitbox.width = (48*weapon.size*3);  //width = 2 slashes + size of player which is gp.tilesize
        weapon.roundHitbox.y = (gp.player.worldY - 48*weapon.size);
        weapon.roundHitbox.height = (48*weapon.size*3);

        for(int i = 0; i < enemies.length; i++){ // i love you <3
            if(enemies[i] != null){
                if(weapon.enemiesHit.indexOf(enemies[i]) == -1){
                    enemies[i].hitbox[0].x = enemies[i].worldX + enemies[i].hitbox[0].x;
                    enemies[i].hitbox[0].y = enemies[i].worldY + enemies[i].hitbox[0].y;
                    if(weapon.roundHitbox.intersects(enemies[i].hitbox[0])){
                        //gp.playSE(5);
                        double damage = weapon.damage * gp.player.damageMultiplier * (1-enemies[i].damageReduction);
                        if(damage < 0) damage = 0;
                        enemies[i].life -= damage;
                        gp.ui.addMessage(damage + " damage!");

                        enemies[i].hit = true;
                        enemies[i].lastDamageTaken = (int)damage;
                        weapon.enemiesHit.add(enemies[i]);
                        weapon.enemiesHitTimer.add(0);

                        if(gp.enemies[i].life <= 0){
                            gp.enemies[i].dying = true;
                            gp.ui.addMessage("killed the " + gp.enemies[i].name + "!");
                            gp.eSpawner.spawnExp(gp.enemies[i].worldX, gp.enemies[i].worldY);
                        }
                    }
                    enemies[i].hitbox[0].x = enemies[i].hitboxDefaultX;
                    enemies[i].hitbox[0].y = enemies[i].hitboxDefaultY;
                }
            }
        }
    }
}
