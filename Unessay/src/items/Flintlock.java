package items;

import entity.Item;
import entity.Projectile;
import main.GamePanel;
import object.OBJ_Bullet;

public class Flintlock extends Item{
    GamePanel gp;

    public Flintlock(GamePanel gp) {
        super(gp);
        this.gp = gp;
        level = 1;
        damage = 10;
        baseSize = 24;

        name = "Flintlock";
        description = "Shoots a bullet in the direction you are moving!\nIt's never a bad idea to have some self-defense on you!\nEspecially useful for when you botch an execution.";

        passOrWeap = WEAPON_NUM;
        weapType = FLINTLOCK_WEAPTYPE;
        getImage();
    }

    private void getImage() {
        icon = setup("/items/flintlockIcon");
    }

    public void scaleImages(){
        
    }

    public void attack(){
        attackCounter++;

        if(attackCounter == (int)(180 *(1-gp.player.cooldownReduction))){
            System.out.println("flintlock has shot");
            Projectile projectile = new OBJ_Bullet(gp);
            projectile.set(gp.player.worldX, gp.player.worldY, gp.player.direction, true, this);
            gp.projectileList.add(projectile);

            attackCounter = 0;
        }

    }

    public void draw(){
        //do nothing
    }

    public int getWidth(){
        return (int)(baseSize*gp.player.sizeMultiplier);
    }

    public int getHeight(){
        return (int)(baseSize*gp.player.sizeMultiplier);
    }

}
