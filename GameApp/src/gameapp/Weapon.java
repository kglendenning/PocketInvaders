
package gameapp;

/**
 * @author Kurt
 */
public class Weapon {
    private String name[] = {"", "Burst", "Rocket", "Spray", "Bomb"};
    private int damage[] = {20, 25, 100, 35, 40};
    private int max[] = {0, 40, 15, 30, 15};
    private int reload[] = {0, 15, 5, 10, 5};
    
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
    
    public int getNumWeapons(){
        return name.length;
    }
}
