package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    // Screen Settings
    final int originalTileSize = 16; // 16x16 tile size (16 is average for older retro games)
    final int scale = 3;    // scale accounting for bigger monitor resolutions in modern times

    public final int tileSize = originalTileSize * scale; // 48x48 tile is what we are left with if 16 * 3
    public final int maxScreenCol = 20;    // how many tiles long do we want screen
    public final int maxScreenRow = 15;    // how many tiles high do we want screen
    // 4:3 ratio

    public final int screenWidth = tileSize * maxScreenCol;    // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow;   // 576 pixels

    // World Settings
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    //public final int worldWidth = tileSize * maxWorldCol;
    //public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public EnemySpawner eSpawner = new EnemySpawner(this);
    Thread gameThread;  //thread can be started/stopped; once its started, it keeps program running until you stop it; calls the run method
    
    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    public Entity enemies[] = new Entity[100];
    public Entity talkingEntity;
    ArrayList<Entity> entityList = new ArrayList<>();


    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;



    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);   //already default in jpanel
        this.addKeyListener(keyH);
        this.setFocusable(true);    //already default in jpanel or more specifically in Component class
    }

    public void setupGame(){

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setEnemy();
        gameState = titleState;

    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    /* @Override
    public void run() {
        
        // sleep method
        double drawInterval = 1000000000/FPS; //one billion nano seconds = 1 second / 60
        //we draw the screen every 1/60 seconds (i.e. we draw the screen 60 times in a second, or 60 frames per second)
        double nextDrawTime = System.nanoTime() + drawInterval; //calculate when our next draw time is


        while(gameThread != null){
            
            update(); // 1 UPDATE: update information such as character position
            repaint(); // 2 DRAW: draw the screen with the updated information

            try {
                double remainingTime = nextDrawTime - System.nanoTime(); //after update() and repaint() is called, some time will have passed so calculate how much time left until next draw time
                remainingTime /= 1000000; //divide by 1,000,000 to get time in milliseconds which is what Thread.sleep() takes as paramter

                //unlikely but if the methods above take more time than we have between intervals, set the remaining time to 0
                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);  //pauses the game loop until sleep time is over

                nextDrawTime += drawInterval; 

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    } */
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval; //find how long its been since last time the time was checked, divide by the drawInterval which is 1/60
            timer += (currentTime - lastTime);
            lastTime = currentTime; //change lastTime checked to the current time
            
            if(delta >= 1){ //once the delta has reached greater or equal to 1, meaning 1/60 of a second has passed, repaint and update, then reset delta
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update(){
        if(gameState == playState){
            // PLAYER
            player.update();

            // NPC
            for(int i = 0; i < npc.length; i++){
                if (npc[i] != null){
                    npc[i].update();
                }
            }

            //ENEMY
            eSpawner.spawnEnemy();
            for(int i = 0; i < enemies.length; i++){
                if(enemies[i] != null){
                    if(enemies[i].checkOffscreen()){
                        enemies[i] = null;
                        if (eSpawner.spawnEnemyFast() != -1) enemies[i].update();;
                    }
                    else enemies[i].update();
                }
            }
        }
        if(gameState == pauseState){
            //nothing for now...
        }
        if(gameState == dialogueState){
            player.updateDialogue();
        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;  //Graphics2D extends Graphics class to provide more changeability
        
        // DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true){
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        }

        //OTHERS
        else{
            // TILE
            tileM.draw(g2);
            
            //ADD ENTITIES TO THE LIST
            entityList.add(player);
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    entityList.add(npc[i]);
                }
            }
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }
            for(int i = 0; i < enemies.length; i++){
                if(enemies[i] != null){
                    entityList.add(enemies[i]);
                }
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>(){
                
                @Override
                public int compare(Entity e1, Entity e2){

                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });
 
            //DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            //EMPTY ENTITY LIST
            entityList.clear();

            // UI
            ui.draw(g2);

        }

        // DEBUG
        if(keyH.checkDrawTime == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose(); //gets rid of g2, saves memory + resources        
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }


}
