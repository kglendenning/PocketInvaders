
package gameapp.General;

/**
 *
 * @author Kurt
 */
public class Logger {
    public static boolean won = false;
    public static int playerShots = 0;
    public static int playerHits = 0;
    public static int damageDealt = 0;
    public static int enemiesKilled = 0;
    
    public static int enemyShotsFired = 0;
    public static int hitsTaken = 0;
    public static int damageTaken = 0; 
    
    public static int healingReceived = 0;
    public static int powerupsSpawned = 0;
    public static int powerupsCollected = 0;
    public static int score = 0;
    
    public static void resetLogger(){
        playerShots = 0;
        playerHits = 0;
        hitsTaken = 0;
        damageTaken = 0;
        enemiesKilled = 0;
        score = 0;
        enemyShotsFired = 0;
        healingReceived = 0;
        powerupsSpawned = 0;
        powerupsCollected = 0;
        damageDealt = 0;
    }
}
