package com.nana.screens;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nana.WriteReadFile.TimeTracker;
import com.nana.WriteReadFile.WriteRead;
import com.nana.game.Love;
import com.nana.gameFont.DeathWinFont;
import com.nana.gameFont.GeneralFont;
import com.nana.music.GameMusic;

public class Leaderboard implements Screen, InputProcessor{
    private OrthographicCamera camera;
    private BitmapFont myFont, myFont1;
    SpriteBatch batch;
    
    public Stage stage;
    final Love game;
    private Texture background;
    private float GAME_WIDTH;
    private float GAME_HEIGHT;
    WriteRead writeRead = WriteRead.getInstance();
    private WriteRead write = new WriteRead();

    int timeTaken;
    TimeTracker timeTracker = TimeTracker.getInstance();
    GameMusic music = GameMusic.getInstance();
    private int bestTime;
    private GeneralFont font;
    private Timer timer;
    private boolean triggerText;
    private int[] smallest5Numbers = new int[5];

    /**
     * @param game takes in the parent game as an argument for switching screens purposes
     * initializing variables from necessary classes needed 
     */

    public Leaderboard(final Love game){
        this.game = game;
        new Timer();
        
        GAME_HEIGHT = Gdx.graphics.getHeight();
		GAME_WIDTH = Gdx.graphics.getWidth();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,0,0); // Camera
        this.triggerText = false;
        this.stage = new Stage(); // To add actors for mostly animation purposes
        background = new Texture(Gdx.files.internal("assets/backgroundImage.png"));
        myFont = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
        myFont1 = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
    
        Gdx.input.setInputProcessor(stage);
       
        
        stage = new Stage(new ScreenViewport()); // To make it compatible with multiple devices
        
        this.font = new GeneralFont(stage, myFont);
        timer = new Timer();
        font.leaderboardCreateAndSetTypingLabel("Leaderboard:");
      
       
    }

    // Not used

    @Override
    public void show() {
        smallest5Numbers = write.getSmallestNumbers();
        // TODO Auto-generated method stub
        timer.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                
                triggerText = true;
                
                // TODO Auto-generated method stub
                
            }
            
        }, 1);
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
        batch.draw(background , 0 , 0 , GAME_WIDTH , GAME_HEIGHT);
      
        if(triggerText){
            try {
                myFont1.getData().setScale(0.5f, 0.5f);
                myFont1.draw(batch, "Best Time: " + write.startGetLinearSearch() + " seconds", 315, 750f);
                myFont1.draw(batch, "1: " + smallest5Numbers[0] + " seconds", 385, 650);
                myFont1.draw(batch, "2: " + smallest5Numbers[1] + " seconds", 385, 550);
                myFont1.draw(batch, "3: " + smallest5Numbers[2] + " seconds", 385, 450);
                myFont1.draw(batch, "4: " + smallest5Numbers[3] + " seconds", 385, 350);
                myFont1.draw(batch, "4: " + smallest5Numbers[4] + " seconds", 385, 250);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        
        
        batch.end();

        if(Gdx.input.isTouched()){
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }

        stage.draw();
        stage.act(Gdx.graphics.getDeltaTime());


       
        
        
    }

     // Abstraction methods that must be implemented to work

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
