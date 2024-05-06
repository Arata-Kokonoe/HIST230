package main;

import entity.Entity;

public class CollisionChecker {

    private GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        
        int entityLeftWorldX = entity.worldX + entity.hitbox.x;
        int entityRightWorldX = entity.worldX + entity.hitbox.x + entity.hitbox.width;
        int entityTopWorldY = entity.worldY + entity.hitbox.y;
        int entityBotWorldY = entity.worldY + entity.hitbox.y + entity.hitbox.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBotRow = entityBotWorldY/gp.tileSize;

        int tileNum1, tileNum2, tileNum3; //only need to check two tiles in each direction

        switch(entity.direction){
        case "up":
            entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;    //what tile is player trying to step into?
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
            break;
        case "down":
            entityBotRow = (entityBotWorldY + entity.speed)/gp.tileSize;    //what tile is player trying to step into?
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBotRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBotRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
            break;
        case "left":
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;    //what tile is player trying to step into?
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBotRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
            break;
        case "right":
            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;    //what tile is player trying to step into?
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBotRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
            break;
        case "downright":
            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;    //what tile is player trying to step into?
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
            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;    //what tile is player trying to step into?
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
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;    //what tile is player trying to step into?
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
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;    //what tile is player trying to step into?
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

    public int checkObject(Entity entity, boolean player){
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++){

            if(gp.obj[i] != null){

                // Get entity's hitbox position
                entity.hitbox.x = entity.worldX + entity.hitbox.x;
                entity.hitbox.y = entity.worldY + entity.hitbox.y;
                //Get the object's hitbox position
                gp.obj[i].hitbox.x = gp.obj[i].worldX + gp.obj[i].hitbox.x;
                gp.obj[i].hitbox.y = gp.obj[i].worldY + gp.obj[i].hitbox.y;

                switch(entity.direction){
                case "up":
                    entity.hitbox.y -= entity.speed;
                    if(entity.hitbox.intersects(gp.obj[i].hitbox)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true){
                            index = i;
                        }
                    }
                    break;
                case "down":
                    entity.hitbox.y += entity.speed;
                    if(entity.hitbox.intersects(gp.obj[i].hitbox)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true){
                            index = i;
                        }
                    }
                    break;
                case "left":
                    entity.hitbox.x -= entity.speed;
                    if(entity.hitbox.intersects(gp.obj[i].hitbox)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true){
                            index = i;
                        }
                    }
                    break;
                case "right":
                    entity.hitbox.x += entity.speed;
                    if(entity.hitbox.intersects(gp.obj[i].hitbox)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true){
                            index = i;
                        }
                    }
                    break;
                case "upleft":
                    entity.hitbox.x -= entity.speed;
                    entity.hitbox.y -= entity.speed;
                    if(entity.hitbox.intersects(gp.obj[i].hitbox)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true){
                            index = i;
                        }
                    }
                    break;
                case "downleft":
                    entity.hitbox.x -= entity.speed;
                    entity.hitbox.y += entity.speed;
                    if(entity.hitbox.intersects(gp.obj[i].hitbox)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true){
                            index = i;
                        }
                    }
                    break;
                case "upright":
                    entity.hitbox.x += entity.speed;
                    entity.hitbox.y -= entity.speed;
                    if(entity.hitbox.intersects(gp.obj[i].hitbox)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true){
                            index = i;
                        }
                    }
                    break;
                case "downright":
                    entity.hitbox.x += entity.speed;
                    entity.hitbox.y += entity.speed;
                    if(entity.hitbox.intersects(gp.obj[i].hitbox)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true){
                            index = i;
                        }
                    }
                    break;
                }
                entity.hitbox.x = entity.hitboxDefaultX;
                entity.hitbox.y = entity.hitboxDefaultY;
                gp.obj[i].hitbox.x = gp.obj[i].hitboxDefaultX;
                gp.obj[i].hitbox.y = gp.obj[i].hitboxDefaultY;
            }
        }
        return index;
    }

    //NPC OR MONSTER
    public int checkEntity(Entity entity, Entity[] target){

        int index = 999;

        for(int i = 0; i < target.length; i++){

            if(target[i] != null){

                // Get entity's hitbox position
                entity.hitbox.x = entity.worldX + entity.hitbox.x;
                entity.hitbox.y = entity.worldY + entity.hitbox.y;
                //Get the object's hitbox position
                target[i].hitbox.x = target[i].worldX + target[i].hitbox.x;
                target[i].hitbox.y = target[i].worldY + target[i].hitbox.y;

                switch(entity.direction){
                case "up":
                    entity.hitbox.y -= entity.speed;
                    if(entity.hitbox.intersects(target[i].hitbox)){
                        entity.collisionOn = true;
                        index = i;
                    }
                    break;
                case "down":
                    entity.hitbox.y += entity.speed;
                    if(entity.hitbox.intersects(target[i].hitbox)){
                        entity.collisionOn = true;
                        index = i;
                    }
                    break;
                case "left":
                    entity.hitbox.x -= entity.speed;
                    if(entity.hitbox.intersects(target[i].hitbox)){
                        entity.collisionOn = true;
                        index = i;

                    }
                    break;
                case "right":
                    entity.hitbox.x += entity.speed;
                    if(entity.hitbox.intersects(target[i].hitbox)){
                        entity.collisionOn = true;
                        index = i;
                    }
                    break;
                case "upleft":
                    entity.hitbox.x -= entity.speed;
                    entity.hitbox.y -= entity.speed;
                    if(entity.hitbox.intersects(target[i].hitbox)){
                        entity.collisionOn = true;
                        index = i;
                    }
                    break;
                case "downleft":
                    entity.hitbox.x -= entity.speed;
                    entity.hitbox.y += entity.speed;
                    if(entity.hitbox.intersects(target[i].hitbox)){
                        entity.collisionOn = true;
                        index = i;
                    }
                    break;
                case "upright":
                    entity.hitbox.x += entity.speed;
                    entity.hitbox.y -= entity.speed;
                    if(entity.hitbox.intersects(target[i].hitbox)){
                        entity.collisionOn = true;
                        index = i;
                    }
                    break;
                case "downright":
                    entity.hitbox.x += entity.speed;
                    entity.hitbox.y += entity.speed;
                    if(entity.hitbox.intersects(target[i].hitbox)){
                        entity.collisionOn = true;
                        index = i;
                    }
                    break;
                }
                entity.hitbox.x = entity.hitboxDefaultX;
                entity.hitbox.y = entity.hitboxDefaultY;
                target[i].hitbox.x = target[i].hitboxDefaultX;
                target[i].hitbox.y = target[i].hitboxDefaultY;
            }
        }
        return index;
    }

    public void checkPlayer(Entity entity){
        // Get entity's hitbox position
        entity.hitbox.x = entity.worldX + entity.hitbox.x;
        entity.hitbox.y = entity.worldY + entity.hitbox.y;
        //Get the object's hitbox position
        gp.player.hitbox.x = gp.player.worldX + gp.player.hitbox.x;
        gp.player.hitbox.y = gp.player.worldY + gp.player.hitbox.y;

        switch(entity.direction){
        case "up":
            entity.hitbox.y -= entity.speed;
            if(entity.hitbox.intersects(gp.player.hitbox)){
                entity.collisionOn = true;
            }
            break;
        case "down":
            entity.hitbox.y += entity.speed;
            if(entity.hitbox.intersects(gp.player.hitbox)){
                entity.collisionOn = true;
            }
            break;
        case "left":
            entity.hitbox.x -= entity.speed;
            if(entity.hitbox.intersects(gp.player.hitbox)){
                entity.collisionOn = true;
            }
            break;
        case "right":
            entity.hitbox.x += entity.speed;
            if(entity.hitbox.intersects(gp.player.hitbox)){
                entity.collisionOn = true;
            }
            break;
        case "upleft":
            entity.hitbox.x -= entity.speed;
            entity.hitbox.y -= entity.speed;
            if(entity.hitbox.intersects(gp.player.hitbox)){
                entity.collisionOn = true;
            }
            break;
        case "downleft":
            entity.hitbox.x -= entity.speed;
            entity.hitbox.y += entity.speed;
            if(entity.hitbox.intersects(gp.player.hitbox)){
                entity.collisionOn = true;
            }
            break;
        case "upright":
            entity.hitbox.x += entity.speed;
            entity.hitbox.y -= entity.speed;
            if(entity.hitbox.intersects(gp.player.hitbox)){
                entity.collisionOn = true;
            }
            break;
        case "downright":
            entity.hitbox.x += entity.speed;
            entity.hitbox.y += entity.speed;
            if(entity.hitbox.intersects(gp.player.hitbox)){
                entity.collisionOn = true;
            }
            break;
        }
        entity.hitbox.x = entity.hitboxDefaultX;
        entity.hitbox.y = entity.hitboxDefaultY;
        gp.player.hitbox.x = gp.player.hitboxDefaultX;
        gp.player.hitbox.y = gp.player.hitboxDefaultY;
    } 
}