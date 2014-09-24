package gameapp;

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

    public Explosion(Projectile projectile) {
        super(projectile);

        setWidth(0);
        setHeight(0);
        setHarmful(true);
        setDamage(10);
        setTimer(15);

        try {
            image = ImageIO.read(new File("images/explosion_1.png"));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        frameWidth = image.getWidth() / 4;
        frameHeight = image.getHeight() / 4;
    }

    /**
     * @return 0 - if not finished, 1 - if finished
     */
    @Override
    public int update() {
        int ret = super.update();

        setWidth((15 - getTimer()) * 6);
        setHeight(getWidth());
        currentFrame++;

        return ret;
    }

    @Override
    public void draw(Graphics g) {
        //g.setColor(Color.ORANGE);
        //g.drawOval(getX()-(getWidth())/2, getY()-(getHeight())/2, getWidth(), getHeight());
        int row = currentFrame / 4;
        int col = currentFrame % 4;
        g.drawImage(image.getSubimage(col * frameWidth, row * frameHeight, frameWidth, frameHeight), getX()-(getWidth())/2, getY()-(getHeight())/2, null);
    }
}
