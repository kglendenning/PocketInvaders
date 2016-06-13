
package gameapp.Projectile;

import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Repair extends Projectile {
    public Repair(int x, int y, boolean up) {
        super(x, y, up);
        ImageIcon icon = new ImageIcon("images/InvisibleShot.gif");
        
        setImage(icon);
        setWidth(icon.getIconWidth());
        setHeight(icon.getIconHeight());
        setWeaponTypeIndex(5);

        setRise(up ? 20 : -20);
        setDamage(Weapon.getDamage(getWeaponTypeIndex()));
    }
}
