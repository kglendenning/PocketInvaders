
package gameapp;
// feeg
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.JFrame;

/**
 * @author Kurt & Chris
 */
public class GameApp {
    public static GameField field = new GameField();
    public static final int WINDOW_HEIGHT = 800, WINDOW_WIDTH = 1200;

    public static void main(String[] args) {
        //create main frame
        JFrame frame = new JFrame("Battlefield");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        center(frame);
        frame.setResizable(false);
        frame.add(field);
        frame.setVisible(true);
        field.startGame();
        frame.addKeyListener(field);
        
        while(true){
            pause();
            field.update();
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
