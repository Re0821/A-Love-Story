package com.nana.helper.DroppingAssets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class GreenBullet {
    private Texture texture;
    private Rectangle rectangle;
 
 /**
 * @param rainBullet takes in the texture of the rain bullet
 * @param randomX initialize a random point in the world based on a random integer
 */
public GreenBullet(Texture rainBullet, float randomX){
    this.texture = rainBullet;
    rectangle = new Rectangle(randomX, 480, 48, 48);
 }
 
 /**
 * @return the texture of the bullet
 */
public Texture getTexture() {
    return texture;
  }

  /**
   * @return the hitbox of the bullet
   */
  public Rectangle getRectangle() {
    return rectangle;
  }

}