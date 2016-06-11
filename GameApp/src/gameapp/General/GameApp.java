package gameapp.General;

import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * @author Kurt & Chris
 */
public class GameApp {
    public static GameField field;
    public static StartMenu startMenu;
    public static final int WINDOW_HEIGHT = 900, WINDOW_WIDTH = 1600;
    public static final int HEIGHT_DIFF = 55, WIDTH_DIFF = 320;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        //do{
            //field = new GameField(WIDTH_DIFF, HEIGHT_DIFF);
            startMenu = new StartMenu();
            startGame();
            //System.out.print("Continue? (y/n): ");
        //}while(in.nextLine().equalsIgnoreCase("y") ? true : false);
        
        System.exit(0);
    }
    
    public static boolean startGame(){
        //create main frame
        JFrame frame = new JFrame("Pocket Invaders");
        frame.setLayout(null);
        frame.getContentPane().setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        center(frame);
        frame.setResizable(false);
        
        //initialize start menu
        startMenu.setBounds(WINDOW_WIDTH/2 - WINDOW_WIDTH/12, 
                            WINDOW_HEIGHT/2 - WINDOW_HEIGHT/6, 
                            WINDOW_WIDTH/6, 
                            WINDOW_HEIGHT/3);
        frame.add(startMenu);
        frame.addKeyListener(startMenu);
        
        frame.setVisible(true);
        
        boolean in_game = false;
        int code = 0;
        String level_name;
        
        while(true){
            pause();
            if(in_game){
                code = field.update();
            } else {
                code = startMenu.update();
            }
            
            if(code > 0){
                if(code == 1) {
                    frame.remove(startMenu);
                    frame.removeKeyListener(startMenu);
                    
                    field = new GameField(WIDTH_DIFF, HEIGHT_DIFF);
                    
                    //initialize field
                    field.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                    field.setSideBarBounds();
                    
                    in_game = true;
                    frame.add(field);
                    frame.add(field.sideBar);
                    frame.addKeyListener(field);
                    
                    if(startMenu.level == 1)
                        level_name = "games/Test.txt";
                    else if(startMenu.level == 2)
                        level_name = "games/BiggerTest.txt";
                    else if(startMenu.level == 3)
                        level_name = "games/BossTest.txt";
                    else
                        level_name = "games/AlinaTest.txt";
                    
                    field.startGame(level_name);
                } else if(code == 2) {
                    frame.remove(field);
                    frame.remove(field.sideBar);
                    frame.removeKeyListener(field);
                    
                    //comment out for fun mode
                    GameField.projectiles.clear();
                    GameField.enemies.clear();
                    GameField.effects.clear();
                    GameField.powerups.clear();
                    
                    in_game = false;
                    frame.add(startMenu);
                    frame.addKeyListener(startMenu);
                } else if(code == 3) {
                    break;
                }
            }
        }
        
        frame.setVisible(false);
        
        return true;
    }
    
    public static void pause(){
        try{
            Thread.sleep(20);
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    /**
     * Center a frame on the screen (will cause problems if
     * frame is bigger than the screen!)
     */
    public static void center(JFrame frame) {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      Point center = ge.getCenterPoint();

      int w = frame.getWidth();
      int h = frame.getHeight();

      int x = center.x - w / 2, y = center.y - h / 2;
      frame.setBounds(x, y, w, h);
      frame.validate();
    }
}
