package gameapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Kurt
 */
public class SideBar extends JPanel {

    public Section top, upper, lower, bottom;
    public JLabel ammoLabel, healthLabel, weaponLabel;
    public ImageIcon weaponIcon, left, right;

    public SideBar() {
        super();
        
        top = new Section(); upper = new Section(); lower = new Section(); bottom = new Section();
        lower.setLeft(new ImageIcon("images/Left.jpg")); lower.setMiddle(new ImageIcon("images/BigBurstIcon.jpg")); lower.setRight(new ImageIcon("images/Right.jpg"));
        ammoLabel = new JLabel(); healthLabel = new JLabel(); weaponLabel = new JLabel();
        
        ammoLabel.setFont(new Font("Serif", Font.BOLD, 40));
        healthLabel.setFont(new Font("Serif", Font.BOLD, 50));
        weaponLabel.setFont(new Font("Serif", Font.PLAIN, 40));
        
        ammoLabel.setForeground(Color.ORANGE);
        healthLabel.setForeground(Color.ORANGE);
        weaponLabel.setForeground(Color.ORANGE);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(top);
        add(upper);
        add(lower);
        add(bottom);
        //top.add(healthMeter);
        top.add(healthLabel);
        upper.add(weaponLabel);
        bottom.add(ammoLabel);
    }

    public void update(GameField field) {
        healthLabel.setText("" + field.player.getHealth() + "/" + field.player.getMaxHealth());
        ammoLabel.setText("" + field.player.getWeaponAmmo()[field.player.getActiveWeapon()]);
        weaponLabel.setText(field.weapon.getWeaponName(field.player.getActiveWeapon()));
        lower.setMiddle(new ImageIcon("images/Big" + field.weapon.getWeaponName(field.player.getActiveWeapon()) + "Icon.jpg"));
        
        top.draw(this);
        upper.draw(this);
        lower.draw(this);
        bottom.draw(this);
    }
}
