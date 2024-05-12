package main;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, interactPressed;
    // DEBUG
    boolean checkDrawTime = false;
    UtilityTool utool = new UtilityTool();

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState == gp.titleState){titleState(code);}
        //PLAY STATE
        else if(gp.gameState == gp.playState){playState(code);}

        //PAUSE STATE
        else if(gp.gameState == gp.pauseState){pauseState(code);}

        //DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState){dialogueState(code);}

        //CHARACTER STATE
        else if (gp.gameState == gp.characterState){characterState(code);}

        //LEVELUP STATE
        else if (gp.gameState == gp.levelupState) levelupState(code);
    }

    public void titleState(int code){
        if(gp.ui.titleScreenState == 0){

            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 3;
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 3){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_SPACE){
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                    //gp.playMusic(0);
                }
                if(gp.ui.commandNum == 1){
                    
                }
                if(gp.ui.commandNum == 2){
                    gp.ui.titleScreenState = 1;
                }

                if(gp.ui.commandNum == 3){
                    System.exit(0);
                }
            }
        }

        if(gp.ui.titleScreenState == 1){
            if (code == KeyEvent.VK_ESCAPE){
                gp.ui.titleScreenState = 0;
            }
        }
    }

    public void playState(int code){
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = true;
        }

        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }

        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_SPACE){
            interactPressed = true;
            System.out.println("interactPressed = true");
        }
        if (code == KeyEvent.VK_C){
            gp.gameState = gp.characterState;
        }

        // DEBUG
        if(code == KeyEvent.VK_T){
            if(checkDrawTime == false){
                checkDrawTime = true;
            }
            else if (checkDrawTime == true){
                checkDrawTime = false;
            }
        }
    }

    public void pauseState(int code){
        if (code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState(int code){
        if (code == KeyEvent.VK_ESCAPE){
            if (gp.talkingEntity != null) gp.talkingEntity.dialogueIndex = 0;
            gp.talkingEntity = null;
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_SPACE){
            interactPressed = true;
            System.out.println("interactPressed = true");
        }
    }

    public void characterState(int code){
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.playState;
        }
    }

    public void levelupState(int code){
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            if(gp.ui.slotRow != 0){
                gp.ui.slotRow -= 3;
                gp.ui.upgradeNum--;
                System.out.println("upgradeNum = " + gp.ui.upgradeNum);
                gp.playSE(9);
            }
        }    
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            if(gp.ui.slotRow != 9){
                if (gp.ui.slotRow == 6 && gp.player.luck < 1){
                    System.out.println("not enough luck");
                }
                else{
                    gp.ui.slotRow += 3;
                    gp.ui.upgradeNum++;
                    System.out.println("upgradeNum = " + gp.ui.upgradeNum);
                    gp.playSE(9);
                }
            }
        }      
        if(code == KeyEvent.VK_SPACE){
            if(gp.player.upgradeChoices[gp.ui.upgradeNum].passOrWeap == 0){
                boolean upgraded = false;
                for(int i = 0; i < gp.player.currentPassives.length; i++){
                    if(gp.player.currentPassives[i] != null && gp.player.upgradeChoices[gp.ui.upgradeNum].name.contentEquals(gp.player.currentPassives[i].name)){
                        gp.player.currentPassives[i].checkLevelUp();
                        upgraded = true;
                        break;
                    }
                }
                if(upgraded == false){
                    int i = utool.checkEntityArr(gp.player.currentPassives);
                    gp.player.currentPassives[i] = gp.player.upgradeChoices[gp.ui.upgradeNum];
                }
            }
            else if(gp.player.upgradeChoices[gp.ui.upgradeNum].passOrWeap == 1){
                boolean upgraded = false;
                for(int i = 0; i < gp.player.currentWeapons.length; i++){
                    if(gp.player.currentWeapons[i] != null && gp.player.upgradeChoices[gp.ui.upgradeNum].name.contentEquals(gp.player.currentWeapons[i].name)){
                        gp.player.currentWeapons[i].checkLevelUp();
                        upgraded = true;
                        break;
                    }
                }
                if(upgraded == false){
                    int i = utool.checkEntityArr(gp.player.currentWeapons);
                    gp.player.currentWeapons[i] = gp.player.upgradeChoices[gp.ui.upgradeNum];
                    if(gp.player.currentWeapons[i].name.contentEquals("Executioner's Badge")){
                        System.out.println("badge obtained");   
                        gp.player.badgeIndex = i;
                        gp.player.hasBadge = true;
                    }
                }
            }
            gp.gameState = gp.playState;
            gp.player.applyPassives();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = false;
        }

        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }

    }

}
