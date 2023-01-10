package com.nana.characters;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.nana.music.GameMusic;

public class SkeletonNPC extends GameEntity{

    private int jump;
    private GameMusic music = new GameMusic();

    public SkeletonNPC(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 15f;
        this.jump = 0;
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
