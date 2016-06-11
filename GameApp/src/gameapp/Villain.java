
package gameapp;

/**
 * @author Kurt
 */
public class Villain {
    private static String name[] = {"Fighter1", "Fighter2"};
    private static int level[] = {0, 1};
    private static double run[] = {3.5, 3.0};
    private static double rise[] = {0, 0};
    private static int health[] = {200, 300};
    private static double fireRate[] = {0.98, 0.95};
    //private int damage[] = {20, 35};
    
    public static String getEnemyName(int type){
        return name[type];
    }
    
    public static int getLevel(int type){
        return level[type];
    }
    
    public static double getRun(int type){
        return run[type];
    }
    
    public static double getRise(int type){
        return rise[type];
    };
    
    public static int getHealth(int type){
        return health[type];
    }
    
    public static double getFireRate(int type){
        return fireRate[type];
    }
    
    /*public int getDamage(int type){
        return damage[type];
    }*/
    
    public static int getEnemyCount(){
        return name.length;
    }
}
