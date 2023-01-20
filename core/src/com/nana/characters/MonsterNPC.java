package com.nana.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public class MonsterNPC extends GameEntity{
 /**
     * @param width takes in the width size of the object
     * @param height takes in the height size of the object
     * @param body takes in a body object
     * initializes and setting the "stage" to set object to screen
     */
    public MonsterNPC(float width, float height, Body body) {
        super(width, height, body);

        this.speed = 0;
        //TODO Auto-generated constructor stub
    }
/* (non-Javadoc)
	 * @see com.nana.characters.GameEntity#update()
     * update the player position constantly based on screen's delta time
	 */
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
