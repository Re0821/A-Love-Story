package com.nana.characters;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.nana.helper.PPM;
import com.nana.helper.TiledMap.Level2TiledMapHelper;

public class SkeletonNPC extends GameEntity{
    protected float x, y;
    public float velX;
    protected float velY;
    protected float speed;
    protected float width, height;
    public Body body;
    protected PPM ppm = new PPM();
    private Random random = new Random();
    private int direction = 0;
    
    public SkeletonNPC(float width, float height, Body body) {
        super(width, height, body);
        this.speed = .5f;
        //TODO Auto-generated constructor stub
    }

  

    @Override
    public void update() {
        x = body.getPosition().x * ppm.getPPM();
        y = body.getPosition().y * ppm.getPPM();
        // TODO Auto-generated method stub
        
    }

    public void changeDirection(){
        direction = random.nextInt(3);
    }
    @Override
    public void render(SpriteBatch batch) {
        // TODO Auto-generated method stub
        
    }

}
