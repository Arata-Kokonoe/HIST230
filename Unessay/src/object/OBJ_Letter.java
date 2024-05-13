package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class OBJ_Letter extends Entity{

    GamePanel gp;
    String message;

    public OBJ_Letter(GamePanel gp, String message){
        super(gp);

        this.gp = gp;

        name = "Letter";
        right0 = setup("/objects/letter", 12, 12);

        hitbox = new Rectangle[1];

        hitbox[0] = new Rectangle();
        hitbox[0].x = 18;
        hitbox[0].width = 12;
        hitbox[0].y = 36;
        hitbox[0].height = 12;

        this.message = message;
        type = 4;

    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;    

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

            switch(leftOrRight){
                case "left":
                    if (spriteNum == 1) image = left0;
                    else if (spriteNum == 2) image = left1;
                    else if (spriteNum == 3) image = left2;
                    else if (spriteNum == 4) image = left3;
                    break;
                case "right":
                    if (spriteNum == 1) image = right0;
                    else if (spriteNum == 2) image = right1;
                    else if (spriteNum == 3) image = right2;
                    else if (spriteNum == 4) image = right3;
                    break;
            } //end of switch

            changeAlpha(g2, 1f);
            g2.drawImage(image, screenX, screenY, null);
        }
    }

    public void update(){
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 4 && contactPlayer == true){
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = message;
            gp.ui.currentCloseup = gp.player.icon;
            this.alive = false;
        }

    }

}
