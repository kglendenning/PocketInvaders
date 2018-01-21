package gameapp.General;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
/**
 *
 * @author Kurt
 */
public class StartMenu extends JPanel implements KeyListener{
    private enum Option {START, LEVEL, EXIT};
    private Option mSelection;
    public int mLevel;
    public boolean mEnter;
    
    public StartMenu() {
        super();
        
        mSelection = Option.START;
        mLevel = 1;
        mEnter = false;
    }
    
    public int Update() {
        repaint();
        
        if(mEnter) {
            mEnter = false;
            return mSelection == Option.START ? 1 : mSelection == Option.EXIT ? 4 : 0;
        }
        
        return 0;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //paint background
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        String start_game = "Start Game";
        String exit_game = "Exit Game";
        g.setFont(new Font("Serif", Font.BOLD, 40));
        
        g.setColor(mSelection == Option.START ? Color.YELLOW : Color.WHITE);
        g.drawString(start_game, 10, 50);
        g.setColor(mSelection == Option.LEVEL ? Color.YELLOW : Color.WHITE);
        g.drawString("Level " + mLevel, 10, 110);
        g.setColor(mSelection == Option.EXIT ? Color.YELLOW : Color.WHITE);
        g.drawString(exit_game, 10, 170);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key;
        
        if ((key = e.getKeyCode()) == KeyEvent.VK_UP) {
            if(null != mSelection)
                switch (mSelection) {
                case START:
                    mSelection = Option.EXIT;
                    break;
                case LEVEL:
                    mSelection = Option.START;
                    break;
                case EXIT:
                    mSelection = Option.LEVEL;
                    break;
                default:
                    break;
            }
        } else if (key == KeyEvent.VK_RIGHT) {
            if(mSelection == Option.LEVEL) {
                if(++mLevel > 4)
                    mLevel = 1;
            }
        } else if (key == KeyEvent.VK_LEFT) {
            if(mSelection == Option.LEVEL) {
                if(--mLevel < 1)
                    mLevel = 4;
            }
        } else if (key == KeyEvent.VK_DOWN) {
            if(null != mSelection)
                switch (mSelection) {
                case START:
                    mSelection = Option.LEVEL;
                    break;
                case LEVEL:
                    mSelection = Option.EXIT;
                    break;
                case EXIT:
                    mSelection = Option.START;
                    break;
                default:
                    break;
            }
        } else if (key == KeyEvent.VK_ENTER) {
            mEnter = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
