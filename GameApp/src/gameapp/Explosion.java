
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
        
        setWidth(0);
        setHeight(0);
        setHarmful(true);
        setDamage(10);
        setTimer(15);
    }
    
    /**
     * @return 0 - if not finished, 1 - if finished
     */
    @Override
    public int update(){
        int ret = super.update();
        
        setWidth((15-getTimer())*6);
        setHeight(getWidth());
        
        return ret;
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.ORANGE);
        g.drawOval(getX()-(getWidth())/2, getY()-(getHeight())/2, getWidth(), getHeight());
    }
}
