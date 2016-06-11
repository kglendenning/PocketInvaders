/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameapp.Enemy;

import gameapp.General.Entity;
import gameapp.General.GameField;
import gameapp.Projectile.Projectile;
import gameapp.Projectile.ShotCallback;
import gameapp.Projectile.Spread;
import gameapp.Projectile.Weapon;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author Kurt
 */
public class Boss extends Enemy {
    private int shotDelay = 200;//, weapon;
    
    public Boss(int panelWidth, int panelHeight, int type) {
        super(panelWidth, panelHeight, type);
        
        setY(10);
        //setWeapon(Villain.getWeapon(type));
        
        setWeaponCallbackIndex(Weapon.addShotCallback(new ShotCallback(){
            @Override
            public ArrayList<Projectile> getShot(int x, int y, boolean up) {
                ArrayList<Projectile> weaponShots = new ArrayList<>();
                
                weaponShots.add(new Spread(x, y, -2, 14, up));
                weaponShots.add(new Spread(x, y, -4, 12, up));
                weaponShots.add(new Spread(x, y, 0, 16, up));
                weaponShots.add(new Spread(x, y, 4, 12, up));
                weaponShots.add(new Spread(x, y, 2, 14, up));
                
                return weaponShots;
            }
        }));
    }
    
    //public void setWeapon(int weapon){
    //    this.weapon = weapon;
    //}
    
    public void shootWeapon(){
        GameField.projectiles.addAll(Weapon.getCallbackShot(getWeaponCallbackIndex(), getX() + (getWidth() / 2), getY(), false));
    }
    
    @Override
    public boolean isHit(Entity entity){
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
