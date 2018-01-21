
package gameapp.Enemy;

import gameapp.General.Entity;
import gameapp.General.GameField;
import gameapp.General.Logger;
import gameapp.Projectile.Projectile;
import gameapp.Projectile.ShotCallback;
import gameapp.Projectile.Spread;
import gameapp.Projectile.WeaponData;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author Kurt
 */
public class Boss extends Enemy {
    private int mShotDelay = 200;//, weapon;
    
    public Boss(int panelWidth, int panelHeight, int type) {
        super(panelWidth, panelHeight, type);
        
        SetY(10);
        //setWeapon(VillainData.GetVillainInfo().getWeapon(type));
        
        SetWeaponCallbackIndex(WeaponData.GetWeaponInfo().addShotCallback(new ShotCallback(){
            @Override
            public ArrayList<Projectile> GetShot(int x, int y, boolean up) {
                ArrayList<Projectile> weaponShots = new ArrayList<>();
                
                weaponShots.add(new Spread(x, y, -2, 14, up));
                weaponShots.add(new Spread(x, y, -4, 12, up));
                weaponShots.add(new Spread(x, y, 0, 16, up));
                weaponShots.add(new Spread(x, y, 4, 12, up));
                weaponShots.add(new Spread(x, y, 2, 14, up));
                Logger.mEnemyShotsFired += 5;
                
                return weaponShots;
            }
        }));
    }
    
    //public void setWeapon(int weapon){
    //    this.weapon = weapon;
    //}
    
    public void ShootWeapon(){
        GameField.mProjectiles.addAll(WeaponData.GetWeaponInfo().getCallbackShot(GetWeaponCallbackIndex(), GetX() + (GetWidth() / 2), GetY(), false));
    }
    
    @Override
    public boolean IsHit(Entity entity){
        int xpoints[] = {GetX(), GetX() + GetWidth(), GetX() + GetWidth() / 2};
        int ypoints[] = {GetY(), GetY(), GetY() + GetHeight()};
        return new Polygon(xpoints, ypoints, 3).intersects(entity.GetBoundingBox());
    }
    
    /**
     * @return 0 - do nothing, 1 - add projectile
     */
    @Override
    public int Update(){
        Move();
        
        if(--mShotDelay == 0) {
            ShootWeapon();
            mShotDelay = 200;
        }
        
        double action = Math.random();
        return action > VillainData.GetVillainInfo().GetFireRate(GetVillainTypeIndex()) ? 1 : 0;
    }
}
