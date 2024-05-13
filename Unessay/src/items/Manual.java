package items;

import entity.Entity;
import entity.Item;
import main.GamePanel;

public class Manual extends Item{
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
                    gp.player.cooldownReduction += 0.15;
                    break;
                case 2:
                    gp.player.cooldownReduction += 0.15;
                    break;
                case 3:
                    gp.player.cooldownReduction += 0.1;
                    break;
                case 4:
                    gp.player.cooldownReduction += 0.1;
                    break;
                case 5:
                    gp.player.cooldownReduction += 0.05;
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
            gp.itemH.availableUpgrades.remove(gp.itemH.availableUpgrades.indexOf(this));
            //DEBUG
            System.out.println("manual reached level 4\n");
            for (Entity e: gp.itemH.availableUpgrades) {
                System.out.println("item: " + e.name);
            }
            System.out.println("\n");
        }
    }
}
