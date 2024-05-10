package weapons;

import main.GamePanel;
import main.UtilityTool;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Weapon{
    BufferedImage icon;
    GamePanel gp;
    public double size = 1.0;
    public int cd; 
    
    public Weapon (GamePanel gp){
        this.gp = gp;
    }

    public BufferedImage setup(String imagePath){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res" + imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

}
