
package gameapp.General;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Entity {
    private int x, y, width, height, panelWidth, panelHeight;
    double rise, run;
    private ImageIcon image;
    
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
    
    public void setRun(double run){
        this.run = run;
    }
    
    public void setRise(double rise){
        this.rise = rise;
    }
    
    public void setImage(ImageIcon image){
        this.image = image;
    }
    
    public void setPanelWidth(int panelWidth){
        this.panelWidth = panelWidth;
    }
    
    public void setPanelHeight(int panelHeight){
        this.panelHeight = panelHeight;
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
    
    public double getRun(){
        return run;
    }
    
    public double getRise(){
        return rise;
    }
    
    public ImageIcon getImageIcon(){
        return image;
    }
    
    public int getPanelWidth(){
        return panelWidth;
    }
    
    public int getPanelHeight(){
        return panelHeight;
    }
    
    public Rectangle getBoundingBox(){
        return new Rectangle(x, y, width, height);
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
