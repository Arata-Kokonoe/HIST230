package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Exp_Crystal extends Entity{

    public OBJ_Exp_Crystal(GamePanel gp){
        super(gp);

        right0 = setup("/objects/expCrystal");
    }
}
