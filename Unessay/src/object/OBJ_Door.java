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
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;
    }
}