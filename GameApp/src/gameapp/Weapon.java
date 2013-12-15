
package gameapp;

/**
 * @author Kurt
 */
public class Weapon {
    String name[] = {"", "Burst", "Rocket"};
    int damage[] = {20, 25, 100};
    
    public String getWeaponName(int type){
        return name[type];
    }
    
    public int getDamage(int type){
        return damage[type];
    }
}
