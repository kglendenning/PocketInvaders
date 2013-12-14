
package gameapp;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Powerup {
    private int x, y, width, height, acceleration, run, rise, type, timer;
    private ImageIcon image;
    
    public Powerup(Point center, int type){
        setX(center.x);
        setY(center.y);
        setRise(-2);
        setRun(1);
        setAcceleration(1);
        setType(type);
        setTimer(5);
        
        switch (type){
            case 0:
                setImage(new ImageIcon("images/TestIcon.jpg"));
                break;
            case 1:
                setImage(new ImageIcon("images/BurstIcon.jpg"));
                break;
            case 2:
                setImage(new ImageIcon("images/BombIcon.jpg"));
                break;
            case 3:
                setImage(new ImageIcon("images/SprayIcon.jpg"));
                break;
            default:
                setImage(new ImageIcon("images/RocketIcon.jpg"));
                break;
        }
        setWidth(image.getIconWidth());
        setHeight(image.getIconHeight());
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void setWidth(int width){
        this.width = width;
    }
    
    public void setHeight(int height){
        this.height = height;
    }
    
    public void setRun(int run){
        this.run = run;
    }
    
    public void setRise(int rise){
        this.rise = rise;
    }
    
    public void setAcceleration(int acceleration){
        this.acceleration = acceleration;
    }
    
    public void setImage(ImageIcon image){
        this.image = image;
    }
    
    public void setType(int type){
        this.type = type;
    }
    
    public void setTimer(int timer){
        this.timer = timer;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getRun(){
        return run;
    }
    
    public int getRise(){
        return rise;
    }
    
    public int getAcceleration(){
        return acceleration;
    }
    
    public ImageIcon getImageIcon(){
        return image;
    }
    
    public int getType(){
        return type;
    }
    
    public int getTimer(){
        return timer;
    }
    
    public Rectangle getBoundingBox(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    public void update() {
        move();
        
        timer--;
        if(timer == 0){
            rise += acceleration;
            timer = 5;
        }
    }
    
    public void move(){
        x += run;
        y += rise;
    }
    
    public void draw(Graphics g) {
        //draw self
        g.drawImage(getImageIcon().getImage(), getX(), getY(), null);
    }

    
}
