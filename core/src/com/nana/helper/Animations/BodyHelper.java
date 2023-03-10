package com.nana.helper.Animations;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nana.helper.PPM;

public class BodyHelper {
    private static PPM ppm = new PPM();
/**
 * Credit: Small Pixel Games on YouTube
 * https://www.youtube.com/watch?v=8rBG7IWdDis&ab_channel=SmallPixelGames
 * Code is not the exactly the same, but was mainly taken from this video.
 */

    /**
     * creates the player body
     * @param x sets the x position of the player
     * @param y sets the y position of the player
     * @param width the polygon width definition of the player
     * @param height the polygon height definition of the player
     * @param isStatic to check if static or to set to dynamic
     * @param world allow the player to be rendered into the world
     * @return the body
     */

    public static Body createBody(float x, float y, float width, float height, boolean isStatic, World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody; // When this is true, it will be a static body. Else, it is a dynamic body
        bodyDef.position.set(x / ppm.getPPM(), y / ppm.getPPM());
        bodyDef.fixedRotation = true; // doesn't let player rotate around
        Body body = world.createBody(bodyDef); // creates player

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / ppm.getPPM(), height / 2 / ppm.getPPM());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0f;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
        
    }

    /**
     * creates a moveable NPC body
     * @param x takes in the x position of the NPC to set the NPC location
     * @param y takes in the y position of the NPC to set the NPC location
     * @param width takes in the width size of the NPC to set the NPC location
     * @param height takes in the height size of the NPC to set the NPC location
     * @param isStatic takes in a boolean var to see if the NPC is non-moving or not 
     * @param world takes in the current world that it is in
     * @return the body of the NPC
     */
    public static Body createNPC(float x, float y, float width, float height, boolean isStatic, World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody; // When this is true, it will be a static body. Else, it is a dynamic body
        bodyDef.position.set(x / ppm.getPPM(), y / ppm.getPPM());
        bodyDef.fixedRotation = true; // doesn't let player rotate around
        Body body = world.createBody(bodyDef); 

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / ppm.getPPM(), height / 2 / ppm.getPPM());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0f;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
        
    }

     /**
      * creates a static NPC body 
     * @param x takes in the x position of the NPC to set the NPC location
     * @param y takes in the y position of the NPC to set the NPC location
     * @param width takes in the width size of the NPC to set the NPC location
     * @param height takes in the height size of the NPC to set the NPC location
     * @param isStatic takes in a boolean var to see if the NPC is non-moving or not 
     * @param world takes in the current world that it is in
     * @return the body of the NPC
     */
    public static Body createStaticNPC(float x, float y, float width, float height, boolean isStatic, World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x / ppm.getPPM(), y / ppm.getPPM());
        bodyDef.fixedRotation = true; // doesn't let player rotate around
        Body body = world.createBody(bodyDef); 

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / ppm.getPPM(), height / 2 / ppm.getPPM());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0f;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
        
    }
    
}
