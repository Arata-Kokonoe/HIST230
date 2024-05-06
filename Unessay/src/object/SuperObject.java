package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle hitbox = new Rectangle(0,0,48,48);
    public int hitboxDefaultX = 0;
    public int hitboxDefaultY = 0;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp){

        int screenX = worldX - gp.player.worldX + gp.player.screenX;    //calculate where on screen to draw the tile (if player is at 60, 60 then the tile at 60,60 in the world map will be at x=0, but also add player.screenX because player is in middle of the screen, not the corner)
        int screenY = worldY - gp.player.worldY + gp.player.screenY;    //or for example if player is at 60, 60 and tile is at 0, 0, the tile should be drawn -60, -60 from screen (also account for player being in middle of screen)

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
