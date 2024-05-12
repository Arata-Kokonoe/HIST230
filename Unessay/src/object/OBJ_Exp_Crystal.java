package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class OBJ_Exp_Crystal extends Entity{

    GamePanel gp;

    public OBJ_Exp_Crystal(GamePanel gp){
        super(gp);

        this.gp = gp;

        name = "Exp Crystal";
        right0 = setup("/objects/expCrystal", 12, 12);

        hitbox.x = 18;
        hitbox.width = 12;
        hitbox.y = 36;
        hitbox.height = 12;

        type = 3;

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

        if(this.type == 3 && contactPlayer == true){
            gp.player.exp++;
            gp.player.checkLevelUp();
            this.alive = false;
        }

    }

}
