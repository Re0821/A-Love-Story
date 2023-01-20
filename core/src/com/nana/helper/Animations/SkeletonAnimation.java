package com.nana.helper.Animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.nana.helper.PPM;

public class SkeletonAnimation{
    private static final float FRAME_TIME = 1/6f;
    public int r;
    private float elapsed_time;
    private Animation<AtlasRegion> animationAtlas;
    private TextureAtlas charset;
    private TextureRegion currentFrame;
    private String pathName, regionName;
    private String[][] path;
    private RandomMovement randomMovement;

    /**
     * initialize 2D array with path and regionName (derived from atlases) and other variables
     */
    public SkeletonAnimation(){
        this.randomMovement = new RandomMovement();
        new PPM();

        path = new String[3][3];
        path[0][0] = "assets/skeletonNPC/skeletonRunLeft.atlas";
        path[1][0] = "skeletonRunLeft";
        path[0][1] = "assets/skeletonNPC/skeletonRunRight.atlas";
        path[1][1] = "skeletonRunRight";

    }

    /**
     * gets the random number from RandomMovement class and starts the animation
     * @return the current frame of the animation and then looping it again
     */
    public TextureRegion createAnimation(){
        r = randomMovement.getRandomNumber();
        randomMovement();
        charset = new TextureAtlas(Gdx.files.internal(pathName));
        animationAtlas = new Animation<>(FRAME_TIME, charset.findRegions(regionName));
        animationAtlas.setFrameDuration(FRAME_TIME);
        elapsed_time += Gdx.graphics.getDeltaTime();
        currentFrame = animationAtlas.getKeyFrame(elapsed_time, true);
      
        return currentFrame;
    }

    /**
     * based on the class "randomMovement", get a new direction that the skeleton go every 3 seconds
     */
    public void randomMovement(){

        if(r == 1){
            pathName = path[0][0];
            regionName = path[1][0];
        }

        else if(r == 2){
            pathName = path[0][1];
            regionName = path[1][1];
        }

        

    }

}
