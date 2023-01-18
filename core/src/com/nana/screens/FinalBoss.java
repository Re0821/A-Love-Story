package com.nana.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nana.characters.MonsterNPC;
import com.nana.characters.Player;
import com.nana.characters.SkeletonNPC;
import com.nana.game.Love;
import com.nana.gameFont.GeneralFont;
import com.nana.gameFont.LiveFont;
import com.nana.helper.BossBullet;
import com.nana.helper.BossRain;
import com.nana.helper.Immunity;
import com.nana.helper.Lives;
import com.nana.helper.PPM;
import com.nana.helper.Animations.FadeOutEffect;
import com.nana.helper.Animations.MonsterAnimation;
import com.nana.helper.Animations.PlayerAnimation;
import com.nana.helper.Animations.SkeletonAnimation;
import com.nana.helper.TiledMap.FinalTiledMapHelper;


public class FinalBoss implements Screen{
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Timer timer, timer1, timer2, timer3;
    public Rectangle playerRectangle;
    private Rectangle skeletonRectangle;
    private Rectangle monsterRectangle;
    private GeneralFont font;
    private BossRain bossFight;
    private OrthographicCamera camera;
    private FinalTiledMapHelper tiledMapHelper;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private PPM ppm = new PPM();
    public Player player;
    private SpriteBatch batch;
    private PlayerAnimation animation;
    private SkeletonAnimation skeletonAnimation;
    private MonsterAnimation monsterAnimation;
    private Stage stage;
    private BitmapFont myFont;
    private Death deathScreen;
    private SkeletonNPC skeleton;
    public MonsterNPC monster;
    private Immunity immunity = new Immunity();
    private final Love game;
    public Lives lives;
    public float absDistSkeletonX;
    public float absDistSkeletonY;
    private LiveFont liveFont;
    private FadeOutEffect fadeOut;
    private boolean startDrawingRain = false;
    private ArrayList<BossBullet> bullets;
    private boolean bulletTrigger = false;

    public FinalBoss(final Love game){        
        // setting the gravity of the game relative to real world's gravity
        this.deathScreen = new Death(game);
        this.game = game;
        batch = new SpriteBatch();
        this.bullets = new ArrayList<BossBullet>();
        bossFight = new BossRain(batch);
        this.fadeOut = new FadeOutEffect(game, deathScreen, .6f, batch);
        this.liveFont = new LiveFont();
        this.world = new World(new Vector2(0,-25f), false);
        this.camera = new OrthographicCamera();
        this.animation = new PlayerAnimation();
        this.skeletonAnimation = new SkeletonAnimation();
        this.monsterAnimation = new MonsterAnimation();
        this.stage = new Stage();
        this.lives = new Lives();
        this.timer = new Timer();
        this.timer1 = new Timer();
        this.timer2 = new Timer();
        this.timer3 = new Timer();
        renderer = new OrthogonalTiledMapRenderer(map);
        tiledMapHelper = new FinalTiledMapHelper(this);
        renderer = tiledMapHelper.setupMap();
        box2DDebugRenderer = new Box2DDebugRenderer();
        myFont = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
        Gdx.input.setInputProcessor(stage);
        stage = new Stage(new ScreenViewport());
        this.font = new GeneralFont(stage, myFont);
       

       
       
    }
        
    


    @Override
    public void show() {
        // TODO Auto-generated method stub
        batch = new SpriteBatch();
        startBossFight();
         
       
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
       
        liveFont.drawLiveFont(batch, bossFight.lives.lives);

        if(startDrawingRain){
        bossFight.rainInit(playerRectangle, startDrawingRain);
        bossFight.draw(batch);
        }

        if(bulletTrigger){
            addBullet();
            bulletTrigger = false;
        }

        updateBullet();
        
    for(BossBullet bullet: bullets){
        bullet.render(batch);
    }
        batch.draw(animation.createAnimation(), player.getBody().getPosition().x * ppm.getPPM() - 60, player.getBody().getPosition().y * ppm.getPPM() - 55, 100, 100);
        batch.draw(skeletonAnimation.createAnimation(), skeleton.getBody().getPosition().x * ppm.getPPM() - 60, skeleton.getBody().getPosition().y * ppm.getPPM() - 45  , 100, 100);
        // batch.draw(monsterAnimation.createAnimation(), monster.getBody().getPosition().x * ppm.getPPM() - 50, monster.getBody().getPosition().y * ppm.getPPM() - 50, 100, 100);
        myFont.getData().setScale(.5f, .5f);

        if(bossFight.immunity.isImmune() == true || immunity.isImmune()){
            myFont.getData().setScale(.5f, .5f);
            myFont.draw(batch, "Currently Immune", 650, 900);
        }
        
    

        if(player.body.getPosition().x > 29.951666){

            player.body.setTransform(2.618358f, 10.514998f, 0f);
    
        }
                
        
        batch.end();


        
        if(bossFight.lives.lives <= 0){
            player.speed = 0f;
            fadeOut.start();
            fadeOut.render(delta);
            
            if(fadeOut.finished){
                game.setScreen(deathScreen);
            }
        }

        
       

        stage.act(Gdx.graphics.getDeltaTime());

        box2DDebugRenderer.render(world, camera.combined.scl(ppm.getPPM()));
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
            bossFight.lives.lives--;
            animation.deathAnimation = true;
            immunity.giveImmunity();
            

        } else {
            lives.hurt = false;
        }
    }

    
    public void addBullet(){
            bullets.add(new BossBullet(monster.body.getPosition().x + 675, monster.body.getPosition().y + 450));
    }

    public void updateBullet(){
        ArrayList<BossBullet> leftBulletsToRemove = new ArrayList<BossBullet>();
		for (BossBullet bullet : bullets) {
           
			
            //x += SPEED * deltaTime;
            Rectangle rect = new Rectangle(bullet.x, bullet.y, 200, 50);
            bullet.update(Gdx.graphics.getDeltaTime() * - 1, rect, playerRectangle);
        
       
			if (bullet.remove){
				leftBulletsToRemove.add(bullet);
            }

            if(bullet.hurtBullet){
                bossFight.lives.lives = bossFight.lives.lives - 2;
            }


           

      
		}
        bullets.removeAll(leftBulletsToRemove);
    }
    public void startBossFight(){
        if (timer.isEmpty()) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    font.createAndSetTypingLabel("If you want to pass through here, \nyou have to go through me first.");
                    
                    
                }
            }, 4);

           
        }

        timer1.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                font.stage.clear();
                timer.stop();
                startDrawingRain = true;
                // TODO Auto-generated method stub
                
            }
            
        }, 10);

       

        timer2.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                timer1.stop();
                startDrawingRain = false;
                
            
                // TODO Auto-generated method stub
                
            }
            
        }, 40, 5);

        timer3.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                timer2.stop();
                bulletTrigger = true;
            
                // TODO Auto-generated method stub
                
            }
            
        }, 20, 5);
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
