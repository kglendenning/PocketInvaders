
package gameapp;

import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Projectile extends Entity{
    private int damage = weapon.getDamage(0), type = 0;
    
    public Projectile(int x, int y, boolean up){
        ImageIcon icon = new ImageIcon("images/Projectile.gif");
        
        setWidth(icon.getIconWidth());
        setHeight(icon.getIconHeight());
        setX(x-(getWidth()/2));
        setY(y);
        setRun(0);
        setRise(up ? -10 : 10);
        setImage(icon);
        //setType(0);
        //setDamage(weapon.getDamage(getType()));
    }
    
    public void setDamage(int damage){
        this.damage = damage;
    }
    
    public void setType(int type){
        this.type = type;
    }
    
    public int getDamage(){
        return damage;
    }
    
    public int getType(){
        return type;
    }
    
    public Point getCenter(){
        return new Point(getX()+getWidth()/2, getY()+getHeight()/2);
    }
}
