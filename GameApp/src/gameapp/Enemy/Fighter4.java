
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
    public boolean isHit(Entity entity){
        return new Rectangle(getX(), getY()+getHeight()/4, getWidth(), getHeight()/2).intersects(entity.getBoundingBox());
    }
    
    @Override
    public void shootProjectile(){
        GameField.projectiles.add(new Projectile(getX(), getY()+getHeight(), false));
        GameField.projectiles.add(new Projectile(getX()+getWidth(), getY()+getHeight(), false));
        Logger.enemyShotsFired += 2;
    }
}
