
package gameapp.Enemy;

import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Elite extends Enemy{

    //does nothing yet, just a placeholder modified from enemy2
    public Elite(int panelWidth, int panelHeight, int type){
        super(panelWidth, panelHeight, type);
    }
    
    /*public boolean isHit(Projectile shot){
        int xpoints[] = {getX(), getX()+getWidth(), getX()+getWidth()/2};
        int ypoints[] = {getY(), getY(), getY()+getHeight()};
        return new Polygon(xpoints, ypoints, 3).intersects(shot.getBoundingBox());
    }*/
}
