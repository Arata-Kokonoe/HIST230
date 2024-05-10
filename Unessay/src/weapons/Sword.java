package weapons;

import java.awt.image.BufferedImage;

import main.GamePanel;

public class Sword extends Weapon{

    BufferedImage rightSlash, leftSlash;

    public Sword(GamePanel gp){
        super(gp);
        cd = 600;
        getImage();
    }

    public void getImage(){

        icon = setup("/res/weapons/sword/swordIcon");
        rightSlash = setup("/res/weapons/sword/rightSlash");
        leftSlash = setup("/res/weapons/sword/leftSlash");

    }

    public void attack(){
        
    }
}
