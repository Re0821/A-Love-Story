package com.nana.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nana.game.Love;
import com.nana.gameFont.DeathWinFont;
import com.nana.music.GameMusic;

public class Death implements Screen, InputProcessor{
    private OrthographicCamera camera;
    private BitmapFont myFont;
    SpriteBatch batch;
    public boolean died = false;
    public Stage stage;
    final Love game;
    private Texture background1;
    private Texture background2;
    private float backgroundVelocity;
    private float backgroundX;
    private float GAME_WIDTH;
    private float GAME_HEIGHT;
    GameMusic music = GameMusic.getInstance();
/**
     * @param game takes in the parent game as an argument for switching screens purposes
     * initializing variables from necessary classes needed 
     */

    public Death(final Love game){
        this.game = game;
        this.died = true;
        background1 = new Texture(Gdx.files.internal("assets/background1.jpg"));
        background2 = new Texture(Gdx.files.internal("assets/background2.jpg"));
        backgroundVelocity = 2;
        GAME_HEIGHT = Gdx.graphics.getHeight();
		GAME_WIDTH = Gdx.graphics.getWidth();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,0,0); // Camera

        this.stage = new Stage(); // To add actors for mostly animation purposes
        
        myFont = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
    
        Gdx.input.setInputProcessor(stage);

      
        stage = new Stage(new ScreenViewport()); // To make it compatible with multiple devices
        
        InputMultiplexer im = new InputMultiplexer(stage,this); // To allow for multiple functions
        DeathWinFont deathFont = new DeathWinFont(stage, myFont);

        deathFont.createAndSetTypingLabel("{COLOR=RED}{SICK}{FAST}You Failed");
        deathFont.adjustedFont1("{COLOR=WHITE}{SICK}{FAST} PRESS ANYWHERE TO START AGAIN");
        Gdx.input.setInputProcessor(im);

     
    }
    

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }
  /* (non-Javadoc)
     * @see com.badlogic.gdx.Screen#render(float)
     * @param takes in the current deltaTime of the screen as a parameter
     * render and draw sprite (picture) elements needed for the screen; as well as performing as performing logic tasks behind the scene
     */
    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(0,0,0,1); 
        // TODO Auto-generated method stub
      
        batch.begin();
        batch.draw(background1 , backgroundX , 0 , GAME_WIDTH , GAME_HEIGHT);
		batch.draw(background2 , backgroundX + GAME_WIDTH, 0 , GAME_WIDTH,GAME_HEIGHT);
        backgroundX -= backgroundVelocity;

        System.out.println(backgroundX + GAME_WIDTH); // Debugging purposes. Decreases from 1024 px
		if (backgroundX +GAME_WIDTH ==0){
			backgroundX = 0;
		}
        batch.end();

        if(Gdx.input.isTouched()){
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }

        stage.draw();
        stage.act(Gdx.graphics.getDeltaTime());


       
        
        
    }

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
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
}
