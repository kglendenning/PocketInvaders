
package gameapp.Enemy;

import java.util.ArrayList;

/**
 * @author Kurt
 */
public class Villain {
    private class VillainInfo {
        public VillainInfo(String name, int level, double run, double rise, int health, double fireRate, int weapon, int damage) {
            mName = name;
            mLevel = level;
            mRun = run;
            mRise = rise;
            mHealth = health;
            mFireRate = fireRate;
            mWeapon = weapon;
            mDamage = damage;
        }
        
        String mName;
        int mLevel;
        double mRun;
        double mRise;
        int mHealth;
        double mFireRate;
        int mWeapon;
        int mDamage;
    }
    
    private static final ArrayList<VillainInfo> mVillainInfo = new ArrayList<>();
    
    public Villain() {
        mVillainInfo.add(new VillainInfo("Fighter1", 0, 3.5, 0, 200, 0.98, 0, 20));
        mVillainInfo.add(new VillainInfo("Fighter2", 2, 3.0, 0, 300, 0.95, 0, 20));
        mVillainInfo.add(new VillainInfo("Boss1", 5, 2.0, 0, 5000, 0.95, 0, 25));
        mVillainInfo.add(new VillainInfo("Fighter4", 1, 4.5, 0, 100, 0.98, 0, 10));
    }
    
    public static String GetEnemyName(int type){
        return mVillainInfo.get(type).mName;
    }
    
    public static int GetLevel(int type){
        return mVillainInfo.get(type).mLevel;
    }
    
    public static double GetRun(int type){
        return mVillainInfo.get(type).mRun;
    }
    
    public static double GetRise(int type){
        return mVillainInfo.get(type).mRise;
    };
    
    public static int GetHealth(int type){
        return mVillainInfo.get(type).mHealth;
    }
    
    public static double GetFireRate(int type){
        return mVillainInfo.get(type).mFireRate;
    }
    
    public static int GetWeapon(int type){
        return mVillainInfo.get(type).mWeapon;
    }
    
    public int GetDamage(int type){
        return mVillainInfo.get(type).mDamage;
    }
    
    public static int GetEnemyCount(){
        return mVillainInfo.size();
    }
}
