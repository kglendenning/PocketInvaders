
package gameapp;

import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Enemy2 extends Enemy{

    public Enemy2(int panelWidth, int panelHeight){
        super(panelWidth, panelHeight);
        ImageIcon icon = new ImageIcon("images/Fighter2.jpg");

        setX((int) (Math.random()*(panelWidth-icon.getIconWidth())));
        setWidth(icon.getIconWidth());
        setHeight(icon.getIconHeight());
        setRun(4);
        setLevel(1);
        setImage(icon);
        setHealth(260);
    }
    
    /**
     * @return 0 - do nothing, 1 - add projectile
     */
    public int update(){
        move();
        
        double action = Math.random();
        return action > .95 ? 1 : 0;
    }
    
    /*public boolean isHit(Projectile shot){
        int xpoints[] = {getX(), getX()+getWidth(), getX()+getWidth()/2};
        int ypoints[] = {getY(), getY(), getY()+getHeight()};
        return new Polygon(xpoints, ypoints, 3).intersects(shot.getBoundingBox());
    }*/
}
