
package gameapp;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Projectile extends Entity{
    private int damage;
    
    public Projectile(int x, int y, boolean up){
        ImageIcon icon = new ImageIcon("images/Projectile.gif");
        
        setWidth(icon.getIconWidth());
        setHeight(icon.getIconHeight());
        setX(x-(getWidth()/2));
        setY(y);
        setRun(0);
        setRise(up ? -10 : 10);
        setImage(icon);
        setDamage(20);
    }
    
    public void setDamage(int damage){
        this.damage = damage;
    }
    
    public int getDamage(){
        return damage;
    }
    
    public Rectangle getBoundingBox(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    public Point getCenter(){
        return new Point(getX()+getWidth()/2, getY()+getHeight()/2);
    }
}
