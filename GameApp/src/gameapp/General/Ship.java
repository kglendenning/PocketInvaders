
package gameapp.General;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Kurt
 */
public class Ship extends Entity{
    protected int mHealth, mMaxHealth, mWeapon, mWeaponCallbackIndex;
    private boolean mHasDrop;
    private String mDrop;
    
    public Ship(){
        SetX(0);
        SetY(0);
    }
    
    public Ship(int x, int y){
        SetX(x);
        SetY(y);
    }
    
    public final void SetWeaponCallbackIndex(int aIndex){
        this.mWeaponCallbackIndex = aIndex;
    }
    
    public final void SetWeapon(int aWeapon){
        mWeapon = aWeapon;
    }
    
    public final void SetHealth(int aHealth){
        mHealth = aHealth;
    }
    
    public final void SetMaxHealth(int aHealth){
        mMaxHealth = aHealth;
    }
    
    public final void SetHasDrop(boolean aHasDrop){
        mHasDrop = aHasDrop;
    }
    
    public final void SetDrop(String aDrop){
        mDrop = aDrop;
    }
            
    public final boolean GetHasDrop(){
        return mHasDrop;
    }
        
    public final String GetDrop(){
        return mDrop;
    }
      
    public final int GetWeaponCallbackIndex(){
        return mWeaponCallbackIndex;
    }
    
    public final int GetWeapon(){
        return mWeapon;
    }
    
    public final int GetHealth(){
        return mHealth;
    }
    
    public final int GetMaxHealth(){
        return mMaxHealth;
    }
    
    public Point GetCenter(){
        return new Point(GetX()+GetWidth()/2, GetY()+GetHeight()/2);
    }
    
    public boolean IsHit(Entity entity){
        return new Rectangle(GetX(), GetY(), GetWidth(), GetHeight()).intersects(entity.GetBoundingBox());
    }
    
    //easier to assume positive param is damage for now
    public void AlterHealth(int damage){
        SetHealth(GetHealth() - damage);
        if (GetHealth() < 0) {
            SetHealth(0);
        } else if(GetHealth() > GetMaxHealth()) {
            SetHealth(GetMaxHealth());
        }
    }
    
    /**
     * @param aDamage Damage of projectile
     * @return 0 - if nothing, 1 - if killed
     */
    public boolean TakeDamage(int aDamage){
        mHealth -= aDamage;
        return mHealth <= 0;
    }
}
