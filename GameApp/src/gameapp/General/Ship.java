
package gameapp.General;

import gameapp.Enemy.Enemy;
import gameapp.Projectile.Projectile;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Kurt
 */
public class Ship extends Entity{
    private int health, maxHealth, weapon, weaponCallbackIndex;
    private boolean hasDrop;
    private String drop;
    
    public Ship(){
        //int randX = (int) (Math.random() * 1200);
        //int randY = (int) (Math.random() * 800);
        setX(0);
        setY(0);
    }
    
    public Ship(int x, int y){
        setX(x);
        setY(y);
    }
    
    public void setWeaponCallbackIndex(int index){
        this.weaponCallbackIndex = index;
    }
    
    public void setWeapon(int weapon){
        this.weapon = weapon;
    }
    
    public void setHealth(int health){
        this.health = health;
    }
    
    public void setMaxHealth(int health){
        this.maxHealth = health;
    }
    
    public void setHasDrop(boolean hasDrop){
        this.hasDrop = hasDrop;
    }
            
    public boolean getHasDrop(){
        return this.hasDrop;
    }
    
    public void setDrop(String drop){
        this.drop=drop;
    }
        
    public String getDrop(){
        return this.drop;
    }
      
    public int getWeaponCallbackIndex(){
        return weaponCallbackIndex;
    }
    
    public int getWeapon(){
        return weapon;
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
