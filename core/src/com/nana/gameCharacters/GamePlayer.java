package com.nana.gameCharacters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.nana.helper.GamePlayEntity;

public class GamePlayer extends GamePlayEntity{
    public static final int PPM = 16;

    public GamePlayer(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 4f;
        //TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
    }

    @Override
    public void render(SpriteBatch batch) {
        // TODO Auto-generated method stub
        
    }
    
}
