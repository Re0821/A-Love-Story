package com.nana.gameFont;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LiveFont {

    public BitmapFont font;

    /**
     * get the default font that is used in this game
     */
    public LiveFont() {
        this.font = new BitmapFont(Gdx.files.internal("assets/gameFont.fnt"));
        font.setColor(Color.CYAN);
        font.getData().setScale(.5f, .5f);
    }

    /**
     * @param batch takes in SpriteBatch as a parameter to render to screen
     * @param lives takes in the total amount of lives that the player current have 
     */
    public void drawLiveFont(SpriteBatch batch, int lives){
        font.draw(batch, "Lives: " + lives, 25, 900);
    }

}

