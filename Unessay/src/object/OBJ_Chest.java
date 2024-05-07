package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{


    public OBJ_Chest(GamePanel gp){

        super(gp);
        
        name = "Chest";
        right1 = setup("/objects/chest");

        //hitbox.x = 5 or something if we want to change hitbox of the key
    }
}