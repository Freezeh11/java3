package Component;

import GameEngine.Component;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 *
 */
public class BoxBounds extends Component implements Serializable {
    private Rectangle2D boundbox;
    private float width, height;
    public BoxBounds(float width, float height){

        this.width = width;
        this.height = height;
    }

    public void init(){
        boundbox = new Rectangle2D.Float(getGameObject().getX(), getGameObject().getY(), width, height);
    }

    @Override
    public void update(double dt){

    }

    public float getX(){
        return getGameObject().getX();
    }

    public float getY(){
        return getGameObject().getY();
    }
    /*@Override
    public void draw(Graphics2D g2){
        g2.drawRect((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
    }*/

    public Rectangle2D getBoundbox(){
        return boundbox;
    }

    @Override
    public Component copy() {
        return new BoxBounds(width, height);
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }
}
