package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity{
    
    public OBJ_Sword_Normal(GamePanel gp){
        super(gp);

        name = "Normal Sword";
        right0 = setup("/weapons/sword/swordIcon");
        attackValue = 1;
    }
}
