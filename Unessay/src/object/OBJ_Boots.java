package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity{


    public OBJ_Boots(GamePanel gp){

        super(gp);
        
        name = "Boots";
        right1 = setup("/objects/boots");

        //hitbox.x = 5 or something if we want to change hitbox of the key
    }
}