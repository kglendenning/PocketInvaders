
package gameapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * @author Kurt
 */
public class Explosion extends Effect {
    
    public Explosion(Projectile projectile){
        super(projectile);
        
        setSize(0);
        setTimer(20);
    }
    
    /**
     * @return 0 - if not finished, 1 - if finished
     */
    @Override
    public int update(){
        int ret = super.update();
        
        setSize((25-getTimer())*3);
        
        return ret;
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.ORANGE);
        g.drawOval(getX()-(getSize())/2, getY()-(getSize())/2, getSize(), getSize());
    }
}
