package com.nana.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nana.characters.Player;
import com.nana.helper.PPM;
import com.nana.helper.TiledMapHelper;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class TutorialGameScreen implements Screen {
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TiledMapHelper tiledMapHelper;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private SpriteBatch batch;
    private PPM ppm = new PPM();
    private Player player;
    private BitmapFont myFont;
    private Stage stage;
    

    /**
     * Initializing the world
     */

    public TutorialGameScreen(){
        this.stage = new Stage();
        // setting the gravity of the game relative to real world's gravity
        this.world = new World(new Vector2(0,-25f), false);
        this.camera = new OrthographicCamera();
        stage = new Stage(new ScreenViewport());
        renderer = new OrthogonalTiledMapRenderer(map);
        tiledMapHelper = new TiledMapHelper(this);
        renderer = tiledMapHelper.setupMap();
       
        box2DDebugRenderer = new Box2DDebugRenderer();
        myFont = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
        Label.LabelStyle label1Style = new Label.LabelStyle();
        label1Style.font = myFont;
        TypingLabel tutorialLabel = new TypingLabel("", label1Style);
       
        stage.addActor(tutorialLabel);
        
    }

    
    public TypingLabel titleLabelSetting(TypingLabel label){
        label.setAlignment(Align.center);
        // label.setSize(Gdx.graphics.getWidth(), 200);
        // label.setPosition(2, Gdx.graphics.getHeight()-Gdx.graphics.getWidth());
        return label;
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
        stage.draw();
        renderer.setView(camera);
      
        renderer.render();
       

        box2DDebugRenderer.render(world, camera.combined.scl(ppm.getPPM()));
        
      

      
    }

    public void update(){
        world.step(1/60f, 6, 2);
        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);

        player.update();
        
    }

    public void cameraUpdate(){
        Vector3 position = camera.position;

        // get the x and y player position and bring it to the world's position by pixel by meter and then round it by dividing and mulitplying by 10 for smoother camera movements
        // position.x = Math.round(player.getBody().getPosition().x * ppm.getPPM() * 16.9f) / 10f;
        // position.y = Math.round(player.getBody().getPosition().y * ppm.getPPM() * 11) / 10f;

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