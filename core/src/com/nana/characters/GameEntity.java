package com.nana.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.nana.helper.PPM;

public abstract class GameEntity {
    protected float x, y;
    public float velX;
    protected float velY;
    protected float speed;
    protected float width, height;
    public Body body;
    protected PPM ppm = new PPM();

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

    public abstract void update();
    
    public abstract void render(SpriteBatch batch);

    public Body getBody(){
        return body;
    }
}
