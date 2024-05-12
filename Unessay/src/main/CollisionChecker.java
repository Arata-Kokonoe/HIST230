package main;

import entity.Entity;

public class CollisionChecker {

    private GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        
        int entityLeftWorldX = entity.worldX + entity.hitbox.x; //find left x of hitbox
        int entityRightWorldX = entity.worldX + entity.hitbox.x + entity.hitbox.width;  //find right x of hitbox
        int entityTopWorldY = entity.worldY + entity.hitbox.y;  //find top y of hitbox
        int entityBotWorldY = entity.worldY + entity.hitbox.y + entity.hitbox.height;   //find bottom y of hitbox

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
    }

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
                entity.hitbox.x = entity.worldX + entity.hitbox.x;
                entity.hitbox.y = entity.worldY + entity.hitbox.y;
                //Get the object's hitbox position
                gp.obj[i].hitbox.x = gp.obj[i].worldX + gp.obj[i].hitbox.x;
                gp.obj[i].hitbox.y = gp.obj[i].worldY + gp.obj[i].hitbox.y;

                switch(entity.direction){ //change hitbox position based on direction entity is moving
                case "up":
                    entity.hitbox.y -= entity.speed;
                    break;
                case "down":
                    entity.hitbox.y += entity.speed;
                    break;
                case "left":
                    entity.hitbox.x -= entity.speed;
                    break;
                case "right":
                    entity.hitbox.x += entity.speed;
                    break;
                case "upleft":
                    entity.hitbox.x -= entity.speed;
                    entity.hitbox.y -= entity.speed;
                    break;
                case "downleft":
                    entity.hitbox.x -= entity.speed;
                    entity.hitbox.y += entity.speed;
                    break;
                case "upright":
                    entity.hitbox.x += entity.speed;
                    entity.hitbox.y -= entity.speed;
                    break;
                case "downright":
                    entity.hitbox.x += entity.speed;
                    entity.hitbox.y += entity.speed;
                    break;
                }

                if(entity.hitbox.intersects(gp.obj[i].hitbox)){ //if the newly moved hitbox intersects an object's hitbox
                    if(gp.obj[i].collision == true){
                        entity.collisionOn = true;  //turn collisionOn boolean to true
                    }
                    if(player == true){
                        index = i;  //if its the player that this method was called using, then return index of object
                        //otherwise, we wouldn't need it since other entities cannot pick up item
                    }
                }
                //reset the hitbox positions we changed in the method
                entity.hitbox.x = entity.hitboxDefaultX;
                entity.hitbox.y = entity.hitboxDefaultY;
                gp.obj[i].hitbox.x = gp.obj[i].hitboxDefaultX;
                gp.obj[i].hitbox.y = gp.obj[i].hitboxDefaultY;
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
                entity.hitbox.x = entity.worldX + entity.hitbox.x;
                entity.hitbox.y = entity.worldY + entity.hitbox.y;
                //Get the object's hitbox position
                target[i].hitbox.x = target[i].worldX + target[i].hitbox.x;
                target[i].hitbox.y = target[i].worldY + target[i].hitbox.y;

                switch(entity.direction){
                case "up":
                    entity.hitbox.y -= entity.speed;
                    break;
                case "down":
                    entity.hitbox.y += entity.speed;
                    break;
                case "left":
                    entity.hitbox.x -= entity.speed;
                    break;
                case "right":
                    entity.hitbox.x += entity.speed;
                    break;
                case "upleft":
                    entity.hitbox.x -= entity.speed;
                    entity.hitbox.y -= entity.speed;
                    break;
                case "downleft":
                    entity.hitbox.x -= entity.speed;
                    entity.hitbox.y += entity.speed;
                    break;
                case "upright":
                    entity.hitbox.x += entity.speed;
                    entity.hitbox.y -= entity.speed;
                    break;
                case "downright":
                    entity.hitbox.x += entity.speed;
                    entity.hitbox.y += entity.speed;
                    break;
                }
                if(entity.hitbox.intersects(target[i].hitbox)){
                    if(target[i] != entity){
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                    

                entity.hitbox.x = entity.hitboxDefaultX;
                entity.hitbox.y = entity.hitboxDefaultY;
                target[i].hitbox.x = target[i].hitboxDefaultX;
                target[i].hitbox.y = target[i].hitboxDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity){ //used by enemies to check if they contacted player
        //similar code to checkObj and checkEntity

        boolean contactPlayer = false;

        // Get entity's hitbox position
        entity.hitbox.x = entity.worldX + entity.hitbox.x;
        entity.hitbox.y = entity.worldY + entity.hitbox.y;
        //Get the object's hitbox position
        gp.player.hitbox.x = gp.player.worldX + gp.player.hitbox.x;
        gp.player.hitbox.y = gp.player.worldY + gp.player.hitbox.y;

        switch(entity.direction){
        case "up":
            entity.hitbox.y -= entity.speed;
            break;
        case "down":
            entity.hitbox.y += entity.speed;
            break;
        case "left":
            entity.hitbox.x -= entity.speed;
            break;
        case "right":
            entity.hitbox.x += entity.speed;
            break;
        case "upleft":
            entity.hitbox.x -= entity.speed;
            entity.hitbox.y -= entity.speed;
            break;
        case "downleft":
            entity.hitbox.x -= entity.speed;
            entity.hitbox.y += entity.speed;
            break;
        case "upright":
            entity.hitbox.x += entity.speed;
            entity.hitbox.y -= entity.speed;
            break;
        case "downright":
            entity.hitbox.x += entity.speed;
            entity.hitbox.y += entity.speed;
            break;
        }
        if(entity.hitbox.intersects(gp.player.hitbox)){
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.hitbox.x = entity.hitboxDefaultX;
        entity.hitbox.y = entity.hitboxDefaultY;
        gp.player.hitbox.x = gp.player.hitboxDefaultX;
        gp.player.hitbox.y = gp.player.hitboxDefaultY;

        return contactPlayer;
    } 

    public void checkWeaponHit(Entity weapon, Entity[] enemies){

        weapon.hitbox.x = (int)(gp.player.worldX - 48*weapon.size); //48 because rn base size is 48
        weapon.hitbox.width = (int)(48*weapon.size*2 + gp.tileSize);  //width = 2 slashes + size of player which is gp.tilesize
        weapon.hitbox.y = (gp.player.worldY);
        weapon.hitbox.height = gp.tileSize;

        for(int i = 0; i < enemies.length; i++){
            if(enemies[i] != null){
                enemies[i].hitbox.x = enemies[i].worldX + enemies[i].hitbox.x;
                enemies[i].hitbox.y = enemies[i].worldY + enemies[i].hitbox.y;
                if(weapon.hitbox.intersects(enemies[i].hitbox)){
                    //gp.playSE(5);
                    if(enemies[i].invincible == false){
                        double damage = weapon.damage * gp.player.damageMultiplier * (1-enemies[i].damageReduction);
                        if(damage < 0) damage = 0;
                        enemies[i].life -= damage;
                        gp.ui.addMessage(damage + " damage!");

                        if(gp.enemies[i].life <= 0){
                            gp.enemies[i].dying = true;
                            gp.ui.addMessage("killed the " + gp.enemies[i].name + "!");
                            gp.eSpawner.spawnExp(gp.enemies[i].worldX, gp.enemies[i].worldY);
                        }
                    }
                }
                enemies[i].hitbox.x = enemies[i].hitboxDefaultX;
                enemies[i].hitbox.y = enemies[i].hitboxDefaultY;
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
                enemies[i].hitbox.x = enemies[i].worldX + enemies[i].hitbox.x;
                enemies[i].hitbox.y = enemies[i].worldY + enemies[i].hitbox.y;
                if(weapon.roundHitbox.intersects(enemies[i].hitbox)){
                    //gp.playSE(5);
                    if(enemies[i].invincible == false){
                        double damage = weapon.damage * gp.player.damageMultiplier * (1-enemies[i].damageReduction);
                        if(damage < 0) damage = 0;
                        enemies[i].life -= damage;
                        gp.ui.addMessage(damage + " damage!");
                        

                        if(gp.enemies[i].life <= 0){
                            gp.enemies[i].dying = true;
                            gp.ui.addMessage("killed the " + gp.enemies[i].name + "!");
                            gp.eSpawner.spawnExp(gp.enemies[i].worldX, gp.enemies[i].worldY);
                        }
                    }
                }
                enemies[i].hitbox.x = enemies[i].hitboxDefaultX;
                enemies[i].hitbox.y = enemies[i].hitboxDefaultY;
            }
        }
    }
}
