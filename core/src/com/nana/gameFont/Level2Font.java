package com.nana.gameFont;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class Level2Font {
    private Stage stage;
    private BitmapFont myFont;

    public Level2Font(Stage stage, BitmapFont font) {
        this.stage = stage;
        this.myFont = font;
    }

    public void createAndSetTypingLabel(String text) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = myFont;
        TypingLabel titleLabel = new TypingLabel(text, labelStyle);
        titleLabel.setAlignment(Align.center);
        titleLabel.setFontScale(.35f);
        titleLabel.setSize(Gdx.graphics.getWidth() - 50, 350);
        titleLabel.setPosition(20, Gdx.graphics.getHeight() - Gdx.graphics.getWidth() + 775);
        stage.addActor(titleLabel);
    }
}
