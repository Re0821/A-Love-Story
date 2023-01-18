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
import com.nana.helper.DroppingAssets.WhiteBullet;
import com.nana.helper.DroppingAssets.YellowBullet;
import com.nana.screens.FinalBoss;

public class BossRain {
    private ArrayList<GreenBullet> greenBullets;
    private ArrayList<YellowBullet> yellowBullets;
    private ArrayList<WhiteBullet> whiteBullets;
    private Texture greenBullet, yellowBullet, whiteBullet;
    private long lastGreenBulletDropTIme, lastYellowBulletDropTime, lastWhiteBulletDropTime;
    public Immunity immunity;
    public Lives lives;
    public Rectangle playerRectangle;
    public Rectangle rect1;
    public  Rectangle rect2;
    public Rectangle rect3;
    public Timer timer1, timer2;
    public SpriteBatch batch;
    public int mulitplier;
    private int multiplier;
    public boolean startWhite = false;

    public BossRain(SpriteBatch batch, int multiplier){
        this.batch = new SpriteBatch();
        this.multiplier = multiplier;
        this.greenBullet = new Texture(Gdx.files.internal("assets/GreenBullet.png"));
        this.yellowBullet = new Texture(Gdx.files.internal("assets/YellowBullet.png"));
        this.whiteBullet = new Texture(Gdx.files.internal("assets/WhiteBullet.png"));
        greenBullets = new ArrayList<GreenBullet>();
        yellowBullets = new ArrayList<YellowBullet>();
        whiteBullets = new ArrayList<WhiteBullet>();
        this.immunity = new Immunity();
        this.lives = new Lives();
    }


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
    
}

private void spawnGreen(){
    GreenBullet green = new GreenBullet(greenBullet, MathUtils.random(0, 1024-64));
    greenBullets.add(green);
    lastGreenBulletDropTIme = TimeUtils.nanoTime();
}

private void spawnWhite(){
    WhiteBullet white = new WhiteBullet(whiteBullet, MathUtils.random(0, 1024-64));
    whiteBullets.add(white);
    lastWhiteBulletDropTime = TimeUtils.nanoTime();
}


private void spawnYellow(){
    YellowBullet yellow = new YellowBullet(yellowBullet, MathUtils.random(0,1024-64));
    yellowBullets.add(yellow);
    lastYellowBulletDropTime = TimeUtils.nanoTime();
}

public void draw(SpriteBatch batch){
    for(GreenBullet greenBullet: greenBullets){
        batch.draw(greenBullet.getTexture(), greenBullet.getRectangle().x, greenBullet.getRectangle().y + 100, 64, 64);
    }
    
    for(YellowBullet yellowBullet: yellowBullets){
        batch.draw(yellowBullet.getTexture(), yellowBullet.getRectangle().x, yellowBullet.getRectangle().y + 100, 64, 64);

        for(WhiteBullet whiteBullet: whiteBullets){
            batch.draw(whiteBullet.getTexture(), whiteBullet.getRectangle().x, whiteBullet.getRectangle().y + 100, 64, 64);
}
}
}
}
