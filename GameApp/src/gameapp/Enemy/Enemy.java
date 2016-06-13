
package gameapp.Enemy;

import gameapp.General.GameField;
import gameapp.General.Logger;
import gameapp.Projectile.Projectile;
import gameapp.General.Ship;
import gameapp.Projectile.Weapon;
import gameapp.Projectile.Powerup;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Enemy extends Ship{
    private int level, weaponTableIndex;
    //private ArrayList<ImageIcon> parts = new ArrayList<>();
    
    public Enemy(int panelWidth, int panelHeight, int type){
        ImageIcon icon = new ImageIcon("images/"+Villain.getEnemyName(type)+".png");
        //ImageIcon bottom = new ImageIcon("images/Fighter1Bottom.png");
        //ImageIcon top = new ImageIcon("images/Fighter1Top.png");
        int seed = (int) (Math.random()*1000.0);
        
        setPanelWidth(panelWidth);
        setPanelHeight(panelHeight);
        setWidth(icon.getIconWidth());
        setHeight(icon.getIconHeight());
        setRun(((seed & 1) > 0 ? 1 : -1) * Villain.getRun(type));
        setRise(((seed & 2) > 0 ? 1 : -1) * Villain.getRise(type));
        setLevel(Villain.getLevel(type));
        setMaxHealth(Villain.getHealth(type));
        setHealth(Villain.getHealth(type));
        setWeaponTypeIndex(type);
        setImage(icon);
        setX((int) (Math.random()*(panelWidth-icon.getIconWidth())));
        setY((int) (Math.random()*(panelHeight/4)));
        double chance = Math.random();
        int tier = 0;
             if(chance < 00.30) tier = 0;
        else if(chance < 00.60) tier = 1;
        else if(chance < 99.99) tier = 2;
        switch(tier){
            case 0: setHasDrop(false);
            case 1: setHasDrop(true); setDrop("Health");
            case 2: setHasDrop(true); setDrop("Ammo");
        }
        //parts.add(bottom);
        //parts.add(top);
        //setWidth(bottom.getIconWidth());
        //setHeight(bottom.getIconHeight()+top.getIconHeight());
    }
    
    public void setLevel(int level){
        this.level = level;
    }
    
    public void setWeaponTypeIndex(int weaponTableIndex){
        this.weaponTableIndex = weaponTableIndex;
    }
    
    public int getLevel(){
        return level;
    }
    
    public int getWeaponTypeIndex(){
        return weaponTableIndex;
    }
    
    
    
    /**
     * @return 0 - do nothing, 1 - add projectile
     */
    public int update(){
        move();
        
        double action = Math.random();
        return action > Villain.getFireRate(getWeaponTypeIndex()) ? 1 : 0;
    }
    
    public void shootProjectile(){
        GameField.projectiles.add(new Projectile(getX()+(getWidth()/2), getY()+getHeight(), false));
        Logger.enemyShotsFired++;
    }
    
    //public boolean isHit(Projectile shot){
        //if(shot.getBoundingBox().intersects(new Rectangle(getX(), getY(), getWidth(), parts.get(0).getIconHeight()))){
        //    return true;
        //}else{
        //    return shot.getBoundingBox().intersects(new Rectangle(getX()+getWidth()/2-parts.get(1).getIconWidth()/2, parts.get(0).getIconHeight(),
        //            parts.get(1).getIconWidth(), parts.get(1).getIconHeight()));
        //}
    //}
    
    @Override
    public void move(){
        if(getX()+getRun() < 0 || getX()+getWidth()+getRun() >= getPanelWidth()){
            setRun(0-getRun());
        }
        
        setX((int)(getX()+getRun()));
        setY((int)(getY()+getRise()));
    }

    @Override
    public void draw(Graphics g) {
        //draw self
        g.drawImage(getImageIcon().getImage(), getX(), getY(), null);
    }
}
