
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
    private int size = 10;
    
    public Effect(Projectile projectile){
        Point center = projectile.getCenter();
        
        setX(center.x);
        setY(center.y);
        setSize(10);
        setTimer(3);
    }
    
    public void setTimer(int timer){
        this.timer = timer;
    }
    
    public void setSize(int size){
        this.size = size;
    }
    
    public int getTimer(){
        return timer;
    }
    
    public int getSize(){
        return size;
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
        g.fillOval(getX()-(size)/2, getY()-(size)/2, size, size);
    }
}
