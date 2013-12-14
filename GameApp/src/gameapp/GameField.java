
package gameapp;

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
public final class GameField extends JPanel implements KeyListener{
    public Player player;
    public ArrayList<Enemy> enemies = new ArrayList<>();
    public ArrayList<Projectile> projectiles = new ArrayList<>();
    public ArrayList<Effect> effects = new ArrayList<>();
    public ArrayList<Powerup> powerups = new ArrayList<>();
    public Scanner in;
    public int levelcount, enemycount, spawndelay;
        
    public GameField(){
        super();
    }
    
    public void setPlayer(){
        player = new Player(getWidth(), getHeight());
    }
    
    public void startGame(){
        setPlayer();
        
        //read in level count
        try{
            in = new Scanner(new File("games/BiggerTest.txt"));
            levelcount = in.nextInt();
            nextLevel();
        }catch(Exception e){
            System.out.println("Error: " + e);
            System.exit(0);
        }
    }
    
    public void nextLevel(){
        enemycount = in.nextInt();
        if(levelcount > 0){
            spawndelay = in.nextInt();
        }
        levelcount--;
    }
    
    public void nextEnemy(){
        addEnemy(in.nextInt());
        enemycount--;
        if(enemycount > 0){
           spawndelay = in.nextInt();
        }
    }
    
    public void addEnemy(int level){
        switch (level){
            case 0:
                enemies.add(new Enemy(getWidth(), getHeight()));
                break;
            case 1:
                enemies.add(new Enemy2(getWidth(), getHeight()));
        }
    }
    
    public void update(){
        player.update();
        
        for(int i = 0; i < effects.size(); i++){
            if(effects.get(i).update() == 1){
                effects.remove(i);
                i--;
            }
        }
        
        for(int i = 0; i < powerups.size(); i++){
            powerups.get(i).update();
            if(powerups.get(i).getY() > getHeight()){
                powerups.remove(i);
                i--;
            }
        }
        
        for(int i = 0; i < projectiles.size(); i++){
            Projectile shot = projectiles.get(i);
            shot.move();
            if(shot.getY() + shot.getHeight() >= this.getHeight()){
                projectiles.remove(i);
                i--;
            }
        }
        
        for(Enemy enemy : enemies){
            handleAction(enemy);
        }
        
        detectHits();
        if(spawndelay == 0){
            if(enemycount == 0){
                if(enemies.isEmpty()){
                    if(levelcount == 0){
                        //game end
                        System.out.println("You win.");
                        System.exit(0);
                    }else{nextLevel();}
                }
            } else {nextEnemy();}
        } else {spawndelay--;}
        repaint();
    }
    
    public void handleAction(Enemy enemy){
        int action = enemy.update();
        
        if(action == 1){
            projectiles.add(enemy.shootProjectile());
        }
    }
    
    public void detectHits(){
        //detect player collision
        for(int i = 0; i < projectiles.size(); i++){
            if(player.isHit(projectiles.get(i))){
                effects.add(new Effect(projectiles.get(i).getCenter()));
                
                //player is killed
                if(player.takeDamage(projectiles.get(i).getDamage()) == 1){
                    System.out.println("You lose.");
                    System.exit(0);
                }
                
                projectiles.remove(i);
                i--;
            }
        }
        
        //detect powerup collection
        for(int i = 0; i < powerups.size(); i++){
            if(player.isHit(powerups.get(i))){
                player.enhance(powerups.get(i).getType());
                powerups.remove(i);
                i--;
            }
        }
        
        //detect enemy collision
        ArrayList<Projectile> playerShots = player.getProjectiles();
        for(int i = 0; i < playerShots.size(); i++){
            for(int j = 0; j < enemies.size(); j++){
                if(enemies.get(j).isHit(playerShots.get(i))){
                    effects.add(new Effect(playerShots.get(i).getCenter()));
                    
                    //enemy is killed
                    if(enemies.get(j).takeDamage(playerShots.get(i).getDamage()) == 1){
                        generatePowerup(enemies.get(j));
                        enemies.remove(j);
                    }
                    
                    playerShots.remove(i);
                    i--;
                    break;
                }
            }
        }
    }
    
    public void generatePowerup(Enemy enemy){
        int level = enemy.getLevel();
        double chance = Math.random() + ((double) level * 0.10);
        int type = (int) (Math.random() * 4.0) + 1;
        
        if(chance >= 0.90){
            powerups.add(new Powerup(enemy.getCenter(), type));
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //paint background
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        //paint enemies
        for(Enemy enemy : enemies){
            enemy.draw(g);
        }
        
        //paint player objects
        player.draw(g);
        
        //draw projectiles
        for(Projectile shot : projectiles){
            g.drawImage(shot.getImageIcon().getImage(), shot.getX(), shot.getY(), null);
        }
        
        //draw effects
        for(Effect effect : effects){
            effect.draw(g);
        }
        
        //draw powerups
        for(Powerup powerup : powerups){
            powerup.draw(g);
        }
    }

    @Override
    public void keyTyped(KeyEvent e){
        
    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_A){
            //move left
            player.setMoveLeft(true);
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            //move right
            player.setMoveRight(true);
        }else if(e.getKeyCode() == KeyEvent.VK_W){
            //move up
            player.setMoveUp(true);
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            //move down
            player.setMoveDown(true);
        }else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            //shoot standard projectile
            player.setShooting(true);
        }else if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            //speed up
            player.setSpeed(false);
        }else if(e.getKeyCode() == KeyEvent.VK_UP){
            //shoot weapon
            player.shootWeapon();
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_A){
            player.setMoveLeft(false);
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            player.setMoveRight(false);
        }else if(e.getKeyCode() == KeyEvent.VK_W){
            player.setMoveUp(false);
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            player.setMoveDown(false);
        }else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            player.setShooting(false);
        }else if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            player.setSpeed(true);
        }
    }
}
