package com.nana.gameFont;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LiveFont {

    public BitmapFont font;

    public LiveFont() {
        this.font = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
        font.setColor(Color.CYAN);
        font.getData().setScale(.5f, .5f);
    }

    public void drawLiveFont(SpriteBatch batch, int lives){
        font.draw(batch, "Lives: " + lives, 25, 900);
    }

}

