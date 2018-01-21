
package gameapp.Projectile;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Repair extends Projectile {
    public Repair(int x, int y, boolean up) {
        super(x, y, up);
        
        SetWeaponTypeIndex(5);

        SetRise(up ? 20 : -20);
        SetDamage(WeaponData.GetWeaponInfo().GetDamage(GetWeaponTypeIndex()));
    }
    
    @Override
    public void Draw(Graphics g) {}
}
