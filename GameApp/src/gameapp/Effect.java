
package gameapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 * @author Kurt
 */
public class Effect extends Entity{
    private int timer;
    private final int SIZE = 10;
    
    public Effect(Point center){
        setX(center.x);
        setY(center.y);
        setTimer(3);
    }
    
    public void setTimer(int timer){
        this.timer = timer;
    }
    
    /**
     * @return 0 - if not finished, 1 - if finished
     */
    public int update(){
        timer--;
        return timer <= 0 ? 1 : 0;
    }
    
    @Override
    public void draw(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillOval(getX()-(SIZE)/2, getY()-(SIZE)/2, SIZE, SIZE);
    }
}
