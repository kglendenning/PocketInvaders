/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameapp.Enemy;

import gameapp.General.Entity;
import gameapp.General.GameField;
import gameapp.Projectile.Weapon;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 *
 * @author Kurt
 */
public class Boss extends Enemy {
    private int shotDelay = 200, weapon;
    
    public Boss(int panelWidth, int panelHeight, int type) {
        super(panelWidth, panelHeight, type);
        
        setY(10);
        setWeapon(Villain.getWeapon(type));
    }
    
    public void setWeapon(int weapon){
        this.weapon = weapon;
    }
    
    public void shootWeapon(){
        GameField.projectiles.addAll(Weapon.getShot(weapon, getX() + (getWidth() / 2), getY(), false));
    }
    
    @Override
    public boolean isHit(Entity entity){
        //return new Rectangle(getX(), getY(), getWidth(), getHeight()).intersects(entity.getBoundingBox());
        
        int xpoints[] = {getX(), getX() + getWidth(), getX() + getWidth() / 2};
        int ypoints[] = {getY(), getY(), getY() + getHeight()};
        return new Polygon(xpoints, ypoints, 3).intersects(entity.getBoundingBox());
    }
    
    /**
     * @return 0 - do nothing, 1 - add projectile
     */
    @Override
    public int update(){
        move();
        
        if(--shotDelay == 0) {
            shootWeapon();
            shotDelay = 200;
        }
        
        double action = Math.random();
        return action > Villain.getFireRate(getType()) ? 1 : 0;
    }
}
