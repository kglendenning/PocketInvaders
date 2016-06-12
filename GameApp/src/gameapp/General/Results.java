
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
class Results extends JPanel implements KeyListener{
    public boolean enter;
    
    public Results() {
        super();
        
        enter = false;
    }
    
    public int update() {
        repaint();
        
        if(enter){
            enter = false;
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
        
        String result = Logger.won ? "You Win!" : "You Lose.";
        g.setFont(new Font("Serif", Font.BOLD, 40));
        g.setColor(Color.WHITE);
        g.drawString(result, 10, 50);
        
        g.setFont(new Font("Serif", Font.BOLD, 15));
        g.drawString("Shots fired: " + Logger.playerShots, 10, 75);
        g.drawString("Shots landed: " + Logger.playerHits, 10, 95);
        double accuracy = Logger.playerShots > 0 ? (double) Logger.playerHits / (double) Logger.playerShots : 0;
        g.drawString("Accuracy: " + String.format("%.4f", accuracy), 10, 115);
        g.drawString("Damage dealt: " + Logger.damageDealt, 10, 135);
        g.drawString("Enemies killed: " + Logger.enemiesKilled, 10, 155);
        
        g.drawString("Enemy shots: " + Logger.enemyShotsFired, 10, 175);
        g.drawString("Hits taken: " + Logger.hitsTaken, 10, 195);
        g.drawString("Damage taken: " + Logger.damageTaken, 10, 215);
        
        g.drawString("Powerups spawned: " + Logger.powerupsSpawned, 10, 235);
        g.drawString("Powerups collected: " + Logger.powerupsCollected, 10, 255);
        g.drawString("Healing received: " + Logger.healingReceived, 10, 275);
        
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.setColor(Color.YELLOW);
        g.drawString("Continue", 10, 320);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key;
        
        if ((key = e.getKeyCode()) == KeyEvent.VK_ENTER) {
            enter = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
