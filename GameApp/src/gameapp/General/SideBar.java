package gameapp.General;

import gameapp.Projectile.WeaponData;
import gameapp.Enemy.Enemy;
import gameapp.Player.Player;
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

    public Section mTop, mUpper, mLower, mBottom;
    public JLabel mAmmoLabel, mHealthLabel, mWeaponLabel, mTargetLabel;
    public ImageIcon mWeaponIcon, mLeft, mRight;
    public int mHealth, mMaxHealth, mTargetHealth, mTargetMaxHealth;

    public SideBar() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        mTop = new Section(); mUpper = new Section(); mLower = new Section(); mBottom = new Section();
        mLower.SetLeft(new ImageIcon("images/Left.jpg")); mLower.SetMiddle(new ImageIcon("images/BigBurstIcon.jpg")); mLower.SetRight(new ImageIcon("images/Right.jpg"));
        mAmmoLabel = new JLabel(); mHealthLabel = new JLabel(); mWeaponLabel = new JLabel(); mTargetLabel = new JLabel();
        
        mAmmoLabel.setFont(new Font("Serif", Font.BOLD, 40));
        mHealthLabel.setFont(new Font("Serif", Font.BOLD, 50));
        mWeaponLabel.setFont(new Font("Serif", Font.PLAIN, 40));
        mTargetLabel.setFont(new Font("Serif", Font.BOLD, 30));
        mTop.setLayout(null);
        mHealthLabel.setBounds(25, 20, 200, 50);
        mTargetLabel.setBounds(25, 130, 200, 30);
        
        mAmmoLabel.setForeground(Color.ORANGE);
        mHealthLabel.setForeground(Color.ORANGE);
        mWeaponLabel.setForeground(Color.ORANGE);
        mTargetLabel.setForeground(Color.ORANGE);
        
        mTop.SetTop();
        add(mTop);
        add(mUpper);
        add(mLower);
        add(mBottom);

        mTop.add(mHealthLabel);
        mTop.add(mTargetLabel);
        mLower.add(mWeaponLabel);
        mBottom.add(mAmmoLabel);
    }

    public void Update(Player player, Enemy target) {
        mHealth = player.GetHealth();
        mMaxHealth = player.GetMaxHealth();
        if(target != null){
            mTargetHealth = target.GetHealth();
            mTargetMaxHealth = target.GetMaxHealth();
        }
        
        mHealthLabel.setText("" + mHealth + "/" + mMaxHealth);
        mAmmoLabel.setText("" + player.GetWeaponAmmo()[player.GetWeapon()]);
        mWeaponLabel.setText(WeaponData.GetWeaponInfo().GetWeaponName(player.GetWeapon()));
        if(mTargetHealth > 0){
            mTargetLabel.setText("Target");
            mTop.mTarget = true;
        }else{
            mTargetLabel.setText("");
            mTop.mTarget = false;
        }
        mLower.SetMiddle(new ImageIcon("images/Big" + WeaponData.GetWeaponInfo().GetWeaponName(player.GetWeapon()) + "Icon.jpg"));
        
        mTop.Draw(this);
        mUpper.Draw(this);
        mLower.Draw(this);
        mBottom.Draw(this);
    }
}
