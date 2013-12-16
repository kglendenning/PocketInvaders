
package gameapp;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Enemy extends Ship{
    private int level, type;
    //private ArrayList<ImageIcon> parts = new ArrayList<>();
    
    public Enemy(int panelWidth, int panelHeight, int type){
        ImageIcon icon = new ImageIcon("images/"+villain.getEnemyName(type)+".jpg");
        //ImageIcon bottom = new ImageIcon("images/Fighter1Bottom.png");
        //ImageIcon top = new ImageIcon("images/Fighter1Top.png");
        
        setPanelWidth(panelWidth);
        setPanelHeight(panelHeight);
        setWidth(icon.getIconWidth());
        setHeight(icon.getIconHeight());
        setRun(villain.getRun(type));
        setRise(villain.getRise(type));
        setLevel(villain.getLevel(type));
        setMaxHealth(villain.getHealth(type));
        setHealth(villain.getHealth(type));
        setType(type);
        setImage(icon);
        setX((int) (Math.random()*(panelWidth-icon.getIconWidth())));
        setY((int) (Math.random()*(panelHeight/4)));
        //parts.add(bottom);
        //parts.add(top);
        //setWidth(bottom.getIconWidth());
        //setHeight(bottom.getIconHeight()+top.getIconHeight());
    }
    
    public void setLevel(int level){
        this.level = level;
    }
    
    public void setType(int type){
        this.type = type;
    }
    
    public int getLevel(){
        return level;
    }
    
    public int getType(){
        return type;
    }
    
    /**
     * @return 0 - do nothing, 1 - add projectile
     */
    public int update(){
        move();
        
        double action = Math.random();
        return action > villain.getFireRate(getType()) ? 1 : 0;
    }
    
    public Projectile shootProjectile(){
        return new Projectile(getX()+(getWidth()/2), getY()+getHeight(), false);
    }
    
    public void shootWeapon(){
        
    }
    
    /*Override
    public boolean isHit(Projectile shot){
        if(shot.getBoundingBox().intersects(new Rectangle(getX(), getY(), getWidth(), parts.get(0).getIconHeight()))){
            return true;
        }else{
            return shot.getBoundingBox().intersects(new Rectangle(getX()+getWidth()/2-parts.get(1).getIconWidth()/2, parts.get(0).getIconHeight(),
                    parts.get(1).getIconWidth(), parts.get(1).getIconHeight()));
        }
    }*/
    
    @Override
    public void move(){
        if(getX()+getRun() < 0 || getX()+getWidth()+getRun() >= getPanelWidth()){
            setRun(0-getRun());
        }
        
        setX(getX()+getRun());
        setY(getY()+getRise());
    }

    @Override
    public void draw(Graphics g) {
        //draw self
        g.drawImage(getImageIcon().getImage(), getX(), getY(), null);
        //g.drawImage(parts.get(0).getImage(), getX(), getY(), null);
        //g.drawImage(parts.get(1).getImage(), getX()+getWidth()/2-parts.get(1).getIconWidth()/2, getY()+parts.get(0).getIconHeight(), null);
    }
}
