
package gameapp.Effect;

import gameapp.General.Entity;
import gameapp.Projectile.Projectile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * @author Kurt
 */
public class Effect extends Entity {
    private int mTimer = 3, mDamage;
    private boolean mHarmful = false;
    
    public Effect(Projectile projectile){
        Point center = projectile.GetCenter();
        
        SetX(center.x);
        SetY(center.y);
        SetWidth(10);
        SetHeight(10);
        //setHarmful(false);
        //setTimer(3);
    }
    
    public final void SetTimer(int timer){
        mTimer = timer;
    }
    
    public final void SetHarmful(boolean harmful){
        this.mHarmful = harmful;
    }
    
    public final void SetDamage(int damage){
        mDamage = damage;
    }
    
    public final int GetTimer(){
        return mTimer;
    }
    
    public final boolean IsHarmful(){
        return mHarmful;
    }
    
    public final int GetDamage(){
        return mDamage;
    }
    
    /**
     * @return 0 - if not finished, 1 - if finished
     */
    public int Update(){
        mTimer--;
        return mTimer <= 0 ? 1 : 0;
    }
    
    @Override
    public void Draw(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillOval(GetX()-(GetWidth())/2, GetY()-(GetHeight())/2, GetWidth(), GetHeight());
    }
}
