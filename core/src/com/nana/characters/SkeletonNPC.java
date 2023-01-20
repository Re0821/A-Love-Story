package com.nana.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.nana.helper.Animations.RandomMovement;


public class SkeletonNPC extends GameEntity{

    private RandomMovement randomMovement;

    /**
     * initializes and setting the "stage" to set object to screen
     * @param width takes in the width size of the object
     * @param height takes in the height size of the object
     * @param body takes in a body object
     */
    public SkeletonNPC(float width, float height, Body body) {
        super(width, height, body);
        this.randomMovement = new RandomMovement();


        this.speed = .22f;
        
        //TODO Auto-generated constructor stub
    }

	/* (non-Javadoc)
     * update the player position constantly based on screen's delta time
	 * @see com.nana.characters.GameEntity#update()
	 */
	@Override
    public void update() {
        // TODO Auto-generated method stub
        x = body.getPosition().x * ppm.getPPM();
        y = body.getPosition().y * ppm.getPPM();
        checkInput();

    }

    @Override
    public void render(SpriteBatch batch) {
        // TODO Auto-generated method stub

    }

    /**
     * check if the random number generator number correspond to which random movement and move accordingly
     */
    public void checkInput(){
        velX = 0;
        if(randomMovement.getRandomNumber() == 1){
            velX = -1;
        }if(randomMovement.getRandomNumber() == 2){
            velX = 1;
        } 
    
        if(x >= 16.5 && 18.5 >= x){
            velX = 1;
        }  else if(x >= 19.5 && 22.5 >= x){
            velX = -1;
        }

        body.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 20 ? body.getLinearVelocity().y  : 20);
    }
}
