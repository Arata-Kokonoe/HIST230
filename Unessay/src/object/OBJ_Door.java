package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity{


    public OBJ_Door(GamePanel gp){

        super(gp);
        
        name = "Door";
        right0 = setup("/objects/door");

        //hitbox.x = 5 or something if we want to change hitbox of the key
        collision = true;
        hitbox[0].x = 0;
        hitbox[0].y = 16;
        hitbox[0].width = 48;
        hitbox[0].height = 32;
        hitboxDefaultX = hitbox[0].x;
        hitboxDefaultY = hitbox[0].y;
    }
}