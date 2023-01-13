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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nana.characters.Player;
import com.nana.game.Love;
import com.nana.gameFont.Level1Font;
import com.nana.gameFont.LiveFont;
import com.nana.helper.Lives;
import com.nana.helper.PPM;
import com.nana.helper.PlayerAnimation;
import com.nana.helper.TiledMap.Level1TiledMapHelper;

public class Level1 implements Screen{
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Level1TiledMapHelper tiledMapHelper;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private PPM ppm = new PPM();
    private Player player;
    private SpriteBatch batch;
    private PlayerAnimation animation;
    private Stage stage;
    private BitmapFont myFont;
    private Level2 gameScreen;
    private double firstSpikePositionX, firstSpikePositionY, secondSpikePositionX, secondSpikePositionY, thirdSpikePositionX, thirdSpikePositionY, fourthSpikePositionX, fourthSpikePositionY;
    private Death deathScreen;
    final Love game;
    private LiveFont liveFont;
    private Lives lives;
    
    public Level1(final Love game){        
        // setting the gravity of the game relative to real world's gravity
        this.game = game;
        this.liveFont = new LiveFont();
        this.lives = new Lives();
        this.gameScreen = new Level2(game);
        this.deathScreen = new Death();
        this.world = new World(new Vector2(0,-25f), false);
  
        this.camera = new OrthographicCamera();
        this.animation = new PlayerAnimation();
        this.stage = new Stage();
      
        renderer = new OrthogonalTiledMapRenderer(map);
        tiledMapHelper = new Level1TiledMapHelper(this);
        renderer = tiledMapHelper.setupMap();
        box2DDebugRenderer = new Box2DDebugRenderer();
        myFont = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
        Gdx.input.setInputProcessor(stage);
        stage = new Stage(new ScreenViewport());
        Level1Font tutorialFont = new Level1Font(stage, myFont);
        tutorialFont.createAndSetTypingLabel("{COLOR=SCARLET}{EASE}{NORMAL}BE CAREFUL OF SPIKES!");

        
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
        stage.draw();
        checkPass();
        batch.begin();
        liveFont.drawLiveFont(batch, lives.lives);
        batch.draw(animation.createAnimation(), player.getBody().getPosition().x * ppm.getPPM() - 60, player.getBody().getPosition().y * ppm.getPPM() - 55, 100, 100);
        batch.end();
        
        stage.act(Gdx.graphics.getDeltaTime());
        box2DDebugRenderer.render(world, camera.combined.scl(ppm.getPPM()));
    }

    public void checkPass(){
        firstSpikePositionX = Math.abs(player.body.getPosition().x  * ppm.getPPM() - 352);
        firstSpikePositionY = Math.abs(player.body.getPosition().y * ppm.getPPM() - 608.00 / 2 - 80.40);
        
        System.out.println(firstSpikePositionX);

        if(firstSpikePositionX < 50 && firstSpikePositionY < 25){
            System.out.println("Dead");
            game.setScreen(deathScreen);
        }

        secondSpikePositionX = Math.abs(player.body.getPosition().x * ppm.getPPM() - 560.48);
        secondSpikePositionY = Math.abs(player.body.getPosition().y * ppm.getPPM() - 608 / 2 - 80.480);

        if(secondSpikePositionX < 50 && secondSpikePositionY < 25){
            System.out.println("Dead");
            game.setScreen(deathScreen);
        }

        thirdSpikePositionX = Math.abs(player.body.getPosition().x * ppm.getPPM() - 720.479);
        thirdSpikePositionY = Math.abs(player.body.getPosition().y * ppm.getPPM() - 608 / 2 - 80.480);

        if(thirdSpikePositionX < 50 && thirdSpikePositionY < 25){
            System.out.println("Dead");
            game.setScreen(deathScreen);
        }

        fourthSpikePositionX = Math.abs(player.body.getPosition().x * ppm.getPPM() - 879.52);
        fourthSpikePositionY = Math.abs(player.body.getPosition().y * ppm.getPPM() - 608 / 2 - 80.480);

        if(fourthSpikePositionX < 50 && fourthSpikePositionY < 25){
            System.out.println("Dead");
            game.setScreen(deathScreen);
        }

        if((player.getBody().getPosition().x * ppm.getPPM()) > 983.5){
            System.out.println("Passed");
            game.setScreen(gameScreen);
        }
    }

    public void update(){
        world.step(1/60f, 6, 2);
        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);
        player.update();
        
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
        batch.dispose();
        stage.dispose();
        myFont.dispose();
        renderer.dispose();
    }
    public TiledMap getMap() {
        return map;
    }
    public void setMap(TiledMap map) {
        this.map = map;
    }
    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }
    public void setRenderer(OrthogonalTiledMapRenderer renderer) {
        this.renderer = renderer;
    }
    public OrthographicCamera getCamera() {
        return camera;
    }
    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public World getWorld() {
        return world;
    }
    public void setWorld(World world) {
        this.world = world;
    }
    public Box2DDebugRenderer getBox2DDebugRenderer() {
        return box2DDebugRenderer;
    }
    public void setBox2DDebugRenderer(Box2DDebugRenderer box2dDebugRenderer) {
        box2DDebugRenderer = box2dDebugRenderer;
    }
    public PPM getPpm() {
        return ppm;
    }
    public void setPpm(PPM ppm) {
        this.ppm = ppm;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    
}
