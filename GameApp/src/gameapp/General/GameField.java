package gameapp.General;

import gameapp.Projectile.WeaponData;
import gameapp.Effect.Effect;
import gameapp.Enemy.Enemy;
import gameapp.Enemy.Boss;
import gameapp.Enemy.Fighter4;
import gameapp.Player.Player;
import gameapp.Projectile.Projectile;
import gameapp.Projectile.Powerup;
import gameapp.Projectile.Ammo;
import gameapp.Projectile.Health;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JPanel;

/**
 * @author Kurt
 */
public final class GameField extends JPanel implements KeyListener {
    public Player mPlayer;
    public Enemy mTarget;
    public static ArrayList<Enemy> mEnemies = new ArrayList<>();
    public static ArrayList<Projectile> mProjectiles = new ArrayList<>();
    public static ArrayList<Effect> mEffects = new ArrayList<>();
    public static ArrayList<Powerup> mPowerups = new ArrayList<>();
    public Scanner mScanner;
    public int mLevelCount, mEnemyCount, mSpawnDelay, mLevel;
    public boolean mDebug = false, mPause = false;
    public String mDebugInfo = "";
    public SideBar mSideBar;
    public int mWidthDiff, mHeightDiff;

    public GameField(int widthDiff, int heightDiff) {
        super();
        
        mWidthDiff = widthDiff;
        mHeightDiff = heightDiff;
        mSideBar = new SideBar();
    }
    
    /**
     * These allow sideBar to be contained in GameField. Less overhead to update
     */
    public void SetSideBarBounds(){
        mSideBar.setBounds(super.getWidth()-mWidthDiff, 0, 300, super.getHeight()-mHeightDiff+3);
    }
    @Override
    public int getHeight(){
        return super.getHeight()-mHeightDiff;
    }
    @Override
    public int getWidth(){
        return super.getWidth()-mWidthDiff;
    }

    public void StartGame(String fileName) {
        mPlayer = new Player(getWidth(), getHeight());

        try {
            mScanner = new Scanner(new File(fileName));
            mLevelCount = mLevel = mScanner.nextInt();
            NextLevel();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }
    }

    public void NextLevel() {
        mEnemyCount = mScanner.nextInt();
        if (mLevelCount > 0) {
            mSpawnDelay = mScanner.nextInt();
        }
        mLevelCount--;
    }

    public void NextEnemy() {
        AddEnemy(mScanner.nextInt());
        mEnemyCount--;
        if (mEnemyCount > 0) {
            mSpawnDelay = mScanner.nextInt();
        }
    }

    public void AddEnemy(int type) {
        switch (type) {
            case 2:
                mEnemies.add(new Boss(getWidth(), getHeight(), type));
                break;
            case 3:
                mEnemies.add(new Fighter4(getWidth(), getHeight(), type));
                break;
            default:
                mEnemies.add(new Enemy(getWidth(), getHeight(), type));
                break;
        }
    }

    public int Update() {
        if (!mPause) {
            mPlayer.Update();

            for (int i = 0; i < mEffects.size(); i++) {
                if (mEffects.get(i).Update() == 1) {
                    mEffects.remove(i);
                    i--;
                }
            }

            for (int i = 0; i < mPowerups.size(); i++) {
                mPowerups.get(i).Update();
                if (mPowerups.get(i).GetY() > getHeight()) {
                    mPowerups.remove(i);
                    i--;
                }
            }

            for (int i = 0; i < mProjectiles.size(); i++) {
                Projectile shot = mProjectiles.get(i);
                shot.Move();
                if (shot.GetY() + shot.GetHeight() >= this.getHeight()) {
                    mProjectiles.remove(i);
                    i--;
                }
            }

            for (Enemy enemy : mEnemies) {
                HandleAction(enemy);
            }

            if(DetectHits()){
                Logger.mWon = false;
                return 2;
            }
            if (mSpawnDelay == 0) {
                if (mEnemyCount == 0) {
                    if (mEnemies.isEmpty()) {
                        if (mLevelCount == 0) {
                            //game end
                            Logger.mWon = true;
                            return 2; //win
                            //System.out.println("You win.");
                            //System.exit(0);
                        } else {
                            NextLevel();
                        }
                    }
                } else {
                    NextEnemy();
                }
            } else {
                mSpawnDelay--;
            }
        } else {
            //display pause
            
        }

        mSideBar.Update(mPlayer, mTarget);
        repaint();
        return 0;
    }

    public void HandleAction(Enemy enemy) {
        int action = enemy.Update();

        if (action == 1) {
            //projectiles.add(enemy.shootProjectile());
            enemy.ShootProjectile();
        }
    }

    public boolean DetectHits() {
        //detect player collision
        for (int i = 0; i < mProjectiles.size(); i++) {
            if (mPlayer.IsHit(mProjectiles.get(i))) {
                mEffects.add(WeaponData.GetWeaponInfo().getEffect(mProjectiles.get(i)));
                if(mProjectiles.get(i).GetDamage() >= 0){
                    Logger.mDamageTaken += mProjectiles.get(i).GetDamage();
                    Logger.mHitsTaken++;
                } else {
                    Logger.mHealingReceived -= mProjectiles.get(i).GetDamage();
                }

                //player is killed
                if (mPlayer.TakeDamage(mProjectiles.get(i).GetDamage())) {
                    return true;
                }

                mProjectiles.remove(i);
                i--;
            }
        }
        
        //detect effect collisions
        for(int i = 0; i < mEffects.size(); i++){
            if(mEffects.get(i).IsHarmful() && mPlayer.IsHit(mEffects.get(i))){
                if(mEffects.get(i).GetDamage() >= 0){
                    Logger.mDamageTaken += mEffects.get(i).GetDamage();
                } else {
                    Logger.mHealingReceived -= mEffects.get(i).GetDamage();
                }
                
                //player is killed
                if(mPlayer.TakeDamage(mEffects.get(i).GetDamage())){
                    return true;
                }
            }
        }

        //detect powerup collection
        for (int i = 0; i < mPowerups.size(); i++) {
            if (mPlayer.IsHit(mPowerups.get(i))) {
                mPlayer.Collect(mPowerups.get(i).GetType(), mPowerups.get(i).getClass());
                mPowerups.remove(i);
                i--;
                Logger.mPowerupsCollected++;
            }
        }

        //detect enemy collision
        ArrayList<Projectile> playerShots = mPlayer.GetProjectiles();
        for (int i = 0; i < playerShots.size(); i++) {
            for (int j = 0; j < mEnemies.size(); j++) {
                if (mEnemies.get(j).IsHit(playerShots.get(i))) {
                    mTarget = mEnemies.get(j);
                    Logger.mDamageDealt += playerShots.get(i).GetDamage();
                    
                    //enemy is killed
         
                    if (mEnemies.get(j).TakeDamage(playerShots.get(i).GetDamage())) {
                        if (mEnemies.get(j).GetHasDrop()){    
                            if (mEnemies.get(j).GetDrop().equals("Health")){        
                                mPowerups.add(new Health(mEnemies.get(j).GetCenter()));
                                Logger.mPowerupsSpawned++;
                            }
                            if (mEnemies.get(j).GetDrop().equals("Ammo")){       
                                mPowerups.add(new Ammo(mEnemies.get(j).GetCenter(),((int) (Math.random() * ((double) WeaponData.GetWeaponInfo().GetNumWeapons()-1.0)) + 1)));
                                Logger.mPowerupsSpawned++;
                            }
                        }
                        mEnemies.remove(j);
                        Logger.mEnemiesKilled++;
                    }

                    if(playerShots.get(i).GetWeaponTypeIndex() != 4){
                        mEffects.add(WeaponData.GetWeaponInfo().getEffect(playerShots.get(i)));
                        Logger.mPlayerHits++;
                        playerShots.remove(i);
                        i--;
                    }

                    break;
                }
            }
        }
        
        //detect enemy effect collisions
        for(int i = 0; i < mEffects.size(); i++){
            for(int j = 0; j < mEnemies.size(); j++){
                if(mEffects.get(i).IsHarmful() && mEnemies.get(j).IsHit(mEffects.get(i))){
                    //enemy is killed

                    if (mEnemies.get(j).TakeDamage(mEffects.get(i).GetDamage())) {
                        if (mEnemies.get(j).GetHasDrop()){    
                            if (mEnemies.get(j).GetDrop().equals("Health")){        
                                mPowerups.add(new Health(mEnemies.get(j).GetCenter()));
                                Logger.mPowerupsSpawned++;
                            }
                            if (mEnemies.get(j).GetDrop().equals("Ammo")){       
                                mPowerups.add(new Ammo(mEnemies.get(j).GetCenter(),((int) (Math.random() * ((double) WeaponData.GetWeaponInfo().GetNumWeapons()-1.0)) + 1)));
                                Logger.mPowerupsSpawned++;
                            }
                        }
                        mEnemies.remove(j);
                        Logger.mEnemiesKilled++;
                    }
                    
                    Logger.mDamageDealt += mEffects.get(i).GetDamage(); 
                }
            }
        }
        
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //paint background
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        //paint enemies
        for (Enemy enemy : mEnemies) {
            enemy.Draw(g);
        }

        //paint player objects
        mPlayer.Draw(g);

        //draw projectiles
        for (Projectile shot : mProjectiles) {
            shot.Draw(g);
        }

        //draw effects
        for (Effect effect : mEffects) {
            effect.Draw(g);
        }

        //draw powerups
        for (Powerup powerup : mPowerups) {
            powerup.Draw(g);
        }

        if (mDebug) {
            ShowDebug(g);
        }
        if(mPause){
            g.setColor(new Color(0.5f, 0.5f, 0.5f, 0.4f));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            g.setFont(new Font("Serif", Font.BOLD, 28));
            g.drawString("Paused", getWidth()/2, getHeight()/2);
        }
        
    }

    public void ShowDebug(Graphics g) {
        mDebugInfo = "Enemies: " + mEnemies.size() + "\t"
                + "Projectiles: " + mProjectiles.size() + "\t"
                + "Player shots: " + mPlayer.GetProjectiles().size() + "\t"
                + "Player hp: " + mPlayer.GetHealth() + "\t"
                + "Effects: " + mEffects.size() + "\t"
                + "Level: " + (mLevel-mLevelCount)
                + "\n"
                + "Player Shots: " + Logger.mPlayerShots + "\t"
                + "Player Hits: " + Logger.mPlayerHits + "\t"
                + "Hits Taken: " + Logger.mHitsTaken + "\t"
                + "Damage Taken: " + Logger.mDamageTaken + "\t"
                + "Enemies Killed: " + Logger.mEnemiesKilled + "\t"
                + "Score: " + Logger.mScore + "\t"
                + "Enemy Shots Fired: " + Logger.mEnemyShotsFired + "\t"
                + "Healing Received: " + Logger.mHealingReceived + "\t"
                + "Powerups Spawned: " + Logger.mPowerupsSpawned + "\t"
                + "Powerups Collected: " + Logger.mPowerupsCollected + "\t"
                + "Damage Dealt: " + Logger.mDamageDealt;
        for(int i = 1; i < WeaponData.GetWeaponInfo().GetNumWeapons(); i++){
            mPlayer.Collect(i, Ammo.class);
        }
        String lines[] = mDebugInfo.split("\t");
        g.setColor(Color.YELLOW);
        for (int i = 0; i < lines.length; i++) {
            g.drawString(lines[i], 10, 20 + i * 10);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key;
        
        if ((key = e.getKeyCode()) == KeyEvent.VK_A) {
            //move left
            mPlayer.SetMoveLeft(true);
        } else if (key == KeyEvent.VK_D) {
            //move right
            mPlayer.SetMoveRight(true);
        } else if (key == KeyEvent.VK_W) {
            //move up
            mPlayer.SetMoveUp(true);
        } else if (key == KeyEvent.VK_S) {
            //move down
            mPlayer.SetMoveDown(true);
        } else if (key == KeyEvent.VK_SPACE) {
            //shoot standard projectile
            mPlayer.SetShooting(true);
        } else if (key == KeyEvent.VK_SHIFT) {
            //speed up
            mPlayer.SetSpeed(false);
        } else if (key == KeyEvent.VK_UP) {
            //shoot weapon
            mPlayer.ShootWeapon();
        } else if (key == KeyEvent.VK_LEFT) {
            mPlayer.ShiftWeapon(false);
        } else if (key == KeyEvent.VK_RIGHT) {
            mPlayer.ShiftWeapon(true);
        } else if (key == KeyEvent.VK_P) {
            mPause = !mPause;
        } else if (key == KeyEvent.VK_F8) {
            mDebug = !mDebug;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key;
        
        if ((key = e.getKeyCode()) == KeyEvent.VK_A) {
            mPlayer.SetMoveLeft(false);
        } else if (key == KeyEvent.VK_D) {
            mPlayer.SetMoveRight(false);
        } else if (key == KeyEvent.VK_W) {
            mPlayer.SetMoveUp(false);
        } else if (key == KeyEvent.VK_S) {
            mPlayer.SetMoveDown(false);
        } else if (key == KeyEvent.VK_SPACE) {
            mPlayer.SetShooting(false);
        } else if (key == KeyEvent.VK_SHIFT) {
            mPlayer.SetSpeed(true);
        }
    }
}
