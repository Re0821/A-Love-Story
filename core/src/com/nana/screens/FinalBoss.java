package com.nana.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
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
import com.nana.music.GameMusic;


public class FinalBoss implements Screen{
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Timer timer, timer1, timer2, timer3, timer4, timer5, finalTimer, textChangeTimer;
    public Rectangle playerRectangle;
    private Rectangle skeletonRectangle;
    private Rectangle monsterRectangle;
    private GeneralFont font;
    private BossRain bossFight;
    private OrthographicCamera camera;
    private FinalTiledMapHelper tiledMapHelper;
    private World world;
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
    private boolean finalBulletTrigger = false;
    private boolean bossFightFinished = false;
    private boolean finalTextTrigger, firstTextTrigger, finalSubTextTrigger, finalChangeSubText, bossHurtAllowed, bulletDrawTrigger, playerHurtAllowed, switchScreen = false;
    private boolean bossHurt = false;
    GameMusic music = GameMusic.getInstance();
  /**
     * @param game takes in the parent game as an argument for switching screens purposes
     * initializing variables from necessary classes needed 
     */

    public FinalBoss(final Love game){   
        // setting the gravity of the game relative to real world's gravity
       
        this.deathScreen = new Death(game);
        this.game = game;
        batch = new SpriteBatch();
        this.bullets = new ArrayList<BossBullet>();
        this.bossFight = new BossRain(batch, 200);
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
        this.timer4 = new Timer();
        this.timer5 = new Timer();
        this.finalTimer = new Timer();
        this.textChangeTimer = new Timer();
        renderer = new OrthogonalTiledMapRenderer(map);
        tiledMapHelper = new FinalTiledMapHelper(this);
        renderer = tiledMapHelper.setupMap();
        myFont = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
        Gdx.input.setInputProcessor(stage);
        stage = new Stage(new ScreenViewport());
        this.font = new GeneralFont(stage, myFont);
       
    
       

       
       
    }
        
    
/* (non-Javadoc)
     * @see com.badlogic.gdx.Screen#show()
     * calls startBossFight to initialize the boss fight
     */

    @Override
    public void show() {
        // TODO Auto-generated method stub
        batch = new SpriteBatch();
        startBossFight();

            
       
    }

     /* (non-Javadoc)
     * @see com.badlogic.gdx.Screen#render(float)
     * @param takes in the current deltaTime of the screen as a parameter
     * render and draw sprite (picture) elements needed for the screen; as well as performing as performing logic tasks behind the scene
     */

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

       
        returnlevel();
        
       
        liveFont.drawLiveFont(batch, bossFight.lives.lives);

        if(startDrawingRain){
        bossFight.rainInit(playerRectangle, startDrawingRain);
        bossFight.draw(batch);
        }

        if(bulletTrigger){
            addBullet();
            bulletTrigger = false;
        }

        if(bulletDrawTrigger){
            updateBullet();
                  
        for(BossBullet bullet: bullets){
            bullet.render(batch);
        }
        }

        
        if(finalBulletTrigger){
            addOmegaBullet();
            updateFinalBullet();
                  
            for(BossBullet bullet: bullets){
                bullet.renderOmegaBullet(batch);
            }
           
        }
        
    for(BossBullet bullet: bullets){
        bullet.render(batch);
    }

        batch.draw(animation.createAnimation(), player.getBody().getPosition().x * ppm.getPPM() - 60, player.getBody().getPosition().y * ppm.getPPM() - 55, 100, 100);
        batch.draw(skeletonAnimation.createAnimation(), skeleton.getBody().getPosition().x * ppm.getPPM() - 60, skeleton.getBody().getPosition().y * ppm.getPPM() - 45  , 100, 100);
        
        if(!bossHurt){
           batch.draw(monsterAnimation.createAnimation(), monster.getBody().getPosition().x * ppm.getPPM() - 50, monster.getBody().getPosition().y * ppm.getPPM() - 35, 100, 100);
        }
       
        if(bossHurt){
            
                finalTimer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                      
                        switchScreen = true;
                        
                    }
                }, 7);    
        }

        timerText();

        myFont.getData().setScale(.5f, .5f);

        if(switchScreen){
            game.setScreen(new Win(game));
        }
        
        if(bossFight.immunity.isImmune() == true || immunity.isImmune()){
            myFont.getData().setScale(.5f, .5f);
            myFont.draw(batch, "Currently Immune", 650, 900);
        }
        
        
        if(!bossFightFinished){
            if(player.body.getPosition().x > 29.951666){

                player.body.setTransform(2.618358f, 10.514998f, 0f);
        
            }
        }
        

       
        if(bossFight.omegaBulletCollected == 10){
            bossHurtAllowed = true;
            playerHurtAllowed = true;
            finalChangeSubText = false;
            bossHurtAllowed = true;
            startDrawingRain = false;
            bulletTrigger = false;
            bulletDrawTrigger = false;
            finalBulletTrigger = true;
        }
        
       
        checkDeath();
        
        batch.end();


      
        stage.act(Gdx.graphics.getDeltaTime());

    }
    /**
     * check if player has died
     * if true; fade the screen and switch to death screen
     */
    public void checkDeath(){
          
        if(bossFight.lives.lives <= 0){
            player.speed = 0f;
            fadeOut.start();
            fadeOut.render(Gdx.graphics.getDeltaTime());
            
            if(fadeOut.finished){
                game.setScreen(deathScreen);
            }
        }
    }

    /**
     * create and set the label onto the screen whenever the variable is set to true
     */
    public void timerText(){
        if(firstTextTrigger){
            font.createAndSetTypingLabel("If you want to pass through here, \nyou have to go through me first.");
        }

        if(finalTextTrigger){
            font.createAndSetTypingLabel("{EASE}{COLOR=SCARLET}{NORMAL}Tired already?");
        }

        if(finalSubTextTrigger){
            font.createAndSetTypingLabel2("{EASE}{NORMAL}Blue bullets restores 1 live");
        }

        if(finalChangeSubText){
            font.createAndSetTypingLabel2("{EASE}{NORMAL}Collect 10 omega bullets to defeat the boss");
            myFont.draw(batch, "Omega Bullets: " + bossFight.omegaBulletCollected, 25 + 200, 900);
        }

    }

    /**
     * updates the camera, world, batch, player, skeleton objects 
     */

    public void update(){
        world.step(1/60f, 6, 2);
        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);
        player.update();
        skeleton.update();
    }

    /**
     * updates the player, skeleton and monster (boss) hitboxes (rectangles)
     */
    public void updateRectangle(){
        playerRectangle = new Rectangle(player.getBody().getPosition().x * ppm.getPPM() - 22 , player.getBody().getPosition().y * ppm.getPPM() -15 , 30, 30);
        skeletonRectangle = new Rectangle(skeleton.getBody().getPosition().x * ppm.getPPM()- 22, skeleton.getBody().getPosition().y * ppm.getPPM() - 15, 40, 40);
        monsterRectangle = new Rectangle(monster.getBody().getPosition().x * ppm.getPPM() - 250, monster.getBody().getPosition().y * ppm.getPPM() - 200, 500, 500);
    }

    /**
     * updates the camera position
     */
    public void cameraUpdate(){
        Vector3 position = camera.position;
        position.x = 513;
        position.y = 460;
        camera.position.set(position);

        camera.viewportWidth = Gdx.graphics.getWidth();
        camera.viewportHeight = Gdx.graphics.getHeight();
        camera.update();
    }

    /**
     * check if the player has overlapped with a skeleton and is not immune
     * if true, take away 1 live and give immunity for 3 seconds
     */
    public void checkHurt(){
        if(playerRectangle.overlaps(skeletonRectangle) && !immunity.isImmune()){
            bossFight.lives.lives--;
            animation.deathAnimation = true;
            immunity.giveImmunity();
            music.gameSound[1].play();
            

        } else {
            lives.hurt = false;
        }
    }

    
    /**
     * add a normal bullet when called
     */
    public void addBullet(){
            bullets.add(new BossBullet(monster.body.getPosition().x + 750, monster.body.getPosition().y + 450));
    }

    /**
     * add the player bullet when called
     */
    public void addOmegaBullet(){
        bullets.add(new BossBullet(monster.body.getPosition().x - 600, monster.body.getPosition().y - 450));
}

    /**
     * updates the normal bullet at 60 fps and create hitbox for them
     */
    public void updateBullet(){
        ArrayList<BossBullet> leftBulletsToRemove = new ArrayList<BossBullet>();
		for (BossBullet bullet : bullets) {
           
			
            //x += SPEED * deltaTime;
            Rectangle rect = new Rectangle(bullet.x, bullet.y + 50, 200, 50);
            Rectangle rect2 = new Rectangle(bullet.x, bullet.y + 50, 200, 50);
            bullet.update(Gdx.graphics.getDeltaTime() * - 1, rect, playerRectangle, rect2, monsterRectangle, bossHurtAllowed, playerHurtAllowed);
        
       
			if (bullet.remove){
				leftBulletsToRemove.add(bullet);
            }

            if(bullet.hurtBullet){
                bossFight.lives.lives = bossFight.lives.lives - 2;
            }

            if(bullet.bossHurt){
                bossHurt = true;
            }
		}
        bullets.removeAll(leftBulletsToRemove);
    }
 /**
     * updates the omega bullet at 60 fps and create hitbox for them
     */
    public void updateFinalBullet(){
        ArrayList<BossBullet> bulletsToRemove = new ArrayList<BossBullet>();
		for (BossBullet bullet : bullets) {
           
			
            //x += SPEED * deltaTime;
            Rectangle rect = new Rectangle(bullet.x, bullet.y + 50, 200, 50);
            Rectangle rect2 = new Rectangle(bullet.x - 100, bullet.y + 50, 200, 50);
            bullet.update(Gdx.graphics.getDeltaTime(), rect, playerRectangle, rect2, monsterRectangle, bossHurtAllowed, playerHurtAllowed);
        
       
			if (bullet.remove){
				bulletsToRemove.add(bullet);
            }

            if(bullet.hurtBullet){
                bossFight.lives.lives = bossFight.lives.lives - 2;
            }

            if(bullet.bossHurt){
                bossHurt = true;
            }
		}
        bullets.removeAll(bulletsToRemove);
    }
    /**
     * starts the boss fight that is running on a timer
     */
    public void startBossFight(){
        if (timer.isEmpty()) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    firstTextTrigger = true;
                    
                    
                }
            }, 4);

           
        }

        timer1.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                font.stage.clear();
                timer.stop();
                firstTextTrigger = false;
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
            
        }, 40);

        timer3.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                timer2.stop();
                bulletTrigger = true;
                bulletDrawTrigger = true;
            
                // TODO Auto-generated method stub
                
            }
            
        }, 45, 5);

        timer4.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                timer3.stop();
                bulletTrigger = false;
                bulletDrawTrigger = false;
                finalTextTrigger = true;
                finalSubTextTrigger = true;
                startDrawingRain = true;
                bossFight.mulitplier = 500;
                bossFight.startWhite = true;
                
            
                // TODO Auto-generated method stub
                
            }
            
        }, 60);

        textChangeTimer.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                finalTextTrigger = false;
                font.stage.clear();
                finalSubTextTrigger = false;
                
                finalChangeSubText = true;
                
                // TODO Auto-generated method stub
                
            }
            
        }, 67);

        timer5.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                textChangeTimer.stop();

                
             
                bulletTrigger = true;
                bulletDrawTrigger = true;
        
                bossFight.startOmega = true;
                // TODO Auto-generated method stub
                
            }
            
        }, 77, 5);

       
    }

        
    


    /**
     * return player to original spawn location if trying to access out of bounds areas
     */
    public void returnlevel(){
        if(player.body.getPosition().x <= 0.23333333f){
              player.body.setTransform(1.7666667f, 10.514998f, 0f);
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
    
    public PPM getPpm() {
        return ppm;
    }
    public void setPpm(PPM ppm) {
        this.ppm = ppm;
    }
    public Player getPlayer() {
        return player;
    }
    /**
     * @param player2 takes in a player object
     * set player into world
     */
    public void setPlayer(Player player2) {
        this.player = player2;
    }
    
    /**
     * @param skeleton takes in a skeletonNPC object
     * set player into world
     */
    public void setSkeleton(SkeletonNPC skeleton){
        this.skeleton = skeleton;
    }

    /**
     * @param monster takes in a monsterNPC object
     * set monster into world
     */
    public void setMonster(MonsterNPC monster){
        this.monster = monster;
    }
}
