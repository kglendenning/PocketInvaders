
package gameapp;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Ship extends Entity{
    private int health, maxHealth;
    
    public Ship(){
        int randX = (int) Math.random() * 1200;
        int randY = (int) Math.random() * 800;
        setX(randX);
        setY(randY);
    }
    
    public Ship(int x, int y){
        setX(x);
        setY(y);
    }
    
    public void setHealth(int health){
        this.health = health;
    }
    
    public void setMaxHealth(int health){
        this.maxHealth = health;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getMaxHealth(){
        return maxHealth;
    }
    
    public Point getCenter(){
        return new Point(getX()+getWidth()/2, getY()+getHeight()/2);
    }
    
    public boolean isHit(Entity entity){
        return new Rectangle(getX(), getY(), getWidth(), getHeight()).intersects(entity.getBoundingBox());
    }
    
    /**
     * @param damage Damage of projectile
     * @return 0 - if nothing, 1 - if killed
     */
    public int takeDamage(int damage){
        health -= damage;
        return health > 0 ? 0 : 1;
    }
}
