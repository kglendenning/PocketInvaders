
package gameapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 * @author Kurt
 */
public class Effect extends JPanel{
    private int x, y;
    private int timer;
    
    public Effect(Point center){
        setX(center.x);
        setY(center.y);
        setTimer(30);
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void setTimer(int timer){
        
    }
    
    /**
     * @return 0 - if not finished, 1 - if finished
     */
    public int update(){
        return timer <= 0 ? 1 : 0;
    }
    
    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillOval(x-(20-timer)/2, y-(20-timer)/2, 20-timer, 20-timer);
    }
}
