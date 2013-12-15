
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
    
    public Section(){
        super();
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
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        if(left != null){
            g.drawImage(left.getImage(), 30, 100, null);
        }
        if(right != null){
            g.drawImage(right.getImage(),  300-30-right.getIconWidth(), 100, null);
        }
        if(middle != null){
            g.drawImage(middle.getImage(), 150-middle.getIconWidth()/2, 80, null);
        }
    }
}
