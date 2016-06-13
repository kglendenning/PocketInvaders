
package gameapp.Projectile;

import java.awt.Point;
import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Health extends Powerup{
    
    public Health(Point center){
        super(center);
        setImage(new ImageIcon("images/HealthIcon.jpg"));
        setWidth(getImageIcon().getIconWidth());
        setHeight(getImageIcon().getIconHeight());
    }
}

