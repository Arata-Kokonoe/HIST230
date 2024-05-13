package object;

import java.awt.image.BufferedImage;

import entity.Item;
import entity.Projectile;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_Bullet extends Projectile{
    
    GamePanel gp;
    UtilityTool utool = new UtilityTool();
    
    public OBJ_Bullet(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Bullet";
        speed = 5;
        maxLife = 120;
        life = maxLife;
        damage = 2;
        alive = false;
        getImage();
    }

    private void getImage() {
        left0 = setup("/objects/bullet/bulletLeft0");
        left1 = setup("/objects/bullet/bulletLeft1");
        right0 = setup("/objects/bullet/bulletRight0");
        right1 = setup("/objects/bullet/bulletRight1");
        up0 = setup("/objects/bullet/bulletUp0");
        up1 = setup("/objects/bullet/bulletUp1");
        down0 = setup("/objects/bullet/bulletDown0");
        down1 = setup("/objects/bullet/bulletDown1");
    }
    
}
