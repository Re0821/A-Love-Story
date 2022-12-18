package com.nana.music;

import com.badlogic.gdx.Gdx;

public class GameMusic {
     public com.badlogic.gdx.audio.Music backgroundMusic[];

     public GameMusic(){
        backgroundMusic = new com.badlogic.gdx.audio.Music[5];
        backgroundMusic[0] = Gdx.audio.newMusic(Gdx.files.internal("assets/music/main.mp3"));
     }
    

}
