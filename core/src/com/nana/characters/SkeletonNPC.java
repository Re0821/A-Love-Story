package com.nana.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.nana.helper.RandomMovement;


public class SkeletonNPC extends GameEntity{

    private RandomMovement randomMovement;

    public SkeletonNPC(float width, float height, Body body) {
        super(width, height, body);
        this.randomMovement = new RandomMovement();


        this.speed = .22f;
        
        //TODO Auto-generated constructor stub
    }

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
