package com.nana.helper.TiledMap;

import java.rmi.server.Skeleton;

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
import com.nana.characters.SkeletonNPC;
import com.nana.helper.BodyHelper;
import com.nana.helper.PPM;
import com.nana.screens.Level1;
import com.nana.screens.Level2;

/**
 * Credit: Small Pixel Games on YouTube
 * https://www.youtube.com/watch?v=8rBG7IWdDis&ab_channel=SmallPixelGames
 * Code is not the exactly the same, but was mainly taken from this video.
 */

public class Level2TiledMapHelper {
        private TiledMap tileMap;
        private Level2 gameScreen;
        private PPM ppm = new PPM();
        private Body body = null;
        private Body skeletonBody = null;

        public float abs;
    
        public Level2TiledMapHelper(Level2 gameScreen){
            this.gameScreen = gameScreen;
           

        }
    
        public OrthogonalTiledMapRenderer setupMap(){
            tileMap = new TmxMapLoader().load("assets/TiledMaps/SecondLevelTiled.tmx");
            parseMapObjects(tileMap.getLayers().get("objects").getObjects());


            return new OrthogonalTiledMapRenderer(tileMap);
        }
    
        private void parseMapObjects(MapObjects mapObjects){
            for(MapObject mapObject : mapObjects){
                if(mapObject instanceof PolygonMapObject){
                    createStaticBody((PolygonMapObject) mapObject);
                }
            
                if(mapObject instanceof RectangleMapObject){
                    Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
                    String rectangleName = mapObject.getName();

                        if(rectangleName.equals("player")){
                            //passing in arguments from constructor in the helper class
                            body = BodyHelper.createBody(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, rectangle.getWidth(), rectangle.getHeight(), false, gameScreen.getWorld());
                            gameScreen.setPlayer(new Player(rectangle.getWidth(), rectangle.getHeight(), body));
                        }


                        if(rectangleName.equals("skeleton")){
                            //passing in arguments from constructor in the helper class
                            skeletonBody = BodyHelper.createNPC
                            (rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, rectangle.getWidth(), rectangle.getHeight(), false, gameScreen.getWorld());
                            gameScreen.setSkeleton(new SkeletonNPC(rectangle.getWidth(), rectangle.getHeight(), skeletonBody));
                        }
                }
            }
             
        }
    
        private void createStaticBody(PolygonMapObject polygonMapObject){
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            Body body = gameScreen.getWorld().createBody(bodyDef);
            Shape shape = createPolygonShape(polygonMapObject);
            body.createFixture(shape, 1000);
            shape.dispose();
        }
    
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
