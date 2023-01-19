package com.nana.helper;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.nana.characters.Player;
import com.nana.helper.Animations.PlayerAnimation;
import com.nana.screens.FinalBoss;

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




    public BossBullet (float x, float y) {
        this.x = x;
        this.y = y;
        this.bullets = new ArrayList<BossBullet>();

        if (texture == null)
            texture = new Texture(Gdx.files.internal("assets/bossBullet.png"));
            playerFinalBullet = new Texture(Gdx.files.internal("assets/PlayerFinalBullet.png"));
    }

    public void update (float deltaTime, Rectangle rect, Rectangle playerRectangle, Rectangle finalBulletRect, Rectangle monsterRectangle, boolean startOmegaBullet, boolean playerHurtAllowed) {

            x += SPEED * deltaTime;

        if (y > Gdx.graphics.getHeight() || x > Gdx.graphics.getWidth())
            remove = true;

        if(rect.overlaps(playerRectangle) && !playerHurtAllowed){
            remove = true;
            hurtBullet = true;
        }

        if(finalBulletRect.overlaps(monsterRectangle) && startOmegaBullet){
            remove = true;
            bossHurt = true;
            bossKilled = true;
    
            //THEN THE BATCH OF THE MONSTER ANIMATION STOPS DRAWING WHICH IS GOING TO BE SET TO A VARIABLE
        }

        

    }

    public void render (SpriteBatch batch) {

        batch.draw(texture, x, y);
    }

    public void renderOmegaBullet(SpriteBatch batch){
        batch.draw(playerFinalBullet, x, y);
    }

}