package com.nana.game;

import com.badlogic.gdx.Game;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nana.screens.TutorialGameScreen;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class MainMenuScreen implements Screen, InputProcessor{
    final Love game;
    private OrthographicCamera camera;
    private BitmapFont myFont;
    SpriteBatch batch;
    public Stage stage;
    private Skin skin;
    private Table table;
    private TextButton startButton;
    private TutorialGameScreen gameScreen;
    private float backgroundVelocity;
    private float backgroundX;
    private Texture background1, background2;
    private int GAME_HEIGHT;
    private int GAME_WIDTH;

    public MainMenuScreen(final Love game){
        this.game = game; // Current screen
        this.gameScreen = new TutorialGameScreen(); // To switch game screen afterwards
        this.stage = new Stage(); // To add actors for mostly animation purposes
        GAME_HEIGHT = Gdx.graphics.getHeight();
		GAME_WIDTH = Gdx.graphics.getWidth();
        
        background1 = new Texture(Gdx.files.internal("assets/backgroundImage.png"));
        background2 = new Texture(Gdx.files.internal("assets/backgroundImage.png"));
        backgroundVelocity = 2;

        myFont = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
        Label.LabelStyle label1Style = new Label.LabelStyle();
        label1Style.font = myFont;
        TypingLabel titleLabel = new TypingLabel("{SICK}{FAST}A LOVE STORY", label1Style);
        
        titleLabelSetting(titleLabel);

        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,0,0); // Camera
        

        skin = new Skin(Gdx.files.internal("assets/terra-mother-ui.json")); // Assets retrieved from open source websites
        stage = new Stage(new ScreenViewport()); // To make it compatible with multiple devices
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center);
        table.setPosition(0, Gdx.graphics.getHeight()/2);
        
        startButton = new TextButton("Start Game", skin);
    
        startButton.center();
        startButton.getLabel().setFontScale(2);
        startButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y){
                
               game.setScreen(gameScreen);

            }
        });
      
        table.add(startButton); // Add to table 
        
        InputMultiplexer im = new InputMultiplexer(stage,this); // To allow for multiple functions
        stage.addActor(titleLabel);

        stage.addActor(table);
        Gdx.input.setInputProcessor(im);
    }

    public TypingLabel titleLabelSetting(TypingLabel label){
        label.setAlignment(Align.center);
        label.setSize(Gdx.graphics.getWidth(), 200);
        label.setPosition(20, Gdx.graphics.getHeight()-Gdx.graphics.getWidth() + 775);
        return label;
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float delta) {
        
        // TODO Auto-generated method stub
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stage.draw();
        game.batch.begin();
        game.batch.draw(background1 , backgroundX , 0 , GAME_WIDTH , GAME_HEIGHT);
		game.batch.draw(background2 , backgroundX + GAME_WIDTH, 0 , GAME_WIDTH,GAME_HEIGHT);

        backgroundX -= backgroundVelocity;

        System.out.println(backgroundX + GAME_WIDTH); // Debugging purposes. Decreases from 1024 px
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
        
    }
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
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
