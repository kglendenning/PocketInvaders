/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameapp;

import java.awt.Point;
import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Ammo extends Powerup{
    
    public Ammo(Point center, int type){
        super(center);
        
        setType(type);
        setImage(new ImageIcon("images/"+Weapon.getWeaponName(type)+"Icon.jpg"));
        setWidth(getImageIcon().getIconWidth());
        setHeight(getImageIcon().getIconHeight());
    }
}
