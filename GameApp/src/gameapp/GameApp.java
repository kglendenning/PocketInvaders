
package gameapp;

import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Kurt & Chris
 */
public class GameApp {
    public static GameField field = new GameField();
    public static SideBar sideBar = new SideBar();
    public static final int WINDOW_HEIGHT = 900, WINDOW_WIDTH = 1600;
    public static void main(String[] args) {
        //create main frame
        JFrame frame = new JFrame("Pocket Invaders");
        frame.setLayout(null);
        frame.getContentPane().setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        center(frame);
        frame.setResizable(true);
        
        field.setBounds(0, 0, WINDOW_WIDTH-320, WINDOW_HEIGHT-55);
        frame.add(field);
        field.startGame();
        frame.addKeyListener(field);
        
        sideBar.setBounds(WINDOW_WIDTH-320, 0, 300, WINDOW_HEIGHT-52);
        frame.add(sideBar);
        frame.setVisible(true);
        
        
        
        while(true){
            pause();
            field.update();
            sideBar.update(field);
            field.getFocusListeners();
        }
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
