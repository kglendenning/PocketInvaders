
package gameapp;

/**
 * @author Kurt
 */
public class Villain {
    private String name[] = {"Fighter1", "Fighter2"};
    private int level[] = {0, 1};
    private int run[] = {3, 4};
    private int rise[] = {0, 0};
    private int health[] = {200, 300};
    private double fireRate[] = {0.98, 0.95};
    //private int damage[] = {20, 35};
    
    public String getEnemyName(int type){
        return name[type];
    }
    
    public int getLevel(int type){
        return level[type];
    }
    
    public int getRun(int type){
        return run[type];
    }
    
    public int getRise(int type){
        return rise[type];
    };
    
    public int getHealth(int type){
        return health[type];
    }
    
    public double getFireRate(int type){
        return fireRate[type];
    }
    
    /*public int getDamage(int type){
        return damage[type];
    }*/
    
    public int getEnemyCount(){
        return name.length;
    }
}
