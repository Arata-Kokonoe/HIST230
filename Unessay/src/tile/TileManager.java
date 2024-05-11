package tile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){

        this.gp = gp;
        
        tile = new Tile[10]; //array holds different types of tiles (aka. tile[0] = grass tile)
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/world03.tmx"); 
    }

    public void getTileImage(){

        setup(0, "black", false);
        setup(1, "blankGrass", false);
        setup(2, "filledGrass", false);
        setup(3, "wood", false);
        setup(4, "stone", false);
        setup(5, "earth", false);
        setup(6, "tree", true);
        setup(7, "water", true);
    }

    public void setup(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 6;

            for (int i = 0; i < row; i++){
                br.readLine();
            }

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                
                String line = br.readLine();
                
                while (col < gp.maxWorldCol) {
                    
                    String numbers[] = line.split(",");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }

            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;    //calculate worldX by multiplying tileSize * worldCol
            int worldY = worldRow * gp.tileSize;    //same with worldY
            int screenX = worldX - gp.player.worldX + gp.player.screenX;    //calculate where on screen to draw the tile (if player is at 60, 60 then the tile at 60,60 in the world map will be at x=0, but also add player.screenX because player is in middle of the screen, not the corner)
            int screenY = worldY - gp.player.worldY + gp.player.screenY;    //or for example if player is at 60, 60 and tile is at 0, 0, the tile should be drawn -60, -60 from screen (also account for player being in middle of screen)

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
           
            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
