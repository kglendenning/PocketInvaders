
package gameapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 * @author Kurt
 */
public class Effect{
    private int x, y;
    private int timer;
    private final int SIZE = 10;
    
    public Effect(Point center){
        setX(center.x);
        setY(center.y);
        setTimer(3);
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
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
    
    public void draw(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillOval(x-(SIZE)/2, y-(SIZE)/2, SIZE, SIZE);
    }
}
