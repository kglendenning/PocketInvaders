
package gameapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 * @author Kurt
 */
public class Effect extends Entity{
    private int timer, damage;
    private boolean harmful;
    
    public Effect(Projectile projectile){
        Point center = projectile.getCenter();
        
        setX(center.x);
        setY(center.y);
        setWidth(10);
        setHeight(10);
        setHarmful(false);
        setTimer(3);
    }
    
    public void setTimer(int timer){
        this.timer = timer;
    }
    
    public void setHarmful(boolean harmful){
        this.harmful = harmful;
    }
    
    public void setDamage(int damage){
        this.damage = damage;
    }
    
    public int getTimer(){
        return timer;
    }
    
    public boolean isHarmful(){
        return harmful;
    }
    
    public int getDamage(){
        return damage;
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
        g.fillOval(getX()-(getWidth())/2, getY()-(getHeight())/2, getWidth(), getHeight());
    }
}
