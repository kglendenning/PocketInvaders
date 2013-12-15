
package gameapp;

import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Rocket extends Projectile {
    public int acceleration, limit;
    
    public Rocket(int x, int y, boolean up){
        super(x, y, up);
        ImageIcon icon = new ImageIcon("images/Rocket.jpg");
        
        setImage(icon);
        setWidth(icon.getIconWidth());
        setHeight(icon.getIconHeight());

        setRise(up ? -3 : 3);
        setDamage(100);
        setLimit(5);
        setAcceleration(up ? -1 : 1);
    }
    
    public void setLimit(int limit){
        this.limit = limit;
    }
    
    public void setAcceleration(int acceleration){
        this.acceleration = acceleration;
    }
    
    public int getLimit(){
        return limit;
    }
    
    public int getAcceleration(){
        return acceleration;
    }
    
    @Override
    public void move(){
        super.move();
        
        limit--;
        if(limit == 0){
            setLimit(5);
            setRise(getRise()+getAcceleration());
        }
    }
}
