
package gameapp.Projectile;

import gameapp.General.Entity;
import java.awt.Point;

/**
 * @author Kurt
 */
public class Powerup extends Entity{
    private int mAcceleration, mType, mTimer;
    
    public Powerup(Point center){
        SetX(center.x);
        SetY(center.y);
        SetRise(-2);
        SetRun(1);
        SetAcceleration(1);
        SetTimer(5);
    }
    
    public final void SetAcceleration(int acceleration){
        mAcceleration = acceleration;
    }
    
    public final void SetType(int type){
        mType = type;
    }
    
    public final void SetTimer(int timer){
        mTimer = timer;
    }
    
    public final int GetAcceleration(){
        return mAcceleration;
    }
    
    public final int GetType(){
        return mType;
    }
    
    public final int GetTimer(){
        return mTimer;
    }
    
    public void Update() {
        Move();
        
        mTimer--;
        if(mTimer == 0){
            SetRise(GetRise() + mAcceleration);
            mTimer = 5;
        }
    }
}
