
package gameapp.Projectile;

import gameapp.Effect.Explosion;
import gameapp.Effect.Effect;
import gameapp.Effect.LongExplosion;
import gameapp.General.Logger;
import java.util.ArrayList;

/**
 * @author Kurt
 */
public class Weapon {
    private static final String[] NAME = {"", "Burst", "Rocket", "Spread", "Boomer"};
    private static final int[] DAMAGE = {20, 35, 150, 35, 1};
    private static final int[] MAX = {0, 25, 15, 20, 5};
    private static final int[] RELOAD = {0, 10, 5, 6, 2};
    private static final int[] EFFECT = {0, 0, 2, 0, 3};
    private static final ArrayList<ShotCallback> shotCallbacks = new ArrayList<>();
    
    public static String getWeaponName(int type){
        return NAME[type];
    }
    
    public static int getDamage(int type){
        return DAMAGE[type];
    }
    
    public static int getMax(int type){
        return MAX[type];
    }
    
    public static int getReload(int type){
        return RELOAD[type];
    }
    
    public static int getEffect(int type){
        return EFFECT[type];
    };
    
    public static int getNumWeapons(){
        return NAME.length;
    }
    
    public static Effect getEffect(Projectile projectile){
        switch (EFFECT[projectile.getWeaponTypeIndex()]){
            case 1:
                return new Effect(projectile);
            case 2:
                return new Explosion(projectile);
            case 3:
                return new LongExplosion(projectile);
            default:
                return new Effect(projectile);
        }
    }
    
    //add a weapon to the list dynamically
    public static int addShotCallback(ShotCallback callback){
        shotCallbacks.add(callback);
        return shotCallbacks.indexOf(callback);
    }
    
    public static ArrayList<Projectile> getShot(int type, int x, int y, boolean up){
        ArrayList<Projectile> weaponShots = new ArrayList<>();
        
        switch (type){
            case 1:
                weaponShots.add(new Burst(x, y, up));
                weaponShots.add(new Burst(x, y, up));
                weaponShots.add(new Burst(x, y, up));
                if(up)
                    Logger.playerShots += 3;
                else 
                    Logger.enemyShotsFired += 3;
                break;
            case 2:
                weaponShots.add(new Rocket(x, y, up));
                if(up)
                    Logger.playerShots++;
                else
                    Logger.enemyShotsFired++;
                break;
            case 3:
                weaponShots.add(new Spread(x, y, -1.0, 7.0, up));
                weaponShots.add(new Spread(x, y, -2.0, 6.0, up));
                weaponShots.add(new Spread(x, y, 0.0, 8.0, up));
                weaponShots.add(new Spread(x, y, 2.0, 6.0, up));
                weaponShots.add(new Spread(x, y, 1.0, 7.0, up));
                if(up)
                    Logger.playerShots += 5;
                else 
                    Logger.enemyShotsFired += 5;
                break;
            case 4:
                weaponShots.add(new Boomer(x, y, up));
                if(up)
                    Logger.playerShots++;
                else
                    Logger.enemyShotsFired++;
                break;
        }
        
        return weaponShots;
    }
    
    public static ArrayList<Projectile> getCallbackShot(int index, int x, int y, boolean up){
        return shotCallbacks.get(index).getShot(x, y, up);
    }
}
