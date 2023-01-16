package com.nana.screens;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nana.characters.MonsterNPC;
import com.nana.characters.Player;
import com.nana.characters.SkeletonNPC;
import com.nana.game.Love;
import com.nana.gameFont.LiveFont;
import com.nana.helper.Immunity;
import com.nana.helper.Lives;
import com.nana.helper.PPM;
import com.nana.helper.Animations.FadeOutEffect;
import com.nana.helper.Animations.MonsterAnimation;
import com.nana.helper.Animations.PlayerAnimation;
import com.nana.helper.Animations.SkeletonAnimation;
import com.nana.helper.DroppingAssets.GreenBullet;
import com.nana.helper.DroppingAssets.YellowBullet;
import com.nana.helper.TiledMap.FinalTiledMapHelper;


public class FinalBoss implements Screen{
    private TiledMap map;
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    private OrthogonalTiledMapRenderer renderer;
    private ArrayList<GreenBullet> greenBullets;
    private ArrayList<YellowBullet> yellowBullets;
    private Rectangle playerRectangle, skeletonRectangle, monsterRectangle;
    private OrthographicCamera camera;
    private FinalTiledMapHelper tiledMapHelper;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private PPM ppm = new PPM();
    private Player player;
    private SpriteBatch batch;
    private PlayerAnimation animation;
    private SkeletonAnimation skeletonAnimation;
    private MonsterAnimation monsterAnimation;
    private Stage stage;
    private BitmapFont myFont;
    private Death deathScreen;
    private SkeletonNPC skeleton;
    private MonsterNPC monster;
    private Immunity immunity = new Immunity();
    private final Love game;
    private Lives lives;
    public float absDistSkeletonX;
    public float absDistSkeletonY;
    private LiveFont liveFont;
    private FadeOutEffect fadeOut;
    private Texture greenBullet, yellowBullet;
    private long lastGreenBulletDropTIme, lastYellowBulletDropTime;
    
    public FinalBoss(final Love game){        
        // setting the gravity of the game relative to real world's gravity
        this.deathScreen = new Death(game);
        this.game = game;
        this.greenBullet = new Texture(Gdx.files.internal("assets/GreenBullet.png"));
        this.yellowBullet = new Texture(Gdx.files.internal("assets/YellowBullet.png"));
        greenBullets = new ArrayList<GreenBullet>();
        yellowBullets = new ArrayList<YellowBullet>();
        batch = new SpriteBatch();
        this.fadeOut = new FadeOutEffect(game, deathScreen, .6f, batch);
        this.liveFont = new LiveFont();
        this.world = new World(new Vector2(0,-25f), false);
        this.camera = new OrthographicCamera();
        this.animation = new PlayerAnimation();
        this.skeletonAnimation = new SkeletonAnimation();
        this.monsterAnimation = new MonsterAnimation();
        this.stage = new Stage();
        this.lives = new Lives();
        renderer = new OrthogonalTiledMapRenderer(map);
        tiledMapHelper = new FinalTiledMapHelper(this);
        renderer = tiledMapHelper.setupMap();
        box2DDebugRenderer = new Box2DDebugRenderer();
        myFont = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
        Gdx.input.setInputProcessor(stage);
        stage = new Stage(new ScreenViewport());
       

        
    }
    @Override
    public void show() {
        // TODO Auto-generated method stub
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        this.update();
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();
        stage.draw();
        updateRectangle();
        checkHurt();
        batch.begin();
        liveFont.drawLiveFont(batch, lives.lives);
        

        batch.draw(animation.createAnimation(), player.getBody().getPosition().x * ppm.getPPM() - 60, player.getBody().getPosition().y * ppm.getPPM() - 55, 100, 100);
        batch.draw(skeletonAnimation.createAnimation(), skeleton.getBody().getPosition().x * ppm.getPPM() - 60, skeleton.getBody().getPosition().y * ppm.getPPM() - 45  , 100, 100);
        batch.draw(monsterAnimation.createAnimation(), monster.getBody().getPosition().x * ppm.getPPM() - 250, monster.getBody().getPosition().y * ppm.getPPM() - 200, 500, 500);

        if(immunity.isImmune() == true){
            myFont.getData().setScale(.5f, .5f);
            myFont.draw(batch, "Currently Immune", 650, 900);
        }

        for(GreenBullet greenBullet: greenBullets){
			batch.draw(greenBullet.getTexture(), greenBullet.getRectangle().x, greenBullet.getRectangle().y + 100, 64, 64);
		}
		
		for(YellowBullet yellowBullet: yellowBullets){
			batch.draw(yellowBullet.getTexture(), yellowBullet.getRectangle().x, yellowBullet.getRectangle().y + 100, 64, 64);
	}
        batch.end();

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
            Rectangle rect = new Rectangle(rectangle.y, rectangle.x, 20, 20);
            rect.y = rectangle.y + 127;
            rect.x = rectangle.x + 20;

            // shapeRenderer.rect(rectangle.x + 20,rectangle.y + 127, 20,20);
			 if(rect.overlaps(playerRectangle) && !immunity.isImmune()){
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

            Rectangle rect = new Rectangle(rectangle.y, rectangle.x, 20, 20);
            rect.y = rectangle.y + 127;
            rect.x = rectangle.x + 20;


			if(rect.overlaps(playerRectangle) && !immunity.isImmune()){
                iter.remove();
                immunity.giveImmunity();
                lives.lives--;
                System.out.println("YELLOW HIT");
            } 
		}

        fadeOut.render(delta);


        if(lives.lives <= 0){
            player.velX = 0;
            fadeOut.start();
            
            if(fadeOut.finished == true){
                game.setScreen(deathScreen);
            }
        }
       

        stage.act(Gdx.graphics.getDeltaTime());

        box2DDebugRenderer.render(world, camera.combined.scl(ppm.getPPM()));
        shapeRenderer.end();
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
    

    public void update(){
        world.step(1/60f, 6, 2);
        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);
        player.update();
        skeleton.update();
       
        
    }

    public void updateRectangle(){
        playerRectangle = new Rectangle(player.getBody().getPosition().x * ppm.getPPM() - 22 , player.getBody().getPosition().y * ppm.getPPM() -15 , 30, 30);
        skeletonRectangle = new Rectangle(skeleton.getBody().getPosition().x * ppm.getPPM()- 22, skeleton.getBody().getPosition().y * ppm.getPPM() - 15, 40, 40);
        monsterRectangle = new Rectangle(monster.getBody().getPosition().x * ppm.getPPM() - 250, monster.getBody().getPosition().y * ppm.getPPM() - 200, 500, 500);
    }

    public void cameraUpdate(){
        Vector3 position = camera.position;
        position.x = 513;
        position.y = 460;
        camera.position.set(position);

        camera.viewportWidth = Gdx.graphics.getWidth();
        camera.viewportHeight = Gdx.graphics.getHeight();
        camera.update();
    }

    public void checkHurt(){
        if(playerRectangle.overlaps(skeletonRectangle) && !immunity.isImmune()){
            lives.lives--;
            System.out.println("hurt");
            animation.deathAnimation = true;
            immunity.giveImmunity();
            

        } else {
            lives.hurt = false;
        }
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose() {
     

        // TODO Auto-generated method stub
      
    }
    public TiledMap getMap() {
        return map;
    }
    public void setMap(TiledMap map) {
        this.map = map;
    }
    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }
    public void setRenderer(OrthogonalTiledMapRenderer renderer) {
        this.renderer = renderer;
    }
    public OrthographicCamera getCamera() {
        return camera;
    }
    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public World getWorld() {
        return world;
    }
    public void setWorld(World world) {
        this.world = world;
    }
    public Box2DDebugRenderer getBox2DDebugRenderer() {
        return box2DDebugRenderer;
    }
    public void setBox2DDebugRenderer(Box2DDebugRenderer box2dDebugRenderer) {
        box2DDebugRenderer = box2dDebugRenderer;
    }
    public PPM getPpm() {
        return ppm;
    }
    public void setPpm(PPM ppm) {
        this.ppm = ppm;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player2) {
        this.player = player2;
    }
    
    public void setSkeleton(SkeletonNPC skeleton){
        this.skeleton = skeleton;
    }

    public void setMonster(MonsterNPC monster){
        this.monster = monster;
    }
}
