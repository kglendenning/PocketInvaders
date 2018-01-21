
package gameapp.Enemy;


/**
 * @author Kurt
 */
public class Elite extends Enemy{

    //does nothing yet, just a placeholder modified from enemy2
    public Elite(int panelWidth, int panelHeight, int type){
        super(panelWidth, panelHeight, type);
    }
    
    /*public boolean IsHit(Projectile shot){
        int xpoints[] = {GetX(), GetX()+GetWidth(), GetX()+GetWidth()/2};
        int ypoints[] = {GetY(), GetY(), GetY()+GetHeight()};
        return new Polygon(xpoints, ypoints, 3).intersects(shot.GetBoundingBox());
    }*/
}
