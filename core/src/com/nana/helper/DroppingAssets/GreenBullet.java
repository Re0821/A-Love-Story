package com.nana.helper.DroppingAssets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class GreenBullet {
    private Texture texture;
    private Rectangle rectangle;
 
 public GreenBullet(Texture rainBullet, float randomX){
    this.texture = rainBullet;
    rectangle = new Rectangle(randomX, 480, 48, 48);
 }
 
 public Texture getTexture() {
    return texture;
  }

  public Rectangle getRectangle() {
    return rectangle;
  }

}