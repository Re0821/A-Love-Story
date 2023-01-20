package com.nana.helper.DroppingAssets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class YellowBullet {
    public Texture texture;
    public Rectangle rectangle;
    /**
     * initializes variables in constructor
     * @param rainBullet takes in the texture of the rain bullet
     * @param randomX initialize a random point in the world based on a random integer
     */
    public YellowBullet(Texture texture, float randomX){
        this.texture = texture;
        rectangle = new Rectangle(randomX, 480, 48, 48);
    }

    /**
     * gets the texture
     * @return the texture of the bullet
     */

    public Texture getTexture() {
        return texture;
    }
    /**
     * gets the rectangle (hitbox)
     * @return the hitbox of the bullet
     */
    public Rectangle getRectangle() {
        return rectangle;
    }
}