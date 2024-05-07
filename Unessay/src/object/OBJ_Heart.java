package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity{


    public OBJ_Heart(GamePanel gp){

        super(gp);
        
        name = "Heart";
        right1 = setup("/objects/heart");

        //hitbox.x = 5 or something if we want to change hitbox of the key
    }
}