
package gameapp.General;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * @author Kurt
 */
public class Entity {
    private int mX, mY, mWidth, mHeight, mPanelWidth, mPanelHeight;
    double mRise, mRun;
    private ImageIcon mImage;
    
    public final void SetX(int x){
        mX = x;
    }
    
    public final void SetY(int y){
        mY = y;
    }
    
    public final void SetWidth(int width){
        mWidth = width;
    }
    
    public final void SetHeight(int height){
        mHeight = height;
    }
    
    public final void SetRun(double run){
        mRun = run;
    }
    
    public final void SetRise(double rise){
        mRise = rise;
    }
    
    public final void SetImage(ImageIcon image){
        mImage = image;
    }
    
    public final void SetPanelWidth(int panelWidth){
        mPanelWidth = panelWidth;
    }
    
    public final void SetPanelHeight(int panelHeight){
        mPanelHeight = panelHeight;
    }
    
    public final int GetX(){
        return mX;
    }
    
    public final int GetY(){
        return mY;
    }
    
    public final int GetWidth(){
        return mWidth;
    }
    
    public final int GetHeight(){
        return mHeight;
    }
    
    public final double GetRun(){
        return mRun;
    }
    
    public final double GetRise(){
        return mRise;
    }
    
    public final ImageIcon GetImageIcon(){
        return mImage;
    }
    
    public final int GetPanelWidth(){
        return mPanelWidth;
    }
    
    public final int GetPanelHeight(){
        return mPanelHeight;
    }
    
    public Rectangle GetBoundingBox(){
        return new Rectangle(mX, mY, mWidth, mHeight);
    }
    
    public void Move(){
        mX += mRun;
        mY += mRise;
    }
    
    public void Draw(Graphics g) {
        //draw self
        g.drawImage(GetImageIcon().getImage(), GetX(), GetY(), null);
    }
}
