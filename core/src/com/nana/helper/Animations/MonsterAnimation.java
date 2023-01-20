package com.nana.helper.Animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.nana.helper.PPM;

public class MonsterAnimation{
    private static final float FRAME_TIME = 1/6f;
    public int r;
    private float elapsed_time;
    private Animation<AtlasRegion> animationAtlas;
    private TextureAtlas charset;
    private TextureRegion currentFrame;
    private String pathName, regionName;
    private String[][] path;

      /**
     * initialize 2D array with path and regionName (derived from atlases) and other variables
     */

    public MonsterAnimation(){
        new PPM();

        path = new String[2][2];
        path[0][0] = "assets/bossNPC/FinalBossAnimation.atlas";
        path[1][0] = "FinalBossAnimation";

    }
/**
     * @return the current frame of the animation and then looping it again
     */
    public TextureRegion createAnimation(){
        startAnimation();
        charset = new TextureAtlas(Gdx.files.internal(pathName));
        animationAtlas = new Animation<>(FRAME_TIME, charset.findRegions(regionName));
        animationAtlas.setFrameDuration(FRAME_TIME);
        elapsed_time += Gdx.graphics.getDeltaTime();
        currentFrame = animationAtlas.getKeyFrame(elapsed_time, true);
      
        return currentFrame;
    }

    /**
     * starts the only animation that the boss have
     */
    public void startAnimation(){

            pathName = path[0][0];
            regionName = path[1][0];


        

    }

}
