
package gameapp.Projectile;

import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Spread extends Projectile{
    
    public Spread(int x, int y, double run, double rise, boolean up){
        super(x, y, up);
        ImageIcon icon = new ImageIcon("images/BurstShot.gif");
        
        setImage(icon);
        setWidth(icon.getIconWidth());
        setHeight(icon.getIconHeight());
        setType(3);

        setRun(run);
        setRise(up ? (-1*rise) : rise);
        setDamage(Weapon.getDamage(getType()));
    }
}
