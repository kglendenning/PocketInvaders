package gameapp.Effect;

import gameapp.Projectile.Projectile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Kurt
 */
public class Explosion extends Effect {
    BufferedImage mImage;
    protected int mCurrentFrame = 0, mFrameWidth, mFrameHeight;
    protected int mRows, mCols, mMaxTime;

    public Explosion(Projectile projectile) {
        super(projectile);

        SetWidth(0);
        SetHeight(0);
        SetHarmful(true);
        SetDamage(10);
        SetTimer(15);
        mMaxTime = 15;
        mRows = 4;
        mCols = 4;

        try {
            mImage = ImageIO.read(new File("images/explosion_1.png"));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        mFrameWidth = mImage.getWidth() / mCols;
        mFrameHeight = mImage.getHeight() / mRows;
    }

    /**
     * @return 0 - if not finished, 1 - if finished
     */
    @Override
    public int Update() {
        int ret = super.Update();

        SetWidth((mMaxTime - GetTimer()) * 6);
        SetHeight(GetWidth());
        mCurrentFrame++;

        return ret;
    }

    @Override
    public void Draw(Graphics g) {
        //g.setColor(Color.ORANGE);
        //g.drawOval(getX()-(getWidth())/2, getY()-(getHeight())/2, getWidth(), getHeight());
        int row = mCurrentFrame / mRows;
        int col = mCurrentFrame % mCols;
        g.drawImage(mImage.getSubimage(col * mFrameWidth, row * mFrameHeight, mFrameWidth, mFrameHeight), GetX()-(GetWidth())/2, GetY()-(GetHeight())/2, null);
    }
}
