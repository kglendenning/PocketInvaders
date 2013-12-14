
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
    private int run, rise, level;
    private ArrayList<ImageIcon> parts = new ArrayList<>();
    
    public Enemy(int width, int height){
        ImageIcon bottom = new ImageIcon("images/Fighter1Bottom.png");
        ImageIcon top = new ImageIcon("images/Fighter1Top.png");
        
        this.panelWidth = width;
        this.panelHeight = height;
        setX((int) (Math.random()*(panelWidth-bottom.getIconWidth())));
        setY((int) (Math.random()*(panelHeight/4)));
        setWidth(bottom.getIconWidth());
        setHeight(bottom.getIconHeight()+top.getIconHeight());
        setRun(3);
        setRise(0);
        setLevel(0);
        parts.add(bottom);
        parts.add(top);
    }
    
    public void setRun(int run){
        this.run = run;
    }
    
    public void setRise(int rise){
        this.rise = rise;
    }
    
    public void setLevel(int level){
        this.level = level;
    }
    
    public int getRun(){
        return run;
    }
    
    public int getRise(){
        return rise;
    }
    
    public int getLevel(){
        return level;
    }
    
    /**
     * @return 0 - do nothing, 1 - add projectile
     */
    public int update(){
        move();
        
        double action = Math.random();
        return action > .98 ? 1 : 0;
    }
    
    public Projectile shootProjectile(){
        return new Projectile(getX()+(getWidth()/2), getY()+getHeight(), false);
    }
    
    public void shootWeapon(){
        
    }
    
    @Override
    public boolean isHit(Projectile shot){
        if(shot.getBoundingBox().intersects(new Rectangle(getX(), getY(), getWidth(), parts.get(0).getIconHeight()))){
            return true;
        }else{
            return shot.getBoundingBox().intersects(new Rectangle(getX()+getWidth()/2-parts.get(1).getIconWidth()/2, parts.get(0).getIconHeight(),
                    parts.get(1).getIconWidth(), parts.get(1).getIconHeight()));
        }
    }
    
    public void move(){
        if(getX()+run < 0 || getX()+getWidth()+run >= panelWidth){
            setRun(0-run);
        }
        
        setX(getX()+run);
        setY(getY()+rise);
    }

    public void draw(Graphics g) {
        //draw self
        //g.drawImage(getImageIcon().getImage(), getX(), getY(), null);
        g.drawImage(parts.get(0).getImage(), getX(), getY(), null);
        g.drawImage(parts.get(1).getImage(), getX()+getWidth()/2-parts.get(1).getIconWidth()/2, getY()+parts.get(0).getIconHeight(), null);
    }
}
