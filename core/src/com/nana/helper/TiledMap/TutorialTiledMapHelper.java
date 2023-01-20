package com.nana.helper.TiledMap;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.nana.characters.Player;
import com.nana.helper.PPM;
import com.nana.helper.Animations.BodyHelper;
import com.nana.screens.TutorialGameScreen;

/**
 * Credit: Small Pixel Games on YouTube
 * https://www.youtube.com/watch?v=8rBG7IWdDis&ab_channel=SmallPixelGames
 * Code is not the exactly the same, but was mainly taken from this video.
 */

public class TutorialTiledMapHelper {
    private TiledMap tileMap;
    private TutorialGameScreen gameScreen;
    private PPM ppm = new PPM();
    private Body body = null;

    /**
     * @param gameScreen takes in the current game screen that it wants to be initialized in as a parameter
     */
    public TutorialTiledMapHelper(TutorialGameScreen gameScreen){
        this.gameScreen = gameScreen;
    }

    /**
     * @return the initialized ver of the map (tileMap)
     */
    public OrthogonalTiledMapRenderer setupMap(){
        tileMap = new TmxMapLoader().load("assets/TiledMaps/LoveTiled.tmx");
        parseMapObjects(tileMap.getLayers().get("objects").getObjects());
        return new OrthogonalTiledMapRenderer(tileMap);
    }

    /**
     * @param mapObjects takes in objects created in "Tiled" to initialize its collision 
     */
    private void parseMapObjects(MapObjects mapObjects){
        for(MapObject mapObject : mapObjects){
            if(mapObject instanceof PolygonMapObject){
                createStaticBody((PolygonMapObject) mapObject);
            }
        
            if(mapObject instanceof RectangleMapObject){
                Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
                String rectangleName = mapObject.getName();
            
                if(body == null){
                    if(rectangleName.equals("player")){
                        //passing in arguments from constructor in the helper class
                        body = BodyHelper.createBody(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, rectangle.getWidth(), rectangle.getHeight(), false, gameScreen.getWorld());
                        gameScreen.setPlayer(new Player(rectangle.getWidth(), rectangle.getHeight(), body));
                    }
                   
                }
               
            }
        }
         
    }

    /**
     * @param polygonMapObject objects that were made in "Tiled" with the polygon draw tool
     * create the static body of the player
     */
    private void createStaticBody(PolygonMapObject polygonMapObject){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = gameScreen.getWorld().createBody(bodyDef);
        Shape shape = createPolygonShape(polygonMapObject);
        body.createFixture(shape, 1000);
        shape.dispose();
    }

    /**
     * @param polygonMapObject takes in objects created in "Tiled" drawn by the polyon shape tool
     * @return the shape of the object (vertices of each objects) to parse
     */
    private Shape createPolygonShape(PolygonMapObject polygonMapObject) {
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length/2];

        for(int i=0; i<vertices.length / 2; i ++){
            Vector2 current = new Vector2(vertices[i * 2 ] / ppm.getPPM() , vertices[i * 2 + 1] / ppm.getPPM());
            worldVertices[i] = current;
        }

        PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        return shape;
    }

}
