package items;

import entity.Entity;
import entity.Item;
import main.GamePanel;

public class Cake extends Item{
    public int level;
    public GamePanel gp;

    public Cake(GamePanel gp){
        super(gp);
        this.gp = gp;
        level = 1;

        name = "Cake";
        description = "Increases your damage!\nWeaken the starving Sans-culottes by showing off your \naccess to food due to your noble status!";

        passOrWeap = PASSIVE_NUM;
        passType = CAKE_PASSTYPE;

        getImage();
    }

    public void getImage(){

        icon = setup("/items/cakeIcon");
        //add cake slices for each level
    }

    public void addEffect(){
        if(applied == false){
            switch(level){
                case 1:
                    System.out.println("added level 1 cake effect");
                    gp.player.damageMultiplier += 0.2;
                    System.out.println("player damageMultiplier = " + gp.player.damageMultiplier);
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
            gp.itemH.availableUpgrades.remove(gp.itemH.availableUpgrades.indexOf(this));
            //DEBUG
            System.out.println("cake reached level 4\n");
            for (Entity e: gp.itemH.availableUpgrades) {
                System.out.println("item: " + e.name);
            }
            System.out.println("\n");
        }
    }
}
