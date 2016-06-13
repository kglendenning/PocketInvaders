
package gameapp.Projectile;

import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Burst extends Projectile{
    
    public Burst(int x, int y, boolean up){
        super(x, y, up);
        ImageIcon icon = new ImageIcon("images/BurstShot.gif");
        
        setX(getX() + ((int) (Math.random() * 20.0)) - 10);
        setY(getY() + ((int) (Math.random() * 30.0)) - 15);
        setImage(icon);
        setWidth(icon.getIconWidth());
        setHeight(icon.getIconHeight());
        setWeaponTypeIndex(1);

        setRise(up ? -8 : 8);
        setDamage(Weapon.getDamage(getWeaponTypeIndex()));
    }
}
