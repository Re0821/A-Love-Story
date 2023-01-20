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
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nana.characters.Player;
import com.nana.game.Love;
import com.nana.gameFont.LiveFont;
import com.nana.gameFont.TutorialFont;
import com.nana.helper.Lives;
import com.nana.helper.PPM;
import com.nana.helper.Animations.PlayerAnimation;
import com.nana.helper.TiledMap.TutorialTiledMapHelper;


public class TutorialGameScreen implements Screen {
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TutorialTiledMapHelper tiledMapHelper;
    private World world;
    private SpriteBatch batch;
    private PPM ppm = new PPM();
    private Player player;
    final Love game;
    private Level1 gameScreen;
    public Stage stage;
    private BitmapFont myFont;
    private PlayerAnimation animation;
    private LiveFont liveFont;
    private Lives lives;

   
  /**
     * @param game takes in the parent game as an argument for switching screens purposes
     * initializing variables from necessary classes needed 
     */

    public TutorialGameScreen(final Love game){
        this.game = game;
        this.stage = new Stage();
        this.gameScreen = new Level1(game);
        this.lives = new Lives();
        // setting the gravity of the game relative to real world's gravity
        this.world = new World(new Vector2(0,-25f), false);
        this.camera = new OrthographicCamera();
        this.animation = new PlayerAnimation();
        liveFont = new LiveFont();
        myFont = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
        Gdx.input.setInputProcessor(stage);

        stage = new Stage(new ScreenViewport());

        TutorialFont tutorialFont = new TutorialFont(stage, myFont);

        tutorialFont.createAndSetTypingLabel("{COLOR=SCARLET}{EASE}{NORMAL}USE A/D TO MOVE LEFT/RIGHT. PRESS SPACE TO JUMP");

        renderer = new OrthogonalTiledMapRenderer(map);
        tiledMapHelper = new TutorialTiledMapHelper(this);
        renderer = tiledMapHelper.setupMap();


        

    }

    
    /* (non-Javadoc)
     * @see com.badlogic.gdx.Screen#show()
     * initializing SpriteBatch for drawing purposes
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
        Gdx.gl.glClearColor(0.0f,0,0.0f,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        
        renderer.setView(camera);
        
        renderer.render();
        stage.draw();
        batch.begin();
        returnLevel();
        liveFont.drawLiveFont(batch, lives.lives);

        batch.draw(animation.createAnimation(), player.getBody().getPosition().x * ppm.getPPM() - 60, player.getBody().getPosition().y * ppm.getPPM() - 55, 100, 100);
        batch.end();
       
        stage.act(Gdx.graphics.getDeltaTime());

    }

    /**
     * changes screen whenever the player reaches the end of the level (screen)
     */
    public void changeScreen(){
        if(player.getBody().getPosition().x > 30){
            game.setScreen(gameScreen);
        }
    }
    /**
     * brings the player back to the original starting position whenever the player tries to reach out of bound areas
     */
    public void returnLevel(){
        if(player.body.getPosition().x <= 0.2333333333f){
             player.body.setTransform(2.3f, 17.483746f, 0f);
        }
    }

    
    /**
     *  updates the world at a 60 fps, camera, batch and the player
     * also updates constantly wheteher player has reached the end of the level
     */
    public void update(){
        world.step(1/60f, 6, 2);
        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);

        player.update();
        changeScreen();
        
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

     // Not used; abstraction methods that must be implemented for the game to work

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



    /**
     * @param player takes in a player body with information about its speed, height etc 
     * initialize the player body/fissure into the world
     */

    public void setPlayer(Player player){
        this.player = player;
    }

}