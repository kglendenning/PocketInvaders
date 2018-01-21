
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
        
        SetX(GetX() + ((int) (Math.random() * 20.0)) - 10);
        SetY(GetY() + ((int) (Math.random() * 30.0)) - 15);
        SetImage(icon);
        SetWidth(icon.getIconWidth());
        SetHeight(icon.getIconHeight());
        SetWeaponTypeIndex(1);

        SetRise(up ? -8 : 8);
        SetDamage(WeaponData.GetWeaponInfo().GetDamage(GetWeaponTypeIndex()));
    }
}
