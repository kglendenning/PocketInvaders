
package gameapp.Projectile;

import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Spread extends Projectile{
    
    public Spread(int x, int y, double run, double rise, boolean up){
        super(x, y, up);
        ImageIcon icon = new ImageIcon("images/BurstShot.gif");
        
        SetImage(icon);
        SetWidth(icon.getIconWidth());
        SetHeight(icon.getIconHeight());
        SetWeaponTypeIndex(3);

        SetRun(run);
        SetRise(up ? (-1*rise) : rise);
        SetDamage(WeaponData.GetWeaponInfo().GetDamage(GetWeaponTypeIndex()));
    }
}
