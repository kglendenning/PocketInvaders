/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameapp;

import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class BurstShot extends Projectile{
    
    public BurstShot(int x, int y, boolean up){
        super(x, y, up);
        ImageIcon icon = new ImageIcon("images/BurstShot.gif");
        
        setX(getX() + ((int) (Math.random() * 20.0)) - 10);
        setY(getY() + ((int) (Math.random() * 30.0)) - 15);
        setImage(icon);
        setWidth(icon.getIconWidth());
        setHeight(icon.getIconHeight());

        setRise(up ? -8 : 8);
        setDamage(25);
    }
}
