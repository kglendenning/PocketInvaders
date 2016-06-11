package gameapp;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Kurt
 */
public class SideBar extends JPanel {

    public Section top, upper, lower, bottom;
    public JLabel ammoLabel, healthLabel, weaponLabel, targetLabel;
    public ImageIcon weaponIcon, left, right;
    public int health, maxHealth, targetHealth, targetMaxHealth;

    public SideBar() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        top = new Section(); upper = new Section(); lower = new Section(); bottom = new Section();
        lower.setLeft(new ImageIcon("images/Left.jpg")); lower.setMiddle(new ImageIcon("images/BigBurstIcon.jpg")); lower.setRight(new ImageIcon("images/Right.jpg"));
        ammoLabel = new JLabel(); healthLabel = new JLabel(); weaponLabel = new JLabel(); targetLabel = new JLabel();
        
        ammoLabel.setFont(new Font("Serif", Font.BOLD, 40));
        healthLabel.setFont(new Font("Serif", Font.BOLD, 50));
        weaponLabel.setFont(new Font("Serif", Font.PLAIN, 40));
        targetLabel.setFont(new Font("Serif", Font.BOLD, 30));
        top.setLayout(null);
        healthLabel.setBounds(25, 20, 200, 50);
        targetLabel.setBounds(25, 130, 200, 30);
        
        ammoLabel.setForeground(Color.ORANGE);
        healthLabel.setForeground(Color.ORANGE);
        weaponLabel.setForeground(Color.ORANGE);
        targetLabel.setForeground(Color.ORANGE);
        
        top.setTop();
        add(top);
        add(upper);
        add(lower);
        add(bottom);

        top.add(healthLabel);
        top.add(targetLabel);
        upper.add(weaponLabel);
        bottom.add(ammoLabel);
    }

    public void update(Player player, Enemy target) {
        health = player.getHealth();
        maxHealth = player.getMaxHealth();
        if(target != null){
            targetHealth = target.getHealth();
            targetMaxHealth = target.getMaxHealth();
        }
        
        healthLabel.setText("" + health + "/" + maxHealth);
        ammoLabel.setText("" + player.getWeaponAmmo()[player.getActiveWeapon()]);
        weaponLabel.setText(Weapon.getWeaponName(player.getActiveWeapon()));
        if(targetHealth > 0){
            targetLabel.setText("Target");
            top.target = true;
        }else{
            targetLabel.setText("");
            top.target = false;
        }
        lower.setMiddle(new ImageIcon("images/Big" + Weapon.getWeaponName(player.getActiveWeapon()) + "Icon.jpg"));
        
        top.draw(this);
        upper.draw(this);
        lower.draw(this);
        bottom.draw(this);
    }
}
