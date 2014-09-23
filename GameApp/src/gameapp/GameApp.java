package gameapp;

import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.Scanner;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Kurt & Chris
 */
public class GameApp {
    public static GameField field;
    //public static SideBar sideBar;
    public static final int WINDOW_HEIGHT = 900, WINDOW_WIDTH = 1600;
    public static final int HEIGHT_DIFF = 55, WIDTH_DIFF = 320;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        do{
            //field = new GameField(WIDTH_DIFF, HEIGHT_DIFF);
            field = new GameField(WIDTH_DIFF, HEIGHT_DIFF);
            //sideBar = new SideBar();
            startGame();
            System.out.print("Continue? (y/n): ");
        }while(in.nextLine().equalsIgnoreCase("y") ? true : false);
        
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
        frame.setResizable(true);
        
        //field.setBounds(0, 0, WINDOW_WIDTH-320, WINDOW_HEIGHT-55);
        field.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.add(field);
        field.setSideBarBounds();
        frame.add(field.sideBar);
        field.startGame("games/BiggerTest.txt");
        frame.addKeyListener(field);
        
        //sideBar.setBounds(WINDOW_WIDTH-320, 0, 300, WINDOW_HEIGHT-52);
        //frame.add(sideBar);
        frame.setVisible(true);
        
        int code = 0;
        
        while(true){
            pause();
            code = field.update();
            if(code > 0){
                break;
            }
            //sideBar.update(field);
            //field.getFocusListeners();
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
