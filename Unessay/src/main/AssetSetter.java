package main;

import object.OBJ_Key;
import enemies.ENE_sansCulotte;
import entity.NPC_OldMan;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Exp_Crystal;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        
        /*gp.obj[0] = new OBJ_Chest(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 24 * gp.tileSize;
        gp.obj[1].worldY = 25 * gp.tileSize;

        gp.obj[2] = new OBJ_Key(gp);
        gp.obj[2].worldX = 22 * gp.tileSize;
        gp.obj[2].worldY = 25 * gp.tileSize;

        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 22 * gp.tileSize;
        gp.obj[3].worldY = 19 * gp.tileSize;

        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 24 * gp.tileSize;
        gp.obj[4].worldY = 19 * gp.tileSize;

        gp.obj[5] = new OBJ_Door(gp);
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 22 * gp.tileSize;

        gp.obj[5] = new OBJ_Boots(gp);
        gp.obj[5].worldX = 22 * gp.tileSize;
        gp.obj[5].worldY = 24 * gp.tileSize;*/

        gp.obj[0] = new OBJ_Exp_Crystal(gp);
        gp.obj[0].worldX = gp.tileSize * 101;
        gp.obj[0].worldY = gp.tileSize * 101;

    }

    public void setNPC(){
        /*gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*102;
        gp.npc[0].worldY = gp.tileSize*102;*/
        
    }

    public void setEnemy(){  
        /*gp.enemies.add(new ENE_sansCulotte(gp));
        gp.enemies.get(0).worldX = gp.tileSize*51;
        gp.enemies.get(0).worldY = gp.tileSize*51;

        gp.enemies.add(new ENE_sansCulotte(gp));
        gp.enemies.get(1).worldX = gp.tileSize*25;
        gp.enemies.get(1).worldY = gp.tileSize*37;*/
    }
}
