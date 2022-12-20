package com.nana.music;

import com.badlogic.gdx.Gdx;

public class GameMusic {
     public com.badlogic.gdx.audio.Music backgroundMusic[];
     public com.badlogic.gdx.audio.Sound gameSound[];

     public GameMusic(){
        backgroundMusic = new com.badlogic.gdx.audio.Music[5];
        backgroundMusic[0] = Gdx.audio.newMusic(Gdx.files.internal("assets/music/main.mp3"));

        gameSound = new com.badlogic.gdx.audio.Sound[2];
        gameSound[0] = Gdx.audio.newSound(Gdx.files.internal("assets/music/jump.mp3"));
     }
    

}
