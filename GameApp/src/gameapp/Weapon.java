
package gameapp;

import java.util.ArrayList;

/**
 * @author Kurt
 */
public class Weapon {
    private String name[] = {"", "Burst", "Rocket", "Spread", "Bomb"};
    private int damage[] = {20, 35, 150, 35, 200};
    private int max[] = {0, 25, 15, 20, 15};
    private int reload[] = {0, 10, 5, 6, 5};
    private int effect[] = {0, 0, 1, 0, 2};
    
    public String getWeaponName(int type){
        return name[type];
    }
    
    public int getDamage(int type){
        return damage[type];
    }
    
    public int getMax(int type){
        return max[type];
    }
    
    public int getReload(int type){
        return reload[type];
    }
    
    public int getEffect(int type){
        return effect[type];
    };
    
    public int getNumWeapons(){
        return name.length;
    }
    
    public Effect getEffect(Projectile projectile){
        switch (effect[projectile.getType()]){
            case 1:
                return new Effect(projectile);
            case 2:
                return new Explosion(projectile);
            default:
                return new Effect(projectile);
        }
    }
    
    public ArrayList<Projectile> getShot(int type, int x, int y, boolean up){
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
