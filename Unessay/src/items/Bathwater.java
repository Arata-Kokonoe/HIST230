package items;

import entity.Entity;
import entity.Item;
import main.GamePanel;

public class Bathwater extends Item{

    public int level;
    public GamePanel gp;

    public Bathwater(GamePanel gp){
        super(gp);
        this.gp = gp;
        level = 1;

        name = "Marat's Bathwater";
        description = "Increases your max health and heals you to full!\nWhy would anyone ever want this?\nMaybe if you were a devoted member\nof the Montagnard...";

        passOrWeap = PASSIVE_NUM;
        passType = BATHWATER_PASSTYPE;
        
        getImage();
    }

    public void getImage(){

        icon = setup("/items/bathwaterIcon");
    }

    public void addEffect(){
        if(applied == false){
            switch(level){
                case 1:
                    gp.player.maxLife += 10;
                    break;
                case 2:
                    gp.player.maxLife += 10;
                    break;
                case 3:
                    gp.player.maxLife += 10;
                    break;
                case 4:
                    gp.player.maxLife += 10;
                    break;
                case 5:
                    gp.player.maxLife += 10;
                    break;
            }
            gp.player.life = maxLife;
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
            System.out.println("bathwater reached level 4\n");
            for (Entity e: gp.itemH.availableUpgrades) {
                System.out.println("item: " + e.name);
            }
            System.out.println("\n");
        }
    }
}
