/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameapp.Effect;

import gameapp.Projectile.Projectile;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Kurt
 */
public class LongExplosion extends Explosion {
    
    public LongExplosion(Projectile projectile) {
        super(projectile);

        SetDamage(25);
        SetTimer(25);
        mMaxTime = 20;
        mRows = 5;
        mCols = 5;

        try {
            mImage = ImageIO.read(new File("images/ExplosionAnim.png"));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        mFrameWidth = mImage.getWidth() / mCols;
        mFrameHeight = mImage.getHeight() / mRows;
    }
    
}
