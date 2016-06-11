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
public class LongExplosion extends Explosion{
    
    public LongExplosion(Projectile projectile) {
        super(projectile);

        setDamage(25);
        setTimer(25);
        maxTime = 20;
        rows = 5;
        cols = 5;

        try {
            image = ImageIO.read(new File("images/ExplosionAnim.png"));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        frameWidth = image.getWidth() / cols;
        frameHeight = image.getHeight() / rows;
    }
    
}
