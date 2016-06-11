
package gameapp.Projectile;

import gameapp.Effect.Explosion;
import gameapp.Effect.Effect;
import java.util.ArrayList;

/**
 * @author Kurt
 */
public class Weapon {
//    private static String name[] = {"", "Burst", "Rocket", "Spread", "Bomb"};
//    private static int damage[] = {20, 35, 150, 35, 50};
//    private static int max[] = {0, 25, 15, 20, 15};
//    private static int reload[] = {0, 10, 5, 6, 5};
//    private static int effect[] = {0, 0, 2, 0, 2};
    private static final String[] NAME = {"", "Burst", "Rocket", "Spread"};
    private static final int[] DAMAGE = {20, 35, 150, 35};
    private static final int[] MAX = {0, 25, 15, 20};
    private static final int[] RELOAD = {0, 10, 5, 6};
    private static final int[] EFFECT = {0, 0, 2, 0};
    
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
        switch (EFFECT[projectile.getType()]){
            case 1:
                return new Effect(projectile);
            case 2:
                return new Explosion(projectile);
            default:
                return new Effect(projectile);
        }
    }
    
    public static ArrayList<Projectile> getShot(int type, int x, int y, boolean up){
        ArrayList<Projectile> weaponShots = new ArrayList<>();
        
        switch (type){
            case 1:
                weaponShots.add(new Burst(x, y, up));
                weaponShots.add(new Burst(x, y, up));
                weaponShots.add(new Burst(x, y, up));
                break;
            case 2:
                weaponShots.add(new Rocket(x, y, up));
                break;
            case 3:
                weaponShots.add(new Spread(x, y, -1, 7, up));
                weaponShots.add(new Spread(x, y, -2, 6, up));
                weaponShots.add(new Spread(x, y, 0, 8, up));
                weaponShots.add(new Spread(x, y, 2, 6, up));
                weaponShots.add(new Spread(x, y, 1, 7, up));
                break;
            case 4:
                weaponShots.add(new Bomb(x, y, up));
                break;
        }
        
        return weaponShots;
    }
}
