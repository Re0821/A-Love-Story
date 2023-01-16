package com.nana.helper.Animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nana.game.Love;

public class FadeOutEffect {
    private float alpha = 0;
    private Love game;
    private Screen nextScreen;
    private Texture blackTexture;
    private float duration;
    private boolean fading = false;
    private SpriteBatch batch;
    public boolean finished = false;

    public FadeOutEffect(Love game, Screen nextScreen, float duration, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;
        this.nextScreen = nextScreen;
        this.duration = duration;
        blackTexture = new Texture(Gdx.files.internal("assets/black.jpg"));
    }
    public void start() {
        fading = true;
    }
    public void render(float delta) {
        if (fading) {
            alpha += delta / duration;
            if (alpha >= 1) {
                finished = true;
                fading = false;
            }
        }

        batch.begin();
        batch.setColor(1, 1, 1, alpha);
        batch.draw(blackTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }
}

