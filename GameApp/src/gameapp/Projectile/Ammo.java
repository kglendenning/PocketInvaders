/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameapp.Projectile;

import java.awt.Point;
import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Ammo extends Powerup{
    
    public Ammo(Point center, int type){
        super(center);
        
        SetType(type);
        SetImage(new ImageIcon("images/"+WeaponData.GetWeaponInfo().GetWeaponName(type)+"Icon.jpg"));
        SetWidth(GetImageIcon().getIconWidth());
        SetHeight(GetImageIcon().getIconHeight());
    }
}
