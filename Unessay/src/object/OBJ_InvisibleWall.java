package object;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class OBJ_InvisibleWall extends Entity{

    public OBJ_InvisibleWall(GamePanel gp){

        super(gp);
        
        name = "Invisible Wall";
        right0 = setup("/objects/blank");

        collision = true;
        hitbox[0] = new Rectangle();
        hitbox[0].x = 0;
        hitbox[0].y = 16;
        hitbox[0].width = 48;
        hitbox[0].height = 48;
        hitboxDefaultX = hitbox[0].x;
        hitboxDefaultY = hitbox[0].y;
    }
}
