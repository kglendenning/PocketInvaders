
package gameapp;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Ship {
    private int x, y, width, height, health;
    public int panelHeight, panelWidth;;
    private ImageIcon image;
    
    public Ship(){
        int randX = (int) Math.random() * 1200;
        int randY = (int) Math.random() * 800;
        setX(randX);
        setY(randY);
        setHealth(200);
    }
    
    public Ship(int x, int y){
        setX(x);
        setY(y);
    }
    
    public void setHealth(int health){
        this.health = health;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void setWidth(int width){
        this.width = width;
    }
    
    public void setHeight(int height){
        this.height = height;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public void setImage(ImageIcon image){
        this.image = image;
        setWidth(image.getIconWidth());
        setHeight(image.getIconHeight());
    }
    
    public Point getCenter(){
        return new Point(getX()+getWidth()/2, getY()+getHeight()/2);
    }
    
    public boolean isHit(Projectile shot){
        return new Rectangle(getX(), getY(), getWidth(), getHeight()).intersects(shot.getBoundingBox());
    }
    
    /**
     * @param damage Damage of projectile
     * @return 0 - if nothing, 1 - if killed
     */
    public int takeDamage(int damage){
        health -= damage;
        return health > 0 ? 0 : 1;
    }
    
    public ImageIcon getImageIcon(){
        return image;
    }
}
