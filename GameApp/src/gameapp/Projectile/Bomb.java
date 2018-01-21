
package gameapp.Projectile;

import java.awt.Point;
import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Bomb extends Projectile{
    
    public Bomb(int x, int y, boolean up){
        super(x, y, up);
        ImageIcon icon = new ImageIcon("images/Bomb.png");
        
        SetImage(icon);
        SetWidth(icon.getIconWidth());
        SetHeight(icon.getIconHeight());
        SetWeaponTypeIndex(4);

        SetRise(up ? -6 : 6);
        SetDamage(WeaponData.GetWeaponInfo().GetDamage(GetWeaponTypeIndex()));
    }
    
    @Override
    public Point GetCenter(){
        return new Point(GetX()+GetWidth()/2, GetY());
    }
}
