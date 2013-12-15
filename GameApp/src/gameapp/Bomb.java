
package gameapp;

import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Bomb extends Projectile{
    
    public Bomb(int x, int y, boolean up){
        super(x, y, up);
        ImageIcon icon = new ImageIcon("images/Bomb.png");
        
        setImage(icon);
        setWidth(icon.getIconWidth());
        setHeight(icon.getIconHeight());
        setType(4);

        setRise(up ? -6 : 6);
        setDamage(weapon.getDamage(getType()));
    }
}