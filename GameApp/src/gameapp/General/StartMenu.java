package gameapp.General;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author Kurt
 */
class StartMenu extends JPanel implements KeyListener{
    enum Option {START, LEVEL, EXIT};
    public Option selection;
    public int level;
    public boolean enter;
    
    public StartMenu() {
        super();
        
        selection = Option.START;
        level = 1;
        enter = false;
    }
    
    public int update() {
        repaint();
        
        if(enter) {
            enter = false;
            return selection == Option.START ? 1 : selection == Option.EXIT ? 3 : 0;
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
        
        g.setColor(selection == Option.START ? Color.YELLOW : Color.WHITE);
        g.drawString(start_game, 10, 50);
        g.setColor(selection == Option.LEVEL ? Color.YELLOW : Color.WHITE);
        g.drawString("Level " + level, 10, 110);
        g.setColor(selection == Option.EXIT ? Color.YELLOW : Color.WHITE);
        g.drawString(exit_game, 10, 170);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key;
        
        if ((key = e.getKeyCode()) == KeyEvent.VK_UP) {
            if(selection == Option.START)
                selection = Option.EXIT;
            else if(selection == Option.LEVEL)
                selection = Option.START;
            else if(selection == Option.EXIT)
                selection = Option.LEVEL;
        } else if (key == KeyEvent.VK_RIGHT) {
            if(selection == Option.LEVEL) {
                if(++level > 3)
                    level = 1;
            }
        } else if (key == KeyEvent.VK_LEFT) {
            if(selection == Option.LEVEL) {
                if(--level < 1)
                    level = 3;
            }
        } else if (key == KeyEvent.VK_DOWN) {
            if(selection == Option.START)
                selection = Option.LEVEL;
            else if(selection == Option.LEVEL)
                selection = Option.EXIT;
            else if(selection == Option.EXIT)
                selection = Option.START;
        } else if (key == KeyEvent.VK_ENTER) {
            enter = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
