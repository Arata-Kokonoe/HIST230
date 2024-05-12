package items;

import entity.Entity;
import main.GamePanel;

public class Cake extends Entity{
    public int level;
    public GamePanel gp;

    public Cake(GamePanel gp){
        super(gp);
        this.gp = gp;
        level = 1;

        name = "Cake";
        description = "Increases your damage!\nEnrage the Sans-culottes";
        passOrWeap = 0;
        passType = 2;
        getImage();
    }

    public void getImage(){

        icon = setup("/items/cakeIcon");
    }

    public void addEffect(){
        if(applied == false){
            switch(level){
                case 1:
                    gp.player.damageMultiplier += 0.15;
                    break;
                case 2:
                    gp.player.damageMultiplier += 0.15;
                    break;
                case 3:
                    gp.player.damageMultiplier += 0.1;
                    break;
                case 4:
                    gp.player.damageMultiplier += 0.1;
                    break;
                case 5:
                    gp.player.damageMultiplier += 0.05;
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
            System.out.println("cake reached level 4\n");
            for (Entity e: gp.player.availableUpgrades) {
                System.out.println("item: " + e.name);
            }
            System.out.println("\n");
        }
    }
}
