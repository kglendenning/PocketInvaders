
package gameapp.Projectile;

import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Rocket extends Projectile {
    public int mAcceleration, mLimit = 5;
    
    public Rocket(int x, int y, boolean up){
        super(x, y, up);
        ImageIcon icon = new ImageIcon("images/Rocket.jpg");
        
        SetImage(icon);
        SetWidth(icon.getIconWidth());
        SetHeight(icon.getIconHeight());

        SetRise(up ? -3 : 3);
        //setLimit(5);
        SetWeaponTypeIndex(2);
        SetDamage(WeaponData.GetWeaponInfo().GetDamage(GetWeaponTypeIndex()));
        SetAcceleration(up ? -1 : 1);
    }
    
    public final void SetLimit(int limit){
        this.mLimit = limit;
    }
    
    public final void SetAcceleration(int acceleration){
        this.mAcceleration = acceleration;
    }
    
    public final int GetLimit(){
        return mLimit;
    }
    
    public final int GetAcceleration(){
        return mAcceleration;
    }
    
    @Override
    public void Move(){
        super.Move();
        
        mLimit--;
        if(mLimit == 0){
            SetLimit(5);
            SetRise(GetRise()+GetAcceleration());
        }
    }
}
