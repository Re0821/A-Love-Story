package com.nana.helper;

import java.util.ArrayList;
import java.util.Iterator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.nana.helper.DroppingAssets.GreenBullet;
import com.nana.helper.DroppingAssets.OmegaBullet;
import com.nana.helper.DroppingAssets.WhiteBullet;
import com.nana.helper.DroppingAssets.YellowBullet;
import com.nana.music.GameMusic;


public class BossRain {
    private ArrayList<GreenBullet> greenBullets;
    private ArrayList<YellowBullet> yellowBullets;
    private ArrayList<WhiteBullet> whiteBullets;
    private ArrayList<OmegaBullet> omegaBullets;
    private Texture greenBullet, yellowBullet, whiteBullet, omegaBullet;
    private long lastGreenBulletDropTIme, lastYellowBulletDropTime, lastWhiteBulletDropTime, lastOmegaBulletDropTime;
    public Immunity immunity;
    public Lives lives;
    public Rectangle playerRectangle;
    public Rectangle rect1;
    public  Rectangle rect2;
    public Rectangle rect3;
    public Rectangle rect4;
    public Timer timer1, timer2;
    public SpriteBatch batch;
    public int mulitplier;
    private int multiplier;
    public boolean startWhite = false;
    public boolean startOmega = false;
    public int omegaBulletCollected;
    private GameMusic music = new GameMusic();
    
    /**
     * initializes variables in constructor
     * @param batch takes in a SpriteBatch as a parameter ƒor rendering textures
     * @param multiplier takes in an int mulitplier object that determines the overall speed of the bullets
     */
    public BossRain(SpriteBatch batch, int multiplier){
        this.batch = new SpriteBatch();
        this.multiplier = multiplier;
        this.greenBullet = new Texture(Gdx.files.internal("assets/GreenBullet.png"));
        this.yellowBullet = new Texture(Gdx.files.internal("assets/YellowBullet.png"));
        this.whiteBullet = new Texture(Gdx.files.internal("assets/WhiteBullet.png"));
        this.omegaBullet = new Texture(Gdx.files.internal("assets/OmegaBullet.png"));
        this.omegaBulletCollected = 0;
        greenBullets = new ArrayList<GreenBullet>();
        yellowBullets = new ArrayList<YellowBullet>();
        whiteBullets = new ArrayList<WhiteBullet>();
        omegaBullets = new ArrayList<OmegaBullet>();
        this.immunity = new Immunity();
        this.lives = new Lives();
    }


/**
 * initializes the boss fight rain
 * @param playerRectangle takes in the player hitbox as a parameter
 * @param startRain takes in a boolean to determine whether it is OK to start the rain droplets
 */
public void rainInit(Rectangle playerRectangle, Boolean startRain){

    if(TimeUtils.nanoTime() - lastGreenBulletDropTIme > 1000000000 && startRain){
        spawnGreen();
    }

    if(TimeUtils.nanoTime() - lastYellowBulletDropTime > 1000000000 && startRain){
        spawnYellow();
    }

    if(TimeUtils.nanoTime() - lastWhiteBulletDropTime > 2100000000 && startRain && startWhite){
        spawnWhite();
    }

    
    if(TimeUtils.nanoTime() - lastOmegaBulletDropTime > 2100000000 && startRain && startOmega){
        spawnOmega();
    }
       
       
    for(Iterator<GreenBullet> iter = greenBullets.iterator(); iter.hasNext();){
        GreenBullet greenBullet = iter.next();
        Rectangle rectangle = greenBullet.getRectangle();
        rectangle.y -= multiplier * Gdx.graphics.getDeltaTime();
        
        rect1 = new Rectangle(rectangle.y, rectangle.x, 20, 20);
        rect1.y = rectangle.y + 127;
        rect1.x = rectangle.x + 20;

        // shapeRenderer.rect(rectangle.x + 20,rectangle.y + 127, 20,20);
         if(rect1.overlaps(playerRectangle) && !immunity.isImmune()){
            iter.remove();
            immunity.giveImmunity();
            lives.lives--;
            music.gameSound[1].play();
            System.out.println("GREEN HIT");    
        }
    }
    
    for(Iterator<YellowBullet> iter = yellowBullets.iterator(); iter.hasNext();){
        YellowBullet yellowBullet = iter.next();
        Rectangle rectangle = yellowBullet.getRectangle();
        rectangle.y -= 200 * Gdx.graphics.getDeltaTime();

        rect2 = new Rectangle(rectangle.y, rectangle.x, 20, 20);
        rect2.y = rectangle.y + 127;
        rect2.x = rectangle.x + 20;


        if(rect2.overlaps(playerRectangle) && !immunity.isImmune()){
            iter.remove();
            immunity.giveImmunity();
            lives.lives--;
            music.gameSound[1].play();
            System.out.println("YELLOW HIT");
        } 
    }

    for(Iterator<WhiteBullet> iter = whiteBullets.iterator(); iter.hasNext();){
        WhiteBullet whiteBullet = iter.next();
        Rectangle rectangle = whiteBullet.getRectangle();
        rectangle.y -= 200 * Gdx.graphics.getDeltaTime();

        rect3 = new Rectangle(rectangle.y, rectangle.x, 20, 20);
        rect3.y = rectangle.y + 127;
        rect3.x = rectangle.x + 20;


        if(rect3.overlaps(playerRectangle) && !immunity.isImmune()){
            iter.remove();
            immunity.giveImmunity();
            lives.lives++;
            System.out.println("WHITE HIT");
        } 
    }

    
    for(Iterator<OmegaBullet> iter = omegaBullets.iterator(); iter.hasNext();){
        OmegaBullet omegaBullet = iter.next();
        Rectangle rectangle = omegaBullet.getRectangle();
        rectangle.y -= 100 * Gdx.graphics.getDeltaTime();

        rect4 = new Rectangle(rectangle.y, rectangle.x, 20, 20);
        rect4.y = rectangle.y + 127;
        rect4.x = rectangle.x + 20;


        if(rect4.overlaps(playerRectangle)){
            iter.remove();
            immunity.giveImmunity();   
            omegaBulletCollected++;
            System.out.println("OMEGA HIT");
        } 
    }
    
}

/**
 * spawn green colored rain
 */
private void spawnGreen(){
    GreenBullet green = new GreenBullet(greenBullet, MathUtils.random(0, 1024-64));
    greenBullets.add(green);
    lastGreenBulletDropTIme = TimeUtils.nanoTime();
}

/**
 * spawn white-blueish rain
 */
private void spawnWhite(){
    WhiteBullet white = new WhiteBullet(whiteBullet, MathUtils.random(0, 1024-64));
    whiteBullets.add(white);
    lastWhiteBulletDropTime = TimeUtils.nanoTime();
}

/**
 * spawn omega (superior) rain
 */
private void spawnOmega(){
    OmegaBullet omega = new OmegaBullet(omegaBullet, MathUtils.random(0, 1024-64));
    omegaBullets.add(omega);
    lastOmegaBulletDropTime = TimeUtils.nanoTime();
}

/**
 * spawn yellow rain
 */
private void spawnYellow(){
    YellowBullet yellow = new YellowBullet(yellowBullet, MathUtils.random(0,1024-64));
    yellowBullets.add(yellow);
    lastYellowBulletDropTime = TimeUtils.nanoTime();
}

/**
 * @param batch takes in sprite batch as a parameter and then rendering each droplets to screen
 */
public void draw(SpriteBatch batch){
    for(GreenBullet greenBullet: greenBullets){
        batch.draw(greenBullet.getTexture(), greenBullet.getRectangle().x, greenBullet.getRectangle().y + 100, 64, 64);
    }
    
    for(YellowBullet yellowBullet: yellowBullets){
        batch.draw(yellowBullet.getTexture(), yellowBullet.getRectangle().x, yellowBullet.getRectangle().y + 100, 64, 64);
    }

        for(WhiteBullet whiteBullet: whiteBullets){
            batch.draw(whiteBullet.getTexture(), whiteBullet.getRectangle().x, whiteBullet.getRectangle().y + 100, 64, 64);
            
}  for(OmegaBullet omegaBullet: omegaBullets){
    batch.draw(omegaBullet.getTexture(), omegaBullet.getRectangle().x, omegaBullet.getRectangle().y + 100, 64, 64);

}
}}

