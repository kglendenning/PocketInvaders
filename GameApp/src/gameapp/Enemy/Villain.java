
package gameapp.Enemy;

/**
 * @author Kurt
 */
public class Villain {
    private static final String[] NAME = {"Fighter1", "Fighter2", "Boss1"};
    private static final int[] LEVEL = {0, 1, 2};
    private static final double[] RUN = {3.5, 3.0, 2.0};
    private static final double[] RISE = {0, 0, 0};
    private static final int[] HEALTH = {200, 300, 5000};
    private static final double[] FIRE_RATE = {0.98, 0.95, 0.95};
    private static final int[] WEAPON = {0, 0, 3};
    //private int damage[] = {20, 35};
    
    public static String getEnemyName(int type){
        return NAME[type];
    }
    
    public static int getLevel(int type){
        return LEVEL[type];
    }
    
    public static double getRun(int type){
        return RUN[type];
    }
    
    public static double getRise(int type){
        return RISE[type];
    };
    
    public static int getHealth(int type){
        return HEALTH[type];
    }
    
    public static double getFireRate(int type){
        return FIRE_RATE[type];
    }
    
    public static int getWeapon(int type){
        return WEAPON[type];
    }
    /*public int getDamage(int type){
        return damage[type];
    }*/
    
    public static int getEnemyCount(){
        return NAME.length;
    }
}
