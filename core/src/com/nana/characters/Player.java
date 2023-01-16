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

    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 8f;
        this.jump = 0;
        //TODO Auto-generated constructor stub
    }

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
