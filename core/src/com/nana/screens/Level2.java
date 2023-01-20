package com.nana.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
import com.nana.music.GameMusic;

public class Level2 implements Screen{
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Level2TiledMapHelper tiledMapHelper;
    private World world;
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
    private Rectangle spikeRectangle1, spikeRectangle2, spikeRectangle3;
    ShapeRenderer shapeRenderer;
    GameMusic music = GameMusic.getInstance();
    
     /**
     * @param game takes in the parent game as an argument for switching screens purposes
     * initializing variables from necessary classes needed 
     */

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
        myFont = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
        Gdx.input.setInputProcessor(stage);
        stage = new Stage(new ScreenViewport());
        GeneralFont level2Font = new GeneralFont(stage, myFont);
        level2Font.level2CreateAndSetTypingLabel("{COLOR=SCARLET}{EASE}{NORMAL}Skeletons are unpredictable and mindless! Avoid them at all cost!");


        
    }
    /* (non-Javadoc)
     * @see com.badlogic.gdx.Screen#show()
     * initializing SpriteBatch to render texture
     */
    @Override
    public void show() {
        // TODO Auto-generated method stub
        batch = new SpriteBatch();
    }

    /* (non-Javadoc)
     * @see com.badlogic.gdx.Screen#render(float)
     * @param takes in the current deltaTime of the screen as a parameter
     * render and draw sprite (picture) elements needed for the screen; as well as performing as performing logic tasks behind the scene
     */

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
        System.out.println(player.body.getPosition().x);

        batch.begin();
        returnlevel();
        liveFont.drawLiveFont(batch, lives.lives);

        batch.draw(animation.createAnimation(), player.getBody().getPosition().x * ppm.getPPM() - 60, player.getBody().getPosition().y * ppm.getPPM() - 55, 100, 100);
        batch.draw(skeletonAnimation.createAnimation(), skeleton.getBody().getPosition().x * ppm.getPPM() - 60, skeleton.getBody().getPosition().y * ppm.getPPM() - 45  , 100, 100);
        batch.end();
     
               
        fadeOut.render(delta);


       checkDeath();
       
        spikesRectangle();
        stage.act(Gdx.graphics.getDeltaTime());
    }

    /**
     * check if player has died
     */
    public void checkDeath(){
        if(lives.lives <= 0){
            fadeOut.start();
            if(fadeOut.finished == true){
                game.setScreen(deathScreen);
            }
        }
    }

    /**
     * fade screen and switch to death screen
     */
    public void died(){
        fadeOut.start();
            if(fadeOut.finished == true){
                game.setScreen(deathScreen);
            }
    }
    /**
     * returns the player to the original spawn position if the player tries to access out of bounds area
     */
    public void returnlevel(){
        if(player.body.getPosition().x <= 0.23333333){
            player.body.setTransform(1.3666667f, 24.514996f,0f);
        }
    }

    /**
     * updates the world, camera, batch, player and the skeleton variables
     */
    public void update(){
        world.step(1/60f, 6, 2);
        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);
        player.update();
        skeleton.update();
        
    }

    /**
     * checks if player died to spikes
     */
    public void spikesRectangle(){
        spikeRectangle1 = new Rectangle(96, 300, 250, 250);
        spikeRectangle2 = new Rectangle(384, 300, 200, 200);
        spikeRectangle3 = new Rectangle(607,100,100,100);
        
        if(spikeRectangle1.overlaps(playerRectangle) || spikeRectangle2.overlaps(playerRectangle) || spikeRectangle3.overlaps(playerRectangle)){
            player.speed = 0f;
            died();
        }
        
    }

    /**
     * updates the camera position
     */

    public void cameraUpdate(){
        Vector3 position = camera.position;
        position.x = 513;
        position.y = 460;
        camera.position.set(position);

        camera.viewportWidth = Gdx.graphics.getWidth();
        camera.viewportHeight = Gdx.graphics.getHeight();
        camera.update();
    }

    /**
     * if player has reached the end of the level, switch screen
     */
    public void checkPass(){
        if(player.getBody().getPosition().x * ppm.getPPM() > 983.5){
            dispose();

            music.playFinalBossTrack();
            game.setScreen(gameScreen);
        }
    }
    /**
     * if player is not immune and the player overlaps with the skeleton, take 1 live away
     */

    public void checkHurt(){

        if(playerRectangle.overlaps(skeletonRectangle) && !immunity.isImmune()){
            lives.lives--;
            animation.deathAnimation = true;
            immunity.giveImmunity();
            music.gameSound[1].play();
            

        } else {
            lives.hurt = false;
        }
    }

    /**
     * updates the player and skeleton hitboxes (rectangle)
     */
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
    public PPM getPpm() {
        return ppm;
    }
    public void setPpm(PPM ppm) {
        this.ppm = ppm;
    }
    public Player getPlayer() {
        return player;
    }
    /**
     * @param player2 takes in a player as a parameter
     * set player into the world
     */
    public void setPlayer(Player player2) {
        this.player = player2;
    }
    
    /**
     * @param skeleton takes in skeleton as a parameter
     * set skeleton into the world
     */
    public void setSkeleton(SkeletonNPC skeleton){
        this.skeleton = skeleton;
    }
}
