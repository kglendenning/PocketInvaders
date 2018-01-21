
package gameapp.Projectile;

import java.awt.Point;
import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Health extends Powerup{
    
    public Health(Point center){
        super(center);
        SetImage(new ImageIcon("images/HealthIcon.jpg"));
        SetWidth(GetImageIcon().getIconWidth());
        SetHeight(GetImageIcon().getIconHeight());
    }
}

