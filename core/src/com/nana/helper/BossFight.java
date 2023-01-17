package com.nana.helper;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.nana.characters.MonsterNPC;
import com.nana.characters.Player;
import com.nana.characters.SkeletonNPC;
import com.nana.helper.DroppingAssets.GreenBullet;
import com.nana.helper.DroppingAssets.YellowBullet;
import com.nana.screens.FinalBoss;

public class BossFight {
    private ArrayList<GreenBullet> greenBullets;
    private ArrayList<YellowBullet> yellowBullets;
    private Texture greenBullet, yellowBullet;
    private long lastGreenBulletDropTIme, lastYellowBulletDropTime;
    private FinalBoss finalBoss;
    private PPM ppm;
    public Immunity immunity;
    public Lives lives;
    private Player player;
    private SkeletonNPC skeleton;
    private MonsterNPC monster;
    private Rectangle playerRectangle;
    public Rectangle rect1;
    public  Rectangle rect2;
    public Timer timer1, timer2;
    public SpriteBatch batch;

    public BossFight(SpriteBatch batch){
        this.batch = new SpriteBatch();
        this.greenBullet = new Texture(Gdx.files.internal("assets/GreenBullet.png"));
        this.yellowBullet = new Texture(Gdx.files.internal("assets/YellowBullet.png"));
        greenBullets = new ArrayList<GreenBullet>();
        yellowBullets = new ArrayList<YellowBullet>();
        this.immunity = new Immunity();
        this.lives = new Lives();
        this.ppm = new PPM();
    }


public void rainInit(SpriteBatch batch, Rectangle playerRectangle){
    
    for(GreenBullet greenBullet: greenBullets){
        batch.draw(greenBullet.getTexture(), greenBullet.getRectangle().x, greenBullet.getRectangle().y + 100, 64, 64);
    }
    
    for(YellowBullet yellowBullet: yellowBullets){
        batch.draw(yellowBullet.getTexture(), yellowBullet.getRectangle().x, yellowBullet.getRectangle().y + 100, 64, 64);
}

    if(TimeUtils.nanoTime() - lastGreenBulletDropTIme > 1000000000){
        spawnGreen();
    }

    if(TimeUtils.nanoTime() - lastYellowBulletDropTime > 2000000000){
        spawnYellow();
    }
       
    for(Iterator<GreenBullet> iter = greenBullets.iterator(); iter.hasNext();){
        GreenBullet greenBullet = iter.next();
        Rectangle rectangle = greenBullet.getRectangle();
        rectangle.y -= 200 * Gdx.graphics.getDeltaTime();
        
        rect1 = new Rectangle(rectangle.y, rectangle.x, 20, 20);
        rect1.y = rectangle.y + 127;
        rect1.x = rectangle.x + 20;

        // shapeRenderer.rect(rectangle.x + 20,rectangle.y + 127, 20,20);
         if(rect1.overlaps(playerRectangle) && !immunity.isImmune()){
            iter.remove();
            immunity.giveImmunity();
            lives.lives--;
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
            System.out.println("YELLOW HIT");
        } 
    }
    
}

private void spawnGreen(){
    GreenBullet green = new GreenBullet(greenBullet, MathUtils.random(0, 1024-64));
    greenBullets.add(green);
    lastGreenBulletDropTIme = TimeUtils.nanoTime();
}

private void spawnYellow(){
    YellowBullet yellow = new YellowBullet(yellowBullet, MathUtils.random(0,1024-64));
    yellowBullets.add(yellow);
    lastYellowBulletDropTime = TimeUtils.nanoTime();
}

}
