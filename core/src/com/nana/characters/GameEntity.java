package com.nana.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.nana.helper.PPM;

public abstract class GameEntity {
    protected float x, y;
    public float velX;
    protected float velY;
    public float speed;
    protected float width, height;
    public Body body;
    protected PPM ppm = new PPM();

    /**
     * @param width takes in the player/NPC object's width size
     * @param height takes in the player/NPC object's height size
     * @param body takes in a body object
     */
    public GameEntity(float width, float height, Body body){
        this.x = body.getPosition().x;
        this.y = body.getPosition().y;
        this.width = width;
        this.height = height;
        this.body = body;
        this.velX = 0;
        this.velY = 0;
        this.speed = 0;
    }

    /**
     * abstract method to update the location of the player/NPC
     */
    public abstract void update();
    
    /**
     * @param batch takes in a SpriteBatch to render to screen
     * abstract method to render player to screen
     */
    public abstract void render(SpriteBatch batch);

    /**
     * @return player/NPC body
     */
    public Body getBody(){
        return body;
    }
}
