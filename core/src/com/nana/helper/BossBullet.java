package com.nana.helper;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.nana.music.GameMusic;

public class BossBullet {
    public static int SPEED = 500;
    private static Texture texture, playerFinalBullet;
    public ArrayList<BossBullet> bullets;
    public float x;
    public float y;
    public boolean remove = false;
    public int numBullet = 0;
    public boolean hurtBullet;
    public boolean bossHurt, bossKilled;
    private GameMusic music = new GameMusic();

    /**
     * initializes variables in constructor
     * @param x initialize the x position of the bullet
     * @param y initialize the y position of the bullet
     */
    public BossBullet (float x, float y) {
        this.x = x;
        this.y = y;
        this.bullets = new ArrayList<BossBullet>();

        if (texture == null)
            texture = new Texture(Gdx.files.internal("assets/bossBullet.png"));
            playerFinalBullet = new Texture(Gdx.files.internal("assets/PlayerFinalBullet.png"));
    }

    /**
     * updates the location and hitbox of the bullets and check if it has overlapped with the final boss NPC, the player etc
     * @param deltaTime takes in the current delta time of the game
     * @param rect takes in the bullet hitbox
     * @param playerRectangle takes in the player hitbox
     * @param finalBulletRect takes in the final bullet hitbox
     * @param monsterRectangle takes in the monster hitbox
     * @param startOmegaBullet takes in a boolean variable to determine if it is OK to start
     * @param playerHurtAllowed takes in a boolean variable to determine if the player can be hurt
     */
    public void update (float deltaTime, Rectangle rect, Rectangle playerRectangle, Rectangle finalBulletRect, Rectangle monsterRectangle, boolean startOmegaBullet, boolean playerHurtAllowed) {

            x += SPEED * deltaTime;

        if (y > Gdx.graphics.getHeight() || x > Gdx.graphics.getWidth())
            remove = true;

        if(rect.overlaps(playerRectangle) && !playerHurtAllowed){
            remove = true;
            hurtBullet = true;
            music.gameSound[1].play();
        }

        if(finalBulletRect.overlaps(monsterRectangle) && startOmegaBullet){
            remove = true;
            bossHurt = true;
            bossKilled = true;
            music.gameSound[2].play();
    
        }

        

    }

    public void render (SpriteBatch batch) {

        batch.draw(texture, x, y);
    }

    public void renderOmegaBullet(SpriteBatch batch){
        batch.draw(playerFinalBullet, x, y);
    }

}