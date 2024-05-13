package main;

public class EventHandler {

    GamePanel gp;
    EventHitbox eventHitbox[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventHitbox = new EventHitbox[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow){

            eventHitbox[col][row] = new EventHitbox();
            eventHitbox[col][row].x = 23;
            eventHitbox[col][row].y = 23;
            eventHitbox[col][row].width = 2;
            eventHitbox[col][row].height = 2;
            eventHitbox[col][row].eventHitboxDefaultX = eventHitbox[col][row].x;
            eventHitbox[col][row].eventHitboxDefaultY = eventHitbox[col][row].y;
            
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent(){

        //Chekc if player is more than 1 tile away from last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance); //choose whichever var is greater
        if(distance > gp.tileSize){ //if the distance of x or y of our player from previous event is greater than 1 tile, allow event to happen again
            canTouchEvent = true;   
        }

        if(canTouchEvent == true){
            /*if(hit(26, 15, "right") == true) damagePitTemp(26, 15, gp.dialogueState);
            if(hit(24, 16, "any") == true) damagePitTemp(24, 16, gp.dialogueState);
            if(hit(25, 14, "up") == true) healingPool(25, 14, gp.dialogueState);*/
        }
    }

    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;

        gp.player.hitbox[0].x = gp.player.worldX + gp.player.hitbox[0].x;
        gp.player.hitbox[0].y = gp.player.worldY + gp.player.hitbox[0].y;
        eventHitbox[col][row].x = col*gp.tileSize + eventHitbox[col][row].x;
        eventHitbox[col][row].y = row*gp.tileSize + eventHitbox[col][row].y;

        if(gp.player.hitbox[0].intersects(eventHitbox[col][row]) && eventHitbox[col][row].eventDone == false){
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.hitbox[0].x = gp.player.hitboxDefaultX;
        gp.player.hitbox[0].y = gp.player.hitboxDefaultY;
        eventHitbox[col][row].x = eventHitbox[col][row].eventHitboxDefaultX;
        eventHitbox[col][row].y = eventHitbox[col][row].eventHitboxDefaultY;
        
        return hit;
    }
    public void damagePitTemp(int col, int row, int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell into a pit!";
        gp.ui.currentCloseup = null;
        gp.player.life -= 10;
        //eventHitbox[col][row].eventDone = true;
        canTouchEvent = false;
    }

    public void healingPool(int col, int row, int gameState){

        if(gp.keyH.interactPressed == true){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.ui.currentDialogue = "You drink the water! Your life has been recovered!";
            gp.player.life += 10;
            if (gp.player.life >= gp.player.maxLife) gp.player.life = gp.player.maxLife;
        }

    }
}
