package com.nana.gameFont;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class DeathWinFont {
    private Stage stage;
    private BitmapFont myFont;
 /**
     * @param stage takes in the stage of the current screen
     * @param font takes in the font that is being used 
     */
    public DeathWinFont(Stage stage, BitmapFont font) {
        this.stage = stage;
        this.myFont = font;
    }
 /**
     * @param text the desired string to be rendered to screen
     * render text to screen based on the desired location wanted
     */
    public void createAndSetTypingLabel(String text) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = myFont;
        TypingLabel titleLabel = new TypingLabel(text, labelStyle);
        titleLabel.setAlignment(Align.center);
        titleLabel.setFontScale(.5f);
        titleLabel.setSize(Gdx.graphics.getWidth() - 50, 300);
        titleLabel.setPosition(20, Gdx.graphics.getHeight() - Gdx.graphics.getWidth() + 775);
        stage.addActor(titleLabel);
    }  
     /**
     * @param text the desired string to be rendered to screen
     * render text to screen based on the desired location wanted
     */
    public void adjustedFont1(String text) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = myFont;
        TypingLabel titleLabel = new TypingLabel(text, labelStyle);
        titleLabel.setAlignment(Align.center);
        titleLabel.setFontScale(.6f);
        titleLabel.setSize(Gdx.graphics.getWidth() - 50, -150);
        titleLabel.setPosition(20, Gdx.graphics.getHeight() - Gdx.graphics.getWidth() + 775);
        stage.addActor(titleLabel);
    }
 /**
     * @param text the desired string to be rendered to screen
     * render text to screen based on the desired location wanted
     */
    public void winFont(String text) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = myFont;
        TypingLabel titleLabel = new TypingLabel(text, labelStyle);
        titleLabel.setAlignment(Align.center);
        titleLabel.setFontScale(.6f);
        titleLabel.setSize(Gdx.graphics.getWidth() - 50, -150);
        titleLabel.setPosition(20, Gdx.graphics.getHeight() - Gdx.graphics.getWidth() + 500);
        stage.addActor(titleLabel);
    }
}
