package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "right";
        leftOrRight = "right";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage(){
        
        left0 = setup("/npc/CHS_left_1");
        left1 = setup("/npc/CHS_left_2");
        left2 = setup("/npc/CHS_left_1");
        left3 = setup("/npc/CHS_left_2");
        right0 = setup("/npc/CHS_right_1");
        right1 = setup("/npc/CHS_right_2");
        right2 = setup("/npc/CHS_right_1");
        right3 = setup("/npc/CHS_right_2");
        icon = setup("/npc/CHS_closeup", gp.tileSize*3, gp.tileSize*3);

    }

    public void setDialogue (){
        dialogues[0] = "Hello!";
        dialogues[1] = "dialogue1";
        dialogues[2] = "dialogue2";
        dialogues[3] = "dialogue3";
        dialogues[4] = "dialogue4";
        dialogues[5] = "dialogue5";
    }

    public void setAction(){
        
        actionLockCounter++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100) + 1; //pick from 1-100

            if(i <= 13){
                direction = "up";
            }
            if(i > 13 && i <= 25){
                direction = "down";
            }
            if(i > 25 && i <= 37){
                direction = "left";
                leftOrRight = "left";
            }
            if(i > 37 && i <= 50){
                direction = "right";
                leftOrRight = "right";
            }
            if(i > 50 && i <= 63){
                direction = "upright";
                leftOrRight = "right";
            }
            if(i > 63 && i <= 76){
                direction = "upleft";
                leftOrRight = "left";
            }
            if(i > 76 && i <= 89){
                direction = "downright";
                leftOrRight = "right";
            }
            if(i > 89 && i <= 100){
                direction = "downleft";
                leftOrRight = "left";
            }

            actionLockCounter = 0;
        }
    }

    public void speak(){
        
        //do this character specific stuff

        super.speak();
    }

   
}
