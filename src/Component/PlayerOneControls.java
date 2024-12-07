package Component;

import GameEngine.Bullet;
import GameEngine.GameObject;
import GameEngine.PlayerCharacter;
import Util.Timer;
import Util.Vector;

import java.awt.event.KeyEvent;

public class PlayerOneControls extends Controls {
    boolean hasJumped;
    protected Vector lastDirection;
    protected float targetVelocityX = 0;
    protected final float acceleration = 3500f;
    protected final float maxSpeed = 300.0f;
    protected Timer reloadTime;
    protected Command shoot;
    private PlayerCharacter player;

    public PlayerOneControls(PlayerCharacter player) {
        hasJumped = false;
        reloadTime = new Timer(1.5f, 1.5f);
        lastDirection = new Vector(1, 0);
        shoot = new Command();
        this.player = player;
    }


    @Override
    public void update(double dt) {
        //System.out.println(reloadTime);
        Vector velocity = getGameObject().getComponent(RigidBody.class).velocity;
        //adjust velocity gradually towards the target
        if (velocity.getX() < targetVelocityX) {
            velocity.setX(Math.min(velocity.getX() + acceleration * (float) dt , targetVelocityX));
        } else if (velocity.getX() > targetVelocityX) {
            velocity.setX(Math.max(velocity.getX() - acceleration * (float) dt , targetVelocityX));
        }
        if (keyLisentner.isKeyPressed(KeyEvent.VK_S)) {
            //lastDirection.setX(0);
            lastDirection.setY(1);
        } else if (keyLisentner.isKeyPressed(KeyEvent.VK_Q)) {
            //lastDirection.setX(0);
            lastDirection.setY(-1);
        }
        if (keyLisentner.isKeyPressed(KeyEvent.VK_W)) {
            //lastDirection.setX(0);
            lastDirection.setY(-1);
            jump();
        } else if (keyLisentner.isKeyPressed(KeyEvent.VK_A)) {
            lastDirection.setX(-1);
            lastDirection.setY(0);
            moveLeft();
        } else if (keyLisentner.isKeyPressed(KeyEvent.VK_D)) {
            lastDirection.setX(1);
            lastDirection.setY(0);
            moveRight();
        } else {
            stop();
        }
        if (reloadTime.isTime(dt) && keyLisentner.isKeyPressed(KeyEvent.VK_SPACE)){
            reloadTime.resetTime();
            shoot.execute();
        }

    }


    public void jump() {
        if (!hasJumped) {
            Vector velocity = getGameObject().getComponent(RigidBody.class).velocity;
            velocity.setY(-900);
            hasJumped = true;
        }
    }

    public void stop() {
        targetVelocityX = 0; // Slowly bring velocity to 0
    }

    @Override
    public void moveLeft() {
        targetVelocityX = -maxSpeed;
    }

    @Override
    public void moveRight() {
        targetVelocityX = maxSpeed;
    }

    @Override
    public void moveDown() {

    }

    public void setCommand(Command shoot){
        this.shoot = shoot;
    }









    public Vector getLastDirection() {
        return lastDirection;
    }
}