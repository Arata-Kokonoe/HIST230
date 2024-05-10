package main;

import main.UtilityTool;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.text.NumberFormatter;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB;
    UtilityTool utool;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public BufferedImage currentCloseup = null;
    public int commandNum = 0;
    public int titleScreenState = 0; //0: first screen, 1: second screen
    public double playTime;

    public UI(GamePanel gp){
        this.gp = gp;
        utool = new UtilityTool();
        
        try {
            InputStream is = getClass().getResourceAsStream("/res/fonts/maruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/res/fonts/purisaBold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void showMessage(String text){

        message = text;
        messageOn = true;

    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(maruMonica);
        //g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //anti-aliasing
        g2.setColor(Color.white);


        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        
        //PLAY STATE
        if(gp.gameState == gp.playState){
            //TIME
            playTime += (double)1/60;
            drawTimer();
            drawPlayerLife();
        }
        //PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
    }

    public void drawPlayerLife(){
        int x = gp.screenWidth/2 - (gp.tileSize/2);
        int y = (int)(gp.screenHeight/2 - (gp.tileSize/1.5));
        double width = gp.tileSize * (gp.player.maxLife / 100);

        //code to draw a line using doubles taken from https://stackoverflow.com/questions/7759549/java-draw-line-based-on-doubles-sub-pixel-precision
        //DRAW BACK OF HEALTHBAR
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(2));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.draw(new Line2D.Double(x, y, x + width, y));
        
        //DRAW CURRENT HEALTHBAR
        g2.setColor(Color.red);
        width = gp.tileSize * (gp.player.life / 100.0);
        g2.draw(new Line2D.Double(x, y, x + width, y));
    }

    public void drawTimer(){
        int timeLeft = 1800 - (int)playTime;
        int minutes = timeLeft/60;
        int seconds = timeLeft % 60;
        String timer = "";
        if (minutes < 10) timer += "0" + minutes + ":";
        else timer += minutes + ":";
        if (seconds < 10) timer += "0" + seconds;
        else timer += seconds;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        int x = getXForCenteredText(timer);
        int y = gp.tileSize;

        g2.setColor(Color.white);
        g2.drawString(timer, x, y);
    }

    public void drawTitleScreen(){


        if(titleScreenState == 0){
            //BACKGROUND
            g2.setColor(new Color(67, 23, 35));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Executioner Surviors";
            int x = getXForCenteredText(text);
            int y = gp.tileSize*3;
            
            //SHADOW
            g2.setColor(new Color(0, 1, 0));
            g2.drawString(text, x+5, y+5);
            //MAIN COLOR
            g2.setColor(new Color(146, 142, 128));
            g2.drawString(text, x, y);

            //CHS IMAGE
            x = gp.screenWidth/2 - (gp.tileSize*4)/2;
            y += gp.tileSize * 1.5;
            g2.drawImage(gp.player.right0, x, y, gp.tileSize*4, gp.tileSize*4, null);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));

            text = "NEW GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize*6;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "CONTROLS";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "QUIT";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 3){
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        else if(titleScreenState == 1){
            
            //CONTROLS SCREEN
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "CONTROLS:";
            int x = getXForCenteredText(text);
            int y = gp.tileSize*2;
            g2.drawString(text, x, y);

            text = "WASD or ARROW KEYS to move";
            x = getXForCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);

            text = "SPACE to interact and continue dialogue";
            x = getXForCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);

            text = "ESC to exit dialogue";
            x = getXForCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);

            text = "P to pause game";
            x = getXForCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);

            //add more controls here

            text = "ESC to exit this menu";
            g2.setFont(g2.getFont().deriveFont(20F));
            g2.setColor(new Color(90, 90, 90));
            x = (int)(gp.screenWidth - g2.getFontMetrics().getStringBounds(text, g2).getWidth() - (gp.tileSize * 0.25));
            y = gp.screenHeight - (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight();
            g2.drawString(text, x, y);

        }
    }

    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){

        //WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize*4;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0, 210); //4th number is alpha number (opacity level)
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
            g2.setColor(c);
            g2.setStroke(new BasicStroke(5)); //width of outlines of graphics
            g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

        if(currentCloseup != null){
            g2.drawImage(utool.scaleImage(currentCloseup, 144, 144), null, x+25, y+25);
            g2.drawRoundRect(x+25, y+25, gp.tileSize*3, gp.tileSize*3, 15, 15);
        }
    }

    public int getXForCenteredText(String text){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    /*if(gameFinished == true){
            
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        String text;
        int textLength;
        int x;
        int y;
        
        text = "You found the treasure!";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHeight/2 - (gp.tileSize*3);
        g2.drawString(text, x, y);

        text = "Your Time is: " + dFormat.format(playTime) + "!";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHeight/2 + (gp.tileSize*4);
        g2.drawString(text, x, y);

        g2.setFont(arial_80B);
        g2.setColor(Color.yellow);
        text = "Congratulations!";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHeight/2 + (gp.tileSize*2);
        g2.drawString(text, x, y);

        gp.gameThread = null;
    }
    else{
        
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, 74, 65); //y is for baseline of text
    
        // TIME
        playTime += (double)1/60;
        g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 65);

        // MESSAGE
        if(messageOn == true){
            g2.setFont(g2.getFont().deriveFont(30F));   //gets font we set for g2 earlier, then change its size to 30
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
        
            messageCounter++;

            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }
    } */ //used to be inside draw method for treasure hunting game
}
