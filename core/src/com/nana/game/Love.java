package com.nana.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nana.screens.MainMenuScreen;


public class Love extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	
	/* (non-Javadoc)
	 * creating the parent game
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 */
	@Override
	public void create() {
		batch = new SpriteBatch();
	
		this.setScreen(new MainMenuScreen(this));
	}

	/* (non-Javadoc)
	 * rendering to screen
	 * @see com.badlogic.gdx.Game#render()
	 */
	@Override
	public void render () {
		super.render();



	}
	
	/* (non-Javadoc)
	 * disposing of batch
	 * @see com.badlogic.gdx.Game#dispose()
	 */
	@Override
	public void dispose () {
		batch.dispose();

	}
}
