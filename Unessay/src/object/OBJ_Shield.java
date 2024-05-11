package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield extends Entity{

    public OBJ_Shield(GamePanel gp){
        super(gp);

        name = "Wood Shield";
        right0 = setup("/weapons/shield/shieldIcon", gp.tileSize, gp.tileSize);
        defenseValue = 1;
    }
}
