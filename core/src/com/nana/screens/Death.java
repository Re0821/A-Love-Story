package com.nana.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.nana.helper.PPM;
import com.nana.helper.PlayerAnimation;

public class Death implements Screen{
    private OrthographicCamera camera;
    private World world;
    private SpriteBatch batch;
    private PlayerAnimation animation;
    private Stage stage;
    private BitmapFont myFont;
    
    public Death(){        
        // setting the gravity of the game relative to real world's gravity
      
  
        this.world = new World(new Vector2(0,-25f), false);
  
        this.camera = new OrthographicCamera();
        this.animation = new PlayerAnimation();
        this.stage = new Stage();
      

        
    }
    @Override
    public void show() {
        // TODO Auto-generated method stub
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        this.update();
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
  


    }

    public void update(){
        world.step(1/60f, 6, 2);
        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);
        
    }

    public void cameraUpdate(){
        Vector3 position = camera.position;
        position.x = 513;
        position.y = 460;
        camera.position.set(position);

        camera.viewportWidth = Gdx.graphics.getWidth();
        camera.viewportHeight = Gdx.graphics.getHeight();
        camera.update();
    }
    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }
}
