package com.nana.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ScreenAdapter {
    final Love game;
    OrthographicCamera camera;
    public GameScreen(final Love game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        ScreenUtils.clear(0,0,0.2f,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }
    
}
