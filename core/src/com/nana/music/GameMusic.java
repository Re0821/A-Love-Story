package com.nana.music;

import java.util.Random;
import com.badlogic.gdx.Gdx;

public class GameMusic {
     public com.badlogic.gdx.audio.Music backgroundMusic[];
     public com.badlogic.gdx.audio.Sound gameSound[];
     private static GameMusic instance = null;

     public int track;
     public Random random = new Random();

     /**
     * initializes the singleton pattern 
     * @return the current class that it is running from
     */
    public static GameMusic getInstance() {
      if (instance == null) {
          instance = new GameMusic();
      }
      return instance;
  }


     /**
      *  initialize the music and the sounds of the game
      */

   public GameMusic(){
        backgroundMusic = new com.badlogic.gdx.audio.Music[7];
        backgroundMusic[0] = Gdx.audio.newMusic(Gdx.files.internal("assets/music/main.mp3"));
        backgroundMusic[1] = Gdx.audio.newMusic(Gdx.files.internal(("assets/music/music1.mp3")));
        backgroundMusic[2] = Gdx.audio.newMusic(Gdx.files.internal("assets/music/music2.mp3"));
        backgroundMusic[3] = Gdx.audio.newMusic(Gdx.files.internal("assets/music/music3.mp3"));
        backgroundMusic[4] = Gdx.audio.newMusic(Gdx.files.internal("assets/music/music4.mp3"));
        backgroundMusic[5] = Gdx.audio.newMusic(Gdx.files.internal("assets/music/music5.mp3"));
        backgroundMusic[6] = Gdx.audio.newMusic(Gdx.files.internal("assets/music/music6.mp3"));

        gameSound = new com.badlogic.gdx.audio.Sound[3];
        gameSound[0] = Gdx.audio.newSound(Gdx.files.internal("assets/music/jump.mp3"));
        gameSound[1] = Gdx.audio.newSound(Gdx.files.internal("assets/music/hurt.mp3"));
        gameSound[2] = Gdx.audio.newSound(Gdx.files.internal("assets/music/Small Bomb Explosion Sound Effect.mp3"));
     }
    

   /**
    * shuffles and play background musics
    */
   public void shufflePlayTrack(){
        
         
         backgroundMusic[getTrack()].play();


     }

   /**
    * stops the current track
    */

   public void stopTrack(){
      for(int i=0;i<7;i++){
         backgroundMusic[i].stop();
      }
   }
   
   /**
    * play the final boss background music
    */
     public void playFinalBossTrack(){
         stopTrack();
         backgroundMusic[4].setLooping(true);
         backgroundMusic[4].play();
       
     }

   /**
    * play the winning track
    */
   public void playWinTrack(){
      stopTrack();
         backgroundMusic[6].setLooping(true);
         backgroundMusic[6].play();
     }

   /**
    * play menu track
    */
   public void playMenuTrack(){

      backgroundMusic[0].setLooping(true);
      backgroundMusic[0].play();

   }

   /**
    * returns a random track to be used for shuffling
    * @return a random track
    */
   public int getTrack(){
      int track = random.nextInt(4);
      if(track == 0){
         track = random.nextInt(4);
      }
      return track;
   }

   /**
    * returns the current track
    * @return track #
    */
   public int getTrackNum(){
      return track;
   }
}
