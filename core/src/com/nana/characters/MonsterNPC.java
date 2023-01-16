package com.nana.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public class MonsterNPC extends GameEntity{

    public MonsterNPC(float width, float height, Body body) {
        super(width, height, body);

        this.speed = 0;
        //TODO Auto-generated constructor stub
    }

	@Override
    public void update() {
        // TODO Auto-generated method stub
        x = body.getPosition().x * ppm.getPPM();
        y = body.getPosition().y * ppm.getPPM();
        velX = 0;
    }

    @Override
    public void render(SpriteBatch batch) {
        // TODO Auto-generated method stub

    }
}
