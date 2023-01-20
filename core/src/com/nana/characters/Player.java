package com.nana.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.nana.music.GameMusic;

public class Player extends GameEntity{

    private int jump;
    private GameMusic music = new GameMusic();
 /**
     * @param width takes in the width size of the object
     * @param height takes in the height size of the object
     * @param body takes in a body object
     * initializes and setting the "stage" to set object to screen
     */
    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 8f;
        this.jump = 0;
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
        
        checkUserInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        // TODO Auto-generated method stub

    }
     /**
     * check which keys are being pressed and function accordingly
     */
    private void checkUserInput(){
        velX = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            velX = 1;

        }if(Gdx.input.isKeyPressed(Input.Keys.A)){
            velX = -1;
            
        } if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && jump < 1){
            float force = body.getMass() * 15;
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
            music.gameSound[0].play();
            jump++;
        }
       
        if(body.getLinearVelocity().y == 0){

            jump = 0;
        }
        
        body.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 20 ? body.getLinearVelocity().y  : 20);
    }
}
