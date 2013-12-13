/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameapp;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Enemy extends Ship{
    private int run, rise;
    
    public Enemy(int width, int height){
        ImageIcon icon = new ImageIcon("images/Fighter1.png");
        
        this.panelWidth = width;
        this.panelHeight = height;
        setX((int) (Math.random()*(panelWidth-icon.getIconWidth())));
        setY((int) (Math.random()*(panelHeight/4)));
        setRun(3);
        setRise(0);
        setImage(icon);
    }
    
    public void setRun(int run){
        this.run = run;
    }
    
    public void setRise(int rise){
        this.rise = rise;
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
    
    public void move(){
        if(getX()+run < 0 || getX()+getWidth()+run >= panelWidth){
            setRun(0-run);
        }
        
        setX(getX()+run);
        setY(getY()+rise);
    }

    public void draw(Graphics g) {
        //draw self
        g.drawImage(getImageIcon().getImage(), getX(), getY(), null);
    }
}
