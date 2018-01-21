
package gameapp.Enemy;

import gameapp.General.Entity;
import gameapp.General.GameField;
import gameapp.General.Logger;
import gameapp.Projectile.Projectile;
import java.awt.Rectangle;

/**
 *
 * @author Kurt
 */
public class Fighter4 extends Enemy {
    
    public Fighter4(int panelWidth, int panelHeight, int type) {
        super(panelWidth, panelHeight, type);
    }
    
    @Override
    public boolean IsHit(Entity entity){
        return new Rectangle(GetX(), GetY()+GetHeight()/4, GetWidth(), GetHeight()/2).intersects(entity.GetBoundingBox());
    }
    
    @Override
    public void ShootProjectile(){
        GameField.mProjectiles.add(new Projectile(GetX(), GetY()+GetHeight(), false));
        GameField.mProjectiles.add(new Projectile(GetX()+GetWidth(), GetY()+GetHeight(), false));
        Logger.mEnemyShotsFired += 2;
    }
}
