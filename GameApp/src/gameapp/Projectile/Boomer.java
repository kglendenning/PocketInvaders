package gameapp.Projectile;

import gameapp.General.GameField;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Boomer extends Projectile{
    
    public Boomer(int x, int y, boolean up) {
        super(x, y, up);
        ImageIcon icon = new ImageIcon("images/Boomer.gif");
        
        SetImage(icon);
        SetWidth(icon.getIconWidth());
        SetHeight(icon.getIconHeight());
        SetWeaponTypeIndex(4);

        SetRise(up ? -3 : 3);
        SetDamage(WeaponData.GetWeaponInfo().GetDamage(GetWeaponTypeIndex()));
    }
    
    public void Detonate(){
        GameField.mEffects.add(WeaponData.GetWeaponInfo().getEffect(this));
    }
}
