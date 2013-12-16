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
public class Player extends Ship {

    private ArrayList<Projectile> projectiles = new ArrayList<>();
    public boolean shooting = false, moveLeft = false, moveRight = false,
            moveUp = false, moveDown = false, speed = true;
    private int shotDelay = 0, activeWeapon;
    private final int MAX_HEALTH = 200;
    private int weaponAmmo[] = new int[20];

    public Player(int panelWidth, int panelHeight) {
        ImageIcon icon = new ImageIcon("images/Player.jpg");

        setPanelWidth(panelWidth);
        setPanelHeight(panelHeight);
        setX(panelWidth / 2 - icon.getIconWidth() / 2);
        setY(panelHeight - icon.getIconHeight());
        setImage(icon);
        setWidth(icon.getIconWidth());
        setHeight(icon.getIconHeight());
        setActiveWeapon(0);
        setHealth(MAX_HEALTH);//testing with default for now
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void update() {
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile shot = projectiles.get(i);
            shot.move();
            if (shot.getY() + shot.getHeight() <= 0) {
                projectiles.remove(i);
                i--;
            }
        }

        shootCheck();
        moveCheck();
    }

    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public void setSpeed(boolean speed) {
        this.speed = speed;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public void setActiveWeapon(int activeWeapon) {
        this.activeWeapon = activeWeapon;
    }

    public int[] getWeaponAmmo() {
        return weaponAmmo;
    }

    public int getActiveWeapon() {
        return activeWeapon;
    }

    public void shootCheck() {
        if (shooting) {
            if (shotDelay == 0) {
                shootProjectile();
                shotDelay = 5;
            }
            shotDelay--;
        } else {
            shotDelay = 0;
        }
    }

    public void moveCheck() {
        if (moveLeft && !moveRight) {
            setX(getX() - (speed ? 7 : 4));
            if (getX() <= 0) {
                setX(0);
            }
        } else if (moveRight && !moveLeft) {
            setX(getX() + (speed ? 7 : 4));
            if (getX() >= getPanelWidth() - getWidth()) {
                setX(getPanelWidth() - getWidth());
            }
        }

        if (moveUp && !moveDown) {
            setY(getY() - (speed ? 7 : 4));
            if (getY() <= 3 * getPanelHeight() / 5) {
                setY(3 * getPanelHeight() / 5);
            }
        } else if (moveDown && !moveUp) {
            setY(getY() + (speed ? 7 : 4));
            if (getY() >= getPanelHeight() - getHeight()) {
                setY(getPanelHeight() - getHeight());
            }
        }
    }

    public void shootProjectile() {
        projectiles.add(new Projectile(getX() + (getWidth() / 2), getY(), true));
    }

    public void shootWeapon() {
        if (weaponAmmo[activeWeapon] > 0) {
            projectiles.addAll(weapon.getShot(activeWeapon, getX() + (getWidth() / 2), getY(), true));
            weaponAmmo[activeWeapon]--;
            
            if(weaponAmmo[activeWeapon] == 0){
                shiftWeapon(true);
            }
        }
    }

    @Override
    public boolean isHit(Projectile shot) {
        int xpoints[] = {getX(), getX() + getWidth(), getX() + getWidth() / 2};
        int ypoints[] = {getY() + getHeight(), getY() + getHeight(), getY()};
        return new Polygon(xpoints, ypoints, 3).intersects(shot.getBoundingBox());
    }

    public boolean isHit(Powerup powerup) {
        int xpoints[] = {getX(), getX() + getWidth(), getX() + getWidth() / 2};
        int ypoints[] = {getY() + getHeight(), getY() + getHeight(), getY()};
        return new Polygon(xpoints, ypoints, 3).intersects(powerup.getBoundingBox());
    }
    
    /**
     * @param damage Damage of projectile
     * @return 0 - if nothing, 1 - if killed
     */
    @Override
    public int takeDamage(int damage){
        setHealth(getHealth()-damage);
        if(getHealth() < 0){
            setHealth(0);
        }
        
        return getHealth() > 0 ? 0 : 1;
    }

    public void shiftWeapon(boolean right) {
        if (right) {
            setActiveWeapon(getActiveWeapon() + 1);
            if (getActiveWeapon() >= weapon.getNumWeapons()) {
                setActiveWeapon(0);
            }
        } else {
            setActiveWeapon(getActiveWeapon() - 1);
            if (getActiveWeapon() <= -1) {
                setActiveWeapon(weapon.getNumWeapons() - 1);
            }
        }
        
        if(weaponAmmo[getActiveWeapon()] == 0 && getActiveWeapon() != 0){
            shiftWeapon(right);
        }
    }

    public void collect(int type, Class className) {
        if(className == Ammo.class){
            if(weaponAmmo[type] <= 0){
                setActiveWeapon(type);
            }
        
            weaponAmmo[type] += weapon.getReload(type);
            if (weaponAmmo[type] > weapon.getMax(type)) {
                weaponAmmo[type] = weapon.getMax(type);
            }
        } else {
            int health = getHealth()+50;
            setHealth(health < 200 ? health : 200);
        }
    }

    @Override
    public void draw(Graphics g) {
        //draw projectiles
        for (Projectile shot : projectiles) {
            g.drawImage(shot.getImageIcon().getImage(), shot.getX(), shot.getY(), null);
        }

        //draw self
        g.drawImage(getImageIcon().getImage(), getX(), getY(), null);
    }
}
