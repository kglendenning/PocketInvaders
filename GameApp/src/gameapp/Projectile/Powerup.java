
package gameapp.Projectile;

import gameapp.General.Entity;
import java.awt.Point;

/**
 * @author Kurt
 */
public class Powerup extends Entity{
    private int acceleration, type, timer;
    
    public Powerup(Point center){
        setX(center.x);
        setY(center.y);
        setRise(-2);
        setRun(1);
        setAcceleration(1);
        setTimer(5);
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
    
    public void update() {
        move();
        
        timer--;
        if(timer == 0){
            setRise(getRise() + acceleration);
            timer = 5;
        }
    }
}
