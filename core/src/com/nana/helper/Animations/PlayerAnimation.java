package com.nana.helper.Animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.nana.helper.Immunity;
import com.nana.music.GameMusic;

public class PlayerAnimation {
    private static final float FRAME_TIME = 1/2f;
    private float elapsed_time;
    private Animation<AtlasRegion> animationAtlas;
    private TextureAtlas charset;
    private TextureRegion currentFrame;
    private String pathName;
    String regionName;
    String[][] path;
    public boolean checkLeft;
    public boolean deathAnimation = false;
    private Immunity immunity;
    private GameMusic music = new GameMusic();

    /**
     * initialize 2D array with path and regionName (derived from atlases) and other variables
    */
    public PlayerAnimation(){
        this.immunity = new Immunity();
        path = new String[6][6];
        path[0][0] = "assets/player/playerBlinkIdle.atlas";
        path[0][1] = "assets/player/playerLeftRun.atlas";
        path[0][2] = "assets/player/playerRun.atlas";
        path[0][3] = "assets/player/playerJump.atlas";
        path[0][4] = "assets/player/playerLeftBlinkIdle.atlas";
        path[0][5] = "assets/player/death.atlas";

        path[1][0] = "playerBlinkIdle";
        path[1][1] = "playerLeftRun";
        path[1][2] = "playerRun";
        path[1][3] = "playerJump";
        path[1][4] = "playerLeftBlinkIdle";
        path[1][5] = "death";
    }
   /**
     * calls the checkLeft() function and checkInput() function and starts/create the animation 
     * @return the current frame of the animation and then looping it again
     */
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

    /**
     * checks key inputs to switch animation (by pointing to another pathName & regionName)
     */
    public void checkInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            pathName = path[0][2];
            regionName = path[1][2];
            checkLeft = false;
            deathAnimation = false;
        }
    
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            pathName = path[0][1];
            regionName = path[1][1];
            checkLeft = true;
            deathAnimation = false;
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            pathName = path[0][3];
            regionName = path[1][3];
            deathAnimation = false;
        }

        else if(checkLeft == true){
            pathName = path[0][4];
            regionName = path[1][4];
            deathAnimation = false;
        }
        
        else if(deathAnimation == true && immunity.isImmune() == false){
            pathName = path[0][5];
            regionName = path[1][5];


        }

        else{
            pathName = path[0][0];
            regionName = path[1][0];
            deathAnimation = false;
        }


    } 
    
    /**
     * check if checkLeft is true; if true, switch to left idle animations
     */

    public void checkLeft(){
        if(checkLeft){
            pathName = path[0][4];
            regionName = path[1][4]; 
        }
    }
}

    

