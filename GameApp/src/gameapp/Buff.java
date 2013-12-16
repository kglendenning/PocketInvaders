
package gameapp;

import java.awt.Point;
import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Buff extends Powerup{
    
    public Buff(Point center, int type){
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
