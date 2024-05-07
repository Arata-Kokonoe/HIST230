package object;

import main.GamePanel;
import entity.Entity;

public class OBJ_Key extends Entity{


    public OBJ_Key(GamePanel gp){
        super(gp);

        name = "Key";
        right1 = setup("/object/key");

        //hitbox.x = 5 or something if we want to change hitbox of the key
    }
}
