
package gameapp.General;

/**
 *
 * @author Kurt
 */
public class Logger {
    public static boolean mWon = false;
    public static int mPlayerShots = 0;
    public static int mPlayerHits = 0;
    public static int mDamageDealt = 0;
    public static int mEnemiesKilled = 0;
    
    public static int mEnemyShotsFired = 0;
    public static int mHitsTaken = 0;
    public static int mDamageTaken = 0; 
    
    public static int mHealingReceived = 0;
    public static int mPowerupsSpawned = 0;
    public static int mPowerupsCollected = 0;
    public static int mScore = 0;
    
    public static void ResetLogger(){
        mPlayerShots = 0;
        mPlayerHits = 0;
        mHitsTaken = 0;
        mDamageTaken = 0;
        mEnemiesKilled = 0;
        mScore = 0;
        mEnemyShotsFired = 0;
        mHealingReceived = 0;
        mPowerupsSpawned = 0;
        mPowerupsCollected = 0;
        mDamageDealt = 0;
    }
}
