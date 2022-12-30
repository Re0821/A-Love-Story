package com.nana.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class PlayerAnimation {
    private static final float FRAME_TIME = 1/4f;
    private float elapsed_time;
    private Animation<AtlasRegion> animationAtlas;
    private TextureAtlas charset;
    private TextureRegion currentFrame;
    private String pathName, regionName;
    private String[][] path;

    public PlayerAnimation(){
        path = new String[4][4];
        path[0][0] = "assets/player/character.atlas";
        path[0][1] = "assets/player/runLeft.atlas";
        path[0][2] = "assets/player/runRight.atlas";
        path[0][3] = "assets/player/jump.atlas";
        path[1][3] = "jump";
        path[1][0] = "idle";
        path[1][1] = "runLeft";
        path[1][2] = "runRight";
    }
 
    public TextureRegion createAnimation(){
        checkInput();
        charset = new TextureAtlas(Gdx.files.internal(pathName));
        animationAtlas = new Animation<>(FRAME_TIME, charset.findRegions(regionName));
        animationAtlas.setFrameDuration(FRAME_TIME);
    
        elapsed_time += Gdx.graphics.getDeltaTime();
    
        currentFrame = animationAtlas.getKeyFrame(elapsed_time, true);

        return currentFrame;
    }

    public void checkInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            pathName = path[0][1];
            regionName = path[1][1];
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            pathName = path[0][2];
            regionName = path[1][2];
        } 

        else if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            pathName = path[0][3];
            regionName = path[1][3];
        }
        
        else{
            pathName = path[0][0];
            regionName = path[1][0];
        }

    }  
}

    

