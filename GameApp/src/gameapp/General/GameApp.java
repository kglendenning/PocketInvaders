package gameapp.General;

import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.JFrame;

/**
 * @author Kurt & Chris
 */
public class GameApp {
    public static GameField mField;
    public static StartMenu mStartMenu;
    public static Results mResultsMenu;
    public static final int WINDOW_HEIGHT = 900, WINDOW_WIDTH = 1600;
    public static final int HEIGHT_DIFF = 55, WIDTH_DIFF = 320;
    
    public static void main(String[] args) {
        StartGame();
        System.exit(0);
    }
    
    public static boolean StartGame(){
        //create main frame
        JFrame frame = new JFrame("Pocket Invaders");
        frame.setLayout(null);
        frame.getContentPane().setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Center(frame);
        frame.setResizable(false);
        
        //initialize start menu
        mStartMenu = new StartMenu();
        mStartMenu.setBounds(WINDOW_WIDTH/2 - WINDOW_WIDTH/12, 
                            WINDOW_HEIGHT/2 - WINDOW_HEIGHT/4, 
                            WINDOW_WIDTH/6, 
                            WINDOW_HEIGHT/2);
        frame.add(mStartMenu);
        frame.addKeyListener(mStartMenu);
        
        mResultsMenu = new Results();
        mResultsMenu.setBounds(WINDOW_WIDTH/2 - WINDOW_WIDTH/12, 
                            WINDOW_HEIGHT/2 - WINDOW_HEIGHT/4, 
                            WINDOW_WIDTH/6, 
                            WINDOW_HEIGHT/2);
        
        frame.setVisible(true);
        
        int game_state = 0;
        int code;
        String level_name;
        
        OUTER:
        while (true) {
            pause();
            switch (game_state) {
                case 0:
                    code = mStartMenu.Update();
                    break;
                case 1:
                    code = mField.Update();
                    break;
                default:
                    code = mResultsMenu.Update();
                    break;
            }
            
            if (code > 0) {
                switch (code) {
                    case 1:
                        frame.remove(mStartMenu);
                        frame.removeKeyListener(mStartMenu);
                        mField = new GameField(WIDTH_DIFF, HEIGHT_DIFF);
                        //initialize field
                        mField.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                        mField.SetSideBarBounds();
                        game_state = 1;
                        frame.add(mField);
                        frame.add(mField.mSideBar);
                        frame.addKeyListener(mField);
                        switch (mStartMenu.mLevel) {
                            case 1:
                                level_name = "games/Test.txt";
                                break;
                            case 2:
                                level_name = "games/BiggerTest.txt";
                                break;
                            case 3:
                                level_name = "games/BossTest.txt";
                                break;
                            default:
                                level_name = "games/AlinaTest.txt";
                                break;
                        }
                        mField.StartGame(level_name);
                        break;
                    case 2:
                        frame.remove(mField);
                        frame.remove(mField.mSideBar);
                        frame.removeKeyListener(mField);
                        //comment out for fun mode
                        GameField.mProjectiles.clear();
                        GameField.mEnemies.clear();
                        GameField.mEffects.clear();
                        GameField.mPowerups.clear();
                        game_state = 2;
                        frame.add(mResultsMenu);
                        frame.addKeyListener(mResultsMenu);
                        break;
                    case 3:
                        frame.remove(mResultsMenu);
                        frame.removeKeyListener(mResultsMenu);
                        Logger.ResetLogger();
                        game_state = 0;
                        frame.add(mStartMenu);
                        frame.addKeyListener(mStartMenu);
                        break;
                    default:
                        break OUTER;
                }
            }
        }
        
        frame.setVisible(false);
        
        return true;
    }
    
    public static void pause(){
        try{
            Thread.sleep(20);
        }catch(InterruptedException e){
            System.out.println("Error: "+e);
        }
    }
    
    /**
     * Center a frame on the screen (will cause problems if
     * frame is bigger than the screen!)
     * 
     * @param frame
     */
    public static void Center(JFrame frame) {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      Point center = ge.getCenterPoint();

      int w = frame.getWidth();
      int h = frame.getHeight();

      int x = center.x - w / 2, y = center.y - h / 2;
      frame.setBounds(x, y, w, h);
      frame.validate();
    }
}
