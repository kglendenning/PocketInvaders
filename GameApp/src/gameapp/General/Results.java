
package gameapp.General;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
/**
 *
 * @author Kurt
 */
public class Results extends JPanel implements KeyListener{
    public boolean mEnter;
    
    public Results() {
        super();
        
        mEnter = false;
    }
    
    public int Update() {
        repaint();
        
        if(mEnter){
            mEnter = false;
            return 3;
        }
        
        return 0;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //paint background
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        String result = Logger.mWon ? "You Win!" : "You Lose.";
        g.setFont(new Font("Serif", Font.BOLD, 40));
        g.setColor(Color.WHITE);
        g.drawString(result, 10, 50);
        
        g.setFont(new Font("Serif", Font.BOLD, 15));
        g.drawString("Shots fired: " + Logger.mPlayerShots, 10, 75);
        g.drawString("Shots landed: " + Logger.mPlayerHits, 10, 95);
        double accuracy = Logger.mPlayerShots > 0 ? (double) Logger.mPlayerHits / (double) Logger.mPlayerShots : 0;
        g.drawString("Accuracy: " + String.format("%.4f", accuracy), 10, 115);
        g.drawString("Damage dealt: " + Logger.mDamageDealt, 10, 135);
        g.drawString("Enemies killed: " + Logger.mEnemiesKilled, 10, 155);
        
        g.drawString("Enemy shots: " + Logger.mEnemyShotsFired, 10, 175);
        g.drawString("Hits taken: " + Logger.mHitsTaken, 10, 195);
        g.drawString("Damage taken: " + Logger.mDamageTaken, 10, 215);
        
        g.drawString("Powerups spawned: " + Logger.mPowerupsSpawned, 10, 235);
        g.drawString("Powerups collected: " + Logger.mPowerupsCollected, 10, 255);
        g.drawString("Healing received: " + Logger.mHealingReceived, 10, 275);
        
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.setColor(Color.YELLOW);
        g.drawString("Continue", 10, 320);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            mEnter = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
