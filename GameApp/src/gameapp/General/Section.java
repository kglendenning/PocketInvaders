
package gameapp.General;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author Kurt
 */
public class Section extends JPanel{
    public ImageIcon mLeft, mMiddle, mRight;
    public SideBar mSideBar;
    public boolean mTop = false, mTarget = false;
    
    public Section(){
        super();
    }
    
    public final void SetTop(){
        mTop = true;
    }
    
    public final void SetLeft(ImageIcon image){
        mLeft = image;
    }
    
    public final void SetMiddle(ImageIcon image){
        mMiddle = image;
    }
    
    public final void SetRight(ImageIcon image){
        mRight = image;
    }
    
    public void Draw(SideBar sideBar){
        mSideBar = sideBar;
        
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        //draw weapon panel images
        if(mLeft != null){
            g.drawImage(mLeft.getImage(), 30, 100, null);
        }
        if(mRight != null){
            g.drawImage(mRight.getImage(),  300-30-mRight.getIconWidth(), 100, null);
        }
        if(mMiddle != null){
            g.drawImage(mMiddle.getImage(), 150-mMiddle.getIconWidth()/2, 80, null);
        }
        
        //health bar
        if(mTop){
            if (mSideBar != null) {
                int playerHealth = (int) (((double) mSideBar.mHealth / (double) mSideBar.mMaxHealth) * 250.0);

                g.setColor(new Color(0, 204, 0));
                g.fillRect(25, 75, playerHealth, 50);
                g.setColor(new Color(204, 0, 0));
                g.fillRect(25 + playerHealth, 75, 250 - playerHealth, 50);

                if (mTarget) {
                    int enemyHealth = (int) (((double) mSideBar.mTargetHealth / (double) mSideBar.mTargetMaxHealth) * 250.0);
                    g.setColor(new Color(0, 204, 0));
                    g.fillRect(25, 165, enemyHealth, 30);
                    g.setColor(new Color(204, 0, 0));
                    g.fillRect(25 + enemyHealth, 165, 250 - enemyHealth, 30);
                }
            }
        }
    }
}
