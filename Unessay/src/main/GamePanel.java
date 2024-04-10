package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    // Screen Settings
    final int originalTileSize = 16; // 16x16 tile size (16 is average for older retro games)
    final int scale = 3;    // scale accounting for bigger monitor resolutions in modern times

    final int tileSize = originalTileSize * scale; // 48x48 tile is what we are left with if 16 * 3
    final int maxScreenCol = 16;    // how many tiles long do we want screen
    final int maxScreenRow = 12;    // how many tiles high do we want screen
    // 4:3 ratio

    final int screenWidth = tileSize * maxScreenCol;    // 768 pixels
    final int screenHeight = tileSize * maxScreenRow;   // 576 pixels

    Thread gameThread;  //thread can be started/stopped; once its started, it keeps program running until you stop it; calls the run method
    

    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        
        while(gameThread != null){
            
            //System.out.println("The game loop is running");
        
            // 1 UPDATE: update information such as character position
            update();

            // 2 DRAW: draw the screen with the updated information
            repaint();

        }
    }
    public void update(){

    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;  //Graphics2D extends Graphics class to provide more changeability
    
        g2.setColor(Color.white);

        g2.fillRect(100, 100, tileSize, tileSize);

        g2.dispose(); //gets rid of g2, saves memory + resources
    }
}
