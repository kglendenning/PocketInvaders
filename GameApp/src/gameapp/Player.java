
package gameapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Player extends Ship{
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    public boolean shooting = false, moveLeft = false, moveRight = false,
            moveUp = false, moveDown = false, speed = true;
    private int shotDelay = 0;
    private final int MAX_HEALTH = 500;
    
    public Player(int width, int height){
        ImageIcon icon = new ImageIcon("images/Player.jpg");
        
        this.panelWidth = width;
        this.panelHeight = height;
        setX(width/2 - icon.getIconWidth()/2);
        setY(height-icon.getIconHeight());
        //setHealth(MAX_HEALTH);//testing with default for now
        setImage(icon);
    }
    
    public ArrayList<Projectile> getProjectiles(){
        return projectiles;
    }
    
    public void update(){
        for(int i = 0; i < projectiles.size(); i++){
            Projectile shot = projectiles.get(i);
            shot.move();
            if(shot.getY() + shot.getHeight() <= 0){
                projectiles.remove(i);
                i--;
            }
        }

        shootCheck();
        moveCheck();
    }
    
    public void setMoveLeft(boolean moveLeft){
        this.moveLeft = moveLeft;
    }
    
    public void setMoveRight(boolean moveRight){
        this.moveRight = moveRight;
    }
    
    public void setMoveUp(boolean moveUp){
        this.moveUp = moveUp;
    }
    
    public void setMoveDown(boolean moveDown){
        this.moveDown = moveDown;
    }
    
    public void setSpeed(boolean speed){
        this.speed = speed;
    }
    
    public void setShooting(boolean shooting){
        this.shooting = shooting;
    }
    
    public void shootCheck(){
        if(shooting){
            if(shotDelay == 0){
                shootProjectile();
                shotDelay = 5;
            }
            shotDelay--;
        }else{
            shotDelay = 0;
        }
    }
    
    public void moveCheck(){
        if(moveLeft && !moveRight){
            setX(getX()-(speed ? 7 : 4));
            if(getX() <= 0){
                setX(0);
            }
        }else if(moveRight && !moveLeft){
            setX(getX()+(speed ? 7 : 4));
            if(getX() >= panelWidth-getWidth()){
                setX(panelWidth-getWidth());
            }
        }
        
        if(moveUp && !moveDown){
            setY(getY()-(speed ? 7 : 4));
            if(getY() <= 3*panelHeight/5){
                setY(3*panelHeight/5);
            }
        }else if(moveDown && !moveUp){
            setY(getY()+(speed ? 7 : 4));
            if(getY() >= panelHeight-getHeight()){
                setY(panelHeight-getHeight());
            }
        }
    }
    
    public void shootProjectile(){
        projectiles.add(new Projectile(getX()+(getWidth()/2), getY(), true));
    }
    
    public void shootWeapon(){
        //projectiles.add(new Rocket(getX()+(getWidth()/2), getY(), true));
        projectiles.add(new BurstShot(getX()+(getWidth()/2), getY(), true));
        projectiles.add(new BurstShot(getX()+(getWidth()/2), getY(), true));
        projectiles.add(new BurstShot(getX()+(getWidth()/2), getY(), true));
    }
    
    @Override
    public boolean isHit(Projectile shot){
        int xpoints[] = {getX(), getX()+getWidth(), getX()+getWidth()/2};
        int ypoints[] = {getY()+getHeight(), getY()+getHeight(), getY()};
        return new Polygon(xpoints, ypoints, 3).intersects(shot.getBoundingBox());
    }
    
    public boolean isHit(Powerup powerup){
        int xpoints[] = {getX(), getX()+getWidth(), getX()+getWidth()/2};
        int ypoints[] = {getY()+getHeight(), getY()+getHeight(), getY()};
        return new Polygon(xpoints, ypoints, 3).intersects(powerup.getBoundingBox());
    }
    
    public void enhance(int type){
        
    }

    public void draw(Graphics g) {
        //draw projectiles
        for(Projectile shot : projectiles){
            g.drawImage(shot.getImageIcon().getImage(), shot.getX(), shot.getY(), null);
        }
        
        //draw self
        g.drawImage(getImageIcon().getImage(), getX(), getY(), null);
    }
}
