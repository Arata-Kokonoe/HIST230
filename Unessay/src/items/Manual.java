package items;

import entity.Entity;
import main.GamePanel;

public class Manual extends Entity{
    public int level;
    public GamePanel gp;

    public Manual(GamePanel gp){
        super(gp);
        this.gp = gp;
        level = 1;

        name = "Weapon Manual";
        description = "Makes your weapons attack faster!";

        passOrWeap = PASSIVE_NUM;
        passType = MANUAL_PASSTYPE;
        
        getImage();
    }

    public void getImage(){

        icon = setup("/items/manualIcon");
    }

    public void addEffect(){
        if(applied == false){
            switch(level){
                case 1:
                    gp.player.cooldown += 0.15;
                    break;
                case 2:
                    gp.player.cooldown += 0.15;
                    break;
                case 3:
                    gp.player.cooldown += 0.1;
                    break;
                case 4:
                    gp.player.cooldown += 0.1;
                    break;
                case 5:
                    gp.player.cooldown += 0.05;
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
            System.out.println("manual reached level 4\n");
            for (Entity e: gp.player.availableUpgrades) {
                System.out.println("item: " + e.name);
            }
            System.out.println("\n");
        }
    }
}
