package com.nana.gameFont;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class GeneralFont {
    public Stage stage;
    private BitmapFont myFont;
    public TypingLabel titleLabel;
    /**
     * initializes variables in construtor
     * @param stage takes in the stage of the current screen
     * @param font takes in the font that is being used 
     */
    public GeneralFont(Stage stage, BitmapFont font) {
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
        titleLabel = new TypingLabel(text, labelStyle);
        titleLabel.setAlignment(Align.center);
        titleLabel.setFontScale(.65f);
        titleLabel.setSize(Gdx.graphics.getWidth()- 1750, 200);
        titleLabel.setPosition( 875, 650);
        stage.addActor(titleLabel);

    }
    /**
     * render text to screen based on the desired location wanted
     * @param text the desired string to be rendered to screen
     */
    public void createAndSetTypingLabel2(String text) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = myFont;
        titleLabel = new TypingLabel(text, labelStyle);
        titleLabel.setAlignment(Align.center);
        titleLabel.setFontScale(.35f);
        titleLabel.setSize(Gdx.graphics.getWidth()- 1750, 100);
        titleLabel.setPosition( 875, 650);
        stage.addActor(titleLabel);

    }
    /**
     * render text to screen based on the desired location wanted
     * @param text the desired string to be rendered to screen
     */
    public void level2CreateAndSetTypingLabel(String Text){
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = myFont;
        TypingLabel titleLabel = new TypingLabel(Text, labelStyle);
        titleLabel.setAlignment(Align.center);
        titleLabel.setFontScale(.35f);
        titleLabel.setSize(Gdx.graphics.getWidth() - 50, 300);
        titleLabel.setPosition(20, Gdx.graphics.getHeight() - Gdx.graphics.getWidth() + 775);
        stage.addActor(titleLabel);
        
    }

    public void leaderboardCreateAndSetTypingLabel(String Text){
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = myFont;
        TypingLabel titleLabel = new TypingLabel(Text, labelStyle);
        titleLabel.setAlignment(Align.center);
        titleLabel.setFontScale(.75f);
        titleLabel.setSize(975f, 250);
        titleLabel.setPosition(20, Gdx.graphics.getHeight() - Gdx.graphics.getWidth() + 775);
        stage.addActor(titleLabel);
        
    }
  
    
    
}
