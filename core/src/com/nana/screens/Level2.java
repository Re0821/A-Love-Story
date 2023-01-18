package com.nana.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nana.characters.Player;
import com.nana.characters.SkeletonNPC;
import com.nana.game.Love;
import com.nana.gameFont.GeneralFont;
import com.nana.gameFont.LiveFont;
import com.nana.helper.Immunity;
import com.nana.helper.Lives;
import com.nana.helper.PPM;
import com.nana.helper.Animations.FadeOutEffect;
import com.nana.helper.Animations.PlayerAnimation;
import com.nana.helper.Animations.SkeletonAnimation;



import com.nana.helper.TiledMap.Level2TiledMapHelper;

public class Level2 implements Screen{
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Level2TiledMapHelper tiledMapHelper;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private PPM ppm = new PPM();
    private Player player;
    private SpriteBatch batch;
    private PlayerAnimation animation;
    private SkeletonAnimation skeletonAnimation;
    private Stage stage;
    private BitmapFont myFont;
    private Death deathScreen;
    private SkeletonNPC skeleton;
    private Immunity immunity = new Immunity();
    private final Love game;
    private Lives lives;
    public float absDistSkeletonX;
    public float absDistSkeletonY;
    private LiveFont liveFont;
    private FadeOutEffect fadeOut;
    private FinalBoss gameScreen;
    private Rectangle playerRectangle, skeletonRectangle;
    
    public Level2(final Love game){        
        // setting the gravity of the game relative to real world's gravity
        this.deathScreen = new Death(game);
        this.game = game;
        this.gameScreen = new FinalBoss(game);
        batch = new SpriteBatch();
        this.fadeOut = new FadeOutEffect(game, deathScreen, .6f, batch);
        this.liveFont = new LiveFont();
        this.world = new World(new Vector2(0,-25f), false);
        this.camera = new OrthographicCamera();
        this.animation = new PlayerAnimation();
        this.skeletonAnimation = new SkeletonAnimation();
        this.stage = new Stage();
        this.lives = new Lives();
        renderer = new OrthogonalTiledMapRenderer(map);
        tiledMapHelper = new Level2TiledMapHelper(this);
        renderer = tiledMapHelper.setupMap();
        box2DDebugRenderer = new Box2DDebugRenderer();
        myFont = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
        Gdx.input.setInputProcessor(stage);
        stage = new Stage(new ScreenViewport());
        GeneralFont level2Font = new GeneralFont(stage, myFont);
        level2Font.createAndSetTypingLabel("{COLOR=SCARLET}{EASE}{NORMAL}Skeletons are unpredictable and mindless! Avoid them at all cost!");


        
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
        updateRectangle();
        
        checkHurt();
        checkPass();

        batch.begin();
        liveFont.drawLiveFont(batch, lives.lives);

        batch.draw(animation.createAnimation(), player.getBody().getPosition().x * ppm.getPPM() - 60, player.getBody().getPosition().y * ppm.getPPM() - 55, 100, 100);
        batch.draw(skeletonAnimation.createAnimation(), skeleton.getBody().getPosition().x * ppm.getPPM() - 60, skeleton.getBody().getPosition().y * ppm.getPPM() - 45  , 100, 100);
        
        batch.end();
     
               
        fadeOut.render(delta);


        if(lives.lives <= 0){
            fadeOut.start();
            if(fadeOut.finished == true){
                game.setScreen(deathScreen);
            }
        }
       

        stage.act(Gdx.graphics.getDeltaTime());
        box2DDebugRenderer.render(world, camera.combined.scl(ppm.getPPM()));
    }

    

    public void update(){
        world.step(1/60f, 6, 2);
        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);
        player.update();
        skeleton.update();
        
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
    public void checkPass(){
        if(player.getBody().getPosition().x * ppm.getPPM() > 983.5){
            dispose();
            game.setScreen(gameScreen);
        }
    }
    public void checkHurt(){

        if(playerRectangle.overlaps(skeletonRectangle)){
            lives.lives--;

            System.out.println("hurt");
            animation.deathAnimation = true;
            immunity.giveImmunity();
            

        } else {
            lives.hurt = false;
        }
    }

    public void updateRectangle(){
        playerRectangle = new Rectangle(player.body.getPosition().x * ppm.getPPM() - 60, player.getBody().getPosition().y * ppm.getPPM() - 55, 100, 100);
        skeletonRectangle = new Rectangle(skeleton.getBody().getPosition().x * ppm.getPPM() - 100, skeleton.getBody().getPosition().y * ppm.getPPM() - 75  , 100, 100);
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
    public void setPlayer(Player player2) {
        this.player = player2;
    }
    
    public void setSkeleton(SkeletonNPC skeleton){
        this.skeleton = skeleton;
    }
}
