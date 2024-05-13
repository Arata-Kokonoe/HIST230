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

        //On page 241 of first volume
        gp.obj[801] = new OBJ_Letter(gp, "\"The time had now come for the performance of the last act\nof the tragedy. It was dark, and, as the Greve was imperfectly\nlighted, lanterns had been provided on the scaffold.\"\n\t\t-Henri-Clement Sanson, Memoirs of the Sansons");
        gp.obj[801].worldX = gp.tileSize*126;
        gp.obj[801].worldY = gp.tileSize*126;

        //On page 105 of first volume
        gp.obj[802] = new OBJ_Letter(gp, "\"If  I  give  these  sickening  details,  it  is  because \r\n" +
                        "Damiens'  execution  was  almost  unique  in  its  atrocious \n" +
                        "cruelty.  Singularly  enough,  this,  the  most  horrible  of  in- \n" +
                        "flictions ever  recorded,  occurred  but  a  few  years  before \n" +
                        "the  abolition  of  torture.\"\n\t\t-Henri-Clement Sanson, Memoirs of the Sansons");
        gp.obj[802].worldX = gp.tileSize*200;
        gp.obj[802].worldY = gp.tileSize*200;

        //On page 171-172 of second volume
        gp.obj[803] = new OBJ_Letter(gp, "\"I  follow  the  preparations  for  the  tragedy,  and  I  \nhave  no  idea  what  is  to occur  next,  and  I  \ndischarge  my  functions  with  the \nmechanical  regularity  of  an  automaton.\"\n\t-Charles-Henri Sanson, Memoirs of the Sansons");
        gp.obj[803].worldX = gp.tileSize*30;
        gp.obj[803].worldY = gp.tileSize*200;

        //On page 171 of second volume
        gp.obj[804] = new OBJ_Letter(gp, "\"Perhaps  I  am  punished  by the  Almighty  for  my  cowardly  obedience\nto  mock justice.\"\n\t-Charles-Henri Sanson, Memoirs of the Sansons");
        gp.obj[804].worldX = gp.tileSize*30;
        gp.obj[804].worldY = gp.tileSize*30;

        //On page 171 of second volume
        gp.obj[805] = new OBJ_Letter(gp, "\"A  terrible  day's  work  !  The  guillotine devoured  fifty-four  victims.\nMy  strength  is  at  an  end,  and I  almost  fainted  away.\"\n\t-Charles-Henri Sanson, Memoirs of the Sansons");
        gp.obj[805].worldX = gp.tileSize*200;
        gp.obj[805].worldY = gp.tileSize*30;
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
