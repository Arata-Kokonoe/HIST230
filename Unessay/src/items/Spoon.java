package items;

import entity.Entity;
import main.GamePanel;

public class Spoon extends Entity{

    public int level;
    public GamePanel gp;

    public Spoon(GamePanel gp){
        super(gp);
        this.gp = gp;
        level = 1;

        name = "The Special Spoon";
        description = "Increases the size of your attacks!";
        passOrWeap = 0;
        passType = 4;
        getImage();
    }

    public void getImage(){

        icon = setup("/items/spoonIcon");
    }

    public void addEffect(){
        if(applied == false){
            switch(level){
                case 1:
                    gp.player.size += 0.2;
                    break;
                case 2:
                    gp.player.size += 0.2;
                    break;
                case 3:
                    gp.player.size += 0.15;
                    break;
                case 4:
                    gp.player.size += 0.15;
                    break;
                case 5:
                    gp.player.size += 0.05;
                    break;
            }
            applied = true;
        }
    }

    public void checkLevelUp(){
        if(level < 5) {
            level++;
            applied = false;
        }
        if(level == 4){
            gp.player.availableUpgrades.remove(gp.player.availableUpgrades.indexOf(this));
            //DEBUG
            System.out.println("spoon reached level 4\n");
            for (Entity e: gp.player.availableUpgrades) {
                System.out.println("item: " + e.name);
            }
            System.out.println("\n");
        }
    }

}
