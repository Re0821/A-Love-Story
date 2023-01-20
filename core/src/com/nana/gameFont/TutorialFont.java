package com.nana.gameFont;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class TutorialFont {
    private Stage stage;
    private BitmapFont myFont;

    /**
     * initializes variables in constructor
     * @param stage takes in the stage of the current screen
     * @param font takes in the font that is being used 
     */
    public TutorialFont(Stage stage, BitmapFont font) {
        this.stage = stage;
        this.myFont = font;
    }

    /**
     * render text to screen based on the desired location wanted
     * @param text the desired string to be rendered to screen
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
}
