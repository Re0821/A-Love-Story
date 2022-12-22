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
    private String path;
    private String regionName;


    public PlayerAnimation(String path, String regionName){
        this.path = path;
        this.regionName = regionName;
    }

    public TextureRegion createAnimation(){
        checkInput();
        charset = new TextureAtlas(Gdx.files.internal(path));
        animationAtlas = new Animation<>(FRAME_TIME, charset.findRegions(regionName));
        animationAtlas.setFrameDuration(FRAME_TIME);
    
        elapsed_time += Gdx.graphics.getDeltaTime();
    
        currentFrame = animationAtlas.getKeyFrame(elapsed_time, true);
       
        return currentFrame;

    }

    public void checkInput(){
       if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
        path = "assets/player/runLeft.atlas";
        regionName = "runLeft"; 
        }

    }
    
}
