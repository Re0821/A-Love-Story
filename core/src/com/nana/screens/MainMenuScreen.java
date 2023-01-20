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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nana.WriteReadFile.TimeTracker;
import com.nana.WriteReadFile.WriteRead;
import com.nana.game.Love;
import com.nana.gameFont.MenuFont;
import com.nana.music.GameMusic;

public class MainMenuScreen implements Screen, InputProcessor{
    final Love game;
    private OrthographicCamera camera;
    private BitmapFont myFont;
    SpriteBatch batch;
    TimeTracker timeTracker = TimeTracker.getInstance();
    WriteRead writeRead = WriteRead.getInstance();
    public Stage stage, stage1;
    private Skin skin;
    private Table table, table1;
    private TextButton startButton, leaderBoardButton;
    private float backgroundVelocity;
    private float backgroundX;
    private Texture background1, background2;
    private int GAME_HEIGHT;
    private int GAME_WIDTH;
    GameMusic music = GameMusic.getInstance();
    /**
     * initializing variables from necessary classes needed 
     * @param game takes in the parent game as an argument for switching screens purposes
     */

    public MainMenuScreen(final Love game){
        this.game = game; // Current screen
        new FinalBoss(game);
        this.stage = new Stage(); // To add actors for mostly animation purposes
        GAME_HEIGHT = Gdx.graphics.getHeight();
		GAME_WIDTH = Gdx.graphics.getWidth();
        music.backgroundMusic[6].stop();
        background1 = new Texture(Gdx.files.internal("assets/backgroundImage.png"));
        background2 = new Texture(Gdx.files.internal("assets/backgroundImage.png"));
        backgroundVelocity = 2;

        myFont = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
    
        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,0,0); // Camera
        
 
        skin = new Skin(Gdx.files.internal("assets/terra-mother-ui.json")); // Assets retrieved from open source websites
        stage = new Stage(new ScreenViewport()); // To make it compatible with multiple devices

        table = new Table();
        table1 = new Table();
        
        table.setWidth(stage.getWidth());
        table.align(Align.center);
        table.setPosition(0, Gdx.graphics.getHeight()/1.75f);

        table1.setWidth(stage.getWidth());
        table1.align(Align.center);
        table1.setPosition(0, Gdx.graphics.getHeight()/2f);
        
        startButton = new TextButton("Start Game", skin);
        leaderBoardButton = new TextButton("Leaderboard", skin);
    
        startButton.center();
        leaderBoardButton.center();
        startButton.getLabel().setFontScale(2);
        leaderBoardButton.getLabel().setFontScale(2);
        music.stopTrack();
        music.playMenuTrack();
        startButton.addListener(new ClickListener(){
            
            @Override
            public void clicked(InputEvent event, float x, float y){

                timeTracker.start();
                music.backgroundMusic[0].stop();
                music.shufflePlayTrack();
               game.setScreen(new TutorialGameScreen(game));

            }
        });

        leaderBoardButton.addListener(new ClickListener(){
 
            @Override
            public void clicked(InputEvent event, float x, float y){

                timeTracker.start();
                music.backgroundMusic[0].stop();
               game.setScreen(new Leaderboard(game));

            }
        });
        
      
        table.add(startButton); // Add to table 
        table1.add(leaderBoardButton);
        
        InputMultiplexer im = new InputMultiplexer(stage,this); // To allow for multiple functions
        MenuFont menuFont = new MenuFont(stage, myFont);

        menuFont.createAndSetTypingLabel("{COLOR=TEAL}{SICK}{FAST}A LOVE STORY");


       
        stage.addActor(table);
        stage.addActor(table1);


        Gdx.input.setInputProcessor(im);
    }

    /* (non-Javadoc)
     * gets the bestTime from the writeRead file to be displayed as the shortest time it took to complete the game
     * @see com.badlogic.gdx.Screen#show()
     */

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

  /* (non-Javadoc)
     * render and draw sprite (picture) elements needed for the screen; as well as performing as performing logic tasks behind the scene
     * @see com.badlogic.gdx.Screen#render(float)
     * @param takes in the current deltaTime of the screen as a parameter     
     */
    

    @Override
    public void render(float delta) {
        
        // TODO Auto-generated method stub
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stage.draw();
        game.batch.begin();
        game.batch.draw(background1 , backgroundX , 0 , GAME_WIDTH , GAME_HEIGHT);
		game.batch.draw(background2 , backgroundX + GAME_WIDTH, 0 , GAME_WIDTH,GAME_HEIGHT);

        backgroundX -= backgroundVelocity;
		if (backgroundX +GAME_WIDTH ==0){
			backgroundX = 0;
		}

        stage.act(Gdx.graphics.getDeltaTime());
        
        game.batch.end();
       
        
        
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

    
    /* (non-Javadoc)
     * disposes of any other unnecessary elements that are not in used
     * @see com.badlogic.gdx.Screen#dispose()
     */
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        myFont.dispose();
        stage.dispose();
        background1.dispose();
        background2.dispose();
        
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

}
