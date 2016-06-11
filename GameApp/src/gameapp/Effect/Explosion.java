package gameapp.Effect;

import gameapp.Effect.Effect;
import gameapp.Projectile.Projectile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Kurt
 */
public class Explosion extends Effect {
    BufferedImage image;
    int currentFrame = 0, frameWidth, frameHeight;
    int rows, cols, maxTime;

    public Explosion(Projectile projectile) {
        super(projectile);

        setWidth(0);
        setHeight(0);
        setHarmful(true);
        setDamage(10);
        setTimer(15);
        maxTime = 15;
        rows = 4;
        cols = 4;

        try {
            image = ImageIO.read(new File("images/explosion_1.png"));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        frameWidth = image.getWidth() / cols;
        frameHeight = image.getHeight() / rows;
    }

    /**
     * @return 0 - if not finished, 1 - if finished
     */
    @Override
    public int update() {
        int ret = super.update();

        setWidth((maxTime - getTimer()) * 6);
        setHeight(getWidth());
        currentFrame++;

        return ret;
    }

    @Override
    public void draw(Graphics g) {
        //g.setColor(Color.ORANGE);
        //g.drawOval(getX()-(getWidth())/2, getY()-(getHeight())/2, getWidth(), getHeight());
        int row = currentFrame / rows;
        int col = currentFrame % cols;
        g.drawImage(image.getSubimage(col * frameWidth, row * frameHeight, frameWidth, frameHeight), getX()-(getWidth())/2, getY()-(getHeight())/2, null);
    }
}
