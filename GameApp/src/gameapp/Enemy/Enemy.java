
package gameapp.Enemy;

import gameapp.General.GameField;
import gameapp.General.Logger;
import gameapp.Projectile.Projectile;
import gameapp.General.Ship;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Enemy extends Ship{
    private int mLevel, mVillainTableIndex;
    //private ArrayList<ImageIcon> parts = new ArrayList<>();
    
    public Enemy(int panelWidth, int panelHeight, int type){
        ImageIcon icon = new ImageIcon("images/"+VillainData.GetVillainInfo().GetEnemyName(type)+".png");
        //ImageIcon bottom = new ImageIcon("images/Fighter1Bottom.png");
        //ImageIcon top = new ImageIcon("images/Fighter1Top.png");
        int seed = (int) (Math.random()*1000.0);
        
        SetPanelWidth(panelWidth);
        SetPanelHeight(panelHeight);
        SetWidth(icon.getIconWidth());
        SetHeight(icon.getIconHeight());
        SetRun(((seed & 1) > 0 ? 1 : -1) * VillainData.GetVillainInfo().GetRun(type));
        SetRise(((seed & 2) > 0 ? 1 : -1) * VillainData.GetVillainInfo().GetRise(type));
        SetLevel(VillainData.GetVillainInfo().GetLevel(type));
        SetMaxHealth(VillainData.GetVillainInfo().GetHealth(type));
        SetHealth(VillainData.GetVillainInfo().GetHealth(type));
        SetVillainTypeIndex(type);
        SetImage(icon);
        SetX((int) (Math.random()*(panelWidth-icon.getIconWidth())));
        SetY((int) (Math.random()*(panelHeight/4)));
        double chance = Math.random();
        int tier = 0;
             if(chance < 00.30) tier = 0;
        else if(chance < 00.60) tier = 1;
        else if(chance < 99.99) tier = 2;
        switch(tier){
            case 0: SetHasDrop(false);
            case 1: SetHasDrop(true); SetDrop("Health");
            case 2: SetHasDrop(true); SetDrop("Ammo");
        }
        //parts.add(bottom);
        //parts.add(top);
        //setWidth(bottom.getIconWidth());
        //setHeight(bottom.getIconHeight()+top.getIconHeight());
    }
    
    public final void SetLevel(int level){
        this.mLevel = level;
    }
    
    public final void SetVillainTypeIndex(int weaponTableIndex){
        this.mVillainTableIndex = weaponTableIndex;
    }
    
    public final int GetLevel(){
        return mLevel;
    }
    
    public final int GetVillainTypeIndex(){
        return mVillainTableIndex;
    }
    
    /**
     * @return 0 - do nothing, 1 - add projectile
     */
    public int Update(){
        Move();
        
        double action = Math.random();
        return action > VillainData.GetVillainInfo().GetFireRate(GetVillainTypeIndex()) ? 1 : 0;
    }
    
    public void ShootProjectile(){
        GameField.mProjectiles.add(new Projectile(GetX()+(GetWidth()/2), GetY()+GetHeight(), false));
        Logger.mEnemyShotsFired++;
    }
    
    //public boolean IsHit(Projectile shot){
        //if(shot.GetBoundingBox().intersects(new Rectangle(GetX(), GetY(), GetWidth(), parts.get(0).GetIconHeight()))){
        //    return true;
        //}else{
        //    return shot.GetBoundingBox().intersects(new Rectangle(GetX()+GetWidth()/2-parts.get(1).GetIconWidth()/2, parts.get(0).GetIconHeight(),
        //            parts.get(1).GetIconWidth(), parts.get(1).GetIconHeight()));
        //}
    //}
    
    @Override
    public void Move(){
        if(GetX()+GetRun() < 0 || GetX()+GetWidth()+GetRun() >= GetPanelWidth()){
            SetRun(0-GetRun());
        }
        
        SetX((int)(GetX()+GetRun()));
        SetY((int)(GetY()+GetRise()));
    }

    @Override
    public void Draw(Graphics g) {
        //draw self
        g.drawImage(GetImageIcon().getImage(), GetX(), GetY(), null);
    }
}
