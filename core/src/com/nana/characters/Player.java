package com.nana.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public class Player extends GameEntity{

    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 4f;
        //TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        x = body.getPosition().x * ppm.getPPM();
        y = body.getPosition().y * ppm.getPPM();
    }

    @Override
    public void render(SpriteBatch batch) {
        // TODO Auto-generated method stub
        
    }
    
}
