package com.nana.helper;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class SkeletonAnimation {
    private static final float FRAME_TIME = 1/6f;
    private float elapsed_time;
    private Animation<AtlasRegion> animationAtlas;
    private TextureAtlas charset;
    private TextureRegion currentFrame;
    private String pathName, regionName;
    private String[][] path;
    private Random random = new Random();
    public int switchDirection = random.nextInt(3);
    private RandomMovement randomMovement;
    public SkeletonAnimation(){
        this.randomMovement = new RandomMovement();
        path = new String[3][3];
        path[0][0] = "assets/skeletonNPC/skeletonRunLeft.atlas";
        path[1][0] = "skeletonRunLeft";
        path[0][1] = "assets/skeletonNPC/skeletonRunRight.atlas";
        path[1][1] = "skeletonRunRight";
        
    }

    public TextureRegion createAnimation(){
        randomMovement();
        charset = new TextureAtlas(Gdx.files.internal(pathName));
        animationAtlas = new Animation<>(FRAME_TIME, charset.findRegions(regionName));
        animationAtlas.setFrameDuration(FRAME_TIME);
        elapsed_time += Gdx.graphics.getDeltaTime();
        currentFrame = animationAtlas.getKeyFrame(elapsed_time, true);
      
        return currentFrame;
    }

    // `1 for left
    // 2 for right
    public void randomMovement(){
        switchDirection = randomMovement.getSwitchDirection();
        if(switchDirection == 1){
            pathName = path[0][0];
            regionName = path[1][0];
        }

        else if(switchDirection == 2){
            pathName = path[0][1];
            regionName = path[1][1];
        }
    }

}
