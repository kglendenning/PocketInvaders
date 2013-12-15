
package gameapp;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author Kurt
 */
public class Section extends JPanel{
    public ImageIcon left, middle, right;
    public SideBar sideBar;
    public boolean top = false;
    
    public Section(){
        super();
    }
    
    public void setTop(){
        this.top = true;
    }
    
    public void setLeft(ImageIcon image){
        this.left = image;
    }
    
    public void setMiddle(ImageIcon image){
        this.middle = image;
    }
    
    public void setRight(ImageIcon image){
        this.right = image;
    }
    
    public void draw(SideBar sideBar){
        if(right != null){
            //g.drawImage(right.getImage(), sideBar.leftHolder.getX(), sideBar.leftHolder.getY(), null);
        }
        if(left != null){
            //g.drawImage(left.getImage(), sideBar.rightHolder.getX(), sideBar.rightHolder.getY(), null);
        }
        if(middle != null){
            //g.drawImage(middle.getImage(), sideBar.weaponLabel.getX(), sideBar.weaponLabel.getY(), null);
        }
        this.sideBar = sideBar;
        
        repaint();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        //draw weapon panel images
        if(left != null){
            g.drawImage(left.getImage(), 30, 100, null);
        }
        if(right != null){
            g.drawImage(right.getImage(),  300-30-right.getIconWidth(), 100, null);
        }
        if(middle != null){
            g.drawImage(middle.getImage(), 150-middle.getIconWidth()/2, 80, null);
        }
        
        //health bar
        if(top){
            int health = (int) (((double) sideBar.health/ (double)sideBar.maxHealth) * 250.0);
            
            g.setColor(new Color(0, 204, 0));
            g.fillRect(25, 75, health, 50);
            g.setColor(new Color(204, 0, 0));
            g.fillRect(25+health, 75, 250-health, 50);
        }
    }
}
