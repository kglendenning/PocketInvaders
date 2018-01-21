package gameapp.Player;

import gameapp.General.Entity;
import gameapp.General.GameField;
import gameapp.General.Logger;
import gameapp.Projectile.Projectile;
import gameapp.Projectile.Ammo;
import gameapp.General.Ship;
import gameapp.Projectile.Boomer;
import gameapp.Projectile.WeaponData;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Kurt
 */
public class Player extends Ship {

    private final ArrayList<Projectile> mProjectiles = new ArrayList<>();
    private boolean mShooting = false, mMoveLeft = false, mMoveRight = false,
            mMoveUp = false, mMoveDown = false, mSpeed = true;
    private int mShotDelay = 0;
    private int mWeaponDelay = 0;
    private final int[] mWeaponAmmo = new int[20];

    public Player(int panelWidth, int panelHeight) {
        ImageIcon icon = new ImageIcon("images/Player.jpg");

        SetPanelWidth(panelWidth);
        SetPanelHeight(panelHeight);
        SetX(panelWidth / 2 - icon.getIconWidth() / 2);
        SetY(panelHeight - icon.getIconHeight());
        SetImage(icon);
        SetWidth(icon.getIconWidth());
        SetHeight(icon.getIconHeight());
        SetWeapon(0);
        SetMaxHealth(200);
        SetHealth(200);//testing with default for now
    }

    public ArrayList<Projectile> GetProjectiles() {
        return mProjectiles;
    }

    public void Update() {
        for (int i = 0; i < mProjectiles.size(); i++) {
            Projectile shot = mProjectiles.get(i);
            shot.Move();
            if (shot.GetY() + shot.GetHeight() <= 0) {
                mProjectiles.remove(i);
                i--;
            }
        }

        ShootCheck();
        MoveCheck();
    }

    public final void SetMoveLeft(boolean aMoveLeft) {
        mMoveLeft = aMoveLeft;
    }

    public final void SetMoveRight(boolean aMoveRight) {
        mMoveRight = aMoveRight;
    }

    public final void SetMoveUp(boolean aMoveUp) {
        mMoveUp = aMoveUp;
    }

    public final void SetMoveDown(boolean aMoveDown) {
        mMoveDown = aMoveDown;
    }

    public final void SetSpeed(boolean aSpeed) {
        mSpeed = aSpeed;
    }

    public final void SetShooting(boolean aShooting) {
        mShooting = aShooting;
    }

    public final int[] GetWeaponAmmo() {
        return mWeaponAmmo;
    }

    public void ShootCheck() {
        if (mShooting) {
            if (mShotDelay == 0) {
                ShootProjectile();
                mShotDelay = 5;
            }
            mShotDelay--;
        } else {
            mShotDelay = 0;
        }
        
        if (mWeaponDelay > 0){
            mWeaponDelay--;
        }
    }

    public void MoveCheck() {
        if (mMoveLeft && !mMoveRight) {
            SetX((int)(GetX() - (mSpeed ? 6.5 : 4.0)));
            if (GetX() <= 0) {
                SetX(0);
            }
        } else if (mMoveRight && !mMoveLeft) {
            SetX(GetX() + (mSpeed ? 7 : 4));
            if (GetX() >= GetPanelWidth() - GetWidth()) {
                SetX(GetPanelWidth() - GetWidth());
            }
        }

        if (mMoveUp && !mMoveDown) {
            SetY(GetY() - (mSpeed ? 7 : 4));
            if (GetY() <= 3 * GetPanelHeight() / 5) {
                SetY(3 * GetPanelHeight() / 5);
            }
        } else if (mMoveDown && !mMoveUp) {
            SetY(GetY() + (mSpeed ? 7 : 4));
            if (GetY() >= GetPanelHeight() - GetHeight()) {
                SetY(GetPanelHeight() - GetHeight());
            }
        }
    }

    protected void ShootProjectile() {
        mProjectiles.add(new Projectile(GetX() + (GetWidth() / 2), GetY(), true));
        Logger.mPlayerShots++;
    }

    public void ShootWeapon() {
        //if boomer
        if(GetWeapon() == 4){
            for(int i = 0; i < mProjectiles.size(); i++){
                if(mProjectiles.get(i) instanceof Boomer){
                    if(mWeaponDelay == 0){
                        ((Boomer) mProjectiles.get(i)).Detonate();
                        mProjectiles.remove(i);
                        Logger.mPlayerHits++;
                        i--;
                    }
                    
                    return;
                }
            }
            
            mWeaponDelay = 100;
        }
        if (mWeaponAmmo[GetWeapon()] > 0) {
            if(GetWeapon() == 5){
                GameField.mProjectiles.addAll(WeaponData.GetWeaponInfo().getShot(GetWeapon(), GetX() + (GetWidth() / 2), GetY(), true));
            } else {
                mProjectiles.addAll(WeaponData.GetWeaponInfo().getShot(GetWeapon(), GetX() + (GetWidth() / 2), GetY(), true));
            }
            mWeaponAmmo[GetWeapon()]--;
            
            if(mWeaponAmmo[GetWeapon()] == 0){
                ShiftWeapon(true);
            }
        }
    }

    @Override
    public boolean IsHit(Entity entity) {
        int xpoints[] = {GetX(), GetX() + GetWidth(), GetX() + GetWidth() / 2};
        int ypoints[] = {GetY() + GetHeight(), GetY() + GetHeight(), GetY()};
        return new Polygon(xpoints, ypoints, 3).intersects(entity.GetBoundingBox());
    }

    /**
     * @param damage Damage of projectile
     * @return 0 - if nothing, 1 - if killed
     */
    @Override
    public boolean TakeDamage(int damage) {
        AlterHealth(damage);

        return GetHealth() <= 0;
    }

    public void ShiftWeapon(boolean right) {
        if (right) {
            SetWeapon(GetWeapon() + 1);
            if (GetWeapon() >= WeaponData.GetWeaponInfo().GetNumWeapons()) {
                SetWeapon(0);
            }
        } else {
            SetWeapon(GetWeapon() - 1);
            if (GetWeapon() <= -1) {
                SetWeapon(WeaponData.GetWeaponInfo().GetNumWeapons() - 1);
            }
        }
        
        if(mWeaponAmmo[GetWeapon()] == 0 && GetWeapon() != 0){
            ShiftWeapon(right);
        }
    }

    public void Collect(int type, Class className) {
        if(className == Ammo.class){
            if(mWeaponAmmo[type] <= 0){
                SetWeapon(type);
            }
        
            mWeaponAmmo[type] += WeaponData.GetWeaponInfo().GetReload(type);
            if (mWeaponAmmo[type] > WeaponData.GetWeaponInfo().GetMax(type)) {
                mWeaponAmmo[type] = WeaponData.GetWeaponInfo().GetMax(type);
            }
        } else {
            //int health = getHealth()+50;
            //setHealth(health < 200 ? health : 200);
            AlterHealth(50);
            Logger.mHealingReceived += 50;
        }
    }

    @Override
    public void Draw(Graphics g) {
        //draw projectiles
        for (Projectile shot : mProjectiles) {
            g.drawImage(shot.GetImageIcon().getImage(), shot.GetX(), shot.GetY(), null);
        }

        //draw self
        g.drawImage(GetImageIcon().getImage(), GetX(), GetY(), null);
    }
}
