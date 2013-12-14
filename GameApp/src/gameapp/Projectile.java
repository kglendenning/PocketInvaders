
package gameapp;

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Projectile {
    private int x, y, width, height, run, rise, damage;
    private ImageIcon image = new ImageIcon("images/Projectile.gif");
    
    public Projectile(int x, int y, boolean up){
        setWidth(image.getIconWidth());
        setHeight(image.getIconHeight());
        setX(x-(getWidth()/2));
        setY(y);
        setRun(0);
        setRise(up ? -10 : 10);
        setDamage(20);
    }
    
    public void setDamage(int damage){
        this.damage = damage;
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
    
    public void setRun(int run){
        this.run = run;
    }
    
    public void setRise(int rise){
        this.rise = rise;
    }
    
    public int getDamage(){
        return damage;
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
    
    public int getRun(){
        return run;
    }
    
    public int getRise(){
        return rise;
    }
    
    public void setImage(ImageIcon image){
        this.image = image;
    }
    
    public ImageIcon getImageIcon(){
        return image;
    }
    
    public Rectangle getBoundingBox(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    public Point getCenter(){
        return new Point(getX()+getWidth()/2, getY()+getHeight()/2);
    }
    
    public void hits(){
        
    }
    
    public void move(){
        x += run;
        y += rise;
    }
}
