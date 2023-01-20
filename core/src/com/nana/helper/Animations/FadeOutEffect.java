package com.nana.helper.Animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nana.game.Love;

public class FadeOutEffect {
    public float alpha = 0;
    private Love game;
    private Screen nextScreen;
    private Texture blackTexture;
    public float duration;
    private boolean fading = false;
    private SpriteBatch batch;
    public boolean finished = false;
    public boolean fadeOut;

    /**
     * initializes variables in constructor
     * @param game takes in the current game screen
     * @param nextScreen takes in the next game screen 
     * @param duration takes in the duration that it wants to fade
     * @param batch takes in the spritebatch to render the screen
     */
    public FadeOutEffect(Love game, Screen nextScreen, float duration, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;
        this.nextScreen = nextScreen;
        this.duration = duration;
        blackTexture = new Texture(Gdx.files.internal("assets/black.jpg"));
    }
    /**
     * start the fading by setting fading to true
     */
    public void start() {
        fading = true;
    }

    /**
     * starts rendering the screen based on the screen's delta time
     * @param delta takes in the current screen's delta time
     */
    public void render(float delta) {
        if (fading) {
            alpha += delta / duration;
            if (alpha >= 1) {
                finished = true;
                fading = false;
                fadeOut = true;
            }

        }

        batch.begin();
        batch.setColor(1, 1, 1, alpha);
        batch.draw(blackTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }
    }

    


