
package gameapp.Projectile;

import java.awt.Point;
import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Health extends Powerup{
    
    public Health(Point center, int type){
        super(center);
        
        switch (type){
            default:
                setImage(new ImageIcon("images/HealthIcon.jpg"));
                setWidth(getImageIcon().getIconWidth());
                setHeight(getImageIcon().getIconHeight());
                break;
        }
    }
}
