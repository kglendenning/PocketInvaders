package gameapp.Player;

import gameapp.General.Entity;
import gameapp.General.GameField;
import gameapp.General.Logger;
import gameapp.Projectile.Projectile;
import gameapp.Projectile.Ammo;
import gameapp.General.Ship;
import gameapp.Projectile.Boomer;
import gameapp.Projectile.Weapon;
import java.awt.Graphics;
import java.awt.Polygon;
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
    private int shotDelay = 0;
    private int weaponDelay = 0;
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
        setWeapon(0);
        setMaxHealth(200);
        setHealth(200);//testing with default for now
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

    public int[] getWeaponAmmo() {
        return weaponAmmo;
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
        
        if (weaponDelay > 0){
            weaponDelay--;
        }
    }

    public void moveCheck() {
        if (moveLeft && !moveRight) {
            setX((int)(getX() - (speed ? 6.5 : 4.0)));
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
        Logger.playerShots++;
    }

    public void shootWeapon() {
        //if boomer
        if(getWeapon() == 4){
            for(int i = 0; i < projectiles.size(); i++){
                if(projectiles.get(i) instanceof Boomer){
                    if(weaponDelay == 0){
                        ((Boomer) projectiles.get(i)).detonate();
                        projectiles.remove(i);
                        Logger.playerHits++;
                        i--;
                    }
                    
                    return;
                }
            }
            
            weaponDelay = 100;
        }
        if (weaponAmmo[getWeapon()] > 0) {
            if(getWeapon() == 5){
                GameField.projectiles.addAll(Weapon.getShot(getWeapon(), getX() + (getWidth() / 2), getY(), true));
            } else {
                projectiles.addAll(Weapon.getShot(getWeapon(), getX() + (getWidth() / 2), getY(), true));
            }
            weaponAmmo[getWeapon()]--;
            
            if(weaponAmmo[getWeapon()] == 0){
                shiftWeapon(true);
            }
        }
    }

    @Override
    public boolean isHit(Entity entity) {
        int xpoints[] = {getX(), getX() + getWidth(), getX() + getWidth() / 2};
        int ypoints[] = {getY() + getHeight(), getY() + getHeight(), getY()};
        return new Polygon(xpoints, ypoints, 3).intersects(entity.getBoundingBox());
    }

    /**
     * @param damage Damage of projectile
     * @return 0 - if nothing, 1 - if killed
     */
    @Override
    public boolean takeDamage(int damage) {
        //setHealth(getHealth() - damage);
        alterHealth(damage);
        //if (getHealth() < 0) {
        //    setHealth(0);
        //}

        return getHealth() <= 0;
    }

    public void shiftWeapon(boolean right) {
        if (right) {
            setWeapon(getWeapon() + 1);
            if (getWeapon() >= Weapon.getNumWeapons()) {
                setWeapon(0);
            }
        } else {
            setWeapon(getWeapon() - 1);
            if (getWeapon() <= -1) {
                setWeapon(Weapon.getNumWeapons() - 1);
            }
        }
        
        if(weaponAmmo[getWeapon()] == 0 && getWeapon() != 0){
            shiftWeapon(right);
        }
    }

    public void collect(int type, Class className) {
        if(className == Ammo.class){
            if(weaponAmmo[type] <= 0){
                setWeapon(type);
            }
        
            weaponAmmo[type] += Weapon.getReload(type);
            if (weaponAmmo[type] > Weapon.getMax(type)) {
                weaponAmmo[type] = Weapon.getMax(type);
            }
        } else {
            //int health = getHealth()+50;
            //setHealth(health < 200 ? health : 200);
            alterHealth(50);
            Logger.healingReceived += 50;
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
