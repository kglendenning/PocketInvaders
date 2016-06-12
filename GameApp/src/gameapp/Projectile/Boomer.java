/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameapp.Projectile;

import gameapp.General.GameField;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Boomer extends Projectile{
    
    public Boomer(int x, int y, boolean up) {
        super(x, y, up);
        ImageIcon icon = new ImageIcon("images/Boomer.gif");
        
        setImage(icon);
        setWidth(icon.getIconWidth());
        setHeight(icon.getIconHeight());
        setType(4);

        setRise(up ? -3 : 3);
        setDamage(Weapon.getDamage(getType()));
    }
    
    public void detonate(){
        GameField.effects.add(Weapon.getEffect(this));
    }
}
