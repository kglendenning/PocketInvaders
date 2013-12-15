
package gameapp;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Powerup extends Entity{
    private int acceleration, type, timer;
    
    public Powerup(Point center, int type){
        setX(center.x);
        setY(center.y);
        setRise(-2);
        setRun(1);
        setAcceleration(1);
        setType(type);
        setTimer(5);
        
        switch (type){
            case 0:
                setImage(new ImageIcon("images/TestIcon.jpg"));
                break;
            case 1:
                setImage(new ImageIcon("images/BurstIcon.jpg"));
                break;
            case 2:
                setImage(new ImageIcon("images/RocketIcon.jpg"));
                break;
            case 3:
                setImage(new ImageIcon("images/SprayIcon.jpg"));
                break;
            default:
                setImage(new ImageIcon("images/BombIcon.jpg"));
                break;
        }
        setWidth(getImageIcon().getIconWidth());
        setHeight(getImageIcon().getIconHeight());
    }
    
    public void setAcceleration(int acceleration){
        this.acceleration = acceleration;
    }
    
    public void setType(int type){
        this.type = type;
    }
    
    public void setTimer(int timer){
        this.timer = timer;
    }
    
    public int getAcceleration(){
        return acceleration;
    }
    
    public int getType(){
        return type;
    }
    
    public int getTimer(){
        return timer;
    }
    
    public Rectangle getBoundingBox(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    public void update() {
        move();
        
        timer--;
        if(timer == 0){
            setRise(getRise() + acceleration);
            timer = 5;
        }
    }
}
