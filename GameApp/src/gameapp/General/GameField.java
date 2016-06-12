package gameapp.General;

import gameapp.Projectile.Weapon;
import gameapp.Projectile.Buff;
import gameapp.Effect.Effect;
import gameapp.Enemy.Enemy;
import gameapp.Enemy.Boss;
import gameapp.Enemy.Fighter4;
import gameapp.Player.Player;
import gameapp.Projectile.Projectile;
import gameapp.Projectile.Powerup;
import gameapp.Projectile.Ammo;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JPanel;

/**
 * @author Kurt
 */
public final class GameField extends JPanel implements KeyListener {
    public Player player;
    public Enemy target;
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static ArrayList<Projectile> projectiles = new ArrayList<>();
    public static ArrayList<Effect> effects = new ArrayList<>();
    public static ArrayList<Powerup> powerups = new ArrayList<>();
    public Scanner in;
    public int levelCount, enemyCount, spawnDelay, level;
    public boolean debug = false, pause = false;
    public String debugInfo = "";
    public SideBar sideBar;
    public int widthDiff, heightDiff;

    public GameField(int widthDiff, int heightDiff) {
        super();
        
        this.widthDiff = widthDiff;
        this.heightDiff = heightDiff;
        sideBar = new SideBar();
    }
    
    /**
     * These allow sideBar to be contained in GameField. Less overhead to update
     */
    public void setSideBarBounds(){
        sideBar.setBounds(super.getWidth()-widthDiff, 0, 300, super.getHeight()-heightDiff+3);
    }
    @Override
    public int getHeight(){
        return super.getHeight()-heightDiff;
    }
    @Override
    public int getWidth(){
        return super.getWidth()-widthDiff;
    }

    public void startGame(String fileName) {
        
        
        player = new Player(getWidth(), getHeight());

        try {
            in = new Scanner(new File(fileName));
            levelCount = level = in.nextInt();
            nextLevel();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }
    }

    public void nextLevel() {
        enemyCount = in.nextInt();
        if (levelCount > 0) {
            spawnDelay = in.nextInt();
        }
        levelCount--;
    }

    public void nextEnemy() {
        addEnemy(in.nextInt());
        enemyCount--;
        if (enemyCount > 0) {
            spawnDelay = in.nextInt();
        }
    }

    public void addEnemy(int type) {
        switch (type) {
            case 2:
                enemies.add(new Boss(getWidth(), getHeight(), type));
                break;
            case 3:
                enemies.add(new Fighter4(getWidth(), getHeight(), type));
                break;
            default:
                enemies.add(new Enemy(getWidth(), getHeight(), type));
                break;
        }
    }

    public int update() {
        if (!pause) {
            player.update();

            for (int i = 0; i < effects.size(); i++) {
                if (effects.get(i).update() == 1) {
                    effects.remove(i);
                    i--;
                }
            }

            for (int i = 0; i < powerups.size(); i++) {
                powerups.get(i).update();
                if (powerups.get(i).getY() > getHeight()) {
                    powerups.remove(i);
                    i--;
                }
            }

            for (int i = 0; i < projectiles.size(); i++) {
                Projectile shot = projectiles.get(i);
                shot.move();
                if (shot.getY() + shot.getHeight() >= this.getHeight()) {
                    projectiles.remove(i);
                    i--;
                }
            }

            for (Enemy enemy : enemies) {
                handleAction(enemy);
            }

            if(detectHits()){
                Logger.won = false;
                return 2;
            }
            if (spawnDelay == 0) {
                if (enemyCount == 0) {
                    if (enemies.isEmpty()) {
                        if (levelCount == 0) {
                            //game end
                            Logger.won = true;
                            return 2; //win
                            //System.out.println("You win.");
                            //System.exit(0);
                        } else {
                            nextLevel();
                        }
                    }
                } else {
                    nextEnemy();
                }
            } else {
                spawnDelay--;
            }
        } else {
            //display pause
            
        }

        sideBar.update(player, target);
        repaint();
        return 0;
    }

    public void handleAction(Enemy enemy) {
        int action = enemy.update();

        if (action == 1) {
            //projectiles.add(enemy.shootProjectile());
            enemy.shootProjectile();
        }
    }

    public boolean detectHits() {
        //detect player collision
        for (int i = 0; i < projectiles.size(); i++) {
            if (player.isHit(projectiles.get(i))) {
                effects.add(Weapon.getEffect(projectiles.get(i)));
                Logger.damageTaken += projectiles.get(i).getDamage();
                Logger.hitsTaken++;

                //player is killed
                if (player.takeDamage(projectiles.get(i).getDamage())) {
                    //System.out.println("You lose.");
                    return true;
                    //System.exit(0);
                }

                projectiles.remove(i);
                i--;
            }
        }
        
        //detect effect collisions
        for(int i = 0; i < effects.size(); i++){
            if(effects.get(i).isHarmful() && player.isHit(effects.get(i))){
                Logger.damageTaken += effects.get(i).getDamage();
                //player is killed
                if(player.takeDamage(effects.get(i).getDamage())){
                    //System.out.println("You lose.");
                    return true;
                    //System.exit(0);
                }
            }
        }

        //detect powerup collection
        for (int i = 0; i < powerups.size(); i++) {
            if (player.isHit(powerups.get(i))) {
                player.collect(powerups.get(i).getType(), powerups.get(i).getClass());
                powerups.remove(i);
                i--;
                Logger.powerupsCollected++;
            }
        }

        //detect enemy collision
        ArrayList<Projectile> playerShots = player.getProjectiles();
        for (int i = 0; i < playerShots.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                if (enemies.get(j).isHit(playerShots.get(i))) {
                    target = enemies.get(j);
                    Logger.damageDealt += playerShots.get(i).getDamage();
                    
                    //enemy is killed
                    if (enemies.get(j).takeDamage(playerShots.get(i).getDamage())) {
                        generatePowerup(enemies.get(j));
                        enemies.remove(j);
                        Logger.enemiesKilled++;
                    }

                    if(playerShots.get(i).getType() != 4){
                        effects.add(Weapon.getEffect(playerShots.get(i)));
                        Logger.playerHits++;
                        playerShots.remove(i);
                        i--;
                    }
                    break;
                }
            }
        }
        
        //detect enemy effect collisions
        for(int i = 0; i < effects.size(); i++){
            for(int j = 0; j < enemies.size(); j++){
                if(effects.get(i).isHarmful() && enemies.get(j).isHit(effects.get(i))){
                    //enemy is killed
                    if (enemies.get(j).takeDamage(effects.get(i).getDamage())) {
                        generatePowerup(enemies.get(j));
                        enemies.remove(j);
                        Logger.enemiesKilled++;
                    }
                    
                    Logger.damageDealt += effects.get(i).getDamage();
                }
            }
        }
        
        return false;
    }

    public void generatePowerup(Enemy enemy) {
        int enemyLevel = enemy.getLevel();
        double chance = Math.random() + ((double) enemyLevel * 0.10);
        int type = (int) (Math.random() * ((double) Weapon.getNumWeapons()-1.0)) + 1;

        if(chance >= 0.80) {
            powerups.add(new Ammo(enemy.getCenter(), type));
            Logger.powerupsSpawned++;
        } else if(chance >= 0.70) {
            powerups.add(new Buff(enemy.getCenter(), type));
            Logger.powerupsSpawned++;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //paint background
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        //paint enemies
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        //paint player objects
        player.draw(g);

        //draw projectiles
        for (Projectile shot : projectiles) {
            shot.draw(g);
        }

        //draw effects
        for (Effect effect : effects) {
            effect.draw(g);
        }

        //draw powerups
        for (Powerup powerup : powerups) {
            powerup.draw(g);
        }

        if (debug) {
            showDebug(g);
        }
        if(pause){
            g.setColor(new Color(0.5f, 0.5f, 0.5f, 0.4f));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            g.setFont(new Font("Serif", Font.BOLD, 28));
            g.drawString("Paused", getWidth()/2, getHeight()/2);
        }
        
    }

    public void showDebug(Graphics g) {
        debugInfo = "Enemies: " + enemies.size() + "\t"
                + "Projectiles: " + projectiles.size() + "\t"
                + "Player shots: " + player.getProjectiles().size() + "\t"
                + "Player hp: " + player.getHealth() + "\t"
                + "Effects: " + effects.size() + "\t"
                + "Level: " + (level-levelCount)
                + "\n"
                + "Player Shots: " + Logger.playerShots + "\t"
                + "Player Hits: " + Logger.playerHits + "\t"
                + "Hits Taken: " + Logger.hitsTaken + "\t"
                + "Damage Taken: " + Logger.damageTaken + "\t"
                + "Enemies Killed: " + Logger.enemiesKilled + "\t"
                + "Score: " + Logger.score + "\t"
                + "Enemy Shots Fired: " + Logger.enemyShotsFired + "\t"
                + "Healing Received: " + Logger.healingReceived + "\t"
                + "Powerups Spawned: " + Logger.powerupsSpawned + "\t"
                + "Powerups Collected: " + Logger.powerupsCollected + "\t"
                + "Damage Dealt: " + Logger.damageDealt;
        for(int i = 1; i < Weapon.getNumWeapons(); i++){
            player.collect(i, Ammo.class);
        }
        String lines[] = debugInfo.split("\t");
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
            player.setMoveLeft(true);
        } else if (key == KeyEvent.VK_D) {
            //move right
            player.setMoveRight(true);
        } else if (key == KeyEvent.VK_W) {
            //move up
            player.setMoveUp(true);
        } else if (key == KeyEvent.VK_S) {
            //move down
            player.setMoveDown(true);
        } else if (key == KeyEvent.VK_SPACE) {
            //shoot standard projectile
            player.setShooting(true);
        } else if (key == KeyEvent.VK_SHIFT) {
            //speed up
            player.setSpeed(false);
        } else if (key == KeyEvent.VK_UP) {
            //shoot weapon
            player.shootWeapon();
        } else if (key == KeyEvent.VK_LEFT) {
            player.shiftWeapon(false);
        } else if (key == KeyEvent.VK_RIGHT) {
            player.shiftWeapon(true);
        } else if (key == KeyEvent.VK_P) {
            pause = !pause;
        } else if (key == KeyEvent.VK_F8) {
            debug = !debug;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key;
        
        if ((key = e.getKeyCode()) == KeyEvent.VK_A) {
            player.setMoveLeft(false);
        } else if (key == KeyEvent.VK_D) {
            player.setMoveRight(false);
        } else if (key == KeyEvent.VK_W) {
            player.setMoveUp(false);
        } else if (key == KeyEvent.VK_S) {
            player.setMoveDown(false);
        } else if (key == KeyEvent.VK_SPACE) {
            player.setShooting(false);
        } else if (key == KeyEvent.VK_SHIFT) {
            player.setSpeed(true);
        }
    }
}
