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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nana.game.Love;
import com.nana.gameFont.DeathFont;
import com.nana.gameFont.MenuFont;
import com.nana.music.GameMusic;

public class Death implements Screen, InputProcessor{
    private OrthographicCamera camera;
    private BitmapFont myFont;
    SpriteBatch batch;
    public Stage stage;
    final Love game;
    private Skin skin;
    private Table table;
    private TextButton startButton;
    private int GAME_HEIGHT;
    private int GAME_WIDTH;


    public Death(final Love game){
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,0,0); // Camera

        this.stage = new Stage(); // To add actors for mostly animation purposes
        GAME_HEIGHT = Gdx.graphics.getHeight();
		GAME_WIDTH = Gdx.graphics.getWidth();
        
        myFont = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
    
        Gdx.input.setInputProcessor(stage);

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
               game.setScreen(new MainMenuScreen(game));

            }
        });
      
        table.add(startButton); // Add to table 
        
        InputMultiplexer im = new InputMultiplexer(stage,this); // To allow for multiple functions
        MenuFont menuFont = new MenuFont(stage, myFont);

        menuFont.createAndSetTypingLabel("{COLOR=TEAL}{SICK}{FAST}You Failed");


       
        stage.addActor(table);
    

        Gdx.input.setInputProcessor(im);
    }
    

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        // TODO Auto-generated method stub
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
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
        
    }
}
