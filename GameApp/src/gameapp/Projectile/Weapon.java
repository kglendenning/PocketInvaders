
package gameapp.Projectile;

import gameapp.Effect.Explosion;
import gameapp.Effect.Effect;
import gameapp.Projectile.Bomb;
import gameapp.Projectile.Projectile;
import gameapp.Projectile.Rocket;
import gameapp.Projectile.Spread;
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
    private static String name[] = {"", "Burst", "Rocket", "Spread"};
    private static int damage[] = {20, 35, 150, 35};
    private static int max[] = {0, 25, 15, 20};
    private static int reload[] = {0, 10, 5, 6};
    private static int effect[] = {0, 0, 2, 0};
    
    public static String getWeaponName(int type){
        return name[type];
    }
    
    public static int getDamage(int type){
        return damage[type];
    }
    
    public static int getMax(int type){
        return max[type];
    }
    
    public static int getReload(int type){
        return reload[type];
    }
    
    public static int getEffect(int type){
        return effect[type];
    };
    
    public static int getNumWeapons(){
        return name.length;
    }
    
    public static Effect getEffect(Projectile projectile){
        switch (effect[projectile.getType()]){
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
