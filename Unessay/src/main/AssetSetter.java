package main;

import object.OBJ_Key;
import object.OBJ_Letter;
import enemies.ENE_sansCulotte;
import entity.NPC_OldMan;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Exp_Crystal;
import object.OBJ_InvisibleWall;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        for (int i = 0; i < 200; i++){
            gp.obj[i] = new OBJ_InvisibleWall(gp);
            gp.obj[i].worldX = gp.tileSize * (i+25);
            gp.obj[i].worldY = gp.tileSize * 25;
        }

        for (int i = 200; i < 400; i++){
            gp.obj[i] = new OBJ_InvisibleWall(gp);
            gp.obj[i].worldX = gp.tileSize * 25;
            gp.obj[i].worldY = gp.tileSize * (i-200+25);
        }

        for (int i = 400; i < 600; i++){
            gp.obj[i] = new OBJ_InvisibleWall(gp);
            gp.obj[i].worldX = gp.tileSize * 225;
            gp.obj[i].worldY = gp.tileSize * (i-400+25);
        }

        for (int i = 600; i < 800; i++){
            gp.obj[i] = new OBJ_InvisibleWall(gp);
            gp.obj[i].worldX = gp.tileSize * (i-600+25);
            gp.obj[i].worldY = gp.tileSize * 225;
        }

        gp.obj[801] = new OBJ_Letter(gp, "The time had now come for the performance of the last act\nof the tragedy. It was dark, and, as the Greve was imperfectly\nlighted, lanterns had been provided on the scaffold.\n--Charles-Henri Sanson, Memoirs of the Sansons");
        gp.obj[801].worldX = gp.tileSize*126;
        gp.obj[801].worldY = gp.tileSize*126;
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
