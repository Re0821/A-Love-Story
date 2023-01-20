package com.nana.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nana.screens.MainMenuScreen;


public class Love extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 * creating the parent game
	 */
	@Override
	public void create() {
		batch = new SpriteBatch();
	
		this.setScreen(new MainMenuScreen(this));
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Game#render()
	 * rendering to screen
	 */
	@Override
	public void render () {
		super.render();



	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Game#dispose()
	 * disposing of batch
	 */
	@Override
	public void dispose () {
		batch.dispose();

	}
}
