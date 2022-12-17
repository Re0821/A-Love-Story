package com.nana.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.nana.game.Love;
import com.nana.helper.TileMapHelper;

public class GameScreen extends ScreenAdapter {
    final Love game;
    OrthographicCamera camera;
    private SpriteBatch batch;
 
    private World world;
    private Box2DDebugRenderer box2dDebugRenderer;
    private final float PPM = 16;
    
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;

    public GameScreen(final Love game){
        this.game = game;
        this.camera = new OrthographicCamera();
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,0), false);
        this.box2dDebugRenderer = new Box2DDebugRenderer();
        this.tileMapHelper = new TileMapHelper();
        this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();
        
        
    }
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float delta) {
        this.update();
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        orthogonalTiledMapRenderer.render();
        batch.begin();
        
        batch.end();
        box2dDebugRenderer.render(world, camera.combined.scl(PPM));

    }

    private void update(){
        world.step(1/60f, 6, 2);
        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);
    }   
    
    private void cameraUpdate(){
        camera.position.set(new Vector3(0,0,0));
        camera.update();
    }
}
