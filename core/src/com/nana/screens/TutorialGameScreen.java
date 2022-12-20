package com.nana.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.nana.characters.Player;
import com.nana.game.Love;
import com.nana.helper.PPM;
import com.nana.helper.TutorialTiledMapHelper;

public class TutorialGameScreen implements Screen {
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TutorialTiledMapHelper tiledMapHelper;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private SpriteBatch batch;
    private PPM ppm = new PPM();
    private Player player;
    final Love game;
    private Level1 gameScreen;
    
    /**
     * Initializing the world
     */

    public TutorialGameScreen(final Love game){
        this.game = game;
        this.gameScreen = new Level1();
        // setting the gravity of the game relative to real world's gravity
        this.world = new World(new Vector2(0,-25f), false);
        this.camera = new OrthographicCamera();
        renderer = new OrthogonalTiledMapRenderer(map);
        tiledMapHelper = new TutorialTiledMapHelper(this);
        renderer = tiledMapHelper.setupMap();
        box2DDebugRenderer = new Box2DDebugRenderer();


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
        renderer.setView(camera);
      
        renderer.render();
        
        box2DDebugRenderer.render(world, camera.combined.scl(ppm.getPPM()));
        
        System.out.println(player.getBody().getPosition().x);

      
    }

    public void changeScreen(){
        if(player.getBody().getPosition().x > 30){
            game.setScreen(gameScreen);
        }
    }


    public void update(){
        world.step(1/60f, 6, 2);
        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);

        player.update();
        changeScreen();
        
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
        dispose();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
  
    }

    public World getWorld() {
        return world;
    }

    
    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

}