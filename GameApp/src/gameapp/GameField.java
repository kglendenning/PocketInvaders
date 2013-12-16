package gameapp;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Kurt
 */
public final class GameField extends JPanel implements KeyListener {

    public Player player;
    public Enemy target;
    public ArrayList<Enemy> enemies = new ArrayList<>();
    public ArrayList<Projectile> projectiles = new ArrayList<>();
    public ArrayList<Effect> effects = new ArrayList<>();
    public ArrayList<Powerup> powerups = new ArrayList<>();
    public Scanner in;
    public int levelCount, enemyCount, spawnDelay, level;
    public boolean debug = false, pause = false;
    public String debugInfo = "";
    public Weapon weapon = new Weapon();

    public GameField() {
        super();
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
        enemies.add(new Enemy(getWidth(), getHeight(), type));
    }

    public void update() {
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

            detectHits();
            if (spawnDelay == 0) {
                if (enemyCount == 0) {
                    if (enemies.isEmpty()) {
                        if (levelCount == 0) {
                            //game end
                            System.out.println("You win.");
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

        repaint();
    }

    public void handleAction(Enemy enemy) {
        int action = enemy.update();

        if (action == 1) {
            projectiles.add(enemy.shootProjectile());
        }
    }

    public void detectHits() {
        //detect player collision
        for (int i = 0; i < projectiles.size(); i++) {
            if (player.isHit(projectiles.get(i))) {
                effects.add(weapon.getEffect(projectiles.get(i)));

                //player is killed
                if (player.takeDamage(projectiles.get(i).getDamage()) == 1) {
                    System.out.println("You lose.");
                    //System.exit(0);
                }

                projectiles.remove(i);
                i--;
            }
        }
        
        //detect effect collisions
        for(int i = 0; i < effects.size(); i++){
            if(effects.get(i).isHarmful() && player.isHit(effects.get(i))){
                //player is killed
                if(player.takeDamage(effects.get(i).getDamage()) == 1){
                    System.out.println("You lose.");
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
            }
        }

        //detect enemy collision
        ArrayList<Projectile> playerShots = player.getProjectiles();
        for (int i = 0; i < playerShots.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                if (enemies.get(j).isHit(playerShots.get(i))) {
                    target = enemies.get(j);
                    effects.add(weapon.getEffect(playerShots.get(i)));

                    //enemy is killed
                    if (enemies.get(j).takeDamage(playerShots.get(i).getDamage()) == 1) {
                        generatePowerup(enemies.get(j));
                        enemies.remove(j);
                    }

                    playerShots.remove(i);
                    i--;
                    break;
                }
            }
        }
        
        //detect enemy effect collisions
        for(int i = 0; i < effects.size(); i++){
            for(int j = 0; j < enemies.size(); j++){
                if(effects.get(i).isHarmful() && enemies.get(j).isHit(effects.get(i))){
                    //enemy is killed
                    if (enemies.get(j).takeDamage(effects.get(i).getDamage()) == 1) {
                        generatePowerup(enemies.get(j));
                        enemies.remove(j);  
                    }
                }
            }
        }
    }

    public void generatePowerup(Enemy enemy) {
        int level = enemy.getLevel();
        double chance = Math.random() + ((double) level * 0.10);
        int type = (int) (Math.random() * 4.0) + 1;

        if(chance >= 0.80) {
            powerups.add(new Ammo(enemy.getCenter(), type));
        } else if(chance >= 0.70) {
            powerups.add(new Buff(enemy.getCenter(), type));
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
                + "";
        for(int i = 1; i < weapon.getNumWeapons(); i++){
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
        if (e.getKeyCode() == KeyEvent.VK_A) {
            //move left
            player.setMoveLeft(true);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            //move right
            player.setMoveRight(true);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            //move up
            player.setMoveUp(true);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            //move down
            player.setMoveDown(true);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            //shoot standard projectile
            player.setShooting(true);
        } else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            //speed up
            player.setSpeed(false);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            //shoot weapon
            player.shootWeapon();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.shiftWeapon(false);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.shiftWeapon(true);
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            pause = !pause;
        } else if (e.getKeyCode() == KeyEvent.VK_F8) {
            debug = !debug;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            player.setMoveLeft(false);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            player.setMoveRight(false);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            player.setMoveUp(false);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.setMoveDown(false);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setShooting(false);
        } else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            player.setSpeed(true);
        }
    }
}
