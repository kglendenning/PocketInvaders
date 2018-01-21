
package gameapp.Projectile;

import gameapp.General.Entity;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Projectile extends Entity {
    private int mDamage = WeaponData.GetWeaponInfo().GetDamage(0), mWeaponTypeIndex = 0;
    
    public Projectile(int x, int y, boolean up){
        ImageIcon icon = new ImageIcon("images/Projectile.gif");
        
        SetWidth(icon.getIconWidth());
        SetHeight(icon.getIconHeight());
        SetX(x-(GetWidth()/2));
        SetY(y);
        SetRun(0);
        SetRise(up ? -10 : 10);
        SetImage(icon);
        //setType(0);
        //setDamage(weapon.getDamage(getType()));
    }
    
    public final void SetDamage(int damage){
        mDamage = damage;
    }
    
    public final void SetWeaponTypeIndex(int type){
        mWeaponTypeIndex = type;
    }
    
    public final int GetDamage(){
        return mDamage;
    }
    
    public final int GetWeaponTypeIndex(){
        return mWeaponTypeIndex;
    }
    
    public Point GetCenter(){
        return new Point(GetX()+GetWidth()/2, GetY()+GetHeight()/2);
    }
}
