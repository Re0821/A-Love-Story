package com.nana.helper;

import javax.net.ssl.TrustManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class PlayerAnimation {
    private static final float FRAME_TIME = 1/2f;
    private float elapsed_time;
    private Animation<AtlasRegion> animationAtlas;
    private TextureAtlas charset;
    private TextureRegion currentFrame;
    private String pathName, regionName;
    private String[][] path;
    private boolean checkLeft;

    public PlayerAnimation(){
        path = new String[5][5];
        path[0][0] = "assets/player/playerBlinkIdle.atlas";
        path[0][1] = "assets/player/playerLeftRun.atlas";
        path[0][2] = "assets/player/playerRun.atlas";
        path[0][3] = "assets/player/playerJump.atlas";
        path[0][4] = "assets/player/playerLeftBlinkIdle.atlas";
        path[1][0] = "playerBlinkIdle";
        path[1][1] = "playerLeftRun";
        path[1][2] = "playerRun";
        path[1][3] = "playerJump";
        path[1][4] = "playerLeftBlinkIdle";
    }
 
    public TextureRegion createAnimation(){
        checkLeft();
        checkInput();
        charset = new TextureAtlas(Gdx.files.internal(pathName));
        animationAtlas = new Animation<>(FRAME_TIME, charset.findRegions(regionName));
        animationAtlas.setFrameDuration(FRAME_TIME);
    
        elapsed_time += Gdx.graphics.getDeltaTime();
    
        currentFrame = animationAtlas.getKeyFrame(elapsed_time, true);
      
        return currentFrame;
    }

    public void checkInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            pathName = path[0][2];
            regionName = path[1][2];
            checkLeft = false;
        }
    
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            pathName = path[0][1];
            regionName = path[1][1];
            checkLeft = true;
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            pathName = path[0][3];
            regionName = path[1][3];
        }

        else if(checkLeft == false){
            pathName = path[0][0];
            regionName = path[1][0];
        }
    } 
    
    public void checkLeft(){
        if(checkLeft){
            pathName = path[0][4];
            regionName = path[1][4]; 
        }
    }
}

    

