package com.nana.gameFont;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.nana.screens.FinalBoss;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class GeneralFont {
    public Stage stage;
    private BitmapFont myFont;
    public TypingLabel titleLabel;

    public GeneralFont(Stage stage, BitmapFont font) {
        this.stage = stage;
        this.myFont = font;
    }

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
}
